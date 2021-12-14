import util.FileUtil;

import java.util.*;

public class Day10 {
    public static void main(String[] args) {
        var in = FileUtil.read("input/day10.txt");
        System.out.println(solve1(in));
        System.out.println(solve2(in));
    }

    private static int solve1(List<String> l) {
        var invalid = l.stream().map(Day10::parse).flatMap(Optional::stream).toList();
        var score = 0;
        for (var c : invalid) {
            score += switch (c) {
                case ')' -> 3;
                case ']' -> 57;
                case '}' -> 1197;
                case '>' -> 25137;
                default -> throw new RuntimeException("Unhandled");
            };
        }

        return score;
    }

    private static long solve2(List<String> l) {
        var incomplete = new ArrayList<String>();
        l.forEach(s -> {if (parse(s).isEmpty()) incomplete.add(s);});

        var scores = new ArrayList<Long>();
        for (String s : incomplete) {
            long score = 0;
            var fix = complete(s);

            for (char c : fix.toCharArray()) {
                score *= 5;
                score += " )]}>".indexOf(c);
            }
            scores.add(score);
        }

        return scores.stream().sorted().toList().get(scores.size()/2);
    }

    private static Optional<Character> parse(String s) {
        var stack = new Stack<Character>();

        for (char c : s.toCharArray()) {
            if (isOpen(c)) {
                stack.push(c);
                continue;
            }
            var c2 = stack.pop();
            if (c2 != matching(c)) {
                return Optional.of(c);
            }

        }

        return Optional.empty();
    }

    private static String complete(String s) {
        var stack = new Stack<Character>();
        var missing = "";

        for (char c : s.toCharArray()) {
            if (isOpen(c)) {
                stack.push(c);
                continue;
            }
            stack.pop();
        }
        while (!stack.empty()) {
            missing += matching(stack.pop());
        }

        return missing;
    }

    private static boolean isOpen(char c) {
        return c == '(' || c == '[' || c == '{' || c == '<';
    }

    private static char matching(char c) {
        return switch (c) {
            case '(' -> ')';
            case '[' -> ']';
            case '{' -> '}';
            case '<' -> '>';
            case ')' -> '(';
            case ']' -> '[';
            case '}' -> '{';
            case '>' -> '<';
            default -> throw new RuntimeException("Unhandled");
        };
    }
}
