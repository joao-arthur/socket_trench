package com.SocketTrench.MatchScene;

import java.awt.Image;

import com.SocketTrench.ImageLoader;
import com.SocketTrench.Engine.Body;
import com.SocketTrench.Engine.Force;
import com.SocketTrench.Engine.GameObject;
import com.SocketTrench.Engine.Square;

public final class ShootGameObject implements GameObject {
    public final Body body;
    public final Image texture;
    public final Force force;

    public ShootGameObject(
            final int x,
            final int y,
            final int speedX) {
        this.body = new Body(58, 30, x, y);
        this.force = new Force(speedX, 0);
        this.texture = ImageLoader.fromPath("shoot.png");
    }

    @Override
    public final Body getBody() {
        return this.body;
    }

    @Override
    public final Image getTexture() {
        return this.texture;
    }

    @Override
    public final Force getForce() {
        return this.force;
    }

    @Override
    public final Square getBounds() {
        return null;
    }

    @Override
    public final void onUpdate() {
    }

    @Override
    public final void onKeyPressed(final int keyCode) {

    }

    @Override
    public final void onKeyReleased(final int keyCode) {

    }
}
