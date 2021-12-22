import util.FileUtil;
import util.Position;
import util.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day15 {
    public static void main(String[] args) {
        var in = FileUtil.read("input/day15.txt");
        System.out.println(solve1(in));
    }

    private static int solve1(List<String> l) {
        grid = Util.parseIntGrid(l);
        cache = new HashMap<>();
        return solve(0,0)-grid[0][0];
    }

    private static int[][] grid;
    private static Map<Position, Integer> cache;

    private static int solve(int y, int x) {
        var p = new Position(x, y);
        if (cache.containsKey(p)) return cache.get(p);
        if (y<0||y>=grid.length||x<0||x>=grid[0].length) return Integer.MAX_VALUE;
        if (y==grid.length-1&&x==grid[0].length-1) return grid[y][x];
        var cost = grid[y][x] + Math.min(solve(y+1, x), solve(y,x+1));
        cache.put(p, cost);
        return cost;
    }

}
