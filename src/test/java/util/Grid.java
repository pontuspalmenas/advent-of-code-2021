package util;

import java.util.HashSet;
import java.util.List;
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

    private Set<Tile<T>> tiles;

    public Grid(int w, int h) {
        this.width = w;
        this.height = h;
    }

    public void add(int x, int y, T value) {
        if (x < 0 || x > width || y < 0 || y > height) throw new RuntimeException("Out of bounds");
        tiles.add(new Tile<T>(x, y, value));
    }

    public Tile<T> get(int x, int y) {
        return tiles.stream().filter(p -> p.x == x && p.y == y).findFirst().orElseThrow();
    }

    public Optional<Tile<T>> find(int x, int y) {
        return tiles.stream().filter(p -> p.x == x && p.y == y).findFirst();
    }

    public Set<Tile<T>> neighbors(Tile<T> t) {
        return tiles.stream().filter(p ->
                        (p.x == t.x-1 && p.y == t.y) ||
                        (p.x == t.x+1 && p.y == t.y) ||
                        (p.x == t.x && p.y == t.y-1) ||
                        (p.x == t.x && p.y == t.y+1))
                .collect(Collectors.toSet());
    }

}
