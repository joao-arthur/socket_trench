package com.SocketTrench.Engine;

import java.awt.Image;

import com.SocketTrench.Engine.Struct.BoxDim;
import com.SocketTrench.Engine.Struct.BoxPos;
import com.SocketTrench.Engine.Struct.Point;

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
    public void onKeyPressed(final int keyCode, final EngineState engineState) {

    }

    @Override
    public void onKeyReleased(final int keyCode) {

    }
}
