import util.FileUtil;

import java.util.*;

// Seven Segment Search
public class Day08 {
    public static void main(String[] args) {
        var in = FileUtil.read("input/day8.txt");
        System.out.println(solve1(in));
        System.out.println(solve2(in));
    }

    private static long solve1(List<String> l) {
        return l.stream()
                .map(s -> s.split("\\|")[1])
                .map(s -> s.split(" "))
                .flatMap(a -> Arrays.stream(a).filter(s -> Arrays.asList(2,3,4,7).contains(s.length())))
                .count();
    }

    private static long solve2(List<String> l) {
        for (String s : l) {
            var inputs = Set.copyOf(Arrays.stream(s.split("\\|")[0].split(" ")).toList());
            var outputs = s.split("\\|")[1];

            var keys = keys(inputs);
            var x = 0;
        }

        throw new RuntimeException("Not Yet Implemented");
    }

    /*
        0 -> 6
        1 -> 2
        2 -> 5
        3 -> 5
        4 -> 4
        5 -> 5
        6 -> 6
        7 -> 3
        8 -> 7
        9 -> 6
     */

    private static Map<String, Integer> keys(Set<String> inputs) {
        // 1 -> 2
        // 4 -> 4
        // 7 -> 3
        // 8 -> 7
        var knownLengths = Map.of(2,1,4,4,3,7,7,8);

        var keys = new HashMap<String, Integer>();
        var reverse = new HashMap<Integer, String>();
        var unmapped = new HashSet<>(inputs);

        // First map all keys of known length
        for (int len : knownLengths.keySet()) {
            int val = knownLengths.get(len);
            String key = unmapped.stream().filter(s -> s.length() == len).findFirst().orElseThrow();
            keys.put(key, val);
            reverse.put(val, key);
            unmapped.remove(key);
        }

        // Deduce the rest
        while (true) {
            if (unmapped.isEmpty()) break;
        }

        return keys;
    }

    private static String deduceNext(Set<String> unmapped, Map<Integer, String> reverse) {
        return "";
    }

    private static boolean containsAnyPermutation(String s) {
        for (String p : permute(s)) {
            if (s.contains(p)) return true;
        }

        return false;
    }

    public static Set<String> permute(String str) {
        Set<String> set = new HashSet<String>();

        // check if string is null
        if (str == null) {
            return null;
        } else if (str.length() == 0) {
            set.add("");
            return set;
        }

        char first = str.charAt(0);
        String sub = str.substring(1);

        Set<String> words = permute(sub);

        for (String strNew : words) {
            for (int i = 0;i<=strNew.length();i++){
                set.add(strNew.substring(0, i) + first + strNew.substring(i));
            }
        }
        return set;
    }
}
