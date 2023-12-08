package com.SocketTrench.Match;

import com.SocketTrench.GUI.GUI;
import com.SocketTrench.Screens.MatchPanel;
import com.SocketTrench.Screens.MatchScreen;

public final class MatchCreator {
    public static void create() {
        final var matchModel = new MatchModel();
        final var matchKeyPressedDispatcher = new MatchKeyPressedDispatcher();
        final var matchKeyReleasedDispatcher = new MatchKeyReleasedDispatcher();

        final var matchKeyPressedObserver = new MatchKeyPressedObserver(matchModel);
        matchKeyPressedDispatcher.register(matchKeyPressedObserver);
        final var matchKeyReleasedObserver = new MatchKeyReleasedObserver(matchModel);
        matchKeyReleasedDispatcher.register(matchKeyReleasedObserver);

        final var matchRenderDispatcher = new MatchRenderDispatcher();

        final var matchManager = new MatchManager(matchModel, matchRenderDispatcher);

        final var matchPanel = new MatchPanel(matchModel);
        matchRenderDispatcher.register(matchPanel);

        final var matchScreen = new MatchScreen(matchPanel, matchKeyPressedDispatcher, matchKeyReleasedDispatcher);

        GUI.getInstance().goTo(matchScreen);
        matchManager.init();
    }
}
