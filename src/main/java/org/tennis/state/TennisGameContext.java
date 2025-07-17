package org.tennis.state;

import org.tennis.model.Player;

public class TennisGameContext {
    private GameState currentState;
    private final Player playerA;
    private final Player playerB;
    public boolean isFinish;
    public TennisGameContext(String nameA, String nameB) {
        this.playerA = new Player(nameA);
        this.playerB = new Player(nameB);
        this.currentState = new NormalState();
        this.isFinish=false;
    }

    public void setState(GameState state) {
        this.currentState = state;
    }

    public void pointWonBy(String playerName) {
        Player scorer = playerName.equals(playerA.getName()) ? playerA : playerB;
        currentState.pointWonBy(scorer, this);
    }

    public String getScore() {
        return currentState.getScore(this);
    }

    public Player getPlayerA() {
        return playerA;
    }

    public Player getPlayerB() {
        return playerB;
    }

    public Boolean getIsFinishFlag() {
        return isFinish;
    }
}