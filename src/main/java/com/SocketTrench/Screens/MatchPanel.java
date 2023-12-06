package com.SocketTrench.Screens;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

import com.SocketTrench.ImageLoader;

public final class MatchPanel extends JPanel {
    private Image background;
    private Image player1;
    private Image player2;

    public MatchPanel() {
        this.background = ImageLoader.fromPath("background.png");
        this.player1 = ImageLoader.fromPath("player1.png");
        this.player2 = ImageLoader.fromPath("player2.png");
    }

    @Override
    protected void paintComponent(Graphics g) {
        final var drawer = (Graphics2D) g;
        drawer.drawImage(this.background, 0, 0, this);
        drawer.drawImage(this.player1, 0, 0, this);
        drawer.drawImage(this.player2, 500, 500, this);
    }
}
