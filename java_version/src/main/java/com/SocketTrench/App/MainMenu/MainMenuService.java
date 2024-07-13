package com.SocketTrench.App.MainMenu;

import com.SocketTrench.App.IdleClient.IdleClientBuilder;
import com.SocketTrench.App.IdleServer.IdleServerBuilder;

final class MainMenuService {
    public final void goToIdleServer() {
        new IdleServerBuilder().build();
    }

    public final void goToIdleClient() {
        new IdleClientBuilder().build();
    }
}
