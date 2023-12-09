package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            playBackgroundMusic("press_start.wav");
            Parent root = new FXMLLoader(getClass().getResource("scene1.fxml")).load();
            Scene scene = new Scene(root);
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("logo.png"))));
            stage.setTitle("Stick Hero");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("acac");
        }
    }

    public static void main(String args) {
        launch(args);
    }

    public static void playBackgroundMusic(String filePath) {
        try {
            File af = new File(filePath);
            AudioInputStream as = AudioSystem.getAudioInputStream(af);
            AudioFormat af1 = as.getFormat();
            DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, af1);
            SourceDataLine sd = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
            sd.open(af1);
            sd.start();
            new Thread(() -> {
                byte[] a = new byte[4096];
                int c;

                try {
                    while ((c = as.read(a, 0, a.length)) != -1) {
                        sd.write(a, 0, c);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sd.drain();
                sd.close();
            }).start();

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
}