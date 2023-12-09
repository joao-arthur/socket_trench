package com.SocketTrench.GameObject;

import java.awt.Image;

import com.SocketTrench.ImageLoader;
import com.SocketTrench.Engine.Body;
import com.SocketTrench.Engine.Force;

public final class Player1GameObject {
    public final Body body;
    public final Force force;
    public final Image texture;

    public Player1GameObject() {
        this.body = new Body(58, 30, 0, 100);
        this.force = new Force(0, 0);
        this.texture = ImageLoader.fromPath("player1.png");
    }

    public final void onUpdate() {
        final var oldX = this.body.x;
        final var oldY = this.body.y;

        var newX = this.body.x + this.force.x;
        var newY = this.body.y + this.force.y;

        if (newX < 0) {
            newX = 0;
        }
        if (newX > 160 - this.body.width) {
            newX = 160 - this.body.width;
        }
        if (newY < 0) {
            newY = 0;
        }
        if (newY > 500 - this.body.height) {
            newY = 500 - this.body.height;
        }

        this.body.x = newX;
        this.body.y = newY;
    }
}
