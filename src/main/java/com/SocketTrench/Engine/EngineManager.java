package com.SocketTrench.Engine;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class EngineManager {
    private static final int FPS = 30;
    private final List<GameObject> gameObjects;
    private EngineRenderDispatcher matchRenderDispatcher;

    public EngineManager(
            final List<GameObject> gameObjects,
            final EngineRenderDispatcher matchRenderDispatcher) {
        this.gameObjects = gameObjects;
        this.matchRenderDispatcher = matchRenderDispatcher;

    }

    public final void onInit() {
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        final Runnable task = () -> {
            this.onUpdate();
        };
        scheduler.scheduleAtFixedRate(task, 0, 1000 / FPS, TimeUnit.MILLISECONDS);
    }

    public final void onUpdate() {
        for (final var gameObject : this.gameObjects) {
            EnginePhysics.apply(gameObject);
            gameObject.onUpdate();
        }
        matchRenderDispatcher.dispatch("RENDER");
    }
}
