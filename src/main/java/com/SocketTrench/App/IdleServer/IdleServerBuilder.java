package com.SocketTrench.App.IdleServer;

import com.SocketTrench.Domain.IdleServer.IdleServerDispatcher;
import com.SocketTrench.GUI.GUI;

public final class IdleServerBuilder {
    public final void build() {
        final var dispatcher = new IdleServerDispatcher();
        final var service = new IdleServerService(dispatcher);
        final var screen = new IdleServerScreen(service);
        GUI.getInstance().goTo(screen);
    }
}
