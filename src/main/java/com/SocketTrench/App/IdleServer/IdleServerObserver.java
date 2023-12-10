package com.SocketTrench.App.IdleServer;

import javax.swing.JOptionPane;

import com.SocketTrench.App.Match.MatchBuilder;
import com.SocketTrench.Events.Observer;
import com.SocketTrench.Socket.SocketMessages;

class IdleServerObserver implements Observer {
    public void handle(final String event) {
        if (event.equals(SocketMessages.CONN_REFUSED)) {
            this.connRefused();
            return;
        }
        if (event.equals(SocketMessages.PORT_IN_USE)) {
            this.portInUse();
            return;
        }
        if (event.equals(SocketMessages.CONN_SERVER)) {
            this.connServer();
            return;
        }
        if (event.equals(SocketMessages.CONN_CLIENT)) {
            this.connClient();
            return;
        }
    }

    private final void connRefused() {
        JOptionPane.showMessageDialog(
            null,
            "It wasn't possible to establish connection!", "ERROR!",
            JOptionPane.ERROR_MESSAGE
        );
        System.exit(0);
    }

    private final void portInUse() {
        JOptionPane.showMessageDialog(
            null,
            "The port '54321' is already in use!", "ERROR!",
            JOptionPane.ERROR_MESSAGE
        );
        System.exit(0);
    }

    private final void connClient() {
        new MatchBuilder().buildPlayer1();
    }

    private final void connServer() {
        new MatchBuilder().buildPlayer2();
    }
}
