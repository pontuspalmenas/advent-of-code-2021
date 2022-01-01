package util;

public record Position(int x, int y) {
    public Position move(Direction d) {
        return new Position(x+d.dx, y+d.dy);
    }

    public Position move(Direction d, int distance) {
        return new Position(x+d.dx*distance, y+d.dy*distance);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Position p2)) return false;
        return this.x == p2.x && this.y == p2.y;
    }
}
