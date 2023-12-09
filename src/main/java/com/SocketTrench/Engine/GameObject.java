package com.SocketTrench.Engine;

import java.awt.Image;

public interface GameObject {
    public Body getBody();

    public Image getTexture();

    public Force getForce();

    public Square getBounds();

    public void onUpdate();

    public void onKeyPressed(final int keyCode);

    public void onKeyReleased(final int keyCode);
}
