import util.FileUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day14 {
    public static void main(String[] args) {
        var in = FileUtil.readBlocks("input/day14.txt");
        System.out.println(solve1(in));
        System.out.println(solve2(in));
    }

    private static long solve1(List<List<String>> ll) {
        return solve(ll, 10);
    }

    private static long solve2(List<List<String>> ll) {
        return solve(ll, 40);
    }

    private static Map<String, Map<String, Integer>> cache;

    /*
        bygg en lista över varje par i template
        rekursivt sök ner för varje par, expandera i n generationer
        uppdatera cachen varteftersom
     */

    private static long solve(List<List<String>> ll, int times) {
        cache = new HashMap<>();

        var s = ll.get(0).get(0);
        var map = parse(ll.get(1));
        for (int i=0;i<times;i++) {
            s = extend(s, map);
        }

        long min = Long.MAX_VALUE;
        long max = 0;
        for (int c : s.chars().distinct().boxed().toList()) {
            var count = count(s, (char)c);
            if (count < min) min = count;
            if (count > max) max = count;
        }
        return max - min;
    }

    private static long count(String s, char element) {
        return s.chars().filter(p -> p == element).count();
    }

    private static Map<String,String> parse(List<String> l) {
        var map = new HashMap<String,String>();
        for (var s : l) {
            var ss = s.split(" -> ");
            map.put(ss[0],ss[1]);
        }
        return map;
    }

    public static String extend(String s, Map<String,String> tokens) {
        var s2 = s;

        var added=0;
        for (int i=0;i<s.length()-1;i++) {
            var pair = s.substring(i, i + 2);
            if (tokens.containsKey(pair)) {
                s2 = addChar(s2, tokens.get(pair), i+1+added);
                added++;
            }
        }

        return s2;
    }

    private static String addChar(String str, String ch, int position) {
        return str.substring(0, position) + ch + str.substring(position);
    }
}