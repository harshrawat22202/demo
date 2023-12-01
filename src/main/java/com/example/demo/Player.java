package com.example.demo;

import java.io.*;
import java.util.*;

public class Player implements Serializable {//implementing flyweight
    private final String Name;
    private final String Password;
    private int HighScore = 0;//getter setter
    private Game Game = null;//getter setter
    private int Cherries = 0;
    private static Map<String, Player> Players_dir = new HashMap<>();
    public String getName() {
        return Name;
    }
    public String getPassword() {
        return Password;
    }
    public int getHighScore() {
        return HighScore;
    }
    public void setHighScore(int highScore) {
        HighScore = highScore;
    }
    public Game getGame() {
        return Game;
    }
    public void setGame(Game game) {
        Game = game;
    }
    public int getCherries() {
        return Cherries;
    }
    public void setCherries(int cherries) {
        Cherries = cherries;
    }
    public static void serialize(Player p) {
    }//for player
    public static Player deserialize() {
        return null;
    }
    private Player(String Name, String Password) {
        this.Name = Name;
        this.Password = Password;
        this.HighScore = 0;
    }
    public static Player getInstance(String Name, String Password) throws NullPointerException {
        if (Players_dir.containsKey(Name)) {
            if (Players_dir.get(Name).getPassword().equals(Password)) {
                return Players_dir.get(Name);
            }
        }
        return null;
    }
    public static void addPlayer(String Name, String Password) throws PlayerAlreadyExistsException {
        if (Players_dir.containsKey(Name)) {
            throw new PlayerAlreadyExistsException();
        } else {
            Players_dir.put(Name, new Player(Name, Password));
        }
    }
    public static Map<String, Player> getPlayers_d() {
        return Players_dir;
    }
    public Game loadGame() {
        return this.Game;
    }
    public void newGame() {
    }
    public void saveGame() {
    }
}