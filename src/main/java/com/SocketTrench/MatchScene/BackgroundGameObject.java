package com.SocketTrench.MatchScene;

import java.awt.Image;

import com.SocketTrench.ImageLoader;
import com.SocketTrench.Engine.Body;
import com.SocketTrench.Engine.Force;
import com.SocketTrench.Engine.GameObject;
import com.SocketTrench.Engine.Square;
import com.SocketTrench.Screens.Screen;

public final class BackgroundGameObject implements GameObject {
    public final Body body;
    public final Image texture;

    public BackgroundGameObject() {
        this.body = new Body(Screen.WIDTH, Screen.HEIGHT, 0, 0);
        this.texture = ImageLoader.fromPath("background.png");
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
        return null;
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
