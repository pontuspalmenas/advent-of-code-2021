import util.FileUtil;
import util.Position;
import util.Util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.IntStream;

public class Day11 {
    public static void main(String[] args) {
        var in = FileUtil.read("input/day11.txt");
        var g = Util.parseIntGrid(in);
        System.out.println(solve1(g));
    }

    private static int solve1(int[][] grid) {
        int flashes = 0;

        for (int step = 0; step < 2; step++) {
            System.out.println("after step: " + step);
            debug(grid);

            for (int y = 0; y < grid.length; y++) {
                for (int x = 0; x < grid[0].length; x++) {
                    grid[y][x]++;
                }
            }

            var willFlash = new HashSet<>(shouldFlash(grid));

            while (!willFlash.isEmpty()) {
                var it = willFlash.iterator();
                var addLater = new ArrayList<Position>();
                while (it.hasNext()) {
                    var p = it.next();

                    for (var np : Util.neighbors(p.y(), p.x(), grid)) {
                        if (!willFlash.contains(np)) {
                            grid[np.y()][np.x()]++;
                            if (grid[np.y()][np.x()] > 9) {
                                addLater.add(np);
                            }
                        }
                    }
                    grid[p.y()][p.x()] = 0;
                    it.remove();
                }
                willFlash.addAll(addLater);
            }
        }
        return flashes;
    }

    private static Set<Position> shouldFlash(int[][] g) {
        var s = new HashSet<Position>();
        for (int y=0;y<g.length;y++) {
            for (int x=0; x<g[0].length; x++) {
                if (g[y][x] > 9) s.add(new Position(x,y));
            }
        }
        return s;
    }

    private static void debug(int[][] g) {
        for (int y=0;y<g.length;y++) {
            for (int x=0; x<g[0].length; x++) {
                System.out.print(g[y][x]);
            }
            System.out.println("");
        }
    }
}
