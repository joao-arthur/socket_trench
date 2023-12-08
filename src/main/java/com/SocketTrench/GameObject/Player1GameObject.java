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
        final var oldX = this.sprite.x;
        final var oldY = this.sprite.y;

        var newX = this.sprite.x + this.force.speedX;
        var newY = this.sprite.y + this.force.speedY;

        if (newX < 0) {
            newX = 0;
        }
        if (newX > 200 - this.sprite.width) {
            newX = 200 - this.sprite.width;
        }
        if (newY < 0) {
            newY = 0;
        }
        if (newY > 500 - this.sprite.height) {
            newY = 500 - this.sprite.height;
        }

        this.sprite.x = newX;
        this.sprite.y = newY;

        return this.sprite.x != oldX && this.sprite.y != oldY;
    }
}
