package com.SocketTrench.App.GameOver;

import com.SocketTrench.GUI.GUI;

final class GameOverService {
    public final void goToInitial() {
        final var service = new InitialService();
        final var screen = new InitialScreen(service);
        GUI.getInstance().goTo(screen);
    }
}
