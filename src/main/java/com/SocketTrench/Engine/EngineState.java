package com.SocketTrench.Engine;

import java.util.List;

public class EngineState {
    private final List<GameObject> gameObjects;

    public EngineState(
            final List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    public void create(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public void destroy(GameObject gameObject) {
        gameObjects.remove(gameObject);
    }
}
