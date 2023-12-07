package com.SocketTrench.Screens;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import com.SocketTrench.Events.Observer;
import com.SocketTrench.Match.MatchService;

public final class MatchPanel extends JPanel implements Observer {
    private final MatchService matchService;

    public MatchPanel(MatchService matchService) {
        this.matchService = matchService;
        this.initComponents();
    }

    private void initComponents() {
        setPreferredSize(new Dimension(Screen.WIDTH, Screen.HEIGHT));
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                // .dispatch(event.)
            }

            @Override
            public void keyReleased(KeyEvent event) {
                // .dispatch(event.)
            }
        });
        this.setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        final var drawer = (Graphics2D) g;
        final var model = matchService.getModel();
        final var background = model.background;
        final var player1 = model.player1;
        final var player2 = model.player2;

        drawer.drawImage(background.texture, background.sprite.x, background.sprite.y, this);
        drawer.drawImage(player1.texture, player1.sprite.x, player1.sprite.y, this);
        drawer.drawImage(player2.texture, player2.sprite.x, player2.sprite.y, this);
    }

    public void handle(String event) {
        this.repaint();
    }
}
