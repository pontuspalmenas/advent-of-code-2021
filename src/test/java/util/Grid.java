package util;

import java.util.Set;

public class Grid {
    record Tile(int x, int y) {}

    private Set<Tile> tiles;
}
