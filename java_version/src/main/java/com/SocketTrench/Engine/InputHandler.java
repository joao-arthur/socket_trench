package com.SocketTrench.Engine;

import com.SocketTrench.Engine.Domain.EngineState;

public interface InputHandler {
    public void onKeyPressed(final int keyCode, final EngineState engineState);
    public void onKeyReleased(final int keyCode);
}
