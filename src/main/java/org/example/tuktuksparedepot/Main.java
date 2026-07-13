package org.example.tuktuksparedepot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.tuktuksparedepot.Operations.CartOp;
import org.example.tuktuksparedepot.Operations.DealerOp;
import org.example.tuktuksparedepot.Operations.InventoryOp;

import java.io.IOException;

public class Main extends Application {

    public static InventoryOp inventoryOp=new InventoryOp();
    public static CartOp cartOp=new CartOp();
    public static DealerOp dealerOp=new DealerOp();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("inventory-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Malabe Tuk-Tuk & Three-Wheeler Spares Depot");
        stage.setScene(scene);
        stage.show();
    }
}
