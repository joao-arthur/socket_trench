package com.SocketTrench.Domain.IdleServer;

import com.SocketTrench.Socket.SocketManager;

public class IdleServerSocketManager implements SocketManager {
    private final IdleServerDispatcher dispatcher;

    public IdleServerSocketManager(final IdleServerDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public void handleMessage(String message) {
        dispatcher.dispatch(message);
    }
}
