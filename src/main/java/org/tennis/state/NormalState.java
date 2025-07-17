package org.tennis.state;

import org.tennis.model.Player;

public class NormalState implements GameState {
    @Override
    public void pointWonBy(Player player, TennisGameContext context) {
        Player opponent = player == context.getPlayerA() ? context.getPlayerB() : context.getPlayerA();

        player.advanceScore();

        if (player.getScore() == 40 && opponent.getScore() == 40) {
            context.setState(new DeuceState());
        } else if (player.getScore() > 40) {
            context.isFinish=true;
            context.setState(new GameWonState(player));
        }
    }

    @Override
    public String getScore(TennisGameContext context) {
        return String.format("Player %s: %d/Player %s: %d",
                context.getPlayerA().getName(), context.getPlayerA().getScore(),
                context.getPlayerB().getName(), context.getPlayerB().getScore());
    }
}