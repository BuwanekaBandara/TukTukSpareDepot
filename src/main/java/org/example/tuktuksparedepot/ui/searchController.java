package org.example.tuktuksparedepot.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.tuktuksparedepot.Operations.InventoryOp;
import org.example.tuktuksparedepot.objects.sparePart;

import java.util.ArrayList;

public class searchController {
    @FXML private TextField keywordField;
    @FXML private TextField brandField;
    @FXML private TextField categoryField;
    @FXML private TextField minPriceField;
    @FXML private TextField maxPriceField;
    @FXML private Button searchBtn;
    @FXML private Button clearBtn;
    @FXML private Label resultCountLabel;
    @FXML private TableView<sparePart> searchTable;
    @FXML private TableColumn<sparePart, String> partCodeCol;
    @FXML private TableColumn<sparePart, String> partNameCol;
    @FXML private TableColumn<sparePart, String> brandCol;
    @FXML private TableColumn<sparePart, Double> priceCol;
    @FXML private TableColumn<sparePart, Integer> quantityCol;
    @FXML private TableColumn<sparePart, String> categoryCol;
    @FXML private TableColumn<sparePart, String> dateCol;

    InventoryOp inventoryOp;
    InventoryController ic=new InventoryController();

    public void setinventoryOp(InventoryOp inventoryOp) {
        this.inventoryOp = inventoryOp;
    }

    @FXML
    public void initialize() {
        setupTableColumns();
    }

    private void setupTableColumns() {
        partCodeCol.setCellValueFactory(new PropertyValueFactory<>("partCode"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("partName"));
        brandCol.setCellValueFactory(new PropertyValueFactory<>("brand"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    @FXML
    private void handleSearch(){
        String keyword  = keywordField.getText().trim();
        if(keyword.isEmpty()){
            keyword=null;
        }
        String brand    = brandField.getText().trim();
        if(brand.isEmpty()){
            brand=null;
        }
        String category = categoryField.getText().trim();
        if(category.isEmpty()){
            category=null;
        }

        double minPrice = 0;
        double maxPrice = 1000000;

        if (!minPriceField.getText().trim().isEmpty()) {
            try {
                minPrice = Double.parseDouble(minPriceField.getText().trim());
            } catch (NumberFormatException e) {
                ic.showAlert("Invalid Input","Min price must be a number.");
                return;
            }
        }

        if (!maxPriceField.getText().trim().isEmpty()) {
            try {
                maxPrice = Double.parseDouble(maxPriceField.getText().trim());
            } catch (NumberFormatException e) {
                ic.showAlert("Invalid Input","Max price must be a number.");
                return;
            }
        }
        ArrayList<sparePart> results = inventoryOp.searchPart(keyword, brand, minPrice, maxPrice, category);
        ObservableList<sparePart> data = FXCollections.observableArrayList(results);
        searchTable.setItems(data);

        resultCountLabel.setText("Results: " + results.size());

    }

    @FXML
    private void handleClear() {
        keywordField.clear();
        brandField.clear();
        categoryField.clear();
        minPriceField.clear();
        maxPriceField.clear();
        searchTable.setItems(null);
        resultCountLabel.setText("Results: 0");
    }
}
