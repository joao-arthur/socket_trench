package com.SocketTrench.Screens;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.net.InetAddress;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.SocketTrench.GUI.GUIScreen;

public final class ClientAwaitScreen implements GUIScreen {
    private final JFrame frame;
    private String serverIP = "";

    public ClientAwaitScreen() {
        this.frame = new JFrame();
        this.initIp();
        this.initComponents();
    }

    private void initIp() {
        try {
            this.serverIP = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void initComponents() {
        final var buttonSize = new Dimension(250, 35);

        final var createMatch = new JLabel();
        createMatch.setHorizontalAlignment(JLabel.CENTER);
        createMatch.setVerticalAlignment(JLabel.CENTER);
        createMatch.setFont(new Font("Arial", Font.PLAIN, 16));
        createMatch.setText("WRITE DOWN THE SERVER IP ADDRESS");

        final var createMatch2 = new JLabel();
        createMatch2.setHorizontalAlignment(JLabel.CENTER);
        createMatch2.setVerticalAlignment(JLabel.CENTER);

        createMatch2.setFont(new Font("Arial", Font.PLAIN, 16));
        createMatch2.setText("ADDRESS TO CONNECT");

        final var createMatch3 = new JLabel();
        createMatch3.setHorizontalAlignment(JLabel.CENTER);
        createMatch3.setVerticalAlignment(JLabel.CENTER);

        createMatch3.setFont(new Font("Arial", Font.PLAIN, 16));
        createMatch3.setText("TO THE MATCH");

        final var connectMatchButton = new JLabel();
        connectMatchButton.setPreferredSize(buttonSize);
        connectMatchButton.setFont(new Font("Arial", Font.PLAIN, 40));
        connectMatchButton.setText(this.serverIP);

        final var content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.add(Box.createVerticalGlue());
        content.add(createMatch);
        content.add(createMatch2);
        content.add(createMatch3);
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
        container.setPreferredSize(new Dimension(Screen.WIDTH, Screen.HEIGHT));

        frame.add(container);
        frame.pack();
        frame.setTitle("Client | Socket Trench");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void dispose() {
        this.frame.dispose();
    }
}
