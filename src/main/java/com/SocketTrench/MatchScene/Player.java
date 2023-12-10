package com.SocketTrench.MatchScene;

import com.SocketTrench.Engine.GameObject;
import com.SocketTrench.Engine.Domain.EngineState;

public interface Player extends GameObject {
    public void shoot(final EngineState engineState);

    public int getLifePoints();
}
