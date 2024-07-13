package com.SocketTrench.MatchScene;

import java.awt.event.KeyEvent;

import com.SocketTrench.Engine.InputHandler;
import com.SocketTrench.Engine.Domain.EngineState;

final class Player1InputHandler implements InputHandler {
    private final Player1GameObject player1;

    public Player1InputHandler(final Player1GameObject player1) {
        this.player1 = player1;
    }

    @Override
    public final void onKeyPressed(final int keyCode, final EngineState engineState) {
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                this.player1.force.x = -4;
                break;
            case KeyEvent.VK_RIGHT:
                this.player1.force.x = 4;
                break;
            case KeyEvent.VK_UP:
                this.player1.force.y = -4;
                break;
            case KeyEvent.VK_DOWN:
                this.player1.force.y = 4;
                break;
            case KeyEvent.VK_SPACE:
                this.player1.shoot(engineState);
                break;
        }
    }

    @Override
    public final void onKeyReleased(final int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                this.player1.force.x = 0;
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
                this.player1.force.y = 0;
                break;
        }
    }
}
