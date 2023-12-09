package com.SocketTrench.Engine;

final class EnginePhysics {
    public static void apply(GameObject gameObject) {
        final BoxDim body = gameObject.getBody();
        final Force force = gameObject.getForce();
        final BoxPos bounds = gameObject.getBounds();
        if (force == null)
            return;
        if (bounds == null) {
            body.x += force.x;
            body.y += force.y;
            return;
        }
        if (body.x < bounds.x1) {
            body.x = bounds.x1;
        }
        if (body.x > bounds.x2) {
            body.x = bounds.x2;
        }
        if (body.y < bounds.y1) {
            body.y = bounds.y1;
        }
        if (body.y > bounds.y2) {
            body.y = bounds.y2;
        }
    }
}
