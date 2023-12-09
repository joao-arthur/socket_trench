package com.SocketTrench.Match;

import java.util.ArrayList;

import com.SocketTrench.Engine.EngineManager;
import com.SocketTrench.Engine.EngineRenderer;
import com.SocketTrench.Engine.EngineState;
import com.SocketTrench.Engine.GameObject;
import com.SocketTrench.GUI.GUI;
import com.SocketTrench.Scenes.Match.BackgroundGameObject;
import com.SocketTrench.Scenes.Match.Player1GameObject;
import com.SocketTrench.Scenes.Match.Player2GameObject;
import com.SocketTrench.Screens.MatchPanel;
import com.SocketTrench.Screens.MatchScreen;

public final class MatchCreator {
    public static void create() {
        final var gameObjects = new ArrayList<GameObject>();
        gameObjects.add(new BackgroundGameObject());
        gameObjects.add(new Player1GameObject());
        gameObjects.add(new Player2GameObject());

        final var engineState = new EngineState(gameObjects);
        final var engineRenderer = new EngineRenderer(gameObjects);
        final var engineManager = new EngineManager(gameObjects);

        final var matchModel = new MatchModel();
        final var matchKeyPressedDispatcher = new MatchKeyPressedDispatcher();
        final var matchKeyReleasedDispatcher = new MatchKeyReleasedDispatcher();
        final var matchKeyPressedObserver = new MatchKeyPressedObserver(matchModel);
        matchKeyPressedDispatcher.register(matchKeyPressedObserver);
        final var matchKeyReleasedObserver = new MatchKeyReleasedObserver(matchModel);
        matchKeyReleasedDispatcher.register(matchKeyReleasedObserver);
        final var matchKeyAdapter = new MatchKeyAdapter(matchModel);
        final var matchRenderDispatcher = new MatchRenderDispatcher();
        final var matchManager = new MatchManager(matchModel, matchRenderDispatcher);
        final var matchPanel = new MatchPanel(matchModel);
        matchRenderDispatcher.register(matchPanel);
        final var matchScreen = new MatchScreen(matchPanel, matchKeyAdapter);
        GUI.getInstance().goTo(matchScreen);
        matchManager.init();
    }
}
