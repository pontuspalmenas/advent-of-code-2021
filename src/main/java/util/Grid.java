package util;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/*
   -------------------
   | 0,0 | 1,0 | 2,0 |
   |-----------------|
   | 0,1 | 1,1 | 2,1 |
   |-----------------|
   | 0,2 | 1,2 | 2,2 |
   -------------------
 */

public class Grid<T> {
    record Tile<T>(int x, int y, T value) {
    }

    private final int width, height;

    private final Set<Tile<T>> tiles;

    public Grid(int w, int h) {
        this.width = w;
        this.height = h;
        this.tiles = new HashSet<>(w*h);
    }

    public void set(int x, int y, T value) {
        if (x < 0 || x > width || y < 0 || y > height) throw new RuntimeException("Out of bounds");
        tiles.add(new Tile<>(x, y, value));
    }

    public T get(int x, int y) {
        final Optional<Tile<T>> tO = tiles.stream().filter(p -> p.x == x && p.y == y).findFirst();
        if (tO.isEmpty()) throw new RuntimeException("Out of bounds");
        return tO.get().value();
    }

    public Optional<Tile<T>> find(int x, int y) {
        return tiles.stream().filter(p -> p.x == x && p.y == y).findFirst();
    }

    // todo: return .value() instead of Tile
    // returns neighbors, including diagonally adjacent for a maximum possible of 8 elements
    public Set<Tile<T>> neighbors(int x, int y) {
        return tiles.stream().filter(p ->
                        (p.x == x-1 && p.y == y) ||
                        (p.x == x+1 && p.y == y) ||
                        (p.x == x && p.y == y-1) ||
                        (p.x == x && p.y == y+1) ||
                        (p.x-1 == x && p.y == y-1) ||
                        (p.x-1 == x && p.y == y+1) ||
                        (p.x+1 == x && p.y == y-1) ||
                        (p.x+1 == x && p.y == y+1))
                .collect(Collectors.toSet());
    }

}
