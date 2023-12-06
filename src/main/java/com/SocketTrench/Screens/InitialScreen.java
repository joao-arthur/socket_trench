package com.SocketTrench.Screens;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public final class InitialScreen {
    private final JFrame screenFrame;

    public InitialScreen() {
        this.screenFrame = new JFrame();
        this.initComponents();
    }

    private void initComponents() {
        screenFrame.setVisible(true);
        screenFrame.setSize(700, 500);
        screenFrame.setTitle("Socket Trench");
        screenFrame.setLocationRelativeTo(null);
        screenFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        final var buttonSize = new Dimension(250, 35);

        final var createMatchButton = new JButton();
        createMatchButton.setPreferredSize(buttonSize);
        createMatchButton.setMaximumSize(buttonSize);
        createMatchButton.setText("CREATE MATCH");

        final var connectMatchButton = new JButton();
        connectMatchButton.setPreferredSize(buttonSize);
        connectMatchButton.setMaximumSize(buttonSize);
        connectMatchButton.setText("CONNECT TO MATCH");

        final var content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.add(Box.createVerticalGlue());
        content.add(createMatchButton);
        content.add(Box.createVerticalGlue());
        content.add(connectMatchButton);
        content.add(Box.createVerticalGlue());

        final var container = new JPanel(new FlowLayout());
        container.setLayout(new BoxLayout(container, BoxLayout.LINE_AXIS));
        container.add(Box.createVerticalGlue());
        container.add(Box.createHorizontalGlue());
        container.add(content);
        container.add(Box.createVerticalGlue());
        container.add(Box.createHorizontalGlue());

        screenFrame.add(container);
        container.revalidate();
    }
}
