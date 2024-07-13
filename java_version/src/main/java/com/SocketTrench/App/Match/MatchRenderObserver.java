package com.SocketTrench.App.Match;

import com.SocketTrench.App.GameOver.GameOverBuilder;
import com.SocketTrench.App.YouWon.YouWonBuilder;
import com.SocketTrench.Domain.Match.MatchMessages;
import com.SocketTrench.Events.Observer;
import com.SocketTrench.MatchScene.Player;
import com.SocketTrench.Socket.SocketInstance;

public class MatchRenderObserver implements Observer {
    private final Player player;
    private final Player opponent;

    public MatchRenderObserver(final Player player, final Player opponent) {
        this.player = player;
        this.opponent = opponent;
    }

    public void handle(final String event) {
        if (this.player.getLifePoints() <= 0) {
            SocketInstance
                    .getInstance()
                    .getConnection()
                    .send(MatchMessages.YOU_WON);
            SocketInstance
                    .getInstance().close();
            new GameOverBuilder().build();
            return;
        }
        if (this.opponent.getLifePoints() <= 0) {
            SocketInstance
                    .getInstance()
                    .getConnection()
                    .send(MatchMessages.GAME_OVER);
            SocketInstance
                    .getInstance().close();
            new YouWonBuilder().build();
            return;
        }
        final var body = this.player.getBody();
        final var force = this.player.getForce();
        if (force.x != 0 || force.y != 0) {
            SocketInstance
                    .getInstance()
                    .getConnection()
                    .send(MatchMessages.MOVE + ";" + body.x + ";" + body.y);
        }
    }
}
