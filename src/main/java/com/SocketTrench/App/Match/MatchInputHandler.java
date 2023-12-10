package com.SocketTrench.App.Match;

import java.awt.event.KeyEvent;

import com.SocketTrench.Domain.Match.MatchMessages;
import com.SocketTrench.Engine.InputHandler;
import com.SocketTrench.Engine.Domain.EngineState;
import com.SocketTrench.Socket.SocketInstance;

public class MatchInputHandler implements InputHandler {
    public MatchInputHandler() {
    }

    @Override
    public final void onKeyPressed(final int keyCode, final EngineState engineState) {
        switch (keyCode) {
            case KeyEvent.VK_SPACE:
                SocketInstance
                        .getInstance()
                        .getConnection()
                        .send(MatchMessages.SHOOT);
        }
    }

    @Override
    public final void onKeyReleased(final int keyCode) {

    }
}
