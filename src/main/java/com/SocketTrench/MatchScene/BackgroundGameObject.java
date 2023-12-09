package com.SocketTrench.MatchScene;

import java.awt.Image;

import com.SocketTrench.ImageLoader;
import com.SocketTrench.Engine.BoxDim;
import com.SocketTrench.Engine.Force;
import com.SocketTrench.Engine.GameObject;
import com.SocketTrench.Engine.BoxPos;
import com.SocketTrench.Screens.Screen;

final class BackgroundGameObject implements GameObject {
    public final BoxDim body;
    public final Image texture;

    public BackgroundGameObject() {
        this.body = new BoxDim(0, 0, Screen.WIDTH, Screen.HEIGHT);
        this.texture = ImageLoader.fromPath("background.png");
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
        return null;
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
