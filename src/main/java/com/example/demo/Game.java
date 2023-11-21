package com.example.demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Game implements Serializable{
    private int score;
    private LocalDateTime Created;
    private LocalDateTime LastPlayed;
    private Data info;

    public Game(){}

    public void serialize(Game g){}//for Game

    public Game deSerialize(){
        return null;
    }
}
