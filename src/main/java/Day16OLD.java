import util.FileUtil;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Day16OLD {
    record ParsePacketResult(Packet p, int nextOffset) {}

    record Packet(String s) {
        static Packet fromHex(String s) {
            return new Packet(hex2bin(s));
        }
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

        int lengthTypeID() {
            return dec(""+s.charAt(6));
        }

        // Returns sub-packet length in bits
        int subPacketsLength() {
            int n = lengthTypeID() == 0 ? 15 : 11;
            return dec(s.substring(7, 7+n));
        }

        List<Packet> subPackets() {
            var l = new ArrayList<Packet>();
            int n = lengthTypeID() == 0 ? 15 : 11;
            var substr = s.substring(7+n,7+n+subPacketsLength());

            return l;
        }

        String bin() {
            return s;
        }
    }

    public static void main(String[] args) {
        var in = FileUtil.read("input/day16.txt").get(0);
        System.out.println(solve1(in));
    }

    private static int solve1(String s) {
        var p = Packet.fromHex(s);
        System.out.println("version: " + p.version());
        System.out.println("type: " + p.type());
        System.out.println("length type: " + p.lengthTypeID());
        System.out.println("length: " + p.subPacketsLength());

        System.out.println(p.subPackets());
        return -1;
    }

    public static String hex2bin(String s) {
        return new BigInteger("1"+s, 16).toString(2).substring(1); // preserve leading zeroes
    }

    private static int dec(String s) {
        return Integer.parseInt(s, 2);
    }
}
