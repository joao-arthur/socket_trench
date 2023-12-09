package com.SocketTrench.Engine;

import java.awt.Image;

import com.SocketTrench.Engine.Struct.BoxDim;
import com.SocketTrench.Engine.Struct.BoxPos;
import com.SocketTrench.Engine.Struct.Point;

public interface GameObject {
    public BoxDim getBody();
    public Image getTexture();
    public Point getForce();
    public BoxPos getBounds();
    public void onUpdate();
    public void onKeyPressed(final int keyCode);
    public void onKeyReleased(final int keyCode);
}
