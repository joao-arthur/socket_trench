package com.SocketTrench.GameObject;

import java.awt.Image;

import com.SocketTrench.ImageLoader;

public final class Player1GameObject {
    public Sprite sprite;
    public Image texture;

    public Player1GameObject() {
        this.sprite = new Sprite(58, 30, 0, 100);
        this.texture = ImageLoader.fromPath("player1.png");
    }
}
