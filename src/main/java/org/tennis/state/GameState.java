package org.tennis.state;

import org.tennis.model.Player;

public interface GameState {
    void pointWonBy(Player player, TennisGameContext context);
    String getScore(TennisGameContext context);
}