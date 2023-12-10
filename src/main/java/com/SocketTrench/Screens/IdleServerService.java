package com.SocketTrench.Screens;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

final class IdleServerService {
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
