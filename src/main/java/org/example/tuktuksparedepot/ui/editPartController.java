package org.example.tuktuksparedepot.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.tuktuksparedepot.Operations.InventoryOp;
import org.example.tuktuksparedepot.objects.sparePart;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class editPartController {

    @FXML private TextField updatePartCode;
    @FXML private TextField updatePartName;
    @FXML private TextField updateBrand;
    @FXML private TextField updatePrice;
    @FXML private TextField updateQuantity;
    @FXML private TextField updateCategory;
    @FXML private DatePicker updateDate;
    @FXML private TextField updateImage;
    @FXML private TextField updateLowStockThresh;
    @FXML private Button editPartBtn;
    @FXML private Button resetFieldsBtn;

    InventoryOp inventoryOp;
    sparePart selectedPart;

    public void setInventoryOp(InventoryOp inventoryOp){
        this.inventoryOp=inventoryOp;
    }
    public void setPart(sparePart selectedPart){
        this.selectedPart=selectedPart;
    }

    public void setText(sparePart selected){

        String price=Double.toString(selected.getPrice());
        String quantity=Integer.toString(selected.getQuantity());
        String lowStockThresh=Integer.toString(selected.getLowStockThreshold());

        updatePartCode.setText(selected.getPartCode());
        updatePartName.setText(selected.getPartName());
        updateBrand.setText(selected.getBrand());
        updatePrice.setText(price);
        updateQuantity.setText(quantity);
        updateCategory.setText(selected.getCategory());
        updateDate.setValue(parseToLocalDate(selected.getDate()));
        updateImage.setText(selected.getImg());
        updateLowStockThresh.setText(lowStockThresh);

    }

    private LocalDate parseToLocalDate(String date){
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate=LocalDate.parse(date.trim(),formatter);
        return localDate;
    }

    public void handleEditPartBtn(){

        String code=updatePartCode.getText();
        if(code.isEmpty()||!selectedPart.getPartCode().equalsIgnoreCase(code)){
            showAlert("Error: Part Code Change","Part Code cannot be changed");
            updatePartCode.setText(selectedPart.getPartCode());
            return;
        }

        String name=updatePartName.getText().trim();
        if(name.isEmpty()){
            showAlert("Error: Empty Part Name","Part Name cannot be empty");
            return;
        }

        String brand=updateBrand.getText().trim();

        double Dprice=0;
        int Iquantity=0;
        try {
            Dprice = Double.parseDouble(updatePrice.getText().trim());

            Iquantity = Integer.parseInt(updateQuantity.getText().trim());
        }
        catch (NumberFormatException e){
            showAlert("Error: Invalid Input","Enter an input of numbers to price and quantity fields");
            return;
        }
        if(Iquantity<0||Dprice<0){
            showAlert("Error: Invalid Quantity","Quantity or Price cannot be negative. Default 0 will be replaced with the negative value.");
        }

        String category=updateCategory.getText().trim();
        if(category.isEmpty()){
            showAlert("Error: Empty Category","Category cannot be empty");
            return;
        }

        String date;
        try {
            date = updateDate.getValue().toString();
        }
        catch (NullPointerException e){
            date=null;
        }
        String image=updateImage.getText().trim();

        int lowStockThreshold;
        try{
            lowStockThreshold=Integer.parseInt(updateLowStockThresh.getText().trim());
            if(lowStockThreshold<1){
                showAlert("Threshold too low","Threshold is too low.Default Threshold will be applied");
            }
        }
        catch(NumberFormatException e){
            showAlert("Error: Invalid Input","Enter an input of numbers");
            return;
        }


        inventoryOp.updatePart(code,name,brand,Dprice,Iquantity,category,date,image,lowStockThreshold);
        showAlert("Success","Successfully Updated Part Details");

        Stage stage=(Stage) editPartBtn.getScene().getWindow();
        stage.close();

    }

    public void handleResetFieldsBtn(){
        setText(selectedPart);
    }

    public void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }






}
