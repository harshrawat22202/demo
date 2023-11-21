package com.example.demo;

import javafx.scene.image.Image;

import java.util.Objects;

public class StickHero {
    private static StickHero s = null;//singleton design pattern
    private final Image i = new Image(Objects.requireNonNull(getClass().getResourceAsStream("hero.png")));

    public static StickHero getStickHero() {
        if (s == null)
            s = new StickHero();
        return s;
    }

    public Image getImage() {
        return this.i;
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
}
