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
        int result = 0;
        for (String s : l) {
            // pre-sort the input, need to map all len6 before len5
            var inputs = Arrays.stream(sortByLen(s.split("\\|")[0])).map(Day08::chars).toList();
            var outputs = Arrays.stream(s.split("\\| ")[1].split(" ")).map(Day08::chars).toList();

            var keys = keys(inputs);

            result += 1000 * keys.get((outputs.get(0))) +
                    100 * keys.get(outputs.get(1)) +
                    10 * keys.get(outputs.get(2)) +
                    keys.get(outputs.get(3));
        }

        return result;
    }

    private static String[] sortByLen(String s) {
        String[] ss = s.split(" ");
        Arrays.sort(ss, (a, b)->Integer.compare(b.length(), a.length()));
        return ss;
    }


    private static Map<Set<Character>, Integer> keys(List<Set<Character>> inputs) {
        var knownLengths = Map.of(2,1,4,4,3,7,7,8);

        var mapper = new Mapper();

        // First map all keys of known length
        for (int len : knownLengths.keySet()) {
            var key = inputs.stream().filter(p -> p.size() == len).findFirst().orElseThrow();
            mapper.add(key, knownLengths.get(len));
        }

        // Deduce the rest
        for (var set : inputs) {
            if (mapper.mapped(set)) continue;
            int len = set.size();
            if (len == 6) { // candidates 6,9,0
                if (!contains(set,1,mapper)) mapper.add(set,6);
                else if (contains(set,4,mapper)) mapper.add(set,9);
                else mapper.add(set,0);
            }
            if (len == 5 && !mapper.mapped(set)) { // candidates 2,3,5
                if (containedIn(set,6,mapper)) mapper.add(set, 5);
                else if (containedIn(set,9,mapper) && contains(set,1,mapper)) mapper.add(set, 3);
                else mapper.add(set, 2);
            }
        }

        return mapper.keys();
    }

    private static Set<Character> chars(String s) {
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            set.add(c);
        }
        return set;
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
