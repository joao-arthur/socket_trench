package com.SocketTrench.Match;

public class MatchService {
    private final MatchModel matchModel;
    private final MatchKeyDispatcher matchKeyDispatcher;

    public MatchService(
            MatchModel matchModel,
            MatchKeyDispatcher matchKeyDispatcher) {
        this.matchModel = matchModel;
        this.matchKeyDispatcher = matchKeyDispatcher;
    }

    public MatchModel getModel() {
        return this.matchModel;
    }

    public void onKeyPressed(int charCode) {
        matchKeyDispatcher.dispatch(String.valueOf(charCode));
    }

    public void onKeyReleased(int charCode) {
        matchKeyDispatcher.dispatch(String.valueOf(charCode));
    }
}
