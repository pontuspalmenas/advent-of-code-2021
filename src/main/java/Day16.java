import util.FileUtil;
import util.Util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Day16 {
    record ParseResult(long value, int offset) {} // value of current packet, offset to next packet

    public static void main(String[] args) {
        var in = FileUtil.read("input/day16.txt").get(0);
        System.out.println(solve1(in));
        System.out.println(solve2(in));
    }

    private static int solve1(String s) {
        return parse(hex2bin(s), -1);
    }

    private static long solve2(String s) {
        return parse(hex2bin(s), 0, -1).get().value;
    }

    // Solves part 2
    private static Optional<ParseResult> parse(String data, int offset, int end) {
        if (offset == end) return Optional.empty();
        if (offset >= data.length()-4) return Optional.empty();

        int typeId = (int)dec(data.substring(offset+3,offset+6));

        if (typeId == 4) {
            offset += 6;
            var bin = "";
            boolean done = false;
            while (!done) {
                if (data.charAt(offset)=='0') done = true;
                bin += data.substring(offset+1,offset+5);
                offset += 5;
            }
            return Optional.of(new ParseResult(dec(bin), offset));
        }

        var values = new ArrayList<Long>();
        int nextOffset = -1;

        if (data.charAt(offset+6)=='0') { // packet length in bits
            int len = (int)dec(data.substring(offset+7,offset+22));
            int subOffset = offset+22;
            int prevOffset;
            while (true) {
                prevOffset = subOffset;
                var sub = parse(data, subOffset, offset+22+len);
                if (sub.isEmpty()) break;
                subOffset = sub.get().offset;
                values.add(sub.get().value);
            }
            nextOffset = prevOffset;
        } else { // number of packets
            long subPacksLeft = dec(data.substring(offset+7,offset+18));
            int subOffset = offset+18;
            while (subPacksLeft > 0) {
                var sub = parse(data, subOffset, -1);
                subPacksLeft--;
                values.add(sub.get().value);
                subOffset = sub.get().offset;
                nextOffset = subOffset;
            }
        }

        long result = operate(typeId, values);
        return Optional.of(new ParseResult(result, nextOffset));
    }

    // Solves part 1
    private static int parse(String s, int count) {
        if (s.length() == 0 || !s.contains("1")) return 0;
        if (count == 0) return parse(s, -1);

        int ver = (int)dec(s.substring(0,3));
        int tid = (int)dec(s.substring(3,6));

        if (tid == 4) {
            int i = 6;
            boolean done = false;
            while (!done) {
                if (s.charAt(i) == '0') done = true;
                i += 5;
            }
            return ver + parse(s.substring(i), count-1);
        }

        if (s.charAt(6) == '0') { // packet length in bits
            var len = (int)dec(s.substring(7,22));
            return ver + parse(s.substring(22, 22+len), -1) + parse(s.substring(22+len), count-1);
        } else { // number of packets
            return ver + parse(s.substring(18), (int)dec(s.substring(7,18)));
        }
    }

    private static long operate(int typeId, List<Long> values) {
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

    private static long dec(String s) {
        return Long.parseLong(s, 2);
    }
}
