package com.SocketTrench.Engine;

import java.awt.Image;

public interface GameObject {
    public BoxDim getBody();
    public Image getTexture();
    public Point getForce();
    public BoxPos getBounds();
    public void onUpdate();
    public void onKeyPressed(final int keyCode);
    public void onKeyReleased(final int keyCode);
}
