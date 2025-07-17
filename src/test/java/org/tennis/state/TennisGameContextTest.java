package org.tennis.state;

import org.junit.Test;
import org.tennis.state.TennisGameContext;
import org.tennis.model.Player;
import org.tennis.state.GameState;

import static org.junit.Assert.assertEquals;

public class TennisGameContextTest {

    @Test
    public void  ScoreChanged() {
        TennisGameContext context = new TennisGameContext("A", "B");
        context.pointWonBy("A");
        assertEquals(15,context.getPlayerA().getScore());
        assertEquals(false,context.isFinish);
    }

    @Test
    public void  ScoreB() {
        TennisGameContext context = new TennisGameContext("A", "B");
        context.pointWonBy("A");
        context.pointWonBy("A");
        context.pointWonBy("A");
        context.pointWonBy("A");

        assertEquals(true,context.isFinish);
    }


}
