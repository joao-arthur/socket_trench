package com.SocketTrench.App.IdleServer;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.SocketTrench.App.Screen;
import com.SocketTrench.GUI.GUIScreen;

final class IdleServerScreen implements GUIScreen {
    private final IdleServerService service;
    private final JFrame frame;

    public IdleServerScreen(final IdleServerService service) {
        this.service = service;
        this.frame = new JFrame();
        this.initComponents();
        this.service.createServer();
    }

    private final void initComponents() {
        final var info = new JLabel();
        info.setPreferredSize(new Dimension(400, 35));
        info.setMaximumSize(new Dimension(400, 35));
        info.setHorizontalAlignment(JLabel.CENTER);
        info.setFont(new Font("Arial", Font.PLAIN, 20));
        info.setText("CONNECT TO THE IP");

        final var ipValue = new JLabel();
        ipValue.setPreferredSize(new Dimension(400, 35));
        ipValue.setMaximumSize(new Dimension(400, 35));
        ipValue.setHorizontalAlignment(JLabel.CENTER);
        ipValue.setFont(new Font("Arial", Font.BOLD, 25));
        ipValue.setText(this.service.getLocalIP());

        final var content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.add(Box.createVerticalGlue());
        content.add(info);
        content.add(ipValue);
        content.add(Box.createVerticalGlue());
        content.add(Box.createVerticalGlue());

        final var container = new JPanel(new FlowLayout());
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        container.add(Box.createHorizontalGlue());
        container.add(content);
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

    @Override
    public final void dispose() {
        this.frame.dispose();
    }
}
