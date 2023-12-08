package com.SocketTrench.Match;

import com.SocketTrench.Events.Observer;

public class MatchKeyReleasedObserver implements Observer<Integer> {
    private final MatchModel matchModel;

    public MatchKeyReleasedObserver(MatchModel matchModel) {
        this.matchModel = matchModel;
    }

    @Override
    public void handle(Integer event) {
        // matchModel.player1.onKeyReleased(event);
    }
}
