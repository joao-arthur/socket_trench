package com.SocketTrench.App.YouWon;

import com.SocketTrench.GUI.GUI;

public class YouWonBuilder {
    public final void build() {
        final var service = new YouWonService();
        final var screen = new YouWonScreen(service);
        GUI.getInstance().goTo(screen);
    }
}
