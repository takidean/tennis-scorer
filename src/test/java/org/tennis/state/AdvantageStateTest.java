import org.junit.jupiter.api.Test;
import org.tennis.state.AdvantageState;
import org.tennis.state.DeuceState;
import org.tennis.state.GameWonState;
import org.tennis.state.TennisGameContext;
import org.tennis.model.Player;

import static org.junit.jupiter.api.Assertions.*;

class AdvantageStateTest {

    @Test
    void pointWonBy_playerWithAdvantageWins_shouldMoveToWinState() {
        TennisGameContext context = new TennisGameContext("A", "B");
        Player playerA = context.getPlayer1();
        Player playerB = context.getPlayer2();
        playerA.setScore(4); // Simulate advantage for player A
        playerB.setScore(3);
        context.setAdvantagePlayer(playerA);
        context.setGameState(new AdvantageState(context));

        context.pointWonBy("A");

        assertTrue(context.getGameState() instanceof GameWonState);
        assertEquals(playerA, context.getWinner());
    }

    @Test
    void pointWonBy_otherPlayerWins_shouldMoveToDeuceState() {
        TennisGameContext context = new TennisGameContext("A", "B");
        Player playerA = context.getPlayer1();
        Player playerB = context.getPlayer2();
        playerA.setScore(4);
        playerB.setScore(3);
        context.setAdvantagePlayer(playerA);
        context.setGameState(new AdvantageState(context));

        context.pointWonBy("B");

        assertTrue(context.getGameState() instanceof DeuceState);
        assertNull(context.getAdvantagePlayer());
    }

    @Test
    void getScore_playerAHasAdvantage_shouldReturnCorrectScore() {
        TennisGameContext context = new TennisGameContext("A", "B");
        Player playerA = context.getPlayer1();
        Player playerB = context.getPlayer2();
        playerA.setScore(4);
        playerB.setScore(3);
        context.setAdvantagePlayer(playerA);
        context.setGameState(new AdvantageState(context));

        assertEquals("Advantage A", context.getScore());
    }

    @Test
    void getScore_playerBHasAdvantage_shouldReturnCorrectScore() {
        TennisGameContext context = new TennisGameContext("A", "B");
        Player playerA = context.getPlayer1();
        Player playerB = context.getPlayer2();
        playerA.setScore(3);
        playerB.setScore(4);
        context.setAdvantagePlayer(playerB);
        context.setGameState(new AdvantageState(context));

        assertEquals("Advantage B", context.getScore());
    }
}
