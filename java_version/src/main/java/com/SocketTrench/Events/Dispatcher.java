package com.SocketTrench.Events;

public interface Dispatcher {
    public void dispatch(final String event);
    public void register(final Observer observer);
}
