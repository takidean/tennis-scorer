package org.tennis.state;

import org.tennis.model.Player;

public class DeuceState implements GameState {
    private Player lastWinner = null;

    @Override
    public void pointWonBy(Player player, TennisGameContext context) {
        if (lastWinner == null || !lastWinner.equals(player)) {
            lastWinner = player;
            context.setState(new AdvantageState(player));
        } else {
            context.setState(new GameWonState(player));
        }
    }

    @Override
    public String getScore(TennisGameContext context) {
        return "Deuce";
    }
}