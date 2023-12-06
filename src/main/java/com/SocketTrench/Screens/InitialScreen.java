package com.SocketTrench.Screens;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.SocketTrench.GUI.GUIScreen;

public final class InitialScreen implements GUIScreen {
    private final JFrame frame;
    private final JButton createMatch;
    private final JButton connectMatch;

    public InitialScreen() {
        this.frame = new JFrame();
        this.createMatch = new JButton();
        this.connectMatch = new JButton();
        this.initComponents();
    }

    private void initComponents() {
        final var buttonSize = new Dimension(250, 50);
        final var buttonFont = new Font("Arial", 0, 20);

        createMatch.setPreferredSize(buttonSize);
        createMatch.setMaximumSize(buttonSize);
        createMatch.setText("CREATE MATCH");
        createMatch.setFont(buttonFont);

        connectMatch.setPreferredSize(buttonSize);
        connectMatch.setMaximumSize(buttonSize);
        connectMatch.setText("CONNECT TO MATCH");
        connectMatch.setFont(buttonFont);

        final var content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.add(Box.createVerticalGlue());
        content.add(createMatch);
        content.add(Box.createVerticalGlue());
        content.add(connectMatch);
        content.add(Box.createVerticalGlue());

        final var container = new JPanel(new FlowLayout());
        container.setLayout(new BoxLayout(container, BoxLayout.LINE_AXIS));
        container.add(Box.createVerticalGlue());
        container.add(Box.createHorizontalGlue());
        container.add(content);
        container.add(Box.createVerticalGlue());
        container.add(Box.createHorizontalGlue());

        frame.add(container);

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

    public void enable() {
        createMatch.setEnabled(true);
        connectMatch.setEnabled(true);
    }

    public void disable() {
        createMatch.setEnabled(false);
        connectMatch.setEnabled(false);
    }
}
