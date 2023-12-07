package com.SocketTrench.GameObject;

import java.awt.Image;
import java.awt.event.KeyEvent;

import com.SocketTrench.ImageLoader;

public final class Player1GameObject {
    public Sprite sprite;
    private Force force;
    public Image texture;

    public Player1GameObject() {
        this.sprite = new Sprite(58, 30, 0, 100);
        this.force = new Force(0, 0);
        this.texture = ImageLoader.fromPath("player1.png");
    }

    private void onKeyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                this.force.speedX = -1;
                break;
            case KeyEvent.VK_RIGHT:
                this.force.speedX = 1;
                break;
            case KeyEvent.VK_UP:
                this.force.speedY = -1;
                break;
            case KeyEvent.VK_DOWN:
                this.force.speedY = 1;
                break;
            case KeyEvent.VK_SPACE:
                break;
        }
    }

    private void onKeyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                this.force.speedX = -1;
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
                this.force.speedY = 0;
                break;
        }
    }

    public void update() {
        this.sprite.x += this.force.speedX;
        this.sprite.y += this.force.speedY;
    }
}
