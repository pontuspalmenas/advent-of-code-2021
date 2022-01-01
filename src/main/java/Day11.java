import util.FileUtil;
import util.GridUtil;
import util.Position;

import java.util.*;

public class Day11 {
    public static void main(String[] args) {
        var in = FileUtil.read("input/day11.txt");
        System.out.println(solve1(GridUtil.parseIntGrid(in)));
        System.out.println(solve2(GridUtil.parseIntGrid(in)));
    }

    private static int solve1(int[][] grid) {
        int flashes = 0;

        for (int step = 0; step < 100; step++) {
            var flash = new Stack<Position>();

            for (int y = 0; y < grid.length; y++) {
                for (int x = 0; x < grid[0].length; x++) {
                    grid[y][x]++;
                    if (grid[y][x] > 9) flash.add(new Position(x, y));
                }
            }

            while (!flash.isEmpty()) {
                var p = flash.pop();
                if (grid[p.y()][p.x()]==0) continue;
                grid[p.y()][p.x()] = 0;
                flashes++;

                for (var np : GridUtil.diagAdjacent(p.y(),p.x(), grid)) {
                    if (grid[np.y()][np.x()]==0) continue;
                    grid[np.y()][np.x()]++;
                    if (grid[np.y()][np.x()] > 9) flash.add(np);
                }
            }
        }
        return flashes;
    }

    private static int solve2(int[][] grid) {

        int steps=0;
        while (true) {
            int flashes = 0;
            var flash = new Stack<Position>();

            for (int y = 0; y < grid.length; y++) {
                for (int x = 0; x < grid[0].length; x++) {
                    grid[y][x]++;
                    if (grid[y][x] > 9) flash.add(new Position(x, y));
                }
            }

            while (!flash.isEmpty()) {
                var p = flash.pop();
                if (grid[p.y()][p.x()]==0) continue;
                grid[p.y()][p.x()] = 0;
                flashes++;

                for (var np : GridUtil.diagAdjacent(p.y(),p.x(), grid)) {
                    if (grid[np.y()][np.x()]==0) continue;
                    grid[np.y()][np.x()]++;
                    if (grid[np.y()][np.x()] > 9) flash.add(np);
                }
            }
            steps++;
            if (flashes == 100) break;
        }
        return steps;
    }
}
