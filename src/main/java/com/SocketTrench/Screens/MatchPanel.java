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
    private BufferedImage buffer;

    public MatchPanel(final MatchModel matchModel) {
        this.matchModel = matchModel;
        this.initComponents();
    }

    private final void initComponents() {
        setPreferredSize(new Dimension(Screen.WIDTH, Screen.HEIGHT));
    }

    @Override
    protected final void paintComponent(Graphics g) {
        super.paintComponent(g);
        final var drawer = (Graphics2D) g;

        super.paintComponent(g);

        if (buffer == null || buffer.getWidth() != getWidth() || buffer.getHeight() != getHeight()) {
            buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        }

        Graphics2D g2d = buffer.createGraphics();
        // Perform your drawing on g2d
        // ...





        final var background = this.matchModel.background;
        final var player1 = this.matchModel.player1;
        final var player2 = this.matchModel.player2;
        final var player1Shoots = this.matchModel.player1Shoots;
        final var player2Shoots = this.matchModel.player2Shoots;
        g2d.drawImage(background.texture, background.sprite.x, background.sprite.y, null);
        g2d.drawImage(player1.texture, player1.sprite.x, player1.sprite.y, null);
        g2d.drawImage(player2.texture, player2.sprite.x, player2.sprite.y, null);
        for (var shoot : player1Shoots) {
            g2d.drawImage(shoot.texture, shoot.sprite.x, shoot.sprite.y, null);
        }
        for (var shoot : player2Shoots) {
            g2d.drawImage(shoot.texture, shoot.sprite.x, shoot.sprite.y, null);
        }


                // Draw the entire image onto the JPanel
        g.drawImage(buffer, 0, 0, this);

        // Dispose of the graphics context to release resources
        g2d.dispose();

    }

    public final void handle(String event) {
        this.repaint();
    }
}
