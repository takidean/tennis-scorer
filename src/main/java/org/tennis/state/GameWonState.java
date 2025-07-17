package org.tennis.state;

import org.tennis.model.Player;

public class GameWonState implements GameState {
    private final Player winner;

    public GameWonState(Player winner) {
        this.winner = winner;
    }

    @Override
    public void pointWonBy(Player player, TennisGameContext context) {
        // Game is already over
    }

    @Override
    public String getScore(TennisGameContext context) {
        return "Player " + winner.getName() + " wins the game";
    }
}