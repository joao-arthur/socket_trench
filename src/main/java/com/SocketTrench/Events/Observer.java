package com.SocketTrench.Events;

public interface Observer<Event> {
    public void handle(Event event);
}
