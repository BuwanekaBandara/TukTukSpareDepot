module org.example.tuktuksparedepot {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.tuktuksparedepot to javafx.fxml;
    exports org.example.tuktuksparedepot;
}