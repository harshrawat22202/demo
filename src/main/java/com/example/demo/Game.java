package com.example.demo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Game implements Serializable{
    private int score=0;
    private int cherriesCollected=0;
    private final LocalDateTime Created;
    private LocalDateTime LastPlayed;

    public Game(){
        this.Created=LocalDateTime.now();
    }

    public LocalDateTime getCreated() {
        return Created;
    }

    public LocalDateTime getLastPlayed(){
        return this.LastPlayed;
    }

    public void setLastPlayed(LocalDateTime t){
        this.LastPlayed=t;
    }

    public int getCherriesCollected() {
        return cherriesCollected;
    }

    public void setCherriesCollected(int cherriesCollected) {
        this.cherriesCollected = cherriesCollected;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String toString(){
        return LastPlayed.toString()+" "+ score +" "+ cherriesCollected;
    }
}
