package com.example.demo;

public class NoSuchPlayerException extends Exception{
    private String Message="No Such Player";

    @Override
    public String toString(){
        return getMessage();
    }

    @Override
    public String getMessage(){
        return this.Message;
    }
}
