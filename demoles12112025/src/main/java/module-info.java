module com.example.demoles12112025 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;


    opens com.example.demoles12112025 to javafx.fxml;
    exports com.example.demoles12112025;
}