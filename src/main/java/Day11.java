import util.FileUtil;
import util.Grid;
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

    private static int solve1(Grid<Integer> g) {
        int flashes = 0;
        for (int step=0; step<2; step++) {
            //g.stream().forEach(t -> g.set(t.x(), t.y(), t.value()+1)); // ConcurrentModificationException :(

            // increase all
            for (int y=0;y<g.getHeight();y++) {
                for (int x = 0; x < g.getWidth(); x++) {
                    g.set(x, y, g.getV(x, y) + 1);
                }
            }

            // handled flashed
            var hasFlashed = new HashSet<Position>();
            var willFlash = new HashSet<>(g.find(p -> p.value() > 9).toList());
            while (!willFlash.isEmpty()) {
                for (var t : willFlash) {
                    g.set(t.x(), t.y(), 0); // reset
                    hasFlashed.add(new Position(t.x(), t.y()));
                    // increase neighbors
                    var ns = g.neighbors(t.x(), t.y());
                    for (var tt : ns) {
                        if (!hasFlashed.contains(new Position(tt.x(), tt.y()))) {
                            g.set(tt.x(), tt.y(), g.getV(tt.x(), tt.y()) + 1);
                            willFlash.addAll(g.find(p -> p.value() > 9).toList());
                        }
                    }
                    flashes++;
                }
                willFlash.removeIf(p -> hasFlashed.contains(new Position(p.x(), p.y())));
            }

            System.out.println("Step " + step);
            debug(g);
        }


        return flashes;
    }

    private static void debug(Grid<Integer> g) {
        for (int y=0;y<g.getHeight();y++) {
            for (int x = 0; x < g.getWidth(); x++) {
                System.out.print(g.getV(x,y));
            }
            System.out.println("");
        }
    }
}
