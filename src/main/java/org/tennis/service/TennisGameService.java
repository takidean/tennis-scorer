package org.tennis.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tennis.model.Player;

public class TennisGameService implements TennisGame{
    private static final Logger logger = LoggerFactory.getLogger(TennisGameService.class);

    public final Player playerA = new Player("A");
    public final Player playerB = new Player("B");
    private boolean isDeuce = false;
    private Player lastScorer = null;
    private boolean gameFinished =false;

    @Override
    public void play(String sequence) {
        if (sequence == null || sequence.isEmpty()) return;

        String cleaned = sequence.replaceAll("[^AB]", "");

        for (char c : cleaned.toCharArray()) {
            if (gameFinished) break;

            Player player = (c == 'A') ? playerA : playerB;
            pointWonBy(player);
        }

    }


    private void pointWonBy(Player player) {
        Player opponent = (player.getName().equals( playerA.getName())) ? playerB : playerA;

        if (player.getScore() == 40 && opponent.getScore() == 40) {
            if (isDeuce) {
                if (player.equals(lastScorer)) {
                    gameFinished=true;
                    logger.info("{} wins the game", player.getName());
                } else {
                    logger.info("{} has advantage", player.getName());
                    lastScorer = player;
                }
            } else {
                isDeuce = true;
                //we can log here deuce state logger.info("Deuce");
                lastScorer = player;
            }
        } else {
            player.advanceScore();
            logger.info("Player {} : {}", player.getName(), player.getScore());
            if (player.getScore() == 40 && opponent.getScore() == 40) {
                isDeuce = true;
                //logger.info("Deuce");
            }
        }
    }
}