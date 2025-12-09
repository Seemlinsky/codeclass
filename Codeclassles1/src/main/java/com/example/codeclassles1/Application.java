package com.example.codeclassles1;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
//        HBox root = new HBox();
//        VBox vbox = new VBox();
        Pane root = new Pane();
        Scene scene = new Scene(root, 900, 600);
        String titleVar = "codeclass les 1";

        Button button = new Button("Naam die op de Button komt te staan");
        root.getChildren().add(button);
        Label label = new Label(titleVar);

        button.setLayoutX(300);
        button.setLayoutY(300);

        root.getChildren().addAll(label, button);

//        titleVar = "codeclass les 1";
//        string titleVar2 = "codeclass les 1";
//        Double double1 = 2.40;
//        double double2 = 2.40;
//
//        boolean boolean1 = true;
//
//        Int int1 = 1; int = integer
//        int int2 = 1;
        stage.setTitle(titleVar);
        stage.setScene(scene);
//        stage.setFullScreen(true);

//        boolean full = stage.isFullScreen();
//        System.out.println("hallo!");
//        System.out.println(full);
        stage.setResizable(false);
        stage.show();
    }
}