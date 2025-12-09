module com.example.codeclassles1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.codeclassles1 to javafx.fxml;
    exports com.example.codeclassles1;
}

