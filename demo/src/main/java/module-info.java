module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens nl.stefans.energy to javafx.fxml;
    exports nl.stefans.energy;
}