package com.SocketTrench.App.Match;

import java.util.LinkedList;

import com.SocketTrench.Domain.IdleClient.IdleClientSocketManager;
import com.SocketTrench.Domain.Match.MatchDispatcher;
import com.SocketTrench.Domain.Match.MatchSocketManager;
import com.SocketTrench.Engine.EngineRenderDispatcher;
import com.SocketTrench.Engine.GameObject;
import com.SocketTrench.Engine.Domain.EngineKeyAdapter;
import com.SocketTrench.Engine.Domain.EngineManager;
import com.SocketTrench.Engine.Domain.EngineRenderer;
import com.SocketTrench.Engine.Domain.EngineState;
import com.SocketTrench.GUI.GUI;
import com.SocketTrench.MatchScene.BackgroundGameObject;
import com.SocketTrench.MatchScene.MatchGameObject;
import com.SocketTrench.MatchScene.Player1GameObject;
import com.SocketTrench.MatchScene.Player2GameObject;
import com.SocketTrench.Socket.SocketClient;
import com.SocketTrench.Socket.SocketInstance;

public final class MatchBuilder {
    public final void buildPlayer1() {
        final var gameObjects = new LinkedList<GameObject>();
        final var player = new Player1GameObject(true);
        final var opponent = new Player2GameObject(false);
        gameObjects.add(new BackgroundGameObject());
        gameObjects.add(player);
        gameObjects.add(opponent);
        gameObjects.add(new MatchGameObject(new MatchInputHandler()));

        final var matchDispatcher = new MatchDispatcher();
        final var engineRenderDispatcher = new EngineRenderDispatcher();

        SocketInstance
                .getInstance()
                .getConnection()
                .setManager(new MatchSocketManager(matchDispatcher));

        final var engineState = new EngineState(gameObjects);
        final var renderer = new EngineRenderer(gameObjects);
        final var manager = new EngineManager(gameObjects, engineState, engineRenderDispatcher);
        final var keyListener = new EngineKeyAdapter(gameObjects, engineState);
        final var panel = new MatchPanel(renderer);
        final var screen = new MatchScreen(panel, keyListener);

        final var matchRenderObserver = new MatchRenderObserver(player, opponent);
        final var matchObserver = new MatchObserver(engineState, opponent);

        engineRenderDispatcher.register(panel);
        engineRenderDispatcher.register(matchRenderObserver);
        matchDispatcher.register(matchObserver);

        GUI.getInstance().goTo(screen);
        manager.onInit();
    }

    public final void buildPlayer2() {
        final var gameObjects = new LinkedList<GameObject>();
        final var player = new Player2GameObject(true);
        final var opponent = new Player1GameObject(false);
        gameObjects.add(new BackgroundGameObject());
        gameObjects.add(player);
        gameObjects.add(opponent);
        gameObjects.add(new MatchGameObject(new MatchInputHandler()));

        final var matchDispatcher = new MatchDispatcher();
        final var engineRenderDispatcher = new EngineRenderDispatcher();

        SocketInstance
                .getInstance()
                .getConnection()
                .setManager(new MatchSocketManager(matchDispatcher));

        final var engineState = new EngineState(gameObjects);
        final var renderer = new EngineRenderer(gameObjects);
        final var manager = new EngineManager(gameObjects, engineState, engineRenderDispatcher);
        final var keyListener = new EngineKeyAdapter(gameObjects, engineState);
        final var panel = new MatchPanel(renderer);
        final var screen = new MatchScreen(panel, keyListener);

        final var matchRenderObserver = new MatchRenderObserver(player, opponent);
        final var matchObserver = new MatchObserver(engineState, opponent);

        engineRenderDispatcher.register(panel);
        engineRenderDispatcher.register(matchRenderObserver);
        matchDispatcher.register(matchObserver);

        GUI.getInstance().goTo(screen);
        manager.onInit();
    }
}
