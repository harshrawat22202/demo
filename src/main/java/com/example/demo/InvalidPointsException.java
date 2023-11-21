package com.example.demo;

public class InvalidPointsException extends Exception {
    private String Message = "Invalid Points Exception";

    @Override
    public String toString() {
        return getMessage();
    }

    @Override
    public String getMessage() {
        return this.Message;
    }
}
