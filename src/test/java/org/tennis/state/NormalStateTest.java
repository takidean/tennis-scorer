import org.junit.jupiter.api.Test;
import org.tennis.state.NormalState;
import org.tennis.state.DeuceState;
import org.tennis.state.AdvantageState;
import org.tennis.state.GameWonState;
import org.tennis.state.TennisGameContext;
import org.tennis.model.Player;

import static org.junit.jupiter.api.Assertions.*;

class NormalStateTest {

    @Test
    void pointWonBy_playerAWins_shouldUpdateScore() {
        TennisGameContext context = new TennisGameContext("A", "B");
        Player playerA = context.getPlayer1();
        Player playerB = context.getPlayer2();
        context.setGameState(new NormalState(context));

        int initialScore = playerA.getScore();
        context.pointWonBy("A");

        assertEquals(initialScore + 1, playerA.getScore());
    }

    @Test
    void pointWonBy_playerBWins_shouldUpdateScore() {
        TennisGameContext context = new TennisGameContext("A", "B");
        Player playerA = context.getPlayer1();
        Player playerB = context.getPlayer2();
        context.setGameState(new NormalState(context));

        int initialScore = playerB.getScore();
        context.pointWonBy("B");

        assertEquals(initialScore + 1, playerB.getScore());
    }

    @Test
    void updateGameState_bothPlayersHave40_shouldMoveToDeuceState() {
        TennisGameContext context = new TennisGameContext("A", "B");
        Player playerA = context.getPlayer1();
        Player playerB = context.getPlayer2();
        playerA.setScore(3);
        playerB.setScore(3);
        context.setGameState(new NormalState(context));

        context.updateGameState();

        assertTrue(context.getGameState() instanceof DeuceState);
    }

    @Test
    void updateGameState_playerAHasAdvantage_shouldMoveToAdvantageState() {
         TennisGameContext context = new TennisGameContext("A", "B");
         Player playerA = context.getPlayer1();
         Player playerB = context.getPlayer2();
         playerA.setScore(4);
         playerB.setScore(3);
         context.setGameState(new NormalState(context));

         context.updateGameState();

         assertTrue(context.getGameState() instanceof AdvantageState);
    }

    @Test
    void updateGameState_playerAWins_shouldMoveToGameWonState() {
        TennisGameContext context = new TennisGameContext("A", "B");
        Player playerA = context.getPlayer1();
        Player playerB = context.getPlayer2();
        playerA.setScore(4);
        playerB.setScore(2);
        context.setGameState(new NormalState(context));

        context.updateGameState();

        assertTrue(context.getGameState() instanceof GameWonState);
        assertEquals(playerA, context.getWinner());
    }

    @Test
    void getScore_shouldReturnFormattedScore() {
        TennisGameContext context = new TennisGameContext("A", "B");
        Player playerA = context.getPlayer1();
        Player playerB = context.getPlayer2();
        context.setGameState(new NormalState(context));
        playerA.setScore(1);
        playerB.setScore(2);

        // Mocking getScore is not necessary, we can directly assert based on player scores
        assertEquals("15-30", context.getScore());
    }
}
