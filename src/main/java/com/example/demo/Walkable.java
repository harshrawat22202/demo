package com.example.demo;

public class Walkable {
    private int x1;
    private int x2;

    public Walkable(int x1,int x2) throws InvalidPointsException{
        if (x2<=x1){
            System.out.println("Invalid values");
            throw new IllegalArgumentException();
        }
        this.x1=x1;
        this.x2=x2;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }
}
