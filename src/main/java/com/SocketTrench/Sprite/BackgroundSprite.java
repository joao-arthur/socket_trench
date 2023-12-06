package com.SocketTrench.Sprite;

import java.awt.Image;

import com.SocketTrench.ImageLoader;
import com.SocketTrench.Screens.Screen;

public final class BackgroundSprite {
    private Sprite sprite;
    private Image texture;

    public BackgroundSprite() {
        this.sprite = new Sprite(Screen.WIDTH, Screen.HEIGHT, 0, 0);
        this.texture = ImageLoader.fromPath("background.png");
    }
}
