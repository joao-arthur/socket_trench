package com.SocketTrench.App.GameOver;

import com.SocketTrench.GUI.GUI;

public final class GameOverBuilder {
    public final void build() {
        final var service = new GameOverService();
        final var screen = new GameOverScreen(service);
        GUI.getInstance().goTo(screen);
    }
}
