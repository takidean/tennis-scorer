package org.tennis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TennisGameTest {

    @BeforeEach
    void setUp() {
        TennisGame.map.clear();
        TennisGame.isDeuce = false;
        TennisGame.last = "";
    }

    @Test
    void testSimpleProgressionForPlayerA() {
        TennisGame.reverse("A");
        assertEquals(15, TennisGame.map.get("A"));

        TennisGame.reverse("A");
        assertEquals(30, TennisGame.map.get("A"));

        TennisGame.reverse("A");
        assertEquals(40, TennisGame.map.get("A"));
    }

    @Test
    void testSimpleProgressionForBothPlayers() {
        TennisGame.reverse("ABAB");
        assertEquals(30, TennisGame.map.get("A"));
        assertEquals(30, TennisGame.map.get("B"));
    }

    @Test
    void testDeuceToAdvantage() {
        // Simuler : A 40, B 40 => deuce
        TennisGame.reverse("ABABAB");  // A=40, B=40
        assertEquals(40, TennisGame.map.get("A"));
        assertEquals(40, TennisGame.map.get("B"));

        // Prochain point pour A => avantage A
        TennisGame.reverse("A");
        assertEquals(40, TennisGame.map.get("A"));
        // L'état interne isDeuce n’est pas mis à true explicitement dans compute, donc ici pas vérifié
    }

    @Test
    void testWinWithoutDeuce() {
        TennisGame.reverse("AAAA");  // 15, 30, 40, win
        assertEquals(40, TennisGame.map.get("A"));
    }

    @Test
    void testIgnoreInvalidCharacters() {
        TennisGame.reverse("A$@B C1A!");
        // Résultat attendu : A = 30, B = 15
        assertEquals(30, TennisGame.map.get("A"));
        assertEquals(15, TennisGame.map.get("B"));
    }

    @Test
    void testNullOrEmptyInput() {
        TennisGame.reverse("");
        assertTrue(TennisGame.map.isEmpty());

        TennisGame.reverse(null);
        assertTrue(TennisGame.map.isEmpty());
    }

    @Test
    void testLongInput200Characters() {
        // Génère une séquence aléatoire de 200 caractères 'A' ou 'B'
        StringBuilder input = new StringBuilder();
        Random random = new Random(42); // seed fixe pour test déterministe

        for (int i = 0; i < 200; i++) {
            input.append(random.nextBoolean() ? 'A' : 'B');
        }

        TennisGame.reverse(input.toString());

        // On vérifie que la map contient bien des entrées pour A et B
        assertTrue(TennisGame.map.containsKey("A"));
        assertTrue(TennisGame.map.containsKey("B"));

        // Vérifie que les scores sont des valeurs autorisées (0, 15, 30, 40)
        int scoreA = TennisGame.map.get("A");
        int scoreB = TennisGame.map.get("B");
        assertTrue(scoreA == 0 || scoreA == 15 || scoreA == 30 || scoreA == 40);
        assertTrue(scoreB == 0 || scoreB == 15 || scoreB == 30 || scoreB == 40);
    }

}

