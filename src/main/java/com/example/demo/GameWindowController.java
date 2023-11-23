package com.example.demo;

import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

public class GameWindowController implements Initializable {

    private ImageView hero;
    private Line stick;
    private ArrayList<Rectangle> platforms;
    private final double Y = 150;

    private final double YPlatform = 168;
    private double Xhero = 5.0;
    private double speed_Hero = 0.02;
    //    private double XStick = Xhero;
    @FXML
    private AnchorPane pane;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        hero = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("hero.png"))));
        pane.getChildren().add(hero);
        hero.setX(Xhero);
        hero.setY(Y);
        hero.setFitHeight(20);
        hero.setFitWidth(20);
        hero.setPreserveRatio(true);
        platform();
        playGame();
    }

    public void platform() {
        Random random = new Random();
        double space = 25.0;// may be used
        Rectangle firstRectangle = new Rectangle();
        firstRectangle.setX(Xhero);
        firstRectangle.setY(YPlatform);
        firstRectangle.setHeight(YPlatform - pane.getHeight());
        firstRectangle.setWidth(random.nextDouble(40, 50));
        pane.getChildren().addAll(firstRectangle);
    }

    public void moveForward(double newX) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(hero);
        transition.setDuration(Duration.millis((newX - Xhero) / speed_Hero));
        transition.setByX(newX);
        transition.play();
        Xhero = newX;
    }

    public void extendStick(Line stick) {
        ScaleTransition scale = new ScaleTransition(Duration.millis(100), stick);
        scale.setByX(40);
        scale.setCycleCount(Animation.INDEFINITE);
        stick.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.SPACE) {
                scale.play();
            }
        });
        stick.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.SPACE) {
                scale.pause();
            }
        });
    }

    public void playGame() {
        stick = new Line();
        extendStick(stick);
        moveForward(150);
    }
}
