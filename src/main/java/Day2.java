import util.FileUtil;

import java.util.List;

public class Day2 {
    public static void main(String[] args) {
        var in = FileUtil.Read("input/day2.txt");
        System.out.println(Solve1(in));
        System.out.println(Solve2(in));
    }

    private static int Solve1(List<String> l) {
        int h=0, d = 0;
        for (String s : l) {
            var ss = s.split(" ");
            final int size = Integer.parseInt(ss[1]);
            switch (ss[0]) {
                case "forward" -> h += size;
                case "down" -> d += size;
                case "up" -> d -= size;
            }
        }

        return h * d;
    }

    private static int Solve2(List<String> l) {
        int h=0, d = 0;
        var aim = 0;
        for (String s : l) {
            var ss = s.split(" ");
            final int size = Integer.parseInt(ss[1]);
            switch (ss[0]) {
                case "forward" -> {
                    h += size;
                    d += size*aim;
                }
                case "down" -> aim += size;
                case "up" -> aim -= size;
            }
        }

        return h * d;
    }
}
