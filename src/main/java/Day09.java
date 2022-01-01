import util.Direction;
import util.FileUtil;
import util.Position;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

// Smoke Basin
public class Day09 {
    public static void main(String[] args) {
        var in = FileUtil.read("input/day9.txt");
        System.out.println(solve1(in));
        System.out.println(solve2(in));
    }

    private static int solve1(List<String> l) {
        var map = parseMap(l);
        int h = map.length;
        int w = map[0].length;

        int score = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (lowPoint(i, j, map)) {
                    score += 1 + map[i][j];
                }
            }
        }

        return score;
    }

    private static int solve2(List<String> l) {
        var map = pad(parseMap(l), 9);
        int h = map.length;
        int w = map[0].length;

        List<Position> lows = new ArrayList<>();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (lowPoint(i, j, map)) lows.add(new Position(j, i));
            }
        }

        List<List<Integer>> basins = new ArrayList<>();
        for (Position p : lows) {
            basins.add(basin(p, map));
        }

        final List<List<Integer>> largest = basins.stream()
                .sorted(Comparator.comparingInt(List::size))
                .toList().subList(basins.size()-3,basins.size());

        return largest.get(0).size()*largest.get(1).size()*largest.get(2).size();
    }

    private static int[][] pad(int[][] map, int with) {
        int h = map.length;
        int w = map[0].length;
        var padded = new int[h + 2][w + 2];
        for (int[] ints : padded) Arrays.fill(ints, with);
        for (int i = 0; i < h; i++) System.arraycopy(map[i], 0, padded[i + 1], 1, w);
        return padded;
    }

    private static List<Integer> basin(Position lowPoint, int[][] map) {
        var basin = new ArrayList<Integer>();

        var visited = new HashSet<Position>();
        var queue = new ArrayDeque<>(List.of(lowPoint));

        // BFS to find local basin walled of by 9's
        while (!queue.isEmpty()) {
            var np = queue.pop();
            if (visited.contains(np)) continue;
            visited.add(np);
            if (map[np.y()][np.x()] != 9) {
                basin.add(map[np.y()][np.x()]);
                // queue all unvisited neighbors
                queue.addAll(neighbors(np, map).stream().filter(p -> !visited.contains(p)).toList());
            }
        }

        return basin;
    }


    private static Set<Position> neighbors(Position p, int[][] map) {
        var nps = List.of(p.move(Direction.LEFT), p.move(Direction.RIGHT), p.move(Direction.UP), p.move(Direction.DOWN));
        return nps.stream().filter(np -> np.x() > 0 && np.x() < map[0].length && np.y() > 0 && np.y() < map.length).collect(Collectors.toSet());
    }

    private static int[][] parseMap(List<String> l) {
        int w = l.get(0).length();
        int h = l.size();
        var map = new int[h][w];

        for (int i = 0; i < h; i++) {
            String s = l.get(i);
            for (int j = 0; j < w; j++) {
                map[i][j] = Integer.parseInt("" + s.charAt(j));
            }
        }

        return map;
    }

    private static boolean lowPoint(int y, int x, int[][] map) {
        if (map[y][x] == 9) return false;
        int h = map.length;
        int w = map[0].length;
        for (int i = -1; i < 2; i++) {
            if (i + y < 0 || i + y > h - 1) continue;
            for (int j = -1; j < 2; j++) {
                if (j + x < 0 || j + x > w - 1) continue;
                if (map[i + y][j + x] < map[y][x]) return false;
            }
        }

        return true;
    }
}
