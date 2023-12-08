package com.SocketTrench.Screens;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.SocketTrench.GUI.GUIScreen;
import com.SocketTrench.Match.MatchKeyAdapter;

public final class MatchScreen implements GUIScreen {
    private final JFrame frame;
    private final JPanel panel;
    private final MatchKeyAdapter matchKeyAdapter;

    public MatchScreen(
        final JPanel panel,
        final MatchKeyAdapter matchKeyAdapter
    ) {
        this.frame = new JFrame();
        this.panel = panel;
        this.matchKeyAdapter = matchKeyAdapter;
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
        frame.addKeyListener(matchKeyAdapter);
    }

    @Override
    public final void dispose() {
        frame.dispose();
    }
}
