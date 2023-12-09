package com.SocketTrench.Engine;

public final class BoxDim {
    public int x;
    public int y;
    public int w;
    public int h;

    public BoxDim(
        final int x,
        final int y,
        final int w,
        final int h
    ) {
        this.w = w;
        this.h = h;
        this.x = x;
        this.y = y;
    }

    @Override
    public final String toString() {
        return new StringBuilder()
                .append("BoxDim { x: ")
                .append(this.x)
                .append(", y: ")
                .append(this.y)
                .append(", w: ")
                .append(this.w)
                .append(", h: ")
                .append(this.h)
                .append(" }")
                .toString();
    }
}
