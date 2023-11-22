package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class GameWindow {
    @FXML
    private AnchorPane pane;
    private StickHero hero=StickHero.getStickHero();
}