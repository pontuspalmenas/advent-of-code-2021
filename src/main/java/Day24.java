import util.FileUtil;
import util.Util;

import java.util.List;
import java.util.Stack;

public class Day24 {

    /*
        The code repeats with 14 blocks of 18 operations, including "reading" input w
        We have three different registers, z, x, y
        z is a stack of (1..25) using the y value and its position in the program.
        This means we don't actually need to emulate the ALU (it's too slow anyway),
        just find the interesting parts (steps 5 and 15) of each repeating block,
        and we can solve it equationally.

        0   inp w
        1   mul x 0
        2   add x z
        3   mod x 26
        4   div z 1
        5   add x 11    increase x
        6   eql x w
        7   eql x 0
        8   mul y 0
        9   add y 25
        10  mul y x
        11  add y 1
        12  mul z y
        13  mul y 0
        14  add y w
        15  add y 8     increase y
        16  mul y x
        17  add z y
     */


    record XYPair(int x, int y) {}
    record Z(int y, int i) {}

    public static void main(String[] args) {
        var in = FileUtil.read("input/day24.txt");
        System.out.println(solve1(in));
        System.out.println(solve2(in));
    }

    private static long solve1(List<String> l) {
        return solve(l, false);
    }

    private static long solve2(List<String> l) {
        return solve(l, true);
    }

    private static long solve(List<String> l, boolean p2) {
        int[] ans = new int[14];
        var stack = new Stack<Z>();
        var pairs = pairs(l);
        for (int i=0; i<14; i++) {
            int x = pairs.get(i).x();
            int y = pairs.get(i).y();
            if (x > 0) stack.push(new Z(y, i));
            else {
                var v = stack.pop();
                y = v.y;
                int yix;
                int n;
                if (p2) {
                    n = 1;
                    yix = v.i;
                    while (n + y + x < 1) n++;
                } else {
                    n = 9;
                    yix = v.i;
                    while (n + y + x > 9) n--;
                    ans[yix] = n;
                    ans[i] = n + y + x;
                }
                ans[yix] = n;
                ans[i] = n + y + x;
            }

        }
        long r = 0;
        for (int j : ans) r = r * 10 + j;
        return r;
    }

    private static List<XYPair> pairs(List<String> l) {
        return Util.chunked(l.stream(), 18)
                .map(p -> new XYPair(
                        Integer.parseInt(p.get(5).split(" ")[2]),
                        Integer.parseInt(p.get(15).split(" ")[2])
                        )).toList();
    }
}
