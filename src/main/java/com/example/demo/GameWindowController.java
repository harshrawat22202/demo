package com.example.demo;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.media.Media;

import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.util.*;
import java.util.Random.*;

public class GameWindowController implements Initializable {
    @FXML
    private ImageView hero;
    private ArrayList<Rectangle> platforms = new ArrayList<>();
    private ArrayList<Rectangle> st = new ArrayList<>();
    private final double YPlatform = 187;
    private double speed_Hero = 0.2;
    private int check2 = 0;
    @FXML
    private AnchorPane pane;
    @FXML
    private Rectangle stick;
    @FXML
    private Text score;
    @FXML
    private MediaView md;

    @FXML
    private Rectangle ff;

    @FXML
    private Rectangle r2;
    @FXML
    private Rectangle r6;
    @FXML
    private Rectangle r7;
    @FXML
    private ImageView ggg;

    @FXML
    private Button pause;

    @FXML
    private Label r1;

    @FXML
    private Label r22;

    @FXML
    private Label r3;
    @FXML
    private Label r4;
    @FXML
    private Label r5;
    @FXML
    private Button home;
    @FXML
    private Button reload;
    @FXML
    private ImageView ffff;
    @FXML
    private ImageView f2;
    @FXML
    private Text cherries;
    private int number = 0;
    private int index = 0;
    private int index2 = 0;
    private int check;
    private Timeline time;

    private ImageView x;

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
                check2 = 1;
                System.out.println("collision");// chl nahi raha theek se comment out mar dio is field ko
                /*yaha par wahi girne wala animation daldio jo tune neeche dala hai*/
                collision.stop();
                st.get(index2).setOpacity(0);
                if (index2 > 0) {
                    st.get(index2 - 1).setOpacity(0);

                }
                platforms.get(index + 1).setOpacity(0);


                platforms.get(index).setOpacity(0);
                platforms.get(index).setLayoutX(2);
                platforms.get(index - 1).setOpacity(0);
                hero.setOpacity(0);
                pause.setOpacity(0);
                cherries.setOpacity(0);
                if (x != null) {
                    x.setOpacity(0);

                }


                score.setOpacity(0);
                ggg.setOpacity(0);
                ff.setOpacity(0);

                reload.setLayoutX(357);
                reload.setLayoutY(260);

                home.setLayoutY(260);
                home.setLayoutX(261);


                r1.setOpacity(1);
                r2.setOpacity(0.3);
                r22.setOpacity(1);
                r22.setOpacity(1);
                r3.setText(score.getText());
                r3.setOpacity(1);
                r4.setOpacity(1);
                r5.setOpacity(1);
                r5.setText(cherries.getText());
                r6.setOpacity(0.3);
                ffff.setOpacity(1);
                r7.setOpacity(0.3);
                f2.setOpacity(1);


