package org.tennis.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tennis.model.Player;
import org.tennis.state.TennisGameContext;

public class TennisGameService implements TennisGame{


Logger logger =LoggerFactory.getLogger(TennisGameContext.class);

    @Override
    public void play(String sequence) {
        TennisGameContext game = new TennisGameContext("A", "B");
        if (sequence == null || sequence.isEmpty()) return;

        String cleaned = sequence.replaceAll("[^AB]", "");

        for (char c : cleaned.toCharArray()) {
            if (game.getIsFinishFlag()) {
                break;
            }
            game.pointWonBy(String.valueOf(c));
            logger.info(game.getScore());

        }

    }

}