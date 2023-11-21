package com.example.demo;
public class InvalidCredentialsException extends Exception{
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
