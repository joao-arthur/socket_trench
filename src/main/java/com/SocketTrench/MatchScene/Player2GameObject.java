package com.SocketTrench.MatchScene;

import com.SocketTrench.ImageLoader;
import com.SocketTrench.App.Screen;
import com.SocketTrench.Engine.DefaultGameObject;
import com.SocketTrench.Engine.Domain.EngineState;
import com.SocketTrench.Engine.Struct.BoxDim;
import com.SocketTrench.Engine.Struct.BoxPos;
import com.SocketTrench.Engine.Struct.Point;

public final class Player2GameObject extends DefaultGameObject implements Player {
    public Player2GameObject(final boolean canInput) {
        this.body = new BoxDim(Screen.WIDTH - 58, Screen.HEIGHT - 100 - 30, 58, 30);
        this.texture = ImageLoader.fromPath("player2.png");
        this.force = new Point(0, 0);
        this.bounds = new BoxPos(Screen.WIDTH - 158, 0, Screen.WIDTH, Screen.HEIGHT);
        this.inputHandler = canInput ? new Player2InputHandler(this) : null;
    }

    @Override
    public final void shoot(final EngineState engineState) {
        engineState.create(
                new ShootGameObject(
                        this.body.x - 4,
                        this.body.y + this.body.h / 2 - 1,
                        -10));
    }

    @Override
    public final int getLifePoints() {
        return 3;
    }
}
