package com.SocketTrench.Screens;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.SocketTrench.GUI.GUI;
import com.SocketTrench.GUI.GUIScreen;
import com.SocketTrench.Match.MatchCreator;

public final class InitialScreen implements GUIScreen {
    private final JFrame frame;

    public InitialScreen() {
        this.frame = new JFrame();
        this.initComponents();
    }

    private final void initComponents() {
        final var createMatch = new JButton();
        createMatch.setPreferredSize(new Dimension(250, 50));
        createMatch.setMaximumSize(new Dimension(250, 50));
        createMatch.setText("CREATE MATCH");
        createMatch.setFont(new Font("Arial", 0, 20));
        createMatch.addActionListener(event -> {
            MatchCreator.create();
        });

        final var connectMatch = new JButton();
        connectMatch.setPreferredSize(new Dimension(250, 50));
        connectMatch.setMaximumSize(new Dimension(250, 50));
        connectMatch.setText("CONNECT TO MATCH");
        connectMatch.setFont(new Font("Arial", 0, 20));
        connectMatch.addActionListener(event -> {
            GUI.getInstance().goTo(new IdleClientScreen());
        });

        final var content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.add(Box.createVerticalGlue());
        content.add(createMatch);
        content.add(Box.createVerticalGlue());
        content.add(connectMatch);
        content.add(Box.createVerticalGlue());

        final var container = new JPanel(new FlowLayout());
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        container.add(Box.createHorizontalGlue());
        container.add(content);
        container.add(Box.createHorizontalGlue());
        container.setPreferredSize(new Dimension(Screen.WIDTH, Screen.HEIGHT));

        frame.add(container);
        frame.pack();
        frame.setTitle("Socket Trench");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    @Override
    public final void dispose() {
        frame.dispose();
    }
}
