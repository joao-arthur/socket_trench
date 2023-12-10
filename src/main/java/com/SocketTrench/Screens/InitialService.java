package com.SocketTrench.Screens;

import com.SocketTrench.GUI.GUI;

public final class InitialService {
    public final void goToIdleServer() {
        final var service = new IdleServerService();
        final var screen = new IdleServerScreen(service);
        GUI.getInstance().goTo(screen);
    }

    public final void goToIdleClient() {
        final var service = new IdleClientService();
        final var screen = new IdleClientScreen(service);
        GUI.getInstance().goTo(screen);
    }
}
