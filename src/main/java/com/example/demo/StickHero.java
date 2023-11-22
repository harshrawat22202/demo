package com.example.demo;

import javafx.scene.image.Image;

import java.util.Objects;

public class StickHero {
    private static StickHero s = null;//singleton design pattern
    private final Image photo=new Image(Objects.requireNonNull(getClass().getResourceAsStream("hero.png")));

    public static StickHero getStickHero() {
        if (s == null)
            s = new StickHero();
        return s;
    }

    public static void moveForward() {

    }

    public static void moveBackward() {

    }

    public static void extendStick() {
    }

    public static void goDown() {
    }

    public static void goUp() {
    }

    public static void fall(Walkable... walkables) {//check for falling down
    }

    public static void restart() {

    }

    public Image getPhoto() {
        return photo;
    }
}
