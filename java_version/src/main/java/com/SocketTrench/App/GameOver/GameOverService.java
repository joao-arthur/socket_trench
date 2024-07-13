package com.SocketTrench.App.GameOver;

import com.SocketTrench.App.MainMenu.MainMenuBuilder;

final class GameOverService {
    public final void goToMainMenu() {
        new MainMenuBuilder().build();
    }
}
