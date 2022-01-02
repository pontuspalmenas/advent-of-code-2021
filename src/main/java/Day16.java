import util.FileUtil;
import util.Util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Day16 {
    record Packet(int version, int type, List<Long> values) {}
    record ParseResult(int value, int offset) {} // value of current packet, offset to next packet

    public static void main(String[] args) {
        var in = FileUtil.read("input/day16.txt").get(0);
        System.out.println(solve1(in));
    }

    private static int solve1(String s) {
        return parse(hex2bin(s), -1);
    }

    private static int parse(String s, int count) {
        if (s.length() == 0 || !s.contains("1")) return 0;
        if (count == 0) return parse(s, -1);

        int ver = dec(s.substring(0,3));
        int tid = dec(s.substring(3,6));

        if (tid == 4) {
            int i = 6;
            boolean done = false;
            while (!done) {
                if (s.charAt(i) == '0') done = true;
                i += 5;
            }
            return ver + parse(s.substring(i), count-1);
        }

        // handle operator

        if (s.charAt(6) == '0') { // packet length in bits
            var len = dec(s.substring(7,22));
            return ver + parse(s.substring(22, 22+len), -1) + parse(s.substring(22+len), count-1);
        } else { // number of packets
            return ver + parse(s.substring(18), dec(s.substring(7,18)));
        }
    }

    private static int operate(int typeId, List<Integer> values) {
        return switch (typeId) {
            case 0 -> Util.sum(values);
            case 1 -> Util.product(values);
            case 2 -> Util.min(values);
            case 3 -> Util.max(values);
            case 5 -> values.get(0) > values.get(1) ? 1 : 0;
            case 6 -> values.get(0) < values.get(1) ? 1 : 0;
            case 7 -> values.get(0).equals(values.get(1)) ? 1 : 0;
            default -> throw new RuntimeException("Unhandled");
        };
    }

    public static String hex2bin(String s) {
        return new BigInteger("1"+s, 16).toString(2).substring(1); // preserve leading zeroes
    }

    private static int dec(String s) {
        return Integer.parseInt(s, 2);
    }
}
