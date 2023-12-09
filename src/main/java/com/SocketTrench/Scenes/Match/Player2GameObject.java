package com.SocketTrench.Scenes.Match;

import java.awt.Image;

import com.SocketTrench.ImageLoader;
import com.SocketTrench.Engine.Body;
import com.SocketTrench.Engine.Force;
import com.SocketTrench.Engine.GameObject;
import com.SocketTrench.Engine.Square;
import com.SocketTrench.Screens.Screen;

public final class Player2GameObject implements GameObject {
    public final Body body;
    public final Image texture;
    public final Force force;
    public final Square bounds;

    public Player2GameObject() {
        this.body = new Body(58, 30, Screen.WIDTH - 58, Screen.HEIGHT - 100 - 30);
        this.texture = ImageLoader.fromPath("player2.png");
        this.force = new Force(0, 0);
        this.bounds = new Square(Screen.WIDTH - 158, Screen.WIDTH, 0, Screen.HEIGHT - 30);
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
        return this.force;
    }

    @Override
    public final Square getBounds() {
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
