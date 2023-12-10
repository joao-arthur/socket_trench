package com.SocketTrench.MatchScene;

import com.SocketTrench.ImageLoader;
import com.SocketTrench.App.Screen;
import com.SocketTrench.Engine.DefaultGameObject;
import com.SocketTrench.Engine.Struct.BoxDim;
import com.SocketTrench.Engine.Struct.BoxPos;
import com.SocketTrench.Engine.Struct.Point;

final class Player2GameObject extends DefaultGameObject {
    public Player2GameObject() {
        this.body = new BoxDim(Screen.WIDTH - 58, Screen.HEIGHT - 100 - 30, 58, 30);
        this.texture = ImageLoader.fromPath("player2.png");
        this.force = new Point(0, 0);
        this.bounds = new BoxPos(Screen.WIDTH - 158, 0, Screen.WIDTH, Screen.HEIGHT);
    }
}
