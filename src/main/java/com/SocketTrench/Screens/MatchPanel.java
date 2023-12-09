package com.SocketTrench.Screens;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.SocketTrench.Engine.EngineRenderer;
import com.SocketTrench.Events.Observer;

public final class MatchPanel extends JPanel implements Observer<String> {
    private final EngineRenderer engineRenderer;

    public MatchPanel(final EngineRenderer engineRenderer) {
        this.engineRenderer = engineRenderer;
        this.initComponents();
    }

    private final void initComponents() {
        setPreferredSize(new Dimension(Screen.WIDTH, Screen.HEIGHT));
    }

    @Override
    protected final void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        this.engineRenderer.render(graphics);
    }

    public final void handle(final String event) {
        this.repaint();
    }
}
