package com.SocketTrench.Engine;

public final class Point {
    public int x;
    public int y;

    public Point(
        final int x,
        final int y
    ) {
        this.x = x;
        this.y = y;
    }

    @Override
    public final String toString() {
        return new StringBuilder()
            .append("Point { x: ")
            .append(this.x)
            .append(", y: ")
            .append(this.y)
            .append(" }")
            .toString();
    }
}
