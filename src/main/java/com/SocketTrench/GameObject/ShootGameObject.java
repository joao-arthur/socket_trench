package com.SocketTrench.GameObject;

import java.awt.Image;

import com.SocketTrench.ImageLoader;

public final class ShootGameObject {
    public Sprite sprite;
    public Force force;
    public Image texture;

    public ShootGameObject(
        int x,
        int y,
        int speedX
    ) {
        this.sprite = new Sprite(58, 30, x, y);
        this.force = new Force(speedX, 0);
        this.texture = ImageLoader.fromPath("shoot.png");
    }

    public final boolean onUpdate() {
        this.sprite.x += this.force.speedX;
        this.sprite.y += this.force.speedY;

        return this.force.speedX != 0 || this.force.speedY != 0;
    }
}
