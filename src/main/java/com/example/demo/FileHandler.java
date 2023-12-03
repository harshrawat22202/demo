package com.example.demo;

import java.io.*;
import java.util.HashMap;

public class FileHandler {
    private static FileHandler fileHandler = null;

    private static ObjectInputStream in;
    private static ObjectOutputStream out;

    static {
        try {
            in = new ObjectInputStream(new FileInputStream("data.txt"));
            out = new ObjectOutputStream(new FileOutputStream("data.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static FileHandler getInstanceOfFileHandler() {
        if (fileHandler == null) {
            fileHandler = new FileHandler();
        }
        return fileHandler;
    }

    public HashMap<String, Player> getPlayerDirectory() {
        Object o = null;
        try {
            o = in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            //ignore
        }
        return (HashMap<String, Player>) o;
    }

    public void writePlayers(HashMap<String,Player> p){
        try {
            out.writeObject(p);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
