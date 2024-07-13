package com.SocketTrench.Engine.Domain;

import com.SocketTrench.Engine.GameObject;
import com.SocketTrench.Engine.Struct.BoxDim;
import com.SocketTrench.Engine.Struct.BoxPos;
import com.SocketTrench.Engine.Struct.Point;

final class EnginePhysics {
    public static void apply(GameObject gameObject) {
        final BoxDim body = gameObject.getBody();
        final Point force = gameObject.getForce();
        final BoxPos bounds = gameObject.getBounds();
        if (force == null) {
            return;
        }
        body.x += force.x;
        body.y += force.y;
        if (bounds == null) {
            return;
        }
        if (body.x < bounds.x1) {
            body.x = bounds.x1;
        }
        if (body.x > bounds.x2 - body.w) {
            body.x = bounds.x2 - body.w;
        }
        if (body.y < bounds.y1) {
            body.y = bounds.y1;
        }
        if (body.y > bounds.y2 - body.h) {
            body.y = bounds.y2 - body.h;
        }
    }
}
