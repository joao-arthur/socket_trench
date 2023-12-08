package com.SocketTrench.Events;

public interface Dispatcher<Event> {
    public void dispatch(Event event);
    public void register(Observer<Event> observer);
}
