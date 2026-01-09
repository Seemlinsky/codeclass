module com.example.arraylist {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.arraylist to javafx.fxml;
    exports com.example.arraylist;
}