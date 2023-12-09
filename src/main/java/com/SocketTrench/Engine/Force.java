package com.SocketTrench.Engine;

public final class Force {
    public int x;
    public int y;

    public Force(
        final int x,
        final int y
    ) {
        this.x = x;
        this.y = y;
    }

    @Override
    public final String toString() {
        return new StringBuilder()
            .append("Force { x: ")
            .append(this.x)
            .append(", y: ")
            .append(this.y)
            .append(" }")
            .toString();
    }
}
