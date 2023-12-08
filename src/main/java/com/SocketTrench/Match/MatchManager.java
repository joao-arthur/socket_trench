package com.SocketTrench.Match;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class MatchManager {
    private static final int FPS = 30;
    private MatchModel matchModel;
    private MatchRenderDispatcher matchRenderDispatcher;

    public MatchManager(MatchModel matchModel, MatchRenderDispatcher matchRenderDispatcher) {
        this.matchModel = matchModel;
        this.matchRenderDispatcher = matchRenderDispatcher;
    }

    public void init() {
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        final Runnable task = () -> {
            this.onUpdate();
        };
        scheduler.scheduleAtFixedRate(task, 0, 1000 / FPS, TimeUnit.MILLISECONDS);
    }

    public void onUpdate() {
        final var hasChanged = this.matchModel.player1.onUpdate();
        if (hasChanged) {
            matchRenderDispatcher.dispatch("RENDER");
        }
    }
}
