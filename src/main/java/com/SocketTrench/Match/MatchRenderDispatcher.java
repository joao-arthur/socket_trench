package com.SocketTrench.Match;

import java.util.LinkedList;
import java.util.List;

import com.SocketTrench.Events.Dispatcher;
import com.SocketTrench.Events.Observer;

public final class MatchRenderDispatcher implements Dispatcher<String> {
    public final List<Observer<String>> observers = new LinkedList<Observer<String>>();

    @Override
    public final void dispatch(final String event) {
        for (final var observer : observers)
            observer.handle(event);
    }

    @Override
    public final void register(final Observer<String> observer) {
        observers.add(observer);
    }
}
