package com.SocketTrench.App.Match;

import java.awt.event.KeyListener;

import com.SocketTrench.Engine.EngineRenderDispatcher;
import com.SocketTrench.Engine.EngineRenderer;
import com.SocketTrench.GUI.GUI;

public final class MatchBuilder {
    public final void build(
        final EngineRenderDispatcher dispatcher,
        final EngineRenderer renderer,
        final KeyListener keyListener
    ) {
        final var panel = new MatchPanel(renderer);
        final var screen = new MatchScreen(panel, keyListener);
        dispatcher.register(panel);
        GUI.getInstance().goTo(screen);
    }
}
