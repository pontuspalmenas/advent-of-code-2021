import util.FileUtil;
import util.Position;

import java.util.*;
import java.util.List;

public class Day9 {
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
        for (int i=0;i<h;i++) {
            for (int j=0;j<w;j++) {
                if (lowPoint(i,j,map)) score += 1+map[i][j];
            }
        }

        return score;
    }

    private static int solve2(List<String> l) {
        var map = pad(parseMap(l), 9);
        int h = map.length;
        int w = map[0].length;

        List<Position> lows = new ArrayList<>();
        for (int i=0;i<h;i++) {
            for (int j=0;j<w;j++) {
                if (lowPoint(i,j,map)) lows.add(new Position(i,j));
            }
        }

        List<List<Integer>> basins = new ArrayList<>();
        for (Position p : lows) {
            basins.add(basin(p, map));
        }

        return -1;
    }

    private static int[][] pad(int[][] map, int with) {
        int h = map.length;
        int w = map[0].length;
        var padded = new int[h+2][w+2];
        for (int[] ints : padded) Arrays.fill(ints, with);
        for (int i=0;i<h;i++) System.arraycopy(map[i],0,padded[i+1],1,w);
        return padded;
    }

    private static List<Integer> basin(Position p, int[][] map) {
        var basin = new ArrayList<Integer>();

        int h = map.length;
        int w = map[0].length;
        //if (p.x() < 0 || p.x() > w || p.y() < 0 || p.y() > h) return;
        //if (map[p.y()][p.x()] == 9) return;


        var visited = new HashSet<Position>();
        var queue = new ArrayDeque<>(List.of(p));

        while (!queue.isEmpty()) {
            var np = queue.pop();
            if (visited.contains(np)) continue;
            visited.add(np);
            if (map[p.y()][p.x()] != 9) {
                basin.add(map[p.y()][p.x()]);
                // todo: lägg till alla neighbors i queue
            }
        }

        return basin;
    }

    private static int[][] parseMap(List<String> l) {
        int w = l.get(0).length();
        int h = l.size();
        var map = new int[h][w];

        for (int i=0;i<h;i++) {
            String s = l.get(i);
            for (int j=0;j<w;j++) {
                map[i][j] = Integer.parseInt(""+s.charAt(j));
            }
        }

        return map;
    }

    private static boolean lowPoint(int y, int x, int[][] map) {
        int h = map.length;
        int w = map[0].length;
        for (int i=-1;i<2;i++) {
            if (i+y<0 || i+y>h-1) continue;
            for (int j=-1;j<2;j++) {
                if (j+x<0 || j+x>w-1) continue;
                if (map[i+y][j+x] < map[y][x]) return false;
            }
        }

        return true;
    }
}
