module com.example.bakeryapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires xstream;

    opens com.example.bakeryapplication to javafx.fxml;
    opens Resources to xstream;
    exports Models to xstream;
    opens Models to xstream;
    exports Resources to xstream;
    exports com.example.bakeryapplication;
}