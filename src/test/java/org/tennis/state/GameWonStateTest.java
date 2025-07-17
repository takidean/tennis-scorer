import org.junit.jupiter.api.Test;
import org.tennis.state.GameWonState;
import org.tennis.state.TennisGameContext;
import org.tennis.model.Player;

import static org.junit.jupiter.api.Assertions.*;

class GameWonStateTest {

    @Test
    void pointWonBy_shouldNotChangeState() {
        TennisGameContext context = new TennisGameContext("A", "B");
        Player playerA = context.getPlayer1();
        playerA.setScore(4);
        context.setWinner(playerA);
        context.setGameState(new GameWonState(context));

        context.pointWonBy("A");

        assertTrue(context.getGameState() instanceof GameWonState);
    }

    @Test
    void getScore_playerAWon_shouldReturnGameWonMessage() {
        TennisGameContext context = new TennisGameContext("A", "B");
        Player playerA = context.getPlayer1();
        context.setWinner(playerA);
        context.setGameState(new GameWonState(context));

        assertEquals("Game won by A", context.getScore());
    }

    @Test
    void getScore_playerBWon_shouldReturnGameWonMessage() {
        TennisGameContext context = new TennisGameContext("A", "B");
        Player playerB = context.getPlayer2();
        context.setWinner(playerB);
        context.setGameState(new GameWonState(context));

        assertEquals("Game won by B", context.getScore());
    }
}
