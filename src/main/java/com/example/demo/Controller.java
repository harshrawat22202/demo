package com.example.demo;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    private Stage stage;
    private Timeline time;
    @FXML
    private AnchorPane pane;
    @FXML
    private AnchorPane main;
    @FXML
    TextField name;
    @FXML
    TextField passwd;
    @FXML
    Label help;
    @FXML
    Label shop;

    @FXML
    Label name1;
    @FXML
    Label passwd1;
    @FXML
    Button log;
    @FXML
    Button play;

    public void displayLog() {
        log.setOpacity(0.9);
    }

    public void removeLog() {
        log.setOpacity(0);
    }

    public void switchToScene1(ActionEvent event) throws IOException {
        Parent root = new FXMLLoader(getClass().getResource("scene1.fxml")).load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switch1(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logging Out");
        alert.setHeaderText("You are about to LOG OUT");
        alert.setContentText("Confirm please");
        if (alert.showAndWait().get() == ButtonType.OK) {
            Parent root = new FXMLLoader(getClass().getResource("scene1.fxml")).load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }



    public void switchToScene2(ActionEvent event) throws IOException {
        Parent root = new FXMLLoader(getClass().getResource("scene2.fxml")).load(); // Use scene2.fxml
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToScene3(ActionEvent event) throws IOException {
        Parent root = new FXMLLoader(getClass().getResource("scene3.fxml")).load(); // Use scene2.fxml
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    public void switchToLoadMenu(ActionEvent event) throws IOException {
        Parent root = new FXMLLoader(getClass().getResource("LoadMenu.fxml")).load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void displayName() {
        name1.setOpacity(0.25);
    }

    public void removeName() {
        name1.setOpacity(0);
    }

    public void displayPass() {
        passwd1.setOpacity(0.25);
    }

    public void removePass() {
        passwd1.setOpacity(0);
    }

    public void switchToScene4(ActionEvent event) throws IOException {
        if (name.getText().isEmpty()) {
            displayName();
        } else if (passwd.getText().isEmpty()) {
            displayPass();
        } else {
            String n=name.getText();
            String p=passwd.getText();
            try {
                FileHandler.getInstanceOfFileHandler().addPlayer(n, p);
            }catch (PlayerAlreadyExistsException e){
                String info="username should be unique";
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(info);
                alert.showAndWait();
            }
            removeName();
            removePass();


            Parent root = new FXMLLoader(getClass().getResource("scene4.fxml")).load(); // Use scene2.fxml
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void displayHelp() {
        help.setOpacity(0.25);
    }

    public void removeHelp() {
        help.setOpacity(0);

    }

    public void displayShop() {
        shop.setOpacity(0.25);
    }

    public void removeShop() throws IOException {
        shop.setOpacity(0);

    }

    public void exit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exiting");
        alert.setHeaderText("You are about to exit");
        alert.setContentText("Confirm please");
        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) pane.getScene().getWindow();
            stage.close();
        }
    }

    public void exit2() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exiting");
        alert.setHeaderText("You are about to exit");
        alert.setContentText("Confirm please");
        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) main.getScene().getWindow();
            stage.close();
        }
    }

    public void playButton() {
        Parent root;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gameWindow.fxml"));
            root = fxmlLoader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            Button b=(Button) root.lookup("#pause");
            b.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Parent r;
                    try {
                        r=new FXMLLoader(getClass().getResource("PauseWindow.fxml")).load();
                        Stage s=new Stage();
                        Scene scene1=new Scene(r);
                        s.setScene(scene1);
                        s.initModality(Modality.APPLICATION_MODAL);
                        s.show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pauseWindow(ActionEvent event) throws IOException {
        Parent root = new FXMLLoader(getClass().getResource("scene3.fxml")).load(); // Use scene2.fxml
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}