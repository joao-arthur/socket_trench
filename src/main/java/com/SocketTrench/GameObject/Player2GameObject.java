package com.SocketTrench.GameObject;

import java.awt.Image;

import com.SocketTrench.ImageLoader;
import com.SocketTrench.Engine.Body;
import com.SocketTrench.Screens.Screen;

public final class Player2GameObject {
    public final Body body;
    public final Image texture;

    public Player2GameObject() {
        this.body = new Body(58, 30, Screen.WIDTH - 58, Screen.HEIGHT - 100 - 30);
        this.texture = ImageLoader.fromPath("player2.png");
    }
}
