package org.tennis.state;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class NormalStateTest {

    @Test
    public void testPointWonBy_Deuce() {

        TennisGameContext context = new TennisGameContext("A","B");
        context.getPlayerA().advanceScore();
        context.getPlayerA().advanceScore();

        context.getPlayerB().advanceScore();
        context.getPlayerB().advanceScore();
        context.getPlayerB().advanceScore();

        NormalState normalState = new NormalState();
        normalState.pointWonBy(context.getPlayerA(), context);

        // assume GameWonState is implemented correctly
        assertEquals("Deuce", context.getScore());
    }

    @Test
    public void testPointWonBy_GameWon() {

        TennisGameContext context = new TennisGameContext("A","B");
        context.getPlayerB().advanceScore();
        context.getPlayerB().advanceScore();
        context.getPlayerB().advanceScore();

        NormalState normalState = new NormalState();
        normalState.pointWonBy(context.getPlayerA(), context);

        // assume GameWonState is implemented correctly
        assertEquals("A: 15 - B: 40", context.getScore());
    }

}
