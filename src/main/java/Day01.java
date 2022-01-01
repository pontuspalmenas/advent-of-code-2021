import util.FileUtil;
import util.Util;

import java.util.List;

public class Day01 {
    public static void main(String[] args) {
        var in = Util.ints(FileUtil.read("input/day1.txt"));
        System.out.println(solve1(in));
        System.out.println(solve2(in));
    }

    private static int solve1(List<Integer> l) {
        int prev = l.get(0);
        int count = 0;
        for (int i : l) {
            if (i > prev) {
                count++;
            }
            prev = i;
        }
        return count;
    }

    private static int solve2(List<Integer> l) {
        int prev = 0;
        int count = 0;
        for (int i = 0; i < l.size()-2; i++) {
            int sum = l.get(i) + l.get(i+1) + l.get(i+2);
            if (prev != 0 && sum>prev) {
                count++;
            }
            prev = sum;
        }
        return count;
    }

}
