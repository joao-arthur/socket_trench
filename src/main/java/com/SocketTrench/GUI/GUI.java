package com.SocketTrench.GUI;

public final class GUI {
    private static GUI instance;
    private GUIScreen currentScreen;

    public static GUI getInstance() {
        if (GUI.instance == null) {
            GUI.instance = new GUI();
        }
        return GUI.instance;
    }

    public final void goTo(final GUIScreen newScreen) {
        disposeCurrentScreen();
        this.currentScreen = newScreen;
    }

    private final void disposeCurrentScreen() {
        if (this.currentScreen != null) {
            this.currentScreen.dispose();
            this.currentScreen = null;
        }
    }
}
