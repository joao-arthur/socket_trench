package com.SocketTrench.Scenes.Match;

import java.awt.Image;

import com.SocketTrench.ImageLoader;
import com.SocketTrench.Engine.Body;
import com.SocketTrench.Engine.Force;
import com.SocketTrench.Engine.GameObject;
import com.SocketTrench.Engine.Square;
import com.SocketTrench.Screens.Screen;

public final class Player1GameObject implements GameObject {
    public final Body body;
    public final Image texture;
    public final Force force;
    public final Square bounds;

    public Player1GameObject() {
        this.body = new Body(58, 30, 0, 100);
        this.texture = ImageLoader.fromPath("player1.png");
        this.force = new Force(0, 0);
        this.bounds = new Square(0, 158 - 58, 0, Screen.HEIGHT - 30);
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
}
