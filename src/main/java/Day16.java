import util.FileUtil;

import java.math.BigInteger;

public class Day16 {
    record Packet(int version, int type, )

    public static void main(String[] args) {
        var in = FileUtil.read("input/day16.txt").get(0);
        System.out.println(solve1(in));
    }

    private static int solve1(String s) {
        return -1;
    }

    public static String hex2bin(String s) {
        return new BigInteger("1"+s, 16).toString(2).substring(1); // preserve leading zeroes
    }

    private static int dec(String s) {
        return Integer.parseInt(s, 2);
    }
}
