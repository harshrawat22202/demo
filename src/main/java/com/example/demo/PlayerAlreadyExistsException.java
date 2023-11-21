package com.example.demo;
public class PlayerAlreadyExistsException extends Exception{//can be used later
    private String Message="Login Invalid Credentials";

    @Override
    public String toString(){
        return getMessage();
    }

    @Override
    public String getMessage(){
        return this.Message;
    }
}
