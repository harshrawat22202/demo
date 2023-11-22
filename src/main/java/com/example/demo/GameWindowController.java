package com.example.demo;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class GameWindowController implements Initializable {

    @FXML
    private ImageView hero;
    @FXML
    private Line stick;
    private ArrayList<Rectangle> platforms;
    private final double Y = 10.0;
    private double Xhero = 5.0;
    private double speed_Hero = 0.02;
    private double XStick = Xhero;
    @FXML
    private AnchorPane pane;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        hero.setX(Xhero);
        hero.setY(Y);
        platform();
    }

    public void platform() {
        Random random = new Random();
        double space = 25.0;
        Rectangle firstRectangle = new Rectangle(Xhero, Y, random.nextDouble(0, 20), Y);
        pane.getChildren().addAll(firstRectangle);
        moveForward(150.0);
    }

    public void moveForward(double newX) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(hero);
        transition.setDuration(Duration.millis((newX - Xhero) / speed_Hero));
        transition.setByX(newX);
        transition.play();
        Xhero = newX;
    }

    public void extendStick() {
    }
}
