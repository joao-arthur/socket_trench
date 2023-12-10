package com.SocketTrench;

import com.SocketTrench.GUI.GUI;
import com.SocketTrench.Screens.InitialScreen;
import com.SocketTrench.Screens.InitialService;

final class SocketTrench {
    public static final void main(final String args[]) {
        final var service = new InitialService();
        final var screen = new InitialScreen(service);
        GUI.getInstance().goTo(screen);
    }
}
