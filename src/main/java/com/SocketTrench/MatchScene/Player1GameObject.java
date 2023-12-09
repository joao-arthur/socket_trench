package com.SocketTrench.MatchScene;

import java.awt.Image;
import java.awt.event.KeyEvent;

import com.SocketTrench.ImageLoader;
import com.SocketTrench.Engine.BoxDim;
import com.SocketTrench.Engine.Force;
import com.SocketTrench.Engine.GameObject;
import com.SocketTrench.Engine.BoxPos;
import com.SocketTrench.Screens.Screen;

final class Player1GameObject implements GameObject {
    public final BoxDim body;
    public final Image texture;
    public final Force force;
    public final BoxPos bounds;

    public Player1GameObject() {
        this.body = new BoxDim(0, 100, 58, 30);
        this.texture = ImageLoader.fromPath("player1.png");
        this.force = new Force(0, 0);
        this.bounds = new BoxPos(0, 0, 158 - 58, Screen.HEIGHT - 30);
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
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                this.force.x = -4;
                break;
            case KeyEvent.VK_RIGHT:
                this.force.x = 4;
                break;
            case KeyEvent.VK_UP:
                this.force.y = -4;
                break;
            case KeyEvent.VK_DOWN:
                this.force.y = 4;
                break;
            case KeyEvent.VK_SPACE:
                // create(
                // new ShootGameObject(
                // this.body.x + this.body.width,
                // this.body.y + this.body.height / 2 - 1,
                // 10
                // )
                // );
                break;
        }
    }

    @Override
    public final void onKeyReleased(final int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                this.force.x = 0;
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
                this.force.y = 0;
                break;
        }
    }
}
