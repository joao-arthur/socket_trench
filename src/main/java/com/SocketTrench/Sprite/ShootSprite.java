package com.SocketTrench.Sprite;

import java.awt.Image;

import com.SocketTrench.ImageLoader;

public final class ShootSprite {
    private Sprite sprite;
    private Image texture;

    public ShootSprite() {
        this.sprite = new Sprite(58, 30, 0, 0);
        this.texture = ImageLoader.fromPath("shoot.png");
    }
}
