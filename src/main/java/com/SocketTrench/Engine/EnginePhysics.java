package com.SocketTrench.Engine;

public class EnginePhysics {
    public static void apply(GameObject gameObject) {
        final Body body = gameObject.getBody();
        final Force force = gameObject.getForce();
        final Square bounds = gameObject.getBounds();
        if (force == null)
            return;
        if (bounds == null) {
            body.x += force.x;
            body.y += force.y;
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
