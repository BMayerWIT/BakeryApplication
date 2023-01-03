module com.example.bakeryapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires xstream;

    opens com.example.bakeryapplication to javafx.fxml;
    exports com.example.bakeryapplication;
}