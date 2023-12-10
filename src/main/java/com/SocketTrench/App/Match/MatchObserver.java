package com.SocketTrench.App.Match;

import com.SocketTrench.App.GameOver.GameOverBuilder;
import com.SocketTrench.App.YouWon.YouWonBuilder;
import com.SocketTrench.Domain.Match.MatchMessages;
import com.SocketTrench.Engine.Domain.EngineState;
import com.SocketTrench.Events.Observer;
import com.SocketTrench.MatchScene.Player;
import com.SocketTrench.Socket.SocketInstance;
import com.SocketTrench.Socket.SocketMessages;

public class MatchObserver implements Observer {
    private final EngineState engineState;
    private final Player opponent;

    public MatchObserver(
            final EngineState engineState,
            final Player opponent) {
        this.engineState = engineState;
        this.opponent = opponent;
    }

    public void handle(final String event) {
        if (event.equals(SocketMessages.NO_SUCH_ELEMENT)) {
            this.opponentAbandoned();
            return;
        }
        if (event.equals(MatchMessages.GAME_OVER)) {
            this.gameOver();
            return;
        }
        if (event.equals(MatchMessages.YOU_WON)) {
            this.youWon();
            return;
        }
        if (event.equals(MatchMessages.SHOOT)) {
            this.shoot();
            return;
        }
        if (event.startsWith(MatchMessages.MOVE)) {
            this.move(event);
            return;
        }
    }

    private final void opponentAbandoned() {
        SocketInstance.getInstance().close();
        new YouWonBuilder().build();
    }

    private final void gameOver() {
        SocketInstance.getInstance().close();
        new GameOverBuilder().build();
    }

    private final void youWon() {
        SocketInstance.getInstance().close();
        new YouWonBuilder().build();
    }

    private final void move(String event) {
        final var eventX = Integer.parseInt(event.split(";")[1]);
        final var eventY = Integer.parseInt(event.split(";")[2]);
        final var body = opponent.getBody();
        body.x = eventX;
        body.y = eventY;
    }

    private final void shoot() {
        opponent.shoot(this.engineState);
    }
}
