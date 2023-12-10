package com.SocketTrench.App.Match;

import com.SocketTrench.Engine.EngineKeyAdapter;
import com.SocketTrench.Engine.EngineManager;
import com.SocketTrench.Engine.EngineRenderDispatcher;
import com.SocketTrench.Engine.EngineRenderer;
import com.SocketTrench.Engine.EngineState;
import com.SocketTrench.GUI.GUI;
import com.SocketTrench.MatchScene.SceneCreator;

public final class MatchBuilder {
    public final void buildPlayer1() {
        final var gameObjects = SceneCreator.createMatchPlayer1();
        final var dispatcher = new EngineRenderDispatcher();
        final var state = new EngineState(gameObjects);
        final var renderer = new EngineRenderer(gameObjects);
        final var manager = new EngineManager(gameObjects, state, dispatcher);
        final var keyListener = new EngineKeyAdapter(gameObjects, state);
        final var panel = new MatchPanel(renderer);
        final var screen = new MatchScreen(panel, keyListener);
        dispatcher.register(panel);
        GUI.getInstance().goTo(screen);
    }

    public final void buildPlayer2() {
        final var gameObjects = SceneCreator.createMatchPlayer2();
        final var dispatcher = new EngineRenderDispatcher();
        final var state = new EngineState(gameObjects);
        final var renderer = new EngineRenderer(gameObjects);
        final var manager = new EngineManager(gameObjects, state, dispatcher);
        final var keyListener = new EngineKeyAdapter(gameObjects, state);
        final var panel = new MatchPanel(renderer);
        final var screen = new MatchScreen(panel, keyListener);
        dispatcher.register(panel);
        GUI.getInstance().goTo(screen);
    }
}
