package org.tennis.model;

public class Player {
    private final String name;
    private int score = 0;

    public Player(String name) {
        this.name = name;
    }

    public void advanceScore() {
        switch (score) {
            case 0 -> score = 15;
            case 15 -> score = 30;
            case 30 -> score = 40;
        }
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public void resetScore() {
        score = 0;
    }
}