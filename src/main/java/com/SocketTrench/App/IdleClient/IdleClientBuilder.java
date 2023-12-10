package com.SocketTrench.App.IdleClient;

import com.SocketTrench.Domain.IdleClient.IdleClientDispatcher;
import com.SocketTrench.GUI.GUI;

public final class IdleClientBuilder {
    public final void build() {
        final var dispatcher = new IdleClientDispatcher();
        final var service = new IdleClientService(dispatcher);
        final var screen = new IdleClientScreen(service);
        GUI.getInstance().goTo(screen);
    }
}
