package com.SocketTrench.App.IdleServer;

import com.SocketTrench.GUI.GUI;

public final class IdleServerBuilder {
    public final void build() {
        final var service = new IdleServerService();
        final var screen = new IdleServerScreen(service);
        GUI.getInstance().goTo(screen);
    }
}
