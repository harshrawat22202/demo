package com.example.demo;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class GameWindowController implements Initializable {
    @FXML
    private ImageView hero;
    @FXML
    private Rectangle stick;
    private Timeline time;
    private ArrayList<Rectangle> platforms;
    private final double Y = 150;

    private final double YPlatform = 168;
    private double Xhero = 5.0;
    private double speed_Hero = 0.2;
    //    private double XStick = Xhero;
    @FXML
    private AnchorPane pane;
    @FXML
    private Rectangle rect1;
    @FXML
    private Rectangle rect2;
    private int check = 0;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
//        hero = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("hero.png"))));
//        pane.getChildren().add(hero);
//        hero.setX(Xhero);
//        hero.setY(Y);
//        hero.setFitHeight(20);
//        hero.setFitWidth(20);
//        hero.setPreserveRatio(true);
        platform();
        playGame();
        Random r = new Random();
        rect2.setWidth(r.nextDouble(50 - 10 + 1) + 10);
    }

    public void platform() {
//        Random random = new Random();
//        double space = 25.0;// may be used
//        Rectangle firstRectangle = new Rectangle();
//        firstRectangle.setX(Xhero);
//        firstRectangle.setY(YPlatform);
//        firstRectangle.setHeight(YPlatform - pane.getHeight());
//        firstRectangle.setWidth(random.nextDouble(40, 50));
//        pane.getChildren().addAll(firstRectangle);


    }

    //
    public void moveForward(double newX){
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(hero);
        transition.setDuration(Duration.millis((Math.abs(newX - Xhero)) / speed_Hero));
        transition.setByX(newX);
        transition.play();
        double v = (newX - Xhero) / speed_Hero;
        Xhero = newX;
    }
//
//    public void extendStick(Line stick) {
//        ScaleTransition scale = new ScaleTransition(Duration.millis(100), stick);
//        scale.setByX(40);
//        scale.setCycleCount(Animation.INDEFINITE);
//        stick.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
//            if (event.getCode() == KeyCode.SPACE) {
//                scale.play();
//            }
//        });
//        stick.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
//            if (event.getCode() == KeyCode.SPACE) {
//                scale.pause();
//            }
//        });
//    }


    //
    public void reset() {
        Random r = new Random();
        rect1.setWidth(rect2.getWidth());
//        System.out.println(rect2.getWidth());
//        System.out.println(rect1.getWidth());
        double x = r.nextDouble(300 - 100 + 1) + 100;
//        System.out.println(x);
        rect2.setWidth(r.nextDouble(100 - 10 + 1) + 10);
        rect2.setLayoutX(x);
    }

    public void playGame() {
//        stick = new Line();
//        extendStick(stick);

    }

    public void inc() {
        if (check == 0) {
            time = new Timeline(new KeyFrame(Duration.millis(25), event -> incStick()));
            time.setCycleCount(Timeline.INDEFINITE);
            time.play();
        }
    }

    public void incStick() {
        if (check == 0) {
            stick.setHeight(stick.getHeight() + 5);
        }
    }

    public void stop() {
        if (check == 0) {
            check = 1;
            if (time != null) {
                time.stop();
                double targetAngle = 90;
                double pivotX = stick.getX() + stick.getWidth() / 2;
                double pivotY = stick.getY() + stick.getHeight();
                Rotate rotation = new Rotate(0, pivotX, pivotY);
                stick.getTransforms().add(rotation);
                Timeline timeline = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(rotation.angleProperty(), 0)),
                        new KeyFrame(Duration.millis(500), new KeyValue(rotation.angleProperty(), targetAngle))
                );
                double newX=stick.getHeight();
                TranslateTransition transition = new TranslateTransition();
                transition.setNode(hero);
                transition.setDuration(Duration.millis((Math.abs(newX - Xhero)) / speed_Hero));
                transition.setByX(newX);
                SequentialTransition sequentialTransition=new SequentialTransition(timeline,transition);
                sequentialTransition.play();
                double v = (newX - Xhero) / speed_Hero;
                Xhero = newX;
            }
        }
    }
}
