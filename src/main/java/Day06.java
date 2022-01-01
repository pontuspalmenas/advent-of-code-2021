import util.FileUtil;
import util.Util;

import java.util.Arrays;
import java.util.List;

public class Day06 {

    public static void main(String[] args) {
        var in = Util.ints(FileUtil.read("input/day6.txt"));
        System.out.println(solve1(in));
        System.out.println(solve2(in));
    }

    private static long solve1(List<Integer> l) {
        return solve(l, 80);
    }

    private static long solve2(List<Integer> l) {
        return solve(l, 256);
    }

    private static long solve(List<Integer> l, int days) {
        long[] arr = new long[9];
        for (int n : l) {
            arr[n]++;
        }

        for (int day=0; day<days;day++) {
            var newFish = arr[0];
            System.arraycopy(arr, 1, arr, 0, 8);
            arr[6] += newFish;
            arr[8] = newFish;
        }

        return Arrays.stream(arr).sum();
    }
}
