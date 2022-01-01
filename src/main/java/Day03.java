import util.FileUtil;

import java.util.List;

public class Day03 {
    public static void main(String[] args) {
        var in = FileUtil.read("input/day3.txt");
        System.out.println(solve1(in));
        System.out.println(solve2(in));
    }

    private static int solve1(List<String> l) {
        int len = l.get(0).length();
        var gamma = "";
        var epsilon = "";
        for (int i = 0; i < len; i++) {
            char c = commonBit(l, i, true);
            gamma += c;
            epsilon += c == '0' ? '1' : '0';
        }
        return Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2);
    }

    private static int solve2(List<String> l) {
        return ogr(l) * oc2(l);
    }

    private static char commonBit(List<String> l, int ix, boolean most) {
        var zeroes = l.stream().filter(p -> p.charAt(ix) == '0').count();
        var ones = l.stream().filter(p -> p.charAt(ix) == '1').count();

        if (most) return zeroes > ones ? '0' : '1';

        return (zeroes < ones || zeroes == ones) ? '0' : '1';
    }

    private static int ogr(List<String> l) {
        int len = l.get(0).length();
        for (int i = 0; i < len; i++) {
            char c = commonBit(l, i, true);
            final int ix = i;
            l = l.stream().filter(p -> p.charAt(ix) == c).toList();
        }
        return Integer.parseInt(l.get(0), 2);
    }

    private static int oc2(List<String> l) {
        int len = l.get(0).length();
        for (int i = 0; i < len; i++) {
            char c = commonBit(l, i, false);
            final int ix = i;
            l = l.stream().filter(p -> p.charAt(ix) == c).toList();
            if (l.size() == 1) break;
        }
        return Integer.parseInt(l.get(0), 2);
    }
}
