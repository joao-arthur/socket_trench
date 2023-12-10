package com.SocketTrench.App.YouWon;

import com.SocketTrench.GUI.GUI;

final class YouWonService {
    public final void goToInitial() {
        final var service = new InitialService();
        final var screen = new InitialScreen(service);
        GUI.getInstance().goTo(screen);
    }
}
