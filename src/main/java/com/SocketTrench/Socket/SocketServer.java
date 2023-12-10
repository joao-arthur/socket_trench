package com.SocketTrench.Socket;

import java.io.IOException;
import java.io.PrintStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class SocketServer implements SocketConnection {
    private final SocketService service = new SocketService();

    public SocketServer() {
        new Thread(() -> {
            try {
                final var socketServer = new ServerSocket(SocketConstants.PORT);
                final var socket = socketServer.accept();
                final var output = new PrintStream(socket.getOutputStream());
                final var input = new Scanner(socket.getInputStream());
                service.setParams(input, output, socket, socketServer);
            } catch (BindException exception) {
                service.manager.handleMessage(SocketMessages.PORT_IN_USE);
            } catch (IOException exception) {
                Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, exception);
            }
        }).start();
    }

    @Override
    public SocketServer setManager(final SocketManager manager) {
        service.setManager(manager);
        return this;
    }

    @Override
    public void receive(final String message) {
        service.receive(message);
    }

    @Override
    public SocketServer send(final String message) {
        service.send(message);
        return this;
    }

    @Override
    public void close() {
        service.close();
    }
}
