package com.SocketTrench.Sprite;

import java.awt.Image;

import com.SocketTrench.ImageLoader;

public final class Player1Sprite {
    private Sprite sprite;
    private Image texture;

    public Player1Sprite() {
        this.sprite = new Sprite(58, 30, 0, 0);
        this.texture = ImageLoader.fromPath("player1.png");
    }
}