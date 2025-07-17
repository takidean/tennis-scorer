package org.tennis.state;

import org.junit.jupiter.api.Test;
import org.tennis.state.TennisGameContext;
import org.tennis.state.GameState;
import org.tennis.state.NormalState;
import org.tennis.model.Player;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TennisGameContextTest {

    @Test
    void constructor_shouldInitializePlayersAndState() {
        TennisGameContext context = new TennisGameContext("A", "B");
        assertNotNull(context.getPlayerA());
        assertNotNull(context.getPlayerB());
        assertEquals("A", context.getPlayerA().getName());
        assertEquals("B", context.getPlayerB().getName());
        assertTrue(context.getGameState() instanceof NormalState); // Initial state should be NormalState
    }

    @Test
    void pointWonBy_shouldDelegateToCurrentState() {
        TennisGameContext context = new TennisGameContext("A", "B");
        GameState mockState = mock(GameState.class);
        context.setGameState(mockState);

        context.pointWonBy("A");

        verify(mockState).pointWonBy("A");
    }

    @Test
    void getScore_shouldDelegateToCurrentState() {
        TennisGameContext context = new TennisGameContext("A", "B");
        GameState mockState = mock(GameState.class);
        when(mockState.getScore()).thenReturn("Test Score");
        context.setGameState(mockState);

        String score = context.getScore();

        assertEquals("Test Score", score);
        verify(mockState).getScore();
    }

    @Test
    void updateGameState_shouldChangeStateBasedOnScores() {
        TennisGameContext context = new TennisGameContext("A", "B");
        Player playerA = context.getPlayer1();
        Player playerB = context.getPlayer2();

        playerA.setScore(4); // Example score to trigger state change
        playerB.setScore(2);
        context.updateGameState();

        // Add assertions to check the new state, e.g., if it's GameWonState or AdvantageState
    }

    @Test
    void setGameState_shouldUpdateGameState() {
        TennisGameContext context = new TennisGameContext("A", "B");
        GameState mockState = mock(GameState.class);

        context.setGameState(mockState);

        assertEquals(mockState, context.getGameState());
    }
}