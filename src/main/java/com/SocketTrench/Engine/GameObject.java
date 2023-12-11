package com.SocketTrench.Engine;

import java.awt.Image;

import com.SocketTrench.Engine.Domain.EngineState;
import com.SocketTrench.Engine.Struct.BoxDim;
import com.SocketTrench.Engine.Struct.BoxPos;
import com.SocketTrench.Engine.Struct.Point;

public interface GameObject {
    public BoxDim getBody();
    public BoxDim getCollider();
    public Image getTexture();
    public Point getForce();
    public BoxPos getBounds();
    public InputHandler getInputHandler();
    public void onLeaveScreen(final EngineState engineState);
    public void onCollideWith(final GameObject other, final EngineState engineState);
}
