package com.example.demo;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class GameWindowController implements Initializable {
    @FXML
    private ImageView hero;
    private ArrayList<Rectangle> platforms = new ArrayList<>();
    private final double YPlatform = 338;
    private double speed_Hero = 0.2;
    @FXML
    private AnchorPane pane;
    @FXML
    private Rectangle stick;
    @FXML
    private Text score;
    private int number = 0;
    private int index = 0;
    private int check;
    private Timeline time;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        makePlatform();
        pane.getChildren().add(platforms.get(index));
        Rectangle rectangle = platforms.get(index);
        double w = rectangle.getX() + rectangle.getWidth() + new Random().nextDouble(50, 150) + 10;
        platforms.get(index + 1).setLayoutX(w);
        pane.getChildren().add(platforms.get(index + 1));
        index++;
        check = 0;
    }

    public void inc() {
        if (check == 0) {
            time = new Timeline(new KeyFrame(Duration.millis(25), event -> incStick()));
            time.setCycleCount(Timeline.INDEFINITE);
            time.play();
            System.out.println("hello");


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
            time.stop();
            double targetAngle = 90;
            double pivotX = stick.getX() + stick.getWidth() / 2;
            double pivotY = stick.getY() + stick.getHeight();
            Rotate rotation = new Rotate(0, pivotX, pivotY);
            stick.getTransforms().add(rotation);
            Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(rotation.angleProperty(), 0)), new KeyFrame(Duration.millis(500), new KeyValue(rotation.angleProperty(), targetAngle)));
            double newX = stick.getHeight();
            TranslateTransition transition = new TranslateTransition();
            transition.setNode(hero);
            transition.setDuration(Duration.millis((Math.abs(newX)) / speed_Hero));
            transition.setByX(newX);
            if ((stick.getHeight() + stick.getLayoutX()) > (platforms.get(index).getLayoutX() + platforms.get(index).getWidth()) || (stick.getLayoutX() + stick.getHeight()) < platforms.get(index).getLayoutX()) {
                double targetAngle1 = 90;
                System.out.println((stick.getLayoutX() + stick.getHeight()));
                System.out.println(platforms.get(index).getLayoutX());
                System.out.println(stick.getLayoutX() + stick.getHeight());
                System.out.println(platforms.get(index).getLayoutX() + platforms.get(index).getWidth());
                double pivotX1 = stick.getX() + stick.getWidth() / 2;
                double pivotY1 = stick.getY() + stick.getHeight();
                Rotate rotation1 = new Rotate(0, pivotX1, pivotY1);
                stick.getTransforms().add(rotation1);
                Timeline timeline1 = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(rotation1.angleProperty(), 0)), new KeyFrame(Duration.millis(500), new KeyValue(rotation1.angleProperty(), targetAngle1)));
                double newX1 = platforms.get(index).getHeight() + 100;
                TranslateTransition transition1 = new TranslateTransition();
                transition1.setNode(hero);
                transition1.setDuration(Duration.millis((Math.abs(newX1)) / speed_Hero));
                transition1.setByY(newX1);
                SequentialTransition sequentialTransition = new SequentialTransition(timeline, transition, new ParallelTransition(timeline1, transition1));
                sequentialTransition.play();
                Stage stage = (Stage) stick.getScene().getWindow();
                sequentialTransition.setOnFinished(event -> {
                    stage.close();
                });
            } else {
                System.out.println((stick.getLayoutX() + stick.getHeight()));
                System.out.println(platforms.get(index).getLayoutX());
                System.out.println(stick.getLayoutX() + stick.getHeight());
                System.out.println(platforms.get(index).getLayoutX() + platforms.get(index).getWidth());
                double newX1 = platforms.get(index).getLayoutX();
                TranslateTransition transition1 = new TranslateTransition();
                transition1.setNode(platforms.get(index));
                transition1.setDuration(Duration.millis((Math.abs(newX1)) / speed_Hero));
                transition1.setByX(-newX1);
                TranslateTransition transition12 = new TranslateTransition();
                transition12.setNode(hero);
                transition12.setDuration(Duration.millis((Math.abs(newX1)) / speed_Hero));
                transition12.setByX(-stick.getHeight());
                TranslateTransition transition123 = new TranslateTransition();
                transition123.setNode(stick);
                transition123.setDuration(Duration.millis((Math.abs(newX1)) / speed_Hero));
                transition123.setByX(-stick.getHeight());
                SequentialTransition sequentialTransition = new SequentialTransition(timeline, transition, new ParallelTransition(transition1, transition123, transition12));
                sequentialTransition.play();
                transition.setOnFinished(event -> {
                    System.out.println(platforms.get(index - 1).getLayoutX());
                    platforms.get(index - 1).setLayoutX(-90);
                });
                sequentialTransition.setOnFinished(ev -> {
                    stick.setLayoutX(hero.getLayoutX());
                    stick.setHeight(0);
                    System.out.println(platforms.get(index).getLayoutX());
                    System.out.println(hero.getLayoutY());
                    double pivotY1 = stick.getX() + stick.getWidth() / 2;
                    double pivotX1 = stick.getY() + stick.getHeight();
                    Rotate rotation1 = new Rotate(0, pivotX1, pivotY1);
                    stick.getTransforms().add(rotation1);
                    Timeline timeline1 = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(rotation1.angleProperty(), 0)), new KeyFrame(Duration.millis(50), new KeyValue(rotation1.angleProperty(), 270)));
                    timeline1.play();
                    timeline1.setOnFinished(e -> {
                        TranslateTransition transition1234 = new TranslateTransition();
                        System.out.println(stick.getLayoutY());
                        System.out.println(platforms.get(index).getLayoutY());
                        double ne = platforms.get(index).getHeight();
                        transition1234.setNode(stick);
                        transition1234.setDuration(Duration.millis((Math.abs(ne)) / speed_Hero));
                        transition1234.setByY(-newX1 + 23);
                        transition1234.play();
                        transition1234.setOnFinished(eve -> {
                            index++;
                            if (index >= number) {
                                makePlatform();
                            }
                            System.out.println(stick.getLayoutX());
                            pane.getChildren().add(platforms.get(index));
                            Rectangle rectangle = platforms.get(index - 1);
                            double w = rectangle.getX() - 20 + rectangle.getWidth() + new Random().nextDouble(50, 150) + 10;
                            platforms.get(index).setLayoutX(w);
                            check = 0;
                        });
                    });
                    if (stick.getHeight() + stick.getLayoutX() > (platforms.get(index).getLayoutX() + platforms.get(index).getWidth() / 2)) {
                        score.setText(Integer.toString(Integer.parseInt(score.getText()) + 2));
                    } else {
                        score.setText(Integer.toString(Integer.parseInt(score.getText()) + 1));
                    }
                });
            }
        }
    }

    public void makePlatform() {
        for (int i = 0; i < 20; i++) {
            if (number == 0) {
                Rectangle r = new Rectangle();
                number++;
                r.setHeight(150 - pane.getHeight());
                r.setY(YPlatform + hero.getFitHeight());
                r.setHeight(150 - pane.getHeight());
                r.setWidth(hero.getX() + 40 + hero.getFitWidth());
                r.setX(hero.getX());
                platforms.add(r);
            } else {
                Rectangle r = new Rectangle();
                number++;
                r.setHeight(150 - pane.getHeight());
                r.setY(YPlatform + hero.getFitHeight());
                r.setWidth(new Random().nextDouble(40, 100));
                platforms.add(r);
            }
        }
    }

    public Rectangle getStick() {
        return stick;
    }
}