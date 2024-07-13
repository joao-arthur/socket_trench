package com.SocketTrench.MatchScene;

import com.SocketTrench.ImageLoader;
import com.SocketTrench.App.Screen;
import com.SocketTrench.Engine.DefaultGameObject;
import com.SocketTrench.Engine.GameObject;
import com.SocketTrench.Engine.Domain.EngineState;
import com.SocketTrench.Engine.Struct.BoxDim;
import com.SocketTrench.Engine.Struct.BoxPos;
import com.SocketTrench.Engine.Struct.Point;

public final class Player1GameObject extends DefaultGameObject implements Player {
    private int lifePoints = 3;

    public Player1GameObject(final boolean canInput) {
        this.body = new BoxDim(0, 100, 58, 30);
        this.collider = this.body;
        this.texture = ImageLoader.fromPath("player1.png");
        this.force = new Point(0, 0);
        this.bounds = new BoxPos(0, 0, 158, Screen.HEIGHT);
        this.inputHandler = canInput ? new Player1InputHandler(this) : null;
    }

    @Override
    public final void shoot(final EngineState engineState) {
        engineState.create(
            new ShootGameObject(
                this.body.x + this.body.w,
                this.body.y + this.body.h / 2 - 1,
                10
            )
        );
    }

    @Override
    public final int getLifePoints() {
        return this.lifePoints;
    }

    @Override
    public final void onCollideWith(final GameObject other, final EngineState engineState) {
        engineState.destroy(other);
        this.lifePoints -= 1;
    }
}
