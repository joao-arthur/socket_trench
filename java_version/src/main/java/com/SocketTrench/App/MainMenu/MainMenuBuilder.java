package com.SocketTrench.App.MainMenu;

import com.SocketTrench.GUI.GUI;

public final class MainMenuBuilder {
    public final void build() {
        final var service = new MainMenuService();
        final var screen = new MainMenuScreen(service);
        GUI.getInstance().goTo(screen);
    }
}
