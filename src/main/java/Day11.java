import util.FileUtil;
import util.Position;
import util.Util;

import java.util.HashSet;
import java.util.Set;

public class Day11 {
    public static void main(String[] args) {
        var in = FileUtil.read("input/day11.txt");
        var g = Util.parseIntGrid(in);
        System.out.println(solve1(g));
    }

    private static int solve1(int[][] grid) {
        int flashes = 0;
        debug(grid);

        for (int step=0;step<1;step++) {
            var willFlash = new HashSet<>(shouldFlash(grid));
            

            while (!willFlash.isEmpty()) {

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
