package com.SocketTrench.GameObject;

import java.awt.Image;

import com.SocketTrench.ImageLoader;
import com.SocketTrench.Screens.Screen;

public final class Player2GameObject {
    public Sprite sprite;
    public Image texture;

    public Player2GameObject() {
        this.sprite = new Sprite(58, 30, Screen.WIDTH - 58, Screen.HEIGHT - 30);
        this.texture = ImageLoader.fromPath("player2.png");
    }
}
