package org.example.tuktuksparedepot.ui;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.tuktuksparedepot.Operations.CartOp;
import org.example.tuktuksparedepot.Operations.InventoryOp;
import org.example.tuktuksparedepot.fileWriting.FileWrite;
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
    InventoryController ic=new InventoryController();

    public void setInventoryOp(InventoryOp inventoryOp) {
        this.inventoryOp = inventoryOp;
        this.cartOp.setInventoryOp(inventoryOp);
        loadPartComboBox();
    }

    private void loadPartComboBox() {
        ArrayList<sparePart> parts=inventoryOp.getAllParts();
        ObservableList<String> partCodes= FXCollections.observableArrayList();
        for(int i=0;i<parts.size();i++){
            partCodes.add(parts.get(i).getPartCode());
        }
        partComboBox.setItems(partCodes);
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

        cartDiscountCol.setCellValueFactory(cellData ->{if(cellData.getValue().getCartQuantity()>=3){return new SimpleDoubleProperty(cellData.getValue().getSubTotal()*0.05).asObject();}
            else{return new  SimpleDoubleProperty(0).asObject();}
        });
    }
    @FXML
    public void handleAddToCart(){
        String partCode=partComboBox.getSelectionModel().getSelectedItem();
        if (partCode==null){
            ic.showAlert("No part Selected","Select the part from the dropdown to add to cart.");
            return;
        }
        sparePart selectedPart=inventoryOp.getPartByCode(partCode);


        int Qty=0;
        try {
            Qty = Integer.parseInt(quantityField.getText());
            if(Qty<=0){
                ic.showAlert("Invalid Quantity","Quantity must be greater than 0.");
                return;
            }
        }
        catch(NumberFormatException e){
            ic.showAlert("Invalid Quantity","Quantity must be an integer");
        }

        cartOp.addToCart(selectedPart,Qty);

        refreshCart();
        updateTotal();
        quantityField.clear();
        partComboBox.getSelectionModel().clearSelection();
    }

    @FXML
    public void handleRemove(){
        cartItem selected = cartTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            ic.showAlert("No Selection", "Please select an item to remove.");
            return;
        }
        cartOp.deleteFromCart(
                selected.getPart().getPartCode()
        );
        refreshCart();
        updateTotal();
    }

    @FXML
    public void handleCheckout(){
        if(cartOp.getItems().size()==0){
            ic.showAlert("No items selected","Please select an item to checkout.");
            return;
        }
        cartOp.checkout();
        inventoryOp.saveCheckoutInventory();
        ic.showAlert("Success","Payment Successful");
        Stage stage=(Stage) checkoutBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handleClearCart(){
        cartOp.clearCart();
        refreshCart();
        updateTotal();
    }

    private void refreshCart(){
        ObservableList<cartItem> cartItems=FXCollections.observableArrayList(cartOp.getItems());
        cartTable.setItems(cartItems);
        cartTable.getSelectionModel().clearSelection();
    }

    private void updateTotal(){

        ArrayList<Double> TotalList=cartOp.cartTotal();

        Double cartTotal=TotalList.get(0);
        Double totalSynergyDis=TotalList.get(1);
        Double totalBulkDis=TotalList.get(2);

        if(cartOp.getItems().size()==0){
            subtotalLabel.setText("Rs. 0.00");
            bulkDiscountLabel.setText("Rs. 0.00");
            synergyDiscountLabel.setText("Rs. 0.00");
            totalLabel.setText("Rs. 0.00");
            return;
        }

        double rawTotal=0;
        for(int i=0;i<cartOp.getItems().size();i++){
            rawTotal+=cartOp.getItems().get(i).getSubTotal();
        }
        subtotalLabel.setText("Rs. "+String.format("%,.2f",rawTotal));
        bulkDiscountLabel.setText("Rs. "+String.format("%,.2f",totalBulkDis));
        synergyDiscountLabel.setText("Rs. "+String.format("%,.2f",totalSynergyDis));
        totalLabel.setText("Rs. "+String.format("%,.2f",cartTotal));
    }



}
