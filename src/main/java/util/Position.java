package util;

public record Position(int x, int y) {
    public Position move(Direction d) {
        return new Position(x+d.dx, y+d.dy);
    }

    public Position move(Direction d, int distance) {
        return new Position(x+d.dx*distance, y+d.dy*distance);
    }
}
