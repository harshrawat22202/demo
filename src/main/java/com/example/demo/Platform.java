package com.example.demo;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class Platform {
    private AnchorPane anchorPane;
    private double anchorPaneWidth;
    private double anchorPaneLength;

    private Random random=new Random();

    public Platform(AnchorPane pane,double anchorPaneWidth,double anchorPaneLength){
        this.anchorPane=pane;
        this.anchorPaneWidth=anchorPaneWidth;
        this.anchorPaneLength=anchorPaneLength;
    }

    /*public ArrayList<Rectangle> createPlatforms(){
        double space=25.0;
        double secondPlatformWidth=random.nextDouble(anchorPaneWidth-space-30.0)+50;
        System.out.println(secondPlatformWidth);

    }*/
}
