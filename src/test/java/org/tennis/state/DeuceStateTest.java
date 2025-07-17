package org.tennis.state;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeuceStateTest {

    @Test
    public void testPointWonreturnAdvantage() {

        TennisGameContext context = new TennisGameContext("A","B");
        context.getPlayerA().advanceScore();
        context.getPlayerA().advanceScore();

        context.getPlayerB().advanceScore();
        context.getPlayerB().advanceScore();
        context.getPlayerB().advanceScore();

        DeuceState deuceState = new DeuceState();
        deuceState.pointWonBy(context.getPlayerA(), context);

        // assume GameWonState is implemented correctly
        assertEquals("Advantage A", context.getScore());
    }
}
