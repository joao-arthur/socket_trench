package com.SocketTrench.Screens;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import com.SocketTrench.Match.MatchModel;

public final class MatchPanel extends JPanel {
    private final MatchModel matchModel;

    public MatchPanel(MatchModel matchModel) {
        this.matchModel = matchModel;
        this.initComponents();
    }

    private void initComponents() {
        setPreferredSize(new Dimension(Screen.WIDTH, Screen.HEIGHT));
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                // .sendMessage(event.)
            }

            @Override
            public void keyReleased(KeyEvent event) {
                // .sendMessage(event.)
            }

        });
        this.setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        final var drawer = (Graphics2D) g;
        final var background = this.matchModel.background;
        final var player1 = this.matchModel.player1;
        final var player2 = this.matchModel.player2;

        drawer.drawImage(background.texture, background.sprite.x, background.sprite.y, this);
        drawer.drawImage(player1.texture, player1.sprite.x, player1.sprite.y, this);
        drawer.drawImage(player2.texture, player2.sprite.x, player2.sprite.y, this);
    }
}
