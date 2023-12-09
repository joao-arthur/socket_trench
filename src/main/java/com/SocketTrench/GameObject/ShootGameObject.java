package com.SocketTrench.GameObject;

import java.awt.Image;

import com.SocketTrench.ImageLoader;
import com.SocketTrench.Engine.Body;
import com.SocketTrench.Engine.Force;

public final class ShootGameObject {
    public final Body body;
    public final Force force;
    public final Image texture;

    public ShootGameObject(
        int x,
        int y,
        int speedX
    ) {
        this.body = new Body(58, 30, x, y);
        this.force = new Force(speedX, 0);
        this.texture = ImageLoader.fromPath("shoot.png");
    }

    public final void onUpdate() {
        this.body.x += this.force.x;
        this.body.y += this.force.y;
    }
}
