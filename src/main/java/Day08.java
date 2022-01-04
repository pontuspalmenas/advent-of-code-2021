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
    lengths:
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

        var mapper = new Mapper();

        // First map all keys of known length
        for (int len : knownLengths.keySet()) {
            int val = knownLengths.get(len);
            String key = inputs.stream().filter(s -> s.length() == len).findFirst().orElseThrow();
            mapper.add(key, val);
        }

        // Deduce the rest
        for (String s : inputs) {
            if (mapper.mapped(s)) continue;
            int len = s.length();
            if (len == 6) {
                if (!contains(s,1,mapper)) {
                    mapper.add(s,6);
                } else if (contains(s,4,mapper)) {
                    mapper.add(s,9);
                } else {
                    mapper.add(s,0);
                }
            }
            if (len == 5) { // candidates 2,3,5
                if (containedIn(s,6,mapper)) { // 5 contained in 6
                    mapper.add(s, 5);
                } else if (containedIn(s,9,mapper) && contains(s,1,mapper)) {
                    mapper.add(s, 3);
                } else {
                    mapper.add(s, 2);
                }
            }
        }

        debug(mapper);

        return mapper.keys();
    }

    private static void debug(Mapper mapper) {
        for (String s : mapper.keys.keySet()) {
            System.out.printf("%s: %s\n", s, mapper.get(s));
        }
    }

    private static boolean contains(String s, int n, Mapper m) {
        var l = charset(s, n, m);
        return l.get(0).containsAll(l.get(1));
    }

    private static boolean containedIn(String s, int n, Mapper m) {
        var l = charset(s, n, m);
        return l.get(1).containsAll(l.get(0));
    }

    private static List<Set<Character>> charset(String s, int n, Mapper m) {
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            set.add(c);
        }
        Set<Character> set2 = new HashSet<>();
        if (m.get(n) != null) {
            for (char c : m.get(n).toCharArray()) {
                set2.add(c);
            }
        }
        var l = new ArrayList<Set<Character>>();
        l.add(set);
        l.add(set2);
        return l;
    }

    static class Mapper {
        private final HashMap<String, Integer> keys = new HashMap<>();
        private final HashMap<Integer, String> rev = new HashMap<>();
        void add(String s, int val) {
            keys.put(s, val);
            rev.put(val, s);
        }
        int get(String s) {
            return keys.get(s);
        }
        boolean mapped(String s) {
            return keys.containsKey(s);
        }
        String get(int n) {
            return rev.get(n);
        }
        Map<String, Integer> keys() {
            return keys;
        }
    }
}
