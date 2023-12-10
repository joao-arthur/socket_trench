package com.SocketTrench.Socket;

public final class SocketInstance {
    private static SocketInstance instance;
    private SocketConnection socket;

    public static SocketInstance getInstance() {
        if (SocketInstance.instance == null) {
            SocketInstance.instance = new SocketInstance();
        }
        return SocketInstance.instance;
    }

    public final SocketConnection create(final SocketConnection socket) {
        this.socket = socket;
        return socket;
    }

    public final SocketConnection get() {
        return socket;
    }

    public final void close() {
        if (socket != null) {
            socket.close();
            socket = null;
        }
    }
}
