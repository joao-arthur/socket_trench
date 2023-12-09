package com.SocketTrench.MatchScene;

import com.SocketTrench.ImageLoader;
import com.SocketTrench.Engine.BoxDim;
import com.SocketTrench.Engine.DefaultGameObject;
import com.SocketTrench.Screens.Screen;

final class BackgroundGameObject extends DefaultGameObject {
    public BackgroundGameObject() {
        this.body = new BoxDim(0, 0, Screen.WIDTH, Screen.HEIGHT);
        this.texture = ImageLoader.fromPath("background.png");
    }
}
