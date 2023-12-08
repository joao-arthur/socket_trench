package com.SocketTrench.Match;

import com.SocketTrench.Events.Observer;

public class MatchKeyPressedObserver implements Observer<Integer> {
    private final MatchModel matchModel;

    public MatchKeyPressedObserver(final MatchModel matchModel) {
        this.matchModel = matchModel;
    }

    @Override
    public final void handle(final Integer event) {
        // matchModel.player1.onKeyPressed(event);
    }
}
