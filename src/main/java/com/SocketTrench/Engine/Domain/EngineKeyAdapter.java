package com.SocketTrench.Engine.Domain;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.SocketTrench.Engine.GameObject;

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
            final var inputHandler = gameObject.getInputHandler();
            if (inputHandler != null) {
                inputHandler.onKeyPressed(keyCode, this.engineState);
            }
        }
    }

    @Override
    public final void keyReleased(final KeyEvent event) {
        final Integer keyCode = event.getKeyCode();
        this.keys.remove(keyCode);
        for (final var gameObject : this.gameObjects) {
            final var inputHandler = gameObject.getInputHandler();
            if (inputHandler != null) {
                inputHandler.onKeyReleased(keyCode);
            }
        }
    }
}
