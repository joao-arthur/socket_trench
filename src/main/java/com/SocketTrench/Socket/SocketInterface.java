package com.SocketTrench.Socket;

public interface SocketInterface {
    public SocketInterface setManager(final SocketManager manager);
    public void receive(final String message);
    public SocketInterface send(final String message);
    public void close();
}
