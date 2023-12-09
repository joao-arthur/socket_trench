package com.SocketTrench.MatchScene;

import com.SocketTrench.ImageLoader;
import com.SocketTrench.Engine.DefaultGameObject;
import com.SocketTrench.Engine.Struct.BoxDim;
import com.SocketTrench.Engine.Struct.Point;

final class ShootGameObject extends DefaultGameObject {
    public ShootGameObject(
        final int x,
        final int y,
        final int speedX
    ) {
        this.body = new BoxDim(x, y, 58, 30);
        this.force = new Point(speedX, 0);
        this.texture = ImageLoader.fromPath("shoot.png");
    }
}
