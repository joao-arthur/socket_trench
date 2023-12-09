package com.SocketTrench.MatchScene;

import com.SocketTrench.ImageLoader;
import com.SocketTrench.Engine.BoxDim;
import com.SocketTrench.Engine.Force;
import com.SocketTrench.Engine.BoxPos;
import com.SocketTrench.Engine.DefaultGameObject;
import com.SocketTrench.Screens.Screen;

final class Player2GameObject extends DefaultGameObject {
    public Player2GameObject() {
        this.body = new BoxDim(Screen.WIDTH - 58, Screen.HEIGHT - 100 - 30, 58, 30);
        this.texture = ImageLoader.fromPath("player2.png");
        this.force = new Force(0, 0);
        this.bounds = new BoxPos(Screen.WIDTH - 158, 0, Screen.WIDTH, Screen.HEIGHT);
    }
}
