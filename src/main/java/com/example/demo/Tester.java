package com.example.demo;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
public class Tester {
    @Test
    public void test1() {
        assertDoesNotThrow(() -> {
            Main.main("abc");
        });
    }

    @Test
    public void listTester(){
        assertTrue(new GameWindowController().getPlatforms().isEmpty());
    }

    @Test
    public void stick(){
        assertNull(new GameWindowController().getHero());
    }

}
