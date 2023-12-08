package com.SocketTrench.Screens;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.SocketTrench.Events.Observer;
import com.SocketTrench.Match.MatchModel;

public final class MatchPanel extends JPanel implements Observer<String> {
    private final MatchModel matchModel;

    public MatchPanel(MatchModel matchModel) {
        this.matchModel = matchModel;
        this.initComponents();
    }

    private void initComponents() {
        setPreferredSize(new Dimension(Screen.WIDTH, Screen.HEIGHT));
    }

    @Override
    protected void paintComponent(Graphics g) {
        final var drawer = (Graphics2D) g;
        final var model = this.matchModel;
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
