package com.SocketTrench.Socket;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class SocketClient implements SocketConnection {
    private final SocketService service = new SocketService();

    public SocketClient(final String url) {
        new Thread(() -> {
            try {
                final var socket = new Socket(url, SocketConstants.PORT);
                final var output = new PrintStream(socket.getOutputStream());
                final var input = new Scanner(socket.getInputStream());
                service.setParams(input, output, socket, null);
            } catch (ConnectException exception) {
                service.manager.handleMessage(SocketMessages.CONNECTION_REFUSED);
            } catch (IOException exception) {
                Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, exception);
            }
        }).start();
    }

    @Override
    public final SocketClient setManager(final SocketManager manager) {
        service.setManager(manager);
        return this;
    }

    @Override
    public final void receive(final String message) {
        service.receive(message);
    }

    @Override
    public final SocketClient send(final String message) {
        service.send(message);
        return this;
    }

    @Override
    public final void close() {
        service.close();
    }
}
