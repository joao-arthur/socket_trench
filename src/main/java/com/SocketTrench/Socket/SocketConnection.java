package com.SocketTrench.Socket;

public interface SocketConnection {
    public SocketConnection setManager(final SocketManager manager);
    public void receive(final String message);
    public SocketConnection send(final String message);
    public void close();
}
