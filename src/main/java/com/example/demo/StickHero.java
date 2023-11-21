package com.example.demo;

public class StickHero {
    private static StickHero s = null;//singleton design pattern

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
}
