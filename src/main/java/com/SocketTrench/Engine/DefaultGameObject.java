package com.SocketTrench.Engine;

import java.awt.Image;

public abstract class DefaultGameObject implements GameObject {
    public BoxDim body = null;
    public Image texture = null;
    public Point force = null;
    public BoxPos bounds = null;

    @Override
    public final BoxDim getBody() {
        return this.body;
    }

    @Override
    public final Image getTexture() {
        return this.texture;
    }

    @Override
    public final Point getForce() {
        return this.force;
    }

    @Override
    public final BoxPos getBounds() {
        return this.bounds;
    }

    @Override
    public void onUpdate() {

    }

    @Override
    public void onKeyPressed(final int keyCode) {

    }

    @Override
    public void onKeyReleased(final int keyCode) {

    }
}
