package com.SocketTrench.GameObject;

import java.awt.Image;

import com.SocketTrench.ImageLoader;

public final class Player1GameObject {
    private Sprite sprite;
    private Image texture;

    public Player1GameObject() {
        this.sprite = new Sprite(58, 30, 0, 0);
        this.texture = ImageLoader.fromPath("player1.png");
    }
}
