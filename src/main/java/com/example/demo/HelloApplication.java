package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Parent root = new FXMLLoader(getClass().getResource("scene1.fxml")).load();
            Scene scene = new Scene(root);
            stage.getIcons().add(new Image("C:\\IIITD\\3rd sem\\AP\\demo\\demo\\src\\main\\java\\com\\example\\demo\\logo.png"));
            stage.setTitle("Stick Hero");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}