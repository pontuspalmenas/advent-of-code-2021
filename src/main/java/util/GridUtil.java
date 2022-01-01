package util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GridUtil {

    public static int[][] parseIntGrid(List<String> l) {
        final int h = l.size();
        final int w = l.get(0).length();
        int[][] grid = new int[h][w];
        for (int y=0; y<h; y++) {
            for (int x=0; x<w; x++) {
                grid[y][x] = Character.getNumericValue(l.get(y).charAt(x));
            }
        }

        return grid;
    }

    public static Set<Position> diagAdjacent(int y, int x, int[][] grid) {
        final int h = grid.length;
        final int w = grid[0].length;
        var ns = new HashSet<Position>();

        for (int i=-1; i<=1; i++) {
            for (int j=-1; j<=1; j++) {
                var p = new Position(x+j, y+i);
                if (p.y()>=0 && p.y()<h && p.x()>=0 && p.x()<w && !(p.x() == x && p.y() == y)) {
                    ns.add(p);
                }
            }
        }

        return ns;
    }

    public static Set<Position> cardinalAdjacent(int y, int x, int[][] grid) {
        var ns = new HashSet<Position>();

        var p = new Position(x, y);
        testAndAdd(ns, p.move(Direction.UP), grid);
        testAndAdd(ns, p.move(Direction.DOWN), grid);
        testAndAdd(ns, p.move(Direction.LEFT), grid);
        testAndAdd(ns, p.move(Direction.RIGHT), grid);

        return ns;
    }


    public static void debug(int[][] grid) {
        for (int y=0; y<grid.length; y++) {
            for (int x=0; x<grid[y].length; x++) {
                System.out.print(grid[y][x]);
            }
            System.out.println();
        }
    }

    public static int h(int[][] grid) {
        return grid.length;
    }

    public static int w(int[][] grid) {
        return grid[0].length;
    }

    private static void testAndAdd(Set<Position> set, Position p, int[][] grid) {
        if (within(p, grid)) set.add(p);
    }

    public static boolean within(Position p, int[][] grid) {
        return p.y() >= 0 && p.y() < grid.length && p.x() >= 0 && p.x() < grid[0].length;
    }
}
