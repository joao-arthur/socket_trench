package com.SocketTrench.MatchScene;

import java.awt.Image;

import com.SocketTrench.ImageLoader;
import com.SocketTrench.Engine.BoxDim;
import com.SocketTrench.Engine.Force;
import com.SocketTrench.Engine.GameObject;
import com.SocketTrench.Engine.BoxPos;

final class ShootGameObject implements GameObject {
    public final BoxDim body;
    public final Image texture;
    public final Force force;

    public ShootGameObject(
        final int x,
        final int y,
        final int speedX
    ) {
        this.body = new BoxDim(x, y, 58, 30);
        this.force = new Force(speedX, 0);
        this.texture = ImageLoader.fromPath("shoot.png");
    }

    @Override
    public final BoxDim getBody() {
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
    public final BoxPos getBounds() {
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
