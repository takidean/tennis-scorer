package org.tennis;

import org.tennis.service.TennisGameService;

public class Main {

    public static void main(String[] args) {
        TennisGameService tennisGame= new TennisGameService();
        tennisGame.play("AAABBBABABAA");
    }
}