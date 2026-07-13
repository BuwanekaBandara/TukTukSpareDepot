package org.example.tuktuksparedepot.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.tuktuksparedepot.objects.sparePart;
import org.example.tuktuksparedepot.Operations.InventoryOp;
import java.util.ArrayList;

public class InventoryController {

    @FXML private Label lowStockLabel;
    @FXML private Label totalPartsLabel;
    @FXML private Label totalValueLabel;
    @FXML private TableView<sparePart> inventoryTable;
    @FXML private TableColumn<sparePart, String> partCodeCol;
    @FXML private TableColumn<sparePart, String> partNameCol;
    @FXML private TableColumn<sparePart, String> brandCol;
    @FXML private TableColumn<sparePart, Double> priceCol;
    @FXML private TableColumn<sparePart, Integer> quantityCol;
    @FXML private TableColumn<sparePart, String> categoryCol;
    @FXML private TableColumn<sparePart, String> dateCol;
    @FXML private TableColumn<sparePart, String>availabilityCol;
    @FXML private Button addPartBtn;
    @FXML private Button editPartBtn;
    @FXML private Button deletePartBtn;
    @FXML private Button searchBtn;
    @FXML private Button cartBtn;
    @FXML private Button dealersBtn;

    private InventoryOp inventoryOp=new InventoryOp();

    @FXML public void initialize(){
        setTableCols();
        loadInventoryTable();
        updateTotal();
        lowStockList();
    }

    private void setTableCols(){
        partCodeCol.setCellValueFactory(new PropertyValueFactory<>("partCode"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("partName"));
        brandCol.setCellValueFactory(new PropertyValueFactory<>("brand"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        availabilityCol.setCellValueFactory(cellData ->{
            sparePart part=cellData.getValue();
            String availability;
            if(part.getQuantity()==0){
                availability="Out of stock";
            }
            else if(part.getQuantity()<3){
                availability="Low Stock";
            }
            else{
                availability="In stock";
            }
            return new SimpleStringProperty(availability);
        });
    }

    private void loadInventoryTable(){
        ArrayList<sparePart> sorted=inventoryOp.sortedInventory();
        ObservableList<sparePart> sortedItems=FXCollections.observableArrayList(sorted);
        inventoryTable.setItems(sortedItems);
    }

    private void updateTotal(){
        totalPartsLabel.setText("Total Parts: "+inventoryOp.totalQuantity());
        totalValueLabel.setText("Total Value: Rs. "+String.format("%,.2f",inventoryOp.totalPrice()));
    }

    private void lowStockList(){
        ArrayList<sparePart> lowStocks=inventoryOp.getLowStockParts();

        if(lowStocks.size()==0){
            lowStockLabel.setText("Low Stock: None");
        }
        else{
            String codes=" ";
            for(int i=0;i<lowStocks.size();i++){
                codes+=lowStocks.get(i).getPartCode()+" ";
            }
            lowStockLabel.setText("Low Stocks:"+codes);
        }
    }
}
