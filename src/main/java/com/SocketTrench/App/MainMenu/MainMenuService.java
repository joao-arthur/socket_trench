package com.SocketTrench.App.MainMenu;

import com.SocketTrench.GUI.GUI;

final class InitialService {
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
