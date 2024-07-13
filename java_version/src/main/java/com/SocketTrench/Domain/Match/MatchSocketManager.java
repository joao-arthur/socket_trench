package com.SocketTrench.Domain.Match;

import com.SocketTrench.Socket.SocketManager;

public class MatchSocketManager implements SocketManager {
    private final MatchDispatcher dispatcher;

    public MatchSocketManager(final MatchDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public void handleMessage(String message) {
        dispatcher.dispatch(message);
    }
}
