package com.SocketTrench;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public final class ImageLoader {
    public static Image fromPath(final String path) {
        final URL url = ImageLoader.class.getClassLoader().getResource(path);
        return new ImageIcon(url).getImage();
    }
}
