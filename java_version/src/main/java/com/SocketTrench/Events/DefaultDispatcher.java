package com.SocketTrench.Events;

import java.util.LinkedList;
import java.util.List;

public class DefaultDispatcher implements Dispatcher {
    public final List<Observer> observers = new LinkedList<Observer>();

    @Override
    public void dispatch(final String event) {
        for (final var observer : this.observers) {
            observer.handle(event);
        }
    }

    @Override
    public void register(final Observer observer) {
        observers.add(observer);
    }
}
