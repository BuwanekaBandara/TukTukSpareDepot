package org.example.tuktuksparedepot.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.tuktuksparedepot.Operations.InventoryOp;
import org.example.tuktuksparedepot.objects.sparePart;
import org.example.tuktuksparedepot.ui.InventoryController.*;

public class addPartController {

    @FXML private TextField addPartCode;
    @FXML private TextField addPartName;
    @FXML private TextField addBrand;
    @FXML private TextField addPrice;
    @FXML private TextField addQuantity;
    @FXML private TextField addCategory;
    @FXML private DatePicker addDate;
    @FXML private TextField addImage;
    @FXML private TextField addLowStockThresh;
    @FXML private Button addPartBtn;
    @FXML private Button clearFieldsBtn;


    InventoryOp inventoryOp;

    public void setInventoryOp(InventoryOp inventoryOp){
        this.inventoryOp=inventoryOp;
    }

    @FXML
    private void handleClearFields(){
        addPartCode.clear();
        addPartName.clear();
        addBrand.clear();
        addPrice.setText("0");
        addQuantity.setText("0");
        addCategory.clear();
        addDate.setValue(null);
        addImage.clear();
        addLowStockThresh.setText("5");
    }

    @FXML
    private void handleAddPartBtn(){
        String code=addPartCode.getText();
        if (code.trim().isEmpty()){
            showAlert("Error: Empty Part Code","Part Code cannot be empty");
            return;
        }
        else if (code.trim().length() != 4 || code.trim().toUpperCase().charAt(0) != 'P') {
            showAlert("Error: Invalid Part Code","Enter a valid Part Code. Follow this format (Pxxxx)");
            return;
        }
        for(int i=0;i<inventoryOp.sortedInventory().size();i++){
            if(inventoryOp.sortedInventory().get(i).getPartCode().equalsIgnoreCase(code)){
                showAlert("Error: Duplicate Part Code","Part Code already exists");
                return;
            }
        }

        String name=addPartName.getText().trim();
        if(name.isEmpty()){
            showAlert("Error: Empty Part Name","Part Name cannot be empty");
            return;
        }

        String brand=addBrand.getText().trim();

        double price=0;
        int quantity=0;
        try {
            price = Double.parseDouble(addPrice.getText().trim());

            quantity = Integer.parseInt(addQuantity.getText().trim());
        }
        catch (NumberFormatException e){
            showAlert("Error: Invalid Input","Enter an input of numbers to price and quantity fields");
            return;
        }
        if(quantity<0||price<0){
            showAlert("Error: Invalid Quantity","Quantity or Price cannot be negative. Default 0 will be replaced with the negative value.");
        }

        String category=addCategory.getText().trim();
        if(category.isEmpty()){
            showAlert("Error: Empty Category","Category cannot be empty");
            return;
        }

        String date;
        try {
            date = addDate.getValue().toString();
        }
        catch (NullPointerException e){
            date=null;
        }
        String image=addImage.getText().trim();

        int lowStockThreshold;
        try{
            lowStockThreshold=Integer.parseInt(addLowStockThresh.getText().trim());
            if(lowStockThreshold<1){
                showAlert("Threshold too low","Threshold is too low.Default Threshold will be applied");
            }
        }
        catch(NumberFormatException e){
            showAlert("Error: Invalid Input","Enter an input of numbers");
            return;
        }


        sparePart newPart=new sparePart(code,name,brand,price,quantity,category,date,image,lowStockThreshold);

        inventoryOp.addPart(newPart);
        showAlert("Success","Successfully Added Part");

        Stage stage=(Stage) addPartBtn.getScene().getWindow();
        stage.close();

    }

    public void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
