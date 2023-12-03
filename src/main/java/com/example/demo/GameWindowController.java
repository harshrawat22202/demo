package com.example.demo;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class GameWindowController implements Initializable {
    @FXML
    private ImageView hero;
    private ArrayList<Rectangle> platforms = new ArrayList<>();
    private ArrayList<Rectangle> st = new ArrayList<>();
    private final double YPlatform = 338;
    private double speed_Hero = 0.2;
    @FXML
    private AnchorPane pane;
    @FXML
    private Rectangle stick;
    @FXML
    private Text score;
    @FXML
    private Text cherries;
    private int number = 0;
    private int index = 0;
    private int index2 = 0;
    private int check;
    private Timeline time;
    private boolean stopAllAnimation = false;
    private boolean checkCherry = false;
    private AnimationTimer collision = new AnimationTimer() {
        @Override
        public void handle(long l) {
            Node cherry = null;
            for (Node n : pane.getChildren()) {
                if ("Cherry".equals(n.getId())) {
                    cherry = n;
                }
            }
            if (cherry != null) {
                if (hero.getBoundsInParent().intersects(cherry.getBoundsInParent())) {
                    pane.getChildren().remove(cherry);
                    System.out.println("Cherry");
                    cherries.setText(Integer.toString(Integer.parseInt(cherries.getText()) + 1));
                    pane.getChildren().removeIf(child -> "Cherry".equals(child.getId()));
                }
            }
        }
    };

    private AnimationTimer collisonWithPlatform = new AnimationTimer() {
        @Override
        public void handle(long l) {
            if (hero.getBoundsInParent().intersects(platforms.get(index).getBoundsInParent()) && isInverted) {
                stopAllAnimation = true;
                System.out.println("collision");// chl nahi raha theek se comment out mar dio is field ko
                /*yaha par wahi girne wala animation daldio jo tune neeche dala hai*/
                collision.stop();
                Stage stage = (Stage) st.get(index2).getScene().getWindow();
                stage.close();
                collisonWithPlatform.stop();
            }
        }
    };
    private boolean isInverted = false;
    private boolean isMoving = false;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        makePlatform();
        makeStick();
        pane.getChildren().add(platforms.get(index));
        Rectangle rectangle = platforms.get(index);
        double w = rectangle.getX() + rectangle.getWidth() + new Random().nextDouble(50, 150) + 10;
        platforms.get(index + 1).setLayoutX(w);
        pane.getChildren().add(platforms.get(index + 1));
        check = 0;
        st.get(index2).setLayoutX(platforms.get(index).getWidth() - 2);
        pane.getChildren().add(st.get(index2));
        index++;
        collision.start();
        collisonWithPlatform.start();
    }

    public void placeCherry() {
        if (checkCherry) {
            pane.getChildren().removeIf(child -> "Cherry".equals(child.getId()));
        }
        ImageView x = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("Cherry.png"))));
        x.setId("Cherry");
        x.setX(new Random().nextDouble(platforms.get(index - 1).getWidth(), platforms.get(index).getLayoutX() - x.getFitWidth() - 10));
        x.setLayoutY(hero.getLayoutY() + 50);
        pane.getChildren().add(x);
        System.out.println("Placed Cherry");

    }

    public void inc() {
        if (check == 0 && !isMoving) {
            time = new Timeline(new KeyFrame(Duration.millis(25), event -> incStick()));
            time.setCycleCount(Timeline.INDEFINITE);
            time.play();
        } else {
            Rotate rotate = new Rotate(0, stick.getX() + 8, stick.getY() + 40);
            rotate.setAngle(180.0);
            hero.getTransforms().add(rotate);
            if (isInverted) {
                hero.setScaleX(1);
            } else {
                hero.setScaleX(-1);
            }
            isInverted = !isInverted;
        }
    }

    public void incStick() {
        if (check == 0) {
            st.get(index2).setHeight(st.get(index2).getHeight() + 5);
        }
    }

    public void stop() {
        if (check == 0) {
            check = 1;
            time.stop();
            double targetAngle = 90;
            double pivotX = st.get(index2).getX() + st.get(index2).getWidth() / 2;
            double pivotY = st.get(index2).getY() + st.get(index2).getHeight();
            Rotate rotation = new Rotate(0, pivotX, pivotY);
            st.get(index2).getTransforms().add(rotation);
            Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(rotation.angleProperty(), 0)),
                    new KeyFrame(Duration.millis(500), new KeyValue(rotation.angleProperty(), targetAngle)));
            double newX = st.get(index2).getHeight();
            TranslateTransition transition = new TranslateTransition();
            transition.setNode(hero);
            if ((st.get(index2).getHeight() + st.get(index2).getLayoutX()) > (platforms.get(index).getLayoutX() + platforms.get(index).getWidth()) || (st.get(index2).getLayoutX() + st.get(index2).getHeight()) < platforms.get(index).getLayoutX()) {
                double targetAngle1 = 90;
                transition.setDuration(Duration.millis((Math.abs(newX)) / speed_Hero));
                transition.setByX(st.get(index2).getHeight());
                double pivotX1 = st.get(index2).getX() + st.get(index2).getWidth() / 2;
                double pivotY1 = st.get(index2).getY() + st.get(index2).getHeight();
                Rotate rotation1 = new Rotate(0, pivotX1, pivotY1);
                st.get(index2).getTransforms().add(rotation1);
                Timeline timeline1 = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(rotation1.angleProperty(), 0)), new KeyFrame(Duration.millis(500), new KeyValue(rotation1.angleProperty(), targetAngle1)));
                double newX1 = platforms.get(index).getHeight() + 100;
                TranslateTransition transition1 = new TranslateTransition();
                transition1.setNode(hero);
                transition1.setDuration(Duration.millis((Math.abs(newX1)) / speed_Hero));
                transition1.setByY(newX1);
                SequentialTransition sequentialTransition = new SequentialTransition(timeline, transition, new ParallelTransition(timeline1, transition1));
                sequentialTransition.play();
                Stage stage = (Stage) st.get(index2).getScene().getWindow();
                sequentialTransition.setOnFinished(event -> stage.close());
            } else {
                transition.setByX(platforms.get(index).getLayoutX() - platforms.get(index - 1).getWidth() + platforms.get(index).getWidth());
                double newX1 = platforms.get(index).getLayoutX();
                transition.setDuration(Duration.millis((Math.abs(newX1)) / speed_Hero));
                TranslateTransition transition1 = new TranslateTransition();
                transition1.setNode(platforms.get(index));
                transition1.setDuration(Duration.millis((Math.abs(newX1)) / speed_Hero));
                transition1.setByX(-newX1);
                TranslateTransition transition12 = new TranslateTransition();
                transition12.setNode(hero);
                transition12.setDuration(Duration.millis((Math.abs(newX1)) / speed_Hero));
                transition12.setByX(-newX1);
                TranslateTransition transition123 = new TranslateTransition();
                transition123.setNode(st.get(index2));
                transition123.setDuration(Duration.millis((Math.abs(newX1)) / speed_Hero));
                transition123.setByX(-newX1);
                TranslateTransition transition1234 = new TranslateTransition();
                transition1234.setNode(platforms.get(index - 1));
                transition1234.setDuration(Duration.millis((Math.abs(newX1)) / speed_Hero));
                transition1234.setByX(-newX1);
                SequentialTransition sequentialTransition = new SequentialTransition(timeline, transition, new ParallelTransition(transition1, transition123, transition12, transition1234));
                sequentialTransition.play();
                isMoving = true;
                transition.setOnFinished(event -> {
                    if ((index2 != 0)) {
                        st.get(index2 - 1).setHeight(0);
                    }
                    isMoving = false;
                });
                sequentialTransition.setOnFinished(ev -> {
                    int l = index2;
                    index++;
                    pane.getChildren().add(platforms.get(index));
                    Rectangle rectangle = platforms.get(index - 1);
                    double w = rectangle.getX() - 20 + rectangle.getWidth() + new Random().nextDouble(50, 150) + 10 + 50;

                    platforms.get(index).setLayoutX(w);
                    check = 0;
                    index2++;
                    st.get(index2).setLayoutX(platforms.get(index - 1).getWidth() - 2);
                    pane.getChildren().add(st.get(index2));
                    if (st.get(index2 - 1).getHeight() + st.get(index2 - 1).getLayoutX() > (platforms.get(index).getLayoutX() + platforms.get(index).getWidth() / 2)) {
                        score.setText(Integer.toString(Integer.parseInt(score.getText()) + 2));
                    } else {
                        score.setText(Integer.toString(Integer.parseInt(score.getText()) + 1));
                    }
                    placeCherry();
                    checkCherry = true;
                });
            }
        }
    }

    public void makeStick() {
        for (int i = 0; i < 100; i++) {
            Rectangle r = new Rectangle();
            r.setHeight(0);
            r.setWidth(3);
            AnchorPane.setBottomAnchor(r, 150.0);
            r.setLayoutY(365);
            st.add(r);
        }
    }

    public void makePlatform() {
        for (int i = 0; i < 100; i++) {
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
}