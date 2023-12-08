package com.SocketTrench.GameObject;

import java.awt.Image;

import com.SocketTrench.ImageLoader;

public final class Player1GameObject {
    public Sprite sprite;
    public Force force;
    public Image texture;

    public Player1GameObject() {
        this.sprite = new Sprite(58, 30, 0, 100);
        this.force = new Force(0, 0);
        this.texture = ImageLoader.fromPath("player1.png");
    }

    public boolean onUpdate() {
        this.sprite.x += this.force.speedX;
        this.sprite.y += this.force.speedY;

        return this.force.speedX != 0 || this.force.speedY != 0;
    }
}
