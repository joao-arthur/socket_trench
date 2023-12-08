package com.SocketTrench.Events;

public interface Dispatcher<Event> {
    public void dispatch(final Event event);
    public void register(final Observer<Event> observer);
}
