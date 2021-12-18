import util.FileUtil;
import util.Position;
import util.Util;

import java.util.*;
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

        for (int step = 0; step < 12; step++) {
            System.out.println("after step: " + step);
            debug(grid);

            Queue<Position> flash = new LinkedList<>();

            for (int y = 0; y < grid.length; y++) {
                for (int x = 0; x < grid[0].length; x++) {
                    grid[y][x]++;
                    if (grid[y][x] > 9) flash.add(new Position(x, y));
                }
            }

            // varför blir allt rätt efter step 1 förutom mitten, som blir 10 istället för 9?
            // efter step 2 mittens neighbors en för många, och mitten en för få :S
            while (!flash.isEmpty()) {
            var p = flash.poll();
                if (grid[p.y()][p.x()] > 9) {
                    grid[p.y()][p.x()] = 0;
                    flashes++;
                    for (var np : Util.neighbors(p.y(), p.x(), grid)) {
                        if (flash.contains(np)) continue;
                        grid[np.y()][np.x()]++;
                        if (grid[np.y()][np.x()] > 9) {
                            grid[np.y()][np.x()] = 0;
                            flash.add(np);
                        }
                    }
                }
            }
        }
        return flashes;
    }

    private static void debug(int[][] g) {
        for (int y = 0; y < g.length; y++) {
            for (int x = 0; x < g[0].length; x++) {
                System.out.print(g[y][x]);
            }
            System.out.println("");
        }
    }
}
