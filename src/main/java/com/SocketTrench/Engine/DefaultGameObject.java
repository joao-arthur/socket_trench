package com.SocketTrench.Engine;

import java.awt.Image;

import com.SocketTrench.Engine.Domain.EngineState;
import com.SocketTrench.Engine.Struct.BoxDim;
import com.SocketTrench.Engine.Struct.BoxPos;
import com.SocketTrench.Engine.Struct.Point;

public abstract class DefaultGameObject implements GameObject {
    public BoxDim body = null;
    public Image texture = null;
    public Point force = null;
    public BoxPos bounds = null;
    public InputHandler inputHandler = null;

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
    public final InputHandler getInputHandler() {
        return this.inputHandler;
    }

    @Override
    public void onLeaveScreen(final EngineState engineState) {
    }
}
