import util.FileUtil;
import util.Util;

import java.util.List;

public class Day7 {
    public static void main(String[] args) {
        var in = Util.ints(FileUtil.read("input/day7.txt"));
        System.out.println(solve1(in));
        System.out.println(solve2(in));
    }

    private static long solve1(List<Integer> l) {
        return solve(l, false);
    }

    private static long solve2(List<Integer> l) {
        return solve(l, true);
    }

    private static long solve(List<Integer> l, boolean part2) {
        int min = Integer.MAX_VALUE;
        for (int i=0; i<Util.max(l);i++) {
            int cost = 0;
            for (int n : l) {
                int dist = Math.abs(n - i);
                cost += part2 ? (int)(0.5*dist*(dist+1)) : dist;
            }
            if (cost < min) {
                min = cost;
            }
        }

        return min;
    }
}
