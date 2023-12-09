package com.SocketTrench.Match;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class MatchManager {
    private static final int FPS = 30;
    private MatchModel matchModel;
    private MatchRenderDispatcher matchRenderDispatcher;

    public MatchManager(
        final MatchModel matchModel,
        final MatchRenderDispatcher matchRenderDispatcher
    ) {
        this.matchModel = matchModel;
        this.matchRenderDispatcher = matchRenderDispatcher;
    }

    public final void init() {
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        final Runnable task = () -> {
            this.onUpdate();
        };
        scheduler.scheduleAtFixedRate(task, 0, 1000 / FPS, TimeUnit.MILLISECONDS);
    }

    public final void onUpdate() {
        this.matchModel.player1.onUpdate();
        for (final var shoot : this.matchModel.player1Shoots) {
            shoot.onUpdate();
        }
        for (final var shoot : this.matchModel.player2Shoots) {
            shoot.onUpdate();
        }
            matchRenderDispatcher.dispatch("RENDER");
    }
}
