package com.SocketTrench.GameObject;

import java.awt.Image;

import com.SocketTrench.ImageLoader;

public final class Player2GameObject {
    private Sprite sprite;
    private Image texture;

    public Player2GameObject() {
        this.sprite = new Sprite(58, 30, 0, 100);
        this.texture = ImageLoader.fromPath("player2.png");
    }
}
