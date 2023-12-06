package com.SocketTrench.GameObject;

import java.awt.Image;

import com.SocketTrench.ImageLoader;

public final class ShootGameObject {
    private Sprite sprite;
    private Image texture;

    public ShootGameObject() {
        this.sprite = new Sprite(58, 30, 0, 0);
        this.texture = ImageLoader.fromPath("shoot.png");
    }
}
