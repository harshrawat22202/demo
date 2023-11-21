package com.example.demo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    Rectangle stick;

    public void displayLog() throws IOException {
//        help.setText("How to play");
        log.setOpacity(0.9);

    }
    public void removeLog() throws IOException {
//        help.setText("How to play");
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

    public void switchToScene5(ActionEvent event) throws IOException {
        Parent root = new FXMLLoader(getClass().getResource("scene5.fxml")).load(); // Use scene2.fxml
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void displayName() throws IOException {
//        help.setText("How to play");
        name1.setOpacity(0.25);

    }
    public void removeName() throws IOException {
//        help.setText("How to play");
        name1.setOpacity(0);

    }
    public void displayPass() throws IOException {
//        help.setText("How to play");
        passwd1.setOpacity(0.25);

    }
    public void removePass() throws IOException {
//        help.setText("How to play");
        passwd1.setOpacity(0);

    }



    public void switchToScene4(ActionEvent event) throws IOException {
        if (name.getText().isEmpty()){
            displayName();
        } else if (passwd.getText().isEmpty()) {
            displayPass();




        }else {
            removeName();
            removePass();
            Parent root = new FXMLLoader(getClass().getResource("scene4.fxml")).load(); // Use scene2.fxml
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            System.out.println(name.getText().getClass());
            stage.show();

        }



    }

    public void displayHelp() throws IOException {
//        help.setText("How to play");
        help.setOpacity(0.25);

    }
    public void removeHelp() throws IOException {
//        help.setText("How to play");
        help.setOpacity(0);

    }

    public void displayShop() throws IOException {
//        help.setText("How to play");
        shop.setOpacity(0.25);

    }
    public void removeShop() throws IOException {
//        help.setText("How to play");
        shop.setOpacity(0);

    }

    public void exit(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exiting");
        alert.setHeaderText("You are about to exit");
        alert.setContentText("Confirm please");
        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) pane.getScene().getWindow();
//            System.out.println("logged out");
            stage.close();
        }
    }
    public void exit2(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exiting");
        alert.setHeaderText("You are about to exit");
        alert.setContentText("Confirm please");
        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) main.getScene().getWindow();
//            System.out.println("logged out");
            stage.close();
        }
    }
    public void inc(){
        time = new Timeline(new KeyFrame(Duration.millis(25), event -> incStick()));
        time.setCycleCount(Timeline.INDEFINITE);
        time.play();
    }
    public void incStick(){
        stick.setHeight(stick.getHeight()+5);

    }
    public void stop(){
        if (time != null) {
            time.stop();

            double h = stick.getHeight();
            double w = stick.getWidth();

            stick.setHeight(w);
            stick.setWidth(h);


        }

    }

}
