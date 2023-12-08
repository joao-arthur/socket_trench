package com.SocketTrench.Match;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

import com.SocketTrench.GameObject.ShootGameObject;

public final class MatchKeyAdapter implements KeyListener {
    private final MatchModel matchModel;
    private final Set<Integer> keys;

    public MatchKeyAdapter(MatchModel matchModel) {
        this.matchModel = matchModel;
        this.keys = new HashSet<>();
    }

    @Override
    public void keyTyped(KeyEvent event) {
    }

    @Override
    public void keyPressed(KeyEvent event) {
        final Integer keyCode = event.getKeyCode();
        if (this.keys.contains(keyCode)) {
            return;
        }
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                this.matchModel.player1.force.speedX = -4;
                break;
            case KeyEvent.VK_RIGHT:
                this.matchModel.player1.force.speedX = 4;
                break;
            case KeyEvent.VK_UP:
                this.matchModel.player1.force.speedY = -4;
                break;
            case KeyEvent.VK_DOWN:
                this.matchModel.player1.force.speedY = 4;
                break;
            case KeyEvent.VK_SPACE:
                this.matchModel.player1Shoots.add(
                        new ShootGameObject(
                                this.matchModel.player1.sprite.x + this.matchModel.player1.sprite.width,
                                this.matchModel.player1.sprite.y + this.matchModel.player1.sprite.height / 2 - 1,
                                10));
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        final Integer keyCode = event.getKeyCode();
        this.keys.remove(keyCode);
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                this.matchModel.player1.force.speedX = 0;
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
                this.matchModel.player1.force.speedY = 0;
                break;
        }
    }
}
