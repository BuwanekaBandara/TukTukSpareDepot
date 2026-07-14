package org.example.tuktuksparedepot.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.tuktuksparedepot.Operations.DealerOp;
import org.example.tuktuksparedepot.objects.dealer;

import java.util.ArrayList;

public class DealersController {
    @FXML private TableView<dealer>  dealerTable;
    @FXML private TableColumn<dealer, String> dealerIdCol;
    @FXML private TableColumn<dealer, String> dealerName;
    @FXML private TableColumn<dealer, String> dealerTp;
    @FXML private TableColumn<dealer, String> dealerLoc;
    @FXML private Button RandDealerBtn;
    @FXML private Button closeDealers;

    private DealerOp dealerOp = new DealerOp();

    @FXML public void initialize(){
        setupTableColumns();
        handleRandDealers();
    }


    private void setupTableColumns() {
        dealerIdCol.setCellValueFactory(new PropertyValueFactory<>("DealerId"));
        dealerName.setCellValueFactory(new PropertyValueFactory<>("DealerName"));
        dealerTp.setCellValueFactory(new PropertyValueFactory<>("PhoneNum"));
        dealerLoc.setCellValueFactory(new PropertyValueFactory<>("Location"));
    }


    @FXML
    private void handleRandDealers() {
        ArrayList<dealer> selected = dealerOp.sortedRandDealers();
        ObservableList<dealer> data = FXCollections.observableArrayList(selected);
        dealerTable.setItems(data);
    }

    @FXML
    private void handleCloseWindow(){
        Stage stage=(Stage) closeDealers.getScene().getWindow();
        stage.close();
    }

}
