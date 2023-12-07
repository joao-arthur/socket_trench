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
import javax.swing.GroupLayout.Alignment;

import com.SocketTrench.GUI.GUIScreen;

public final class ServerAwaitScreen implements GUIScreen {
    private final JFrame frame;
    private String serverIP = "";

    public ServerAwaitScreen() {
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
        final var ipInfo = new JLabel();
        ipInfo.setPreferredSize(new Dimension(400, 35));
        ipInfo.setMaximumSize(new Dimension(400, 35));
        ipInfo.setHorizontalAlignment(JLabel.CENTER);
        ipInfo.setFont(new Font("Arial", Font.PLAIN, 16));
        ipInfo.setText("USE THE FOLLOWING IP TO CONNECT");

        final var ipValueInfo = new JLabel();
        ipValueInfo.setPreferredSize(new Dimension(400, 35));
        ipValueInfo.setMaximumSize(new Dimension(400, 35));
        ipValueInfo.setHorizontalAlignment(JLabel.CENTER);
        ipValueInfo.setFont(new Font("Arial", Font.PLAIN, 40));
        ipValueInfo.setText(this.serverIP);

        final var content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.add(Box.createVerticalGlue());
        content.add(ipInfo);
        content.add(Box.createVerticalGlue());
        content.add(ipValueInfo);
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
        frame.setTitle("Server | Socket Trench");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void dispose() {
        this.frame.dispose();
    }
}
