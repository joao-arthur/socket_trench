package com.SocketTrench.App.IdleServer;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.SocketTrench.Domain.IdleServer.IdleServerDispatcher;
import com.SocketTrench.Domain.IdleServer.IdleServerSocketManager;
import com.SocketTrench.Socket.SocketInstance;
import com.SocketTrench.Socket.SocketServer;

final class IdleServerService {
    private final IdleServerDispatcher dispatcher;

    public IdleServerService(final IdleServerDispatcher dispatcher) {
        this.dispatcher = dispatcher;
        this.dispatcher.register(new IdleServerObserver());
    }

    public final void createServer() {
        SocketInstance
            .getInstance()
            .create(new SocketServer())
            .setManager(new IdleServerSocketManager(this.dispatcher));
    }

    public final String getLocalIP() {
        try {
            final Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                final NetworkInterface networkInterface = networkInterfaces.nextElement();
                final Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    final InetAddress inetAddress = inetAddresses.nextElement();
                    if (!inetAddress.isLoopbackAddress() && !(inetAddress.getHostAddress().contains(":"))) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (Exception exception) {
            Logger.getLogger(IdleServerService.class.getName()).log(Level.SEVERE, null, exception);
            return null;
        }
        return null;
    }
}
