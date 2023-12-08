package com.SocketTrench;

import com.SocketTrench.GUI.GUI;
import com.SocketTrench.Screens.InitialScreen;

public final class SocketTrench {
    public static final void main(final String args[]) {
        final var gui = GUI.getInstance();
        gui.goTo(new InitialScreen());
    }
}
