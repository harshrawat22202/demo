package com.example.demo;

import java.io.*;
import java.util.*;

public class Player implements Serializable {//implementing flyweight
    private final String Name;
    private final String Password;
    private int HighScore = 0;//getter setter
    private ArrayList<Game> Games = new ArrayList<>();//getter setter
    private static Map<String, Player> Players_dir = new HashMap<>();
    private int game_number = -1;//getter setter

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

    public ArrayList<Game> getGames() {
        return Games;
    }

    public void setGames(ArrayList<Game> games) {
        Games = games;
    }

    public int getGame_number() {
        return game_number;
    }

    public void setGame_number(int game_number) {
        this.game_number = game_number;
    }

    static class Serializer {
        //for serializing and deserializing Player_dir
        private ObjectInputStream in = null;
        private ObjectOutputStream out = null;
        private String file;

        public Serializer(String file) throws IOException {
            this.file = file;
            this.in = new ObjectInputStream(new FileInputStream(file));
            this.out = new ObjectOutputStream(new ObjectOutputStream(out));
        }

        public void serialize_Players(Map<String, Player> p) {//Handle IOException
        }

        public Map<String, Player> deSerialize_Players() {//Handle any Exception
            return null;
        }
    }

    ;

    static class Game_Serializer {//for Serialization and Deserialization of ArrayList<Game>
        private ObjectInputStream in = null;
        private ObjectOutputStream out = null;
        private String file;//getter setter

        public Game_Serializer(String file) throws IOException {
            this.file = file;
            this.in = new ObjectInputStream(new FileInputStream(file));
            this.out = new ObjectOutputStream(new FileOutputStream(file));
        }

        public void serialize(ArrayList<Game> list) {//Handle IOException
        }

        public ArrayList<Game> deSerialize() {//Handle any Exception
            return null;
        }
    }

    ;

    public void serialize(Player p) {
    }//for player

    public Player deserialize() {
        return null;
    }

    private Player(String Name, String Password) {
        this.Name = Name;
        this.Password = Password;
        this.HighScore = 0;
    }

    public static Player getInstance(String Name, String Password) throws NullPointerException {
        return null;
    }

    public static void addPlayer(String Name, String Password) throws PlayerAlreadyExistsException {
    }

    public static Map<String, Player> getPlayers_d() {
        return Players_dir;
    }

    public void playGame() {
    }

    public void loadGame() {
    }

    public void newGame() {
    }

    public void saveGame() {
    }

    public static void useStickHero(Player P, StickHero S) {
    }
}