package com.SocketTrench.Engine;

import java.awt.Image;

public interface GameObject {
    public Body getBody();
    public Force getForce();
    public Image getTexture();
    public void onInit();
    public void onUpdate();
}
