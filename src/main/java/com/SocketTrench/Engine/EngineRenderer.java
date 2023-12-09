package com.SocketTrench.Engine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import com.SocketTrench.Screens.Screen;

public final class EngineRenderer {
    private final List<GameObject> gameObjects;
    private final BufferedImage buffer;

    public EngineRenderer(final List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
        this.buffer = new BufferedImage(Screen.WIDTH, Screen.HEIGHT, BufferedImage.TYPE_INT_RGB);
    }

    public final void render(Graphics graphics) {
        final Graphics2D bufferGraphics = buffer.createGraphics();
        for (var gameObject : this.gameObjects) {
            final var texture = gameObject.getTexture();
            final var body = gameObject.getBody();
            bufferGraphics.drawImage(texture, body.x, body.y, body.w, body.h, null);
        }
        graphics.drawImage(buffer, 0, 0, null);
        bufferGraphics.dispose();
    }
}
