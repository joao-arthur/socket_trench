package com.SocketTrench.MatchScene;

import com.SocketTrench.ImageLoader;
import com.SocketTrench.Engine.DefaultGameObject;
import com.SocketTrench.Engine.Struct.BoxDim;
import com.SocketTrench.App.Screen;

public final class BackgroundGameObject extends DefaultGameObject {
    public BackgroundGameObject() {
        this.body = new BoxDim(0, 0, Screen.WIDTH, Screen.HEIGHT);
        this.texture = ImageLoader.fromPath("background.png");
    }
}
