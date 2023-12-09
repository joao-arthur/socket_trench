package com.SocketTrench.Engine;

import java.util.LinkedList;
import java.util.List;

public final class EngineState {
    private final List<GameObject> gameObjects;
    private final List<GameObject> createList;
    private final List<GameObject> deleteList;

    public EngineState(final List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
        this.createList = new LinkedList<GameObject>();
        this.deleteList = new LinkedList<GameObject>();
    }

    public final void create(final GameObject gameObject) {
        createList.add(gameObject);
    }

    public final void destroy(final GameObject gameObject) {
        deleteList.add(gameObject);
    }

    public final void apply() {
        for (final var gameObject : this.deleteList) {
            this.gameObjects.remove(gameObject);
        }
        this.deleteList.clear();
        for (final var gameObject : this.createList) {
            this.gameObjects.add(gameObject);
        }
        this.createList.clear();
    }
}
