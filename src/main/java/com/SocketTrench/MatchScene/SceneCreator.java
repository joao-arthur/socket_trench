package com.SocketTrench.MatchScene;

import java.util.LinkedList;

import com.SocketTrench.Engine.EngineKeyAdapter;
import com.SocketTrench.Engine.EngineManager;
import com.SocketTrench.Engine.EngineRenderDispatcher;
import com.SocketTrench.Engine.EngineRenderer;
import com.SocketTrench.Engine.EngineState;
import com.SocketTrench.Engine.GameObject;
import com.SocketTrench.App.Match.MatchBuilder;

public final class SceneCreator {
    public static void create() {
        final var gameObjects = new LinkedList<GameObject>();
        gameObjects.add(new BackgroundGameObject());
        gameObjects.add(new Player1GameObject());
        gameObjects.add(new Player2GameObject());
        final var dispatcher = new EngineRenderDispatcher();
        final var state = new EngineState(gameObjects);
        final var renderer = new EngineRenderer(gameObjects);
        final var manager = new EngineManager(gameObjects, state, dispatcher);
        final var keyAdapter = new EngineKeyAdapter(gameObjects, state);
        new MatchBuilder().build(dispatcher, renderer, keyAdapter);
        manager.onInit();
    }
}
