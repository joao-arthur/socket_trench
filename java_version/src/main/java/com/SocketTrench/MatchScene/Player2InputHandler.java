package com.SocketTrench.MatchScene;

import java.awt.event.KeyEvent;

import com.SocketTrench.Engine.InputHandler;
import com.SocketTrench.Engine.Domain.EngineState;

final class Player2InputHandler implements InputHandler {
    private final Player2GameObject player2;

    public Player2InputHandler(final Player2GameObject player2) {
        this.player2 = player2;
    }

    @Override
    public final void onKeyPressed(final int keyCode, final EngineState engineState) {
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                this.player2.force.x = -4;
                break;
            case KeyEvent.VK_RIGHT:
                this.player2.force.x = 4;
                break;
            case KeyEvent.VK_UP:
                this.player2.force.y = -4;
                break;
            case KeyEvent.VK_DOWN:
                this.player2.force.y = 4;
                break;
            case KeyEvent.VK_SPACE:
                this.player2.shoot(engineState);
                break;
        }
    }

    @Override
    public final void onKeyReleased(final int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                this.player2.force.x = 0;
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
                this.player2.force.y = 0;
                break;
        }
    }
}
