package org.example.tuktuksparedepot.ui;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.tuktuksparedepot.Operations.CartOp;
import org.example.tuktuksparedepot.Operations.InventoryOp;
import org.example.tuktuksparedepot.objects.cartItem;
import org.example.tuktuksparedepot.objects.sparePart;

import java.util.ArrayList;

import static javafx.collections.FXCollections.observableArrayList;

public class CartController {
    @FXML private ComboBox<String> partComboBox;
    @FXML private TextField quantityField;
    @FXML private Button addToCartBtn;
    @FXML private Button removeBtn;
    @FXML private TableView<cartItem> cartTable;
    @FXML private TableColumn<cartItem, String> cartCodeCol;
    @FXML private TableColumn<cartItem, String> cartNameCol;
    @FXML private TableColumn<cartItem, Double> cartPriceCol;
    @FXML private TableColumn<cartItem, Integer> cartQtyCol;
    @FXML private TableColumn<cartItem, Double> cartSubtotalCol;
    @FXML private TableColumn<cartItem, Double> cartDiscountCol;
    @FXML private Label subtotalLabel;
    @FXML private Label bulkDiscountLabel;
    @FXML private Label synergyDiscountLabel;
    @FXML private Label totalLabel;
    @FXML private Button checkoutBtn;
    @FXML private Button clearCartBtn;

    InventoryOp inventoryOp;
    CartOp cartOp=new CartOp();

    public void setInventoryOp(InventoryOp inventoryOp) {
        this.inventoryOp = inventoryOp;
        loadPartComboBox();
    }

    private void loadPartComboBox() {
        ArrayList<sparePart> parts=inventoryOp.getAllParts();
        ObservableList<String> partNames= FXCollections.observableArrayList();
        for(int i=0;i<parts.size();i++){
            partNames.add(parts.get(i).getPartName());
        }
        partComboBox.setItems(partNames);
    }

    @FXML
    public void initialize(){
        setTableCols();
    }

    private void setTableCols(){
        cartCodeCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getPart().getPartCode()));

        cartNameCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getPart().getPartName()));

        cartPriceCol.setCellValueFactory(cellData ->
                new SimpleDoubleProperty(cellData.getValue().getPart().getPrice()).asObject());

        cartQtyCol.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getCartQuantity()).asObject());

        cartSubtotalCol.setCellValueFactory(cellData ->
                new SimpleDoubleProperty(cellData.getValue().getSubTotal()).asObject());

        cartDiscountCol.setCellValueFactory(cellData ->{if(cellData.getValue().getCartQuantity()>=3){return new SimpleDoubleProperty(cellData.getValue().getSubTotal()*0.95).asObject();}
            else{return new  SimpleDoubleProperty(0).asObject();}
        });
    }



}
