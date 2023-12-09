package com.SocketTrench.Screens;

import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.SocketTrench.GUI.GUIScreen;

public final class MatchScreen implements GUIScreen {
    private final JFrame frame;
    private final JPanel panel;
    private final KeyListener keyListener;

    public MatchScreen(
            final JPanel panel,
            final KeyListener keyListener) {
        this.frame = new JFrame();
        this.panel = panel;
        this.keyListener = keyListener;
        this.initComponents();
    }

    private final void initComponents() {
        this.frame.add(this.panel);
        frame.pack();
        frame.setTitle("Match | Socket Trench");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.addKeyListener(keyListener);
    }

    @Override
    public final void dispose() {
        frame.dispose();
    }
}
