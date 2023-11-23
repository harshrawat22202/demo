package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class GameWindow {
    @FXML
    private AnchorPane pane;

    private static Stage gameWindow;
    public static void showGameWindow(Stage stage) throws IOException {
        gameWindow = new Stage();
        Parent root = new FXMLLoader(GameWindow.class.getResource("gameWindow.fxml")).load();
        Scene s = new Scene(root);
        s.getRoot();
        gameWindow.setScene(s);
        gameWindow.initModality(Modality.APPLICATION_MODAL);
        gameWindow.initOwner(stage);
        gameWindow.show();
    }
}