module org.example.tuktuksparedepot {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.tuktuksparedepot to javafx.fxml;
    exports org.example.tuktuksparedepot;
    exports org.example.tuktuksparedepot.objects;
    opens org.example.tuktuksparedepot.objects to javafx.fxml;
    exports org.example.tuktuksparedepot.fileHandling;
    opens org.example.tuktuksparedepot.fileHandling to javafx.fxml;
    exports org.example.tuktuksparedepot.Operations;
    opens org.example.tuktuksparedepot.Operations to javafx.fxml;
}