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
        final var player1HasChanged = this.matchModel.player1.onUpdate();
        for (var shoot : this.matchModel.player1Shoots) {
            shoot.onUpdate();
        }
        for (var shoot : this.matchModel.player2Shoots) {
            shoot.onUpdate();
        }
        final var shootsPlayer1HasChanged = this.matchModel.player1Shoots.size() > 0;
        final var shootsPlayer2HasChanged = this.matchModel.player2Shoots.size() > 0;
        final var hasChanged = player1HasChanged ||
                shootsPlayer1HasChanged ||
                shootsPlayer2HasChanged;
        if (hasChanged) {
            matchRenderDispatcher.dispatch("RENDER");
        }
    }
}
