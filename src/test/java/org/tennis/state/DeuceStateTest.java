import org.junit.jupiter.api.Test;
import org.tennis.state.AdvantageState;
import org.tennis.state.DeuceState;
import org.tennis.state.TennisGameContext;
import org.tennis.model.Player;

import static org.junit.jupiter.api.Assertions.*;

class DeuceStateTest {

    @Test
    void pointWonBy_playerAWins_shouldMoveToAdvantageStateForPlayerA() {
        TennisGameContext context = new TennisGameContext("A", "B");
        Player playerA = context.getPlayer1();
        Player playerB = context.getPlayer2();
        playerA.setScore(3);
        playerB.setScore(3);
        context.setGameState(new DeuceState(context));

        context.pointWonBy("A");

        assertTrue(context.getGameState() instanceof AdvantageState);
        assertEquals(playerA, context.getAdvantagePlayer());
    }

    @Test
    void pointWonBy_playerBWins_shouldMoveToAdvantageStateForPlayerB() {
        TennisGameContext context = new TennisGameContext("A", "B");
        Player playerA = context.getPlayer1();
        Player playerB = context.getPlayer2();
        playerA.setScore(3);
        playerB.setScore(3);
        context.setGameState(new DeuceState(context));

        context.pointWonBy("B");

        assertTrue(context.getGameState() instanceof AdvantageState);
        assertEquals(playerB, context.getAdvantagePlayer());
    }

    @Test
    void getScore_shouldReturnDeuce() {
        TennisGameContext context = new TennisGameContext("A", "B");
        Player playerA = context.getPlayer1();
        Player playerB = context.getPlayer2();
        playerA.setScore(3);
        playerB.setScore(3);
        context.setGameState(new DeuceState(context));

        assertEquals("Deuce", context.getScore());
    }
}
