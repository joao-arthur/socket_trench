package com.SocketTrench.MatchScene;

import com.SocketTrench.ImageLoader;
import com.SocketTrench.Engine.BoxDim;
import com.SocketTrench.Engine.Force;
import com.SocketTrench.Engine.DefaultGameObject;

final class ShootGameObject extends DefaultGameObject {
    public ShootGameObject(
        final int x,
        final int y,
        final int speedX
    ) {
        this.body = new BoxDim(x, y, 58, 30);
        this.force = new Force(speedX, 0);
        this.texture = ImageLoader.fromPath("shoot.png");
    }
}
