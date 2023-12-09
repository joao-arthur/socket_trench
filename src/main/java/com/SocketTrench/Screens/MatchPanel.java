package com.SocketTrench.Screens;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.SocketTrench.Events.Observer;
import com.SocketTrench.Match.MatchModel;

public final class MatchPanel extends JPanel implements Observer<String> {
    private final MatchModel matchModel;
    private final BufferedImage buffer;

    public MatchPanel(final MatchModel matchModel) {
        this.matchModel = matchModel;
        this.buffer = new BufferedImage(Screen.WIDTH, Screen.HEIGHT, BufferedImage.TYPE_INT_RGB);
        this.initComponents();
    }

    private final void initComponents() {
        setPreferredSize(new Dimension(Screen.WIDTH, Screen.HEIGHT));
    }

    @Override
    protected final void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D bufferGraphics = buffer.createGraphics();

        final var background = this.matchModel.background;
        final var player1 = this.matchModel.player1;
        final var player2 = this.matchModel.player2;
        final var player1Shoots = this.matchModel.player1Shoots;
        final var player2Shoots = this.matchModel.player2Shoots;
        bufferGraphics.drawImage(background.texture, background.sprite.x, background.sprite.y, null);
        bufferGraphics.drawImage(player1.texture, player1.sprite.x, player1.sprite.y, null);
        bufferGraphics.drawImage(player2.texture, player2.sprite.x, player2.sprite.y, null);
        for (var shoot : player1Shoots) {
            bufferGraphics.drawImage(shoot.texture, shoot.sprite.x, shoot.sprite.y, null);
        }
        for (var shoot : player2Shoots) {
            bufferGraphics.drawImage(shoot.texture, shoot.sprite.x, shoot.sprite.y, null);
        }

        g.drawImage(buffer, 0, 0, null);
        bufferGraphics.dispose();
    }

    public final void handle(String event) {
        this.repaint();
    }
}
