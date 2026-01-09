package com.example.demoles12112025;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        GridPane root = new GridPane();

        Label lbMerk = new Label("Hello World!");
        TextField txtMerk = new TextField();

        Label lbType = new Label();
        TextField txtType = new TextField();

        Label bouwjaar = new Label();
        TextField txtbouwjaar = new TextField();

        Button button = new Button("ok");
        button.setOnAction(e -> {
        String merk = txtMerk.getText();
        String type = txtType.getText();
        String bwjaar = txtbouwjaar.getText();

        voertuig deAuto = new voertuig(merk,type,bwjaar);
        System.out.println(deAuto);
        voertuig vw = new voertuig(merk, type, bwjaar);
        System.out.println(merk+ "|" + type + "|" + bouwjaar);
        });
        Scene scene = new Scene(root, 800, 600);
        //controls toevoegen aan gridpane (i = kolom, i1 = rij)
        root.add(lbMerk, 0, 0);
        root.add(txtMerk, 1, 0);
        root.add(lbType, 2, 0);
        root.add(txtType, 3, 0);
        root.add(bouwjaar, 4, 0);
        root.add(txtbouwjaar, 5, 0);
        root.add(button, 6, 0);
        stage.setTitle("Hello!");
//        root.getChildren().addAll(txtMerk,button);
//        root.getChildren().add(txtMerk);
//        root.getChildren().add(button);



        stage.setScene(scene);
        stage.show();
    }
}
