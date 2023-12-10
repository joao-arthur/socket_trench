package com.SocketTrench.Domain.IdleClient;

import com.SocketTrench.Socket.SocketManager;

public class IdleClientSocketManager implements SocketManager {
    private final IdleClientDispatcher dispatcher;

    public IdleClientSocketManager(final IdleClientDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public void handleMessage(String message) {
        dispatcher.dispatch(message);
    }
}
