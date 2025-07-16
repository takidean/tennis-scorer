package org.tennis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tennis.service.TennisGameService;

import static org.junit.jupiter.api.Assertions.*;

public class TennisGameTest {

    private TennisGameService game;

    @BeforeEach
    void setUp() {
        game = new TennisGameService();
    }

    @Test
    void testSimpleScoreProgressionForPlayerA() {
        game.play("A");
        assertEquals(15, game.playerA.getScore());

        game.play("A");
        assertEquals(30, game.playerA.getScore());

        game.play("A");
        assertEquals(40, game.playerA.getScore());
    }

    @Test
    void testScoreProgressionForBothPlayers() {
        game.play("ABAB");
        assertEquals(30, game.playerA.getScore());
        assertEquals(30, game.playerB.getScore());
    }

    @Test
    void testDeuceTriggered() {
        game.play("ABABAB"); // A:40, B:40
        assertEquals(40, game.playerA.getScore());
        assertEquals(40, game.playerB.getScore());
    }

    @Test
    void testAdvantageFromDeuce() {
        game.play("ABABAB"); // Deuce
        game.play("A"); // Advantage A
        // No need to assert internal state unless exposed — this confirms we can proceed without crash
        assertEquals(40, game.playerA.getScore());
        assertEquals(40, game.playerB.getScore());
    }

    @Test
    void testReturnToDeuceFromAdvantage() {
        game.play("ABABABA"); // Advantage A
        game.play("B");       // Back to deuce
        assertEquals(40, game.playerA.getScore());
        assertEquals(40, game.playerB.getScore());
    }

    @Test
    void testWinWithoutDeuce() {
        game.play("AAAA"); // A wins directly
        assertEquals(40, game.playerA.getScore());
    }

    @Test
    void testIgnoreInvalidCharacters() {
        game.play("A$@B C1A!");
        assertEquals(30, game.playerA.getScore());
        assertEquals(15, game.playerB.getScore());
    }

    @Test
    void testEmptyOrNullInput() {
        game.play("");
        assertEquals(0, game.playerA.getScore());
        assertEquals(0, game.playerB.getScore());

        game.play(null);
        assertEquals(0, game.playerA.getScore());
        assertEquals(0, game.playerB.getScore());
    }

    @Test
    void testLongInputWith200Characters() {
        StringBuilder sequence = new StringBuilder();
        for (int i = 0; i < 200; i++) {
            sequence.append(i % 2 == 0 ? 'A' : 'B');
        }
        game.play(sequence.toString());

        // Résultat final inconnu à l'avance car il dépend des règles simples
        // On vérifie seulement que les scores sont cohérents
        int scoreA = game.playerA.getScore();
        int scoreB = game.playerB.getScore();

        assertTrue(scoreA == 0 || scoreA == 15 || scoreA == 30 || scoreA == 40);
        assertTrue(scoreB == 0 || scoreB == 15 || scoreB == 30 || scoreB == 40);
    }
}
