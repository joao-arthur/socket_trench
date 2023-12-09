package com.SocketTrench.Engine;

import java.util.List;

public final class EngineState {
    private final List<GameObject> gameObjects;

    public EngineState(final List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    public final void create(final GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public final void destroy(final GameObject gameObject) {
        gameObjects.remove(gameObject);
    }
}
