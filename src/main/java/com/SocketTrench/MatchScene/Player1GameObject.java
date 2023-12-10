package com.SocketTrench.MatchScene;

import com.SocketTrench.ImageLoader;
import com.SocketTrench.App.Screen;
import com.SocketTrench.Engine.DefaultGameObject;
import com.SocketTrench.Engine.Struct.BoxDim;
import com.SocketTrench.Engine.Struct.BoxPos;
import com.SocketTrench.Engine.Struct.Point;

final class Player1GameObject extends DefaultGameObject {
    public Player1GameObject(final boolean canInput) {
        this.body = new BoxDim(0, 100, 58, 30);
        this.texture = ImageLoader.fromPath("player1.png");
        this.force = new Point(0, 0);
        this.bounds = new BoxPos(0, 0, 158, Screen.HEIGHT);
        this.inputHandler = canInput ? new Player1InputHandler(this) : null;
    }
}
