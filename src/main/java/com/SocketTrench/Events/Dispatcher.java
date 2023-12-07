package com.SocketTrench.Events;

public interface Dispatcher {
    public void dispatch(String event);
    public void register(Observer observer);
}
