package com.SocketTrench.GameObject;

import java.awt.Image;

import com.SocketTrench.ImageLoader;
import com.SocketTrench.Engine.Body;
import com.SocketTrench.Screens.Screen;

public final class BackgroundGameObject {
    public final Body body;
    public final Image texture;

    public BackgroundGameObject() {
        this.body = new Body(Screen.WIDTH, Screen.HEIGHT, 0, 0);
        this.texture = ImageLoader.fromPath("background.png");
    }
}
