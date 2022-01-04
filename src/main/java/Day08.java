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

            var outputs = Arrays.stream(s.split("\\|")[1].split(" ")).toList();

            var keys = keys(inputs);

            for (String o : outputs) {
                System.out.printf("%s: %d\n", o, keys.get(set(o)));
            }

            var x = 0;
        }

        throw new RuntimeException("Not Yet Implemented");
    }


    private static Map<Set<Character>, Integer> keys(Set<String> inputs) {
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
            mapper.add(set(key), val);
        }

        // Deduce the rest
        for (String s : inputs) {
            var set = set(s);
            if (mapper.mapped(set)) continue;
            int len = set.size();
            if (len == 6) { // candidates 6,9,0
                if (!contains(set,1,mapper)) {
                    mapper.add(set,6);
                } else if (contains(set,4,mapper)) {
                    mapper.add(set,9);
                } else {
                    mapper.add(set,0);
                }
            }
            if (len == 5) { // candidates 2,3,5
                if (containedIn(set,6,mapper)) {
                    mapper.add(set, 5);
                } else if (containedIn(set,9,mapper) && contains(set,1,mapper)) {
                    mapper.add(set, 3);
                } else {
                    mapper.add(set, 2);
                }
            }
        }

        debug(mapper);

        return mapper.keys();
    }

    private static Set<Character> set(String s) {
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            set.add(c);
        }
        return set;
    }

    private static void debug(Mapper mapper) {
        for (var s : mapper.keys.keySet()) {
            System.out.printf("%s: %s\n", s, mapper.get(s));
        }
    }

    private static boolean contains(Set<Character> set, int n, Mapper m) {
        return set.containsAll(m.get(n));
    }

    private static boolean containedIn(Set<Character> set, int n, Mapper m) {
        if (m.get(n)==null) return false;
        return m.get(n).containsAll(set);
    }

    static class Mapper {
        private final HashMap<Set<Character>, Integer> keys = new HashMap<>();
        private final HashMap<Integer, Set<Character>> rev = new HashMap<>();
        void add(Set<Character> s, int val) {
            keys.put(s, val);
            rev.put(val, s);
        }
        int get(Set<Character> s) {
            return keys.get(s);
        }
        boolean mapped(Set<Character> s) {
            return keys.containsKey(s);
        }
        Set<Character> get(int n) {
            return rev.get(n);
        }
        Map<Set<Character>, Integer> keys() {
            return keys;
        }
    }
}
