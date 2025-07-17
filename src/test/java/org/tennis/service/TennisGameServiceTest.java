package org.tennis.service;

import org.junit.jupiter.api.Test;
import org.tennis.service.TennisGameService;
import static org.junit.jupiter.api.Assertions.*;

class TennisGameServiceTest {

    @Test
    void play_emptySequence_shouldNotChangeScore() {
        TennisGameService gameService = new TennisGameService();
        gameService.play("");
        // Add assertions here to check the expected outcome
    }

    @Test
    void play_validSequence_shouldUpdateScore() {
        TennisGameService gameService = new TennisGameService();
        gameService.play("ABAB");
        // Add assertions here to check the expected outcome
    }

    @Test
    void play_sequenceWithInvalidChars_shouldIgnoreInvalidChars() {
        TennisGameService gameService = new TennisGameService();
        gameService.play("A!B@A#B$");
        // Add assertions here to check the expected outcome
    }

    @Test
    void play_sequenceThatFinishesGame_shouldStopUpdatingScore() {
        TennisGameService gameService = new TennisGameService();
        gameService.play("AAAAAAAAAAAA");
        // Add assertions here to check the expected outcome
    }

    @Test
    void play_veryBigString_shouldHandleLargeInput() {
        TennisGameService gameService = new TennisGameService();
        StringBuilder longSequence = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            longSequence.append("A");
            longSequence.append("B");
        }
        gameService.play(longSequence.toString());
        // Add assertions here to check the expected outcome
    }
}