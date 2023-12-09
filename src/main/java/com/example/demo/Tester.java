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
}
