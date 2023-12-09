package com.example.demo;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class FileHandler {
    private static FileHandler fileHandler = null;

    public static FileHandler getInstanceOfFileHandler() {
        if (fileHandler == null) {
            fileHandler = new FileHandler();
        }
        return fileHandler;
    }

    public HashMap<String, Player> getPlayerDirectory()  {
        ObjectInputStream in=null;
        Object o = null;
        try {
            Path p=Paths.get("./data.txt");
            String s=p.toAbsolutePath().toString();
            in=new ObjectInputStream(new FileInputStream(s));
            o = in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            //ignore
        }
        return (HashMap<String, Player>) o;
    }

    public void writePlayers(HashMap<String, Player> p) {
        ObjectOutputStream out=null;
        try {
            Path p1=Paths.get("./data.txt");
            String s=p1.toAbsolutePath().toString();
            out=new ObjectOutputStream(new FileOutputStream(s));
            out.writeObject(p);
            out.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void addPlayer(String Name, String passwd) throws PlayerAlreadyExistsException {
        Player.addPlayer(Name,passwd);
        writePlayers(Player.getPlayers_d());
    }
}
