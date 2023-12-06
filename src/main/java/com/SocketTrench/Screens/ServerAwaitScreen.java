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

public final class ServerAwaitScreen {
    private final JFrame screenFrame;
    private String serverIP = "";

    public ServerAwaitScreen() {
        this.screenFrame = new JFrame();
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
        screenFrame.setVisible(true);
        screenFrame.setTitle("New Match | Socket Trench");
        screenFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        screenFrame.setResizable(false);

        final var buttonSize = new Dimension(250, 35);

        final var createMatchButton = new JLabel();
        createMatchButton.setHorizontalAlignment(JLabel.CENTER);
        createMatchButton.setVerticalAlignment(JLabel.CENTER);

        createMatchButton.setFont(new Font("Arial", Font.PLAIN, 16));
        createMatchButton.setText("USE THE FOLLOWING");

        final var createMatchButton2 = new JLabel();
        createMatchButton2.setHorizontalAlignment(JLabel.CENTER);
        createMatchButton2.setVerticalAlignment(JLabel.CENTER);

        createMatchButton2.setFont(new Font("Arial", Font.PLAIN, 16));
        createMatchButton2.setText("ADDRESS TO CONNECT");

        final var createMatchButton3 = new JLabel();
        createMatchButton3.setHorizontalAlignment(JLabel.CENTER);
        createMatchButton3.setVerticalAlignment(JLabel.CENTER);

        createMatchButton3.setFont(new Font("Arial", Font.PLAIN, 16));
        createMatchButton3.setText("TO THE MATCH");

        final var connectMatchButton = new JLabel();
        connectMatchButton.setPreferredSize(buttonSize);
        connectMatchButton.setFont(new Font("Arial", Font.PLAIN, 40));
        connectMatchButton.setText(this.serverIP);

        final var panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalGlue());
        panel.add(createMatchButton);
        panel.add(createMatchButton2);
        panel.add(createMatchButton3);
        panel.add(Box.createVerticalGlue());
        panel.add(connectMatchButton);
        panel.add(Box.createVerticalGlue());

        final var container = new JPanel(new FlowLayout());
        container.setLayout(new BoxLayout(container, BoxLayout.LINE_AXIS));
        container.add(Box.createVerticalGlue());
        container.add(Box.createHorizontalGlue());
        container.add(panel);
        container.add(Box.createVerticalGlue());
        container.add(Box.createHorizontalGlue());

        screenFrame.add(container);
        screenFrame.setSize(700, 500);
        screenFrame.setLocationRelativeTo(null);
    }
}