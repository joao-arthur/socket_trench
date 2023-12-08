package com.SocketTrench.Screens;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.SocketTrench.Events.Observer;
import com.SocketTrench.Match.MatchModel;

public final class MatchPanel extends JPanel implements Observer<String> {
    private final MatchModel matchModel;

    public MatchPanel(final MatchModel matchModel) {
        this.matchModel = matchModel;
        this.initComponents();
    }

    private final void initComponents() {
        setPreferredSize(new Dimension(Screen.WIDTH, Screen.HEIGHT));
    }

    @Override
    protected final void paintComponent(Graphics g) {
        final var drawer = (Graphics2D) g;
        final var background = this.matchModel.background;
        final var player1 = this.matchModel.player1;
        final var player2 = this.matchModel.player2;
        final var player1Shoots = this.matchModel.player1Shoots;
        final var player2Shoots = this.matchModel.player2Shoots;
        drawer.drawImage(background.texture, background.sprite.x, background.sprite.y, this);
        drawer.drawImage(player1.texture, player1.sprite.x, player1.sprite.y, this);
        drawer.drawImage(player2.texture, player2.sprite.x, player2.sprite.y, this);
        for (var shoot : player1Shoots) {
            drawer.drawImage(shoot.texture, shoot.sprite.x, shoot.sprite.y, this);
        }
        for (var shoot : player2Shoots) {
            drawer.drawImage(shoot.texture, shoot.sprite.x, shoot.sprite.y, this);
        }
    }

    public final void handle(String event) {
        this.repaint();
    }
}
