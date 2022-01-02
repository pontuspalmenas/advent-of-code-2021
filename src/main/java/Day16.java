import util.FileUtil;
import util.Util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Day16 {
    record ParseResult(int value, int offset) {} // value of current packet, offset to next packet

    public static void main(String[] args) {
        var in = FileUtil.read("input/day16.txt").get(0);
        System.out.println(solve1(in));
        System.out.println(solve2(in));
    }

    private static int solve1(String s) {
        //return parse(hex2bin(s), -1);
        return -1;
    }

    private static int solve2(String s) {
        return parse(hex2bin(s), 0, -1).value;
    }

    private final static ParseResult None = new ParseResult(0, -1);

    private static int offset = 0;
    private static ParseResult parse(String data, int offset, int end) {
        if (offset == end) return None;
        if (offset > data.length()-4) return None;

        int typeId = dec(data.substring(offset+3,offset+6));

        if (typeId == 4) {
            offset += 6;
            var bin = "";
            boolean done = false;
            while (!done) {
                if (data.charAt(offset)=='0') done = true;
                bin += data.substring(offset+1,offset+5);
                offset += 5;
            }
            return new ParseResult(dec(bin), offset);
        }

        var values = new ArrayList<Integer>();
        int nextOffset = -1;

        if (data.charAt(offset+6)=='0') { // packet length in bits
            int len = dec(data.substring(offset+7,offset+22));
            int subOffset = offset+22;
            int prevOffset;
            while (true) {
                prevOffset = offset;
                var sub = parse(data, subOffset, subOffset+len);
                if (sub.offset == -1) break;
                subOffset = sub.offset;
                values.add(sub.value);
            }
            nextOffset = prevOffset;
        } else { // number of packets
            int subPacksLeft = dec(data.substring(offset+7,offset+18));
            int subOffset = offset+18;
            while (subPacksLeft > 0) {
                var sub = parse(data, subOffset, -1);
                subPacksLeft--;
                values.add(sub.value);
                nextOffset = subOffset;
            }
        }

        int result = operate(typeId, values);
        return new ParseResult(result, nextOffset);
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
