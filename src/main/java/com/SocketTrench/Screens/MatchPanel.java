package com.SocketTrench.Screens;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.SocketTrench.GameObject.BackgroundGameObject;
import com.SocketTrench.GameObject.Player1GameObject;
import com.SocketTrench.GameObject.Player2GameObject;

public final class MatchPanel extends JPanel {
    private BackgroundGameObject background;
    private Player1GameObject player1;
    private Player2GameObject player2;

    public MatchPanel() {
        this.background = new BackgroundGameObject();
        this.player1 = new Player1GameObject();
        this.player2 = new Player2GameObject();
        this.initComponents();
    }

    private void initComponents() {
        setPreferredSize(new Dimension(Screen.WIDTH, Screen.HEIGHT));
    }

    @Override
    protected void paintComponent(Graphics g) {
        final var drawer = (Graphics2D) g;
        drawer.drawImage(this.background.texture, this.background.sprite.x, this.background.sprite.y, this);
        drawer.drawImage(this.player1.texture, this.player1.sprite.x, this.player1.sprite.y, this);
        drawer.drawImage(this.player2.texture, this.player2.sprite.x, this.player2.sprite.y, this);
    }
}
