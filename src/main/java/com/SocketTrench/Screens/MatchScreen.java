package com.SocketTrench.Screens;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.SocketTrench.GUI.GUIScreen;
import com.SocketTrench.Match.MatchKeyPressedDispatcher;
import com.SocketTrench.Match.MatchKeyReleasedDispatcher;

public final class MatchScreen implements GUIScreen {
    private final JFrame frame;
    private final JPanel panel;
    private final MatchKeyPressedDispatcher matchKeyPressedDispatcher;
    private final MatchKeyReleasedDispatcher matchKeyReleasedDispatcher;

    public MatchScreen(
            JPanel panel,
            MatchKeyPressedDispatcher matchKeyPressedDispatcher,
            MatchKeyReleasedDispatcher matchKeyReleasedDispatcher) {
        this.frame = new JFrame();
        this.panel = panel;
        this.matchKeyPressedDispatcher = matchKeyPressedDispatcher;
        this.matchKeyReleasedDispatcher = matchKeyReleasedDispatcher;
        this.initComponents();
    }

    private void initComponents() {
        this.frame.add(this.panel);
        frame.pack();
        frame.setTitle("Match | Socket Trench");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                matchKeyPressedDispatcher.dispatch(event.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent event) {
                matchKeyReleasedDispatcher.dispatch(event.getKeyCode());
            }
        });
    }

    @Override
    public void dispose() {
        frame.dispose();
    }
}
