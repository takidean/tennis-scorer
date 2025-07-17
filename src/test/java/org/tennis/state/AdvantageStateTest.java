package org.tennis.state;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AdvantageStateTest {

    @Test
    public void testPointWonreturnAWins() {

        TennisGameContext context = new TennisGameContext("A","B");
        context.getPlayerA().advanceScore();
        context.getPlayerA().advanceScore();
        context.getPlayerA().advanceScore();

        context.getPlayerB().advanceScore();
        context.getPlayerB().advanceScore();
        context.getPlayerB().advanceScore();

        AdvantageState advantageState = new AdvantageState(context.getPlayerA());
        advantageState.pointWonBy(context.getPlayerA(), context);

        // assume GameWonState is implemented correctly
        assertEquals("A wins", context.getScore());
    }

    @Test
    public void testPointWonreturnDeuce() {

        TennisGameContext context = new TennisGameContext("A","B");
        context.getPlayerA().advanceScore();
        context.getPlayerA().advanceScore();

        context.getPlayerB().advanceScore();
        context.getPlayerB().advanceScore();
        context.getPlayerB().advanceScore();

        AdvantageState advantageState = new AdvantageState(context.getPlayerA());
        advantageState.pointWonBy(context.getPlayerB(), context);

        // assume GameWonState is implemented correctly
        assertEquals("Deuce", context.getScore());
    }


}
