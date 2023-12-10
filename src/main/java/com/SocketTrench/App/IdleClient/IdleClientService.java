package com.SocketTrench.App.IdleClient;

import com.SocketTrench.Domain.IdleClient.IdleClientDispatcher;
import com.SocketTrench.Domain.IdleClient.IdleClientSocketManager;
import com.SocketTrench.Socket.SocketClient;
import com.SocketTrench.Socket.SocketInstance;

final class IdleClientService {
    private final IdleClientDispatcher dispatcher;

    public IdleClientService(final IdleClientDispatcher dispatcher) {
        this.dispatcher = dispatcher;
        this.dispatcher.register(new IdleClientObserver());
    }

    public final void createClient(final String ip) {
        SocketInstance
            .getInstance()
            .create(new SocketClient(ip))
            .setManager(new IdleClientSocketManager(this.dispatcher));
    }
}
