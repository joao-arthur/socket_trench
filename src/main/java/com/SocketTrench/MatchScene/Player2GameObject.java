package com.SocketTrench.MatchScene;

import java.awt.Image;

import com.SocketTrench.ImageLoader;
import com.SocketTrench.Engine.BoxDim;
import com.SocketTrench.Engine.Force;
import com.SocketTrench.Engine.GameObject;
import com.SocketTrench.Engine.BoxPos;
import com.SocketTrench.Screens.Screen;

final class Player2GameObject implements GameObject {
    public final BoxDim body;
    public final Image texture;
    public final Force force;
    public final BoxPos bounds;

    public Player2GameObject() {
        this.body = new BoxDim(Screen.WIDTH - 58, Screen.HEIGHT - 100 - 30, 58, 30);
        this.texture = ImageLoader.fromPath("player2.png");
        this.force = new Force(0, 0);
        this.bounds = new BoxPos(Screen.WIDTH - 158,0, Screen.WIDTH, Screen.HEIGHT - 30);
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
        return this.force;
    }

    @Override
    public final BoxPos getBounds() {
        return this.bounds;
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
