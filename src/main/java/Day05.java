import util.Direction;
import util.FileUtil;
import util.Position;
import util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

public class Day05 {
    public static void main(String[] args) {
        var in = FileUtil.read("input/day5.txt");
        System.out.println(solve1(in));
        System.out.println(solve2(in));
    }

    private static long solve1(List<String> l) {
        return solve(l, false);
    }

    private static long solve2(List<String> l) {
        return solve(l, true);
    }

    private static long solve(List<String> l, boolean part2) {
        var map = new HashMap<Position, Integer>();

        for (String s : l) {
            var points = Util.ints(s);
            var p1 = new Position(points.get(0), points.get(1));
            var p2 = new Position(points.get(2), points.get(3));
            var line = line(p1, p2, part2);

            for (Position p : line) {
                if (map.containsKey(p)) map.put(p, map.get(p) + 1);
                else map.put(p, 1);
            }
        }

        return map.values().stream().filter(p -> p >= 2).count();
    }

    private static List<Position> line(Position p1, Position p2, boolean allowDiagonal) {
        var list = new ArrayList<Position>();
        if (p1.x() == p2.x()) { // vertical
            ((p1.y() < p2.y()) ? IntStream.rangeClosed(p1.y(), p2.y()) : IntStream.rangeClosed(p2.y(), p1.y()))
                    .forEach(n -> list.add(new Position(p1.x(), n)));
        }
        else if (p1.y() == p2.y()) { // horizontal
            ((p1.x() < p2.x()) ? IntStream.rangeClosed(p1.x(), p2.x()) : IntStream.rangeClosed(p2.x(), p1.x()))
                    .forEach(n -> list.add(new Position(n, p1.y())));
        }
        else if (allowDiagonal) {
            list.add(p1);
            var np = p1;
            while (np.x() != p2.x() && np.y() != p2.y()) {
                if (p2.y() > p1.y()) np = np.move(Direction.DOWN);
                if (p2.y() < p1.y()) np = np.move(Direction.UP);
                if (p2.x() > p1.x()) np = np.move(Direction.RIGHT);
                if (p2.x() < p1.x()) np = np.move(Direction.LEFT);

                list.add(np);
            }
        }

        return list;
    }
}