                temporarySave();
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
        double w = rectangle.getX() + rectangle.getWidth() + new Random().nextDouble() * (150 - 50) + 50 + 10;
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
        if (check2 == 0) {
            if (checkCherry) {
                pane.getChildren().removeIf(child -> "Cherry".equals(child.getId()));
            }
            x = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("Cherry.png"))));
            x.setId("Cherry");
            x.setX(new Random().nextDouble() * (platforms.get(index).getLayoutX() - x.getFitWidth() - 10 - platforms.get(index - 1).getWidth()) + platforms.get(index - 1).getWidth());
            x.setLayoutY(hero.getLayoutY() + 50);
            pane.getChildren().add(x);
            System.out.println("Placed Cherry");
        }
    }

    public void inc() {
        if (check == 0 && !isMoving) {
            time = new Timeline(new KeyFrame(Duration.millis(25), event -> incStick()));
            time.setCycleCount(Timeline.INDEFINITE);
            time.play();
        } else {
            if (check2 == 0) {
                Rotate rotate = new Rotate(0, stick.getX() + 3, stick.getY() + 40);
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
                check2 = 1;
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
                sequentialTransition.setOnFinished(event -> {
                    st.get(index2).setOpacity(0);
                    if (index2 > 0) {
                        st.get(index2 - 1).setOpacity(0);

                    }


                    platforms.get(index).setOpacity(0);
                    platforms.get(index - 1).setOpacity(0);
                    platforms.get(index).setLayoutX(2);
                    hero.setOpacity(0);
                    pause.setOpacity(0);
                    cherries.setOpacity(0);
                    if (x != null) {
                        x.setOpacity(0);

                    }


                    score.setOpacity(0);
                    ggg.setOpacity(0);
                    ff.setOpacity(0);

                    reload.setLayoutX(357);
                    reload.setLayoutY(260);

                    home.setLayoutY(260);
                    home.setLayoutX(261);


                    r1.setOpacity(1);
                    r2.setOpacity(0.3);
                    r22.setOpacity(1);
                    r22.setOpacity(1);
                    r3.setText(score.getText());
                    r3.setOpacity(1);
                    r4.setOpacity(1);
                    r5.setOpacity(1);
                    r5.setText(cherries.getText());
                    r6.setOpacity(0.3);
                    ffff.setOpacity(1);
                    r7.setOpacity(0.3);
                    f2.setOpacity(1);


                });

                temporarySave();
            } else {
                if (check2 == 0) {
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
                        double w = rectangle.getX() - 20 + rectangle.getWidth() + new Random().nextDouble() * (150 - 50) + 50 + 10 + 50;

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
    }

    private void temporarySave() {
        PrintWriter fw = null;
        try {
            fw = new PrintWriter(new FileWriter("data.txt"));
            fw.println(score.getText());
            fw.println(cherries.getText());
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            assert fw != null;
            fw.close();
        }
    }

    public void makeStick() {
        for (int i = 0; i < 100; i++) {
            Rectangle r = new Rectangle();
            r.setHeight(0);
            r.setWidth(3);
            AnchorPane.setBottomAnchor(r, 152.0);
            r.setLayoutY(364);
            st.add(r);
        }
    }

    public void makePlatform() {
        for (int i = 0; i < 100; i++) {
            if (number == 0) {
                Rectangle r = new Rectangle();
                r.setStyle("-fx-border-color: white;");
//                BoxBlur blur = new BoxBlur();
//                blur.setT
//                r.setEffect(blur);
//                r.setFill(Color.BLUE);
//                r.setStyle("-fx-border-color: blue;");
                number++;
                r.setHeight(150 - pane.getHeight());
                r.setY(YPlatform + hero.getFitHeight());
                r.setHeight(150 - pane.getHeight());
                r.setWidth(hero.getX() + 40 + hero.getFitWidth());
                r.setX(hero.getX());
                platforms.add(r);
            } else {
                Rectangle r = new Rectangle();
//                r.setFill(Color.BLUE);
                r.setStyle("-fx-border-color: white;");
//                BoxBlur blur = new BoxBlur();
////                blur.setT
//                r.setEffect(blur);
                number++;
                r.setHeight(150 - pane.getHeight());
                r.setY(YPlatform + hero.getFitHeight());
                r.setWidth(new Random().nextDouble() * (100 - 40) + 40);
                platforms.add(r);
            }
        }
    }

    public void reload1() {
        Stage stage1 = (Stage) cherries.getScene().getWindow();
        stage1.close();

        Parent root;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gameWindow.fxml"));
            root = fxmlLoader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            Button b = (Button) root.lookup("#pause");
            b.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Parent r;
                    try {
                        r = new FXMLLoader(getClass().getResource("PauseWindow.fxml")).load();
                        Stage s = new Stage();
                        Scene scene1 = new Scene(r);
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

    public void scene(ActionEvent event) throws IOException {
        Stage stage1 = (Stage) cherries.getScene().getWindow();
        stage1.close();
    }

    public void addToSave() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("data.txt"));
        String score = sc.nextLine();
        String cherry = sc.nextLine();
        sc.close();
        Scanner sc1 = new Scanner(new FileReader("main.txt"));
        String score1 = sc1.nextLine();
        String cherry1 = sc1.nextLine();
        score1 = Integer.parseInt(score1) + score;
        cherry1 = Integer.parseInt(cherry1) + cherry;
        sc1.close();
        PrintWriter fw = new PrintWriter("main.txt");
        fw.println(score);
        fw.println(cherry);
        fw.close();
    }

    public void revive() throws FileNotFoundException {
        Scanner sc1 = new Scanner(new FileReader("main.txt"));
        String score1 = sc1.nextLine();
        String cherry1 = sc1.nextLine();
        sc1.close();
        Scanner sc = new Scanner(new FileReader("data.txt"));
        String score = sc.nextLine();
        String cherry = sc.nextLine();
        sc.close();
        int total = Integer.parseInt(cherry1) + Integer.parseInt(cherry);
        if (total >= 2) {
            total -= 2;
            reload1();
        }
    }
}