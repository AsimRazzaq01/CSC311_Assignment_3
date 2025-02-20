module com.example.csc311_assignment3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.csc311_assignment3 to javafx.fxml;
    exports com.example.csc311_assignment3;
}