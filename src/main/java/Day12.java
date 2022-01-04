import util.DefaultDict;
import util.FileUtil;

import java.util.*;

public class Day12 {
    public static void main(String[] args) {
        var in = FileUtil.read("input/day12.txt");
        System.out.println(solve1(in));
        System.out.println(solve2(in));
    }

    private static long solve1(List<String> l) {
        var edges = parse(l);
        var todo = new ArrayDeque<List<String>>();
        todo.add(List.of("start"));
        var allPaths = new HashSet<List<String>>();
        while (!todo.isEmpty()) {
            var path = todo.pop();

            if (path.get(path.size()-1).equals("end")) {
                allPaths.add(path);
                continue;
            }

            for (String cave : edges.get(path.get(path.size()-1))) {
                if (!cave.toLowerCase().equals(cave) || !path.contains(cave)) {
                    var temp = new ArrayList<>(path);
                    temp.add(cave);
                    todo.add(temp);
                }
            }
        }

        return allPaths.size();
    }

    private static long solve2(List<String> l) {
        var edges = parse(l);

        var todo = new ArrayDeque<List<String>>();
        todo.add(List.of("start"));
        var allPaths = new HashSet<List<String>>();

        while (!todo.isEmpty()) {
            var path = todo.pop();

            if (path.get(path.size()-1).equals("end")) {
                allPaths.add(path);
                continue;
            }

            for (String cave : edges.get(path.get(path.size()-1))) {
                if (cave.equals("start")) continue;
                if (!isLowerTwice(path) || isUpper(cave) || !path.contains(cave)) {
                    var temp = new ArrayList<>(path);
                    temp.add(cave);
                    todo.add(temp);
                }
            }
        }

        return allPaths.size();
    }

    private static boolean isLowerTwice(List<String> path) {
        return path.stream()
                .filter(Day12::isLower)
                .anyMatch(s -> Collections.frequency(path, s) > 1);
    }

    private static boolean isUpper(String s) {
        return s.toUpperCase().equals(s);
    }
    private static boolean isLower(String s) {
        return s.toLowerCase().equals(s);
    }

    private static Map<String, List<String>> parse(List<String> l) {
        var edges = new DefaultDict<String, List<String>>(ArrayList.class);
        for (var s : l) {
            var ss = s.split("-");
            edges.get(ss[0]).add(ss[1]);
            edges.get(ss[1]).add(ss[0]);
        }
        return edges;
    }
}
