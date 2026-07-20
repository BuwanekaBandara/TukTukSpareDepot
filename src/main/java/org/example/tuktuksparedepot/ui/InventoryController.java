package org.example.tuktuksparedepot.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.tuktuksparedepot.Operations.DealerOp;
import org.example.tuktuksparedepot.objects.dealer;
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
    @FXML private TableColumn<sparePart, Integer> LowStockThreshCol;
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
        LowStockThreshCol.setCellValueFactory(new PropertyValueFactory<>("lowStockThreshold"));
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
        inventoryTable.refresh();
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
    @FXML
    public void handleAddPart(){
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/org/example/tuktuksparedepot/addPart-view.fxml"));

            Parent root=loader.load();
            addPartController controller=loader.getController();
            controller.setInventoryOp(inventoryOp);

            Stage stage=new Stage();
            stage.setTitle("Add new Part");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            loadInventoryTable();
            updateTotal();
            lowStockList();
        }
        catch(Exception e){
            showAlert("Error","Error occured while adding part.");
        }
    }

    @FXML
    public void handleEditPart(){

        sparePart selectedPart=inventoryTable.getSelectionModel().getSelectedItem();

        if(selectedPart==null){
            showAlert("Error","Please select a part to be edited.");
            return;
        }

        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/org/example/tuktuksparedepot/editPart-view.fxml"));

            Parent root=loader.load();
            editPartController controller=loader.getController();
            controller.setInventoryOp(inventoryOp);
            controller.setPart(selectedPart);
            controller.setText(selectedPart);

            Stage stage=new Stage();
            stage.setTitle("Edit Part");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            loadInventoryTable();
            updateTotal();
            lowStockList();
        }
        catch(Exception e){
            showAlert("Error","Error occured while editing part.");
        }
    }

    @FXML
    private void handleDeletePart(){
        sparePart selectedPart=inventoryTable.getSelectionModel().getSelectedItem();

        if(selectedPart==null){
            showAlert("Error: No part selected","Please select a part to delete from the table");
        }
        else{
            inventoryOp.deletePart(selectedPart.getPartCode());
            initialize();
            showAlert("Success","Part deleted successfully");
        }
    }

    @FXML
    private void handleSearch() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/tuktuksparedepot/search-view.fxml"));
            Parent root = loader.load();

            searchController controller = loader.getController();
            controller.setinventoryOp(inventoryOp);

            Stage stage = new Stage();
            stage.setTitle("Search Parts");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            showAlert("Error","Error occured while searching part.");
        }
    }

    @FXML
    private void handleCart(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/tuktuksparedepot/cart-view.fxml"));
            Parent root = loader.load();

            CartController controller=loader.getController();
            controller.setInventoryOp(inventoryOp);

            Stage stage=new Stage();
            stage.setTitle("Cart");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            loadInventoryTable();
            updateTotal();
            lowStockList();
        }
        catch (Exception e){
            showAlert("Error","Error occured while proceeding with cart.");
        }

    }

    @FXML
    private void handleDealers(){
        DealerOp dealerOp=new DealerOp();
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/org/example/tuktuksparedepot/dealers-view.fxml"));
            Parent root=loader.load();

            Stage stage=new Stage();
            stage.setTitle("Random Dealers Selection");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        }
        catch(Exception e){
            showAlert("Error","Error occured while displaying dealers.");
        }
    }
    public void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    private void handleClearSelection(){
        inventoryTable.getSelectionModel().clearSelection();
    }
}
