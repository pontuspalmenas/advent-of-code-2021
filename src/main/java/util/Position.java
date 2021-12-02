package util;

public record Position(int x, int y) {
    public Position Move(Direction d) {
        return new Position(x+d.dx, y+d.dy);
    }

    public Position Move(Direction d, int distance) {
        return new Position(x+d.dx*distance, y+d.dy*distance);
    }
}
