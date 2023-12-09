package com.SocketTrench.Engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class EngineKeyAdapter implements KeyListener {
    private final List<GameObject> gameObjects;
    private final EngineState engineState;
    private final Set<Integer> keys;

    public EngineKeyAdapter(final List<GameObject> gameObjects, final EngineState engineState) {
        this.gameObjects = gameObjects;
        this.engineState = engineState;
        this.keys = new HashSet<>();
    }

    @Override
    public final void keyTyped(final KeyEvent event) {
    }

    @Override
    public final void keyPressed(final KeyEvent event) {
        final Integer keyCode = event.getKeyCode();
        if (this.keys.contains(keyCode)) {
            return;
        }
        for (final var gameObject : this.gameObjects) {
            gameObject.onKeyPressed(keyCode, this.engineState);
        }
    }

    @Override
    public final void keyReleased(final KeyEvent event) {
        final Integer keyCode = event.getKeyCode();
        this.keys.remove(keyCode);
        for (final var gameObject : this.gameObjects) {
            gameObject.onKeyReleased(keyCode);
        }
    }
}
