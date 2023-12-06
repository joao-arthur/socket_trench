package com.SocketTrench.Screens;

import java.awt.Component;

import javax.swing.JFrame;

import com.SocketTrench.GUI.GUIScreen;

public final class MatchScreen implements GUIScreen {
    private final JFrame frame;

    public MatchScreen() {
        this.frame = new JFrame();
        this.initComponents();
    }

    private void initComponents() {
        final var panel = new MatchPanel();
        this.frame.add(panel);
        // panel.revalidate();
        frame.setTitle("Socket Trench");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void dispose() {
        frame.dispose();
    }

    public Component getScreen() {
        return frame;
    }
}
