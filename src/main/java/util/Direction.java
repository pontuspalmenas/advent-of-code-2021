package util;

// We use a coordinate starting at 0,0 in the top left corner
public enum Direction {
    DOWN(0,1),
    UP(0,-1),
    LEFT(-1,0),
    RIGHT(1,0);

    public final int dx, dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
}
