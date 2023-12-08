package com.SocketTrench.Match;

public class MatchCreator {
    public void create() {
        final var matchModel = new MatchModel();
        final var matchKeyDispatcher = new MatchKeyDispatcher();
        final var matchRenderDispatcher = new MatchRenderDispatcher();
        final var matchManager = new MatchManager(matchModel, matchRenderDispatcher);

        // new matchscreen();
        // new matchpanel();
        // new matchkeydispatcher();
        // new matchrenderdispatcher();
    }
}
