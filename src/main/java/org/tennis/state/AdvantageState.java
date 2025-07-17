package org.tennis.state;

import org.tennis.model.Player;

public class AdvantageState implements GameState {
    private final Player advantagePlayer;

    public AdvantageState(Player player) {
        this.advantagePlayer = player;
    }

    @Override
    public void pointWonBy(Player player, TennisGameContext context) {
        if (player.equals(advantagePlayer)) {
            context.isFinish=true;
            context.setState(new GameWonState(player));
        } else {
            context.setState(new DeuceState());
        }
    }

    @Override
    public String getScore(TennisGameContext context) {
        return "Advantage " + advantagePlayer.getName();
    }
}