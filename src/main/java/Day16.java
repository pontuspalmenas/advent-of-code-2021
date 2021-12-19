import util.FileUtil;

public class Day16 {
    record Packet(String s) {
        int version() {
            return dec(s.substring(0,3));
        }

        int type() {
            return dec(s.substring(3,6));
        }

        int value() {
            var sub = s.substring(6);
            var v = "";
            boolean last=false;
            for (int i=0;i<sub.length();i+=5) {
                if (last) break;
                if (sub.charAt(i)=='0') last=true;
                v += sub.substring(i+1,i+5);
            }
            return dec(v);
        }
    }

    public static void main(String[] args) {
        var in = FileUtil.read("input/day14.txt").get(0);
        System.out.println(solve1(in));
    }

    private static int solve1(String s) {
        return -1;
    }

    public static String hex2bin(String s) {
        return Integer.toBinaryString(Integer.parseInt(s, 16));
    }
    
    private static int dec(String s) {
        return Integer.parseInt(s, 2);
    }
}
