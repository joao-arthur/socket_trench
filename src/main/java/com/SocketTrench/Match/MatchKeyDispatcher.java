package com.SocketTrench.Match;

import java.util.LinkedList;
import java.util.List;

import com.SocketTrench.Events.Dispatcher;
import com.SocketTrench.Events.Observer;

final class MatchKeyDispatcher implements Dispatcher {
    public final List<Observer> observers = new LinkedList<Observer>();

    public void dispatch(String event) {
        for (final var observer : observers)
            observer.handle(event);
    }

    public void register(Observer observer) {
        observers.add(observer);
    }
}
