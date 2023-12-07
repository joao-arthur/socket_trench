package com.SocketTrench;

import com.SocketTrench.GUI.GUI;
import com.SocketTrench.Screens.InitialScreen;
import com.SocketTrench.Screens.ServerAwaitScreen;

public final class SocketTrench {
    public static void main(String args[]) {
        final var gui = GUI.getInstance();
        gui.goTo(new ServerAwaitScreen());
    }
}
