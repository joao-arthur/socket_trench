package com.SocketTrench.Engine;

public final class BoxPos {
    public int x1;
    public int y1;
    public int x2;
    public int y2;

    public BoxPos(
        final int x1,
        final int x2,
        final int y1,
        final int y2
    ) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    @Override
    public final String toString() {
        return new StringBuilder()
                .append("BoxPos { x1: ")
                .append(this.x1)
                .append(", y1: ")
                .append(this.y1)
                .append(", x2: ")
                .append(this.x2)
                .append(", y2: ")
                .append(this.y2)
                .append(" }")
                .toString();
    }
}
