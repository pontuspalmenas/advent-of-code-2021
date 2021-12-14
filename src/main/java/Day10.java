import util.FileUtil;

import java.util.List;
import java.util.Optional;
import java.util.Stack;

public class Day10 {
    public static void main(String[] args) {
        var in = FileUtil.read("input/day10.txt");
        System.out.println(solve1(in));
        //System.out.println(solve2(in));
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

    private static boolean isOpen(char c) {
        return c == '(' || c == '[' || c == '{' || c == '<';
    }

    private static char matching(char c) {
        return switch (c) {
            case ')' -> '(';
            case ']' -> '[';
            case '}' -> '{';
            case '>' -> '<';
            default -> throw new RuntimeException("Unhandled");
        };
    }
}
