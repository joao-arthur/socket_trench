package com.SocketTrench.Socket;

public final class SocketInstance {
    private static SocketConnection socket;

    public static SocketConnection create(final SocketConnection socket) {
        SocketInstance.socket = socket;
        return socket;
    }

    public static SocketConnection get() {
        return socket;
    }

    public static void close() {
        socket.close();
        socket = null;
    }
}
