package com.SocketTrench.GUI;

public final class GUI {
    private static GUI instance;
    private GUIScreen currentScreen;

    public static GUI getInstance() {
        if (instance == null)
            instance = new GUI();
        return instance;
    }

    public void goTo(GUIScreen newScreen) {
        disposeCurrentScreen();
        currentScreen = newScreen;
    }

    private void disposeCurrentScreen() {
        if (currentScreen != null) {
            currentScreen.dispose();
            currentScreen = null;
        }
    }
}
