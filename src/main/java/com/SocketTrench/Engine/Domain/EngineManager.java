package com.SocketTrench.Engine.Domain;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.SocketTrench.App.Screen;
import com.SocketTrench.Engine.EngineRenderDispatcher;
import com.SocketTrench.Engine.GameObject;

public final class EngineManager {
    private static final int FPS = 30;
    private final List<GameObject> gameObjects;
    private final EngineState engineState;
    private final EngineRenderDispatcher dispatcher;

    public EngineManager(
        final List<GameObject> gameObjects,
        final EngineState engineState,
        final EngineRenderDispatcher dispatcher
    ) {
        this.gameObjects = gameObjects;
        this.engineState = engineState;
        this.dispatcher = dispatcher;
    }

    public final void onInit() {
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        final Runnable task = () -> {
            this.onUpdate();
        };
        scheduler.scheduleAtFixedRate(task, 0, 1000 / FPS, TimeUnit.MILLISECONDS);
    }

    public final void onUpdate() {
        this.engineState.apply();
        for (final var gameObject : this.gameObjects) {
            EnginePhysics.apply(gameObject);
            final var body = gameObject.getBody();
            if (body != null) {
                if (
                    body.x + body.w < 0 ||
                    body.y + body.h < 0 ||
                    body.x > Screen.WIDTH ||
                    body.y > Screen.HEIGHT
                ) {
                    gameObject.onLeaveScreen(this.engineState);
                }
            }
        }
        dispatcher.dispatch("RENDER");
    }
}
