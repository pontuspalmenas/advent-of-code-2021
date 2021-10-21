package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class Util {
    // Regex parse all ints from the input
    public static List<Integer> Ints(List<String> lines) {
        return Ints(String.join("\r\n", lines));
    }

    public static List<Integer> Ints(String s) {
        var out = new ArrayList<Integer>();
        var p = Pattern.compile("(-?\\d+)");
        var m = p.matcher(s);
        while (m.find()) {
            out.add(Integer.parseInt(m.group()));
        }
        return out;
    }

    public static int Min(List<Integer> l) {
        return l.stream().mapToInt(v -> v).min().orElseThrow(RuntimeException::new);
    }

    public static int Max(List<Integer> l) {
        return l.stream().mapToInt(v -> v).max().orElseThrow(RuntimeException::new);
    }

    public static int Sum(List<Integer> l) {
         return l.stream().mapToInt(v -> v).sum();
    }

    // Regex returns all matches, skipping the full match
    public static List<String> Regex(String r, String s) {
        var out = new ArrayList<String>();
        var p = Pattern.compile(r);
        var m = p.matcher(s);
        while (m.find()) {
            out.add(m.group());
        }
        return out;
    }

    // RegexAll returns all submatches, skipping the full match
}
