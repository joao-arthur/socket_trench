package com.SocketTrench.App.IdleClient;

import com.SocketTrench.GUI.GUI;

public final class IdleClientBuilder {
    public final void build() {
        final var service = new IdleClientService();
        final var screen = new IdleClientScreen(service);
        GUI.getInstance().goTo(screen);
    }
}
