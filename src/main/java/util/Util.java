package util;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Util {
    // Regex parse all ints from the input
    public static List<Integer> ints(List<String> lines) {
        return ints(String.join("\r\n", lines));
    }

    public static List<Integer> ints(String s) {
        var out = new ArrayList<Integer>();
        var p = Pattern.compile("(-?\\d+)");
        var m = p.matcher(s);
        while (m.find()) {
            out.add(Integer.parseInt(m.group()));
        }
        return out;
    }

    public static long min(List<Long> l) {
        return l.stream().mapToLong(v -> v).min().orElseThrow(RuntimeException::new);
    }

    public static long max(List<Long> l) {
        return l.stream().mapToLong(v -> v).max().orElseThrow(RuntimeException::new);
    }

    public static long sum(List<Long> l) {
         return l.stream().mapToLong(v -> v).sum();
    }

    public static long product(List<Long> l) {
        return l.stream().reduce(1L, (a,b) -> a*b);
    }

    // Regex returns all matches, skipping the full match
    public static List<String> regex(String r, String s) {
        var out = new ArrayList<String>();
        var p = Pattern.compile(r);
        var m = p.matcher(s);
        while (m.find()) {
            out.add(m.group());
        }
        return out;
    }

    // From https://stackoverflow.com/questions/27583623/is-there-an-elegant-way-to-process-a-stream-in-chunks
    // Reads the whole stream and then sorts it, instead of chunking mid-stream.
    // Not optimal for huge streams.
    public static <T> Stream<List<T>> chunked(Stream<T> stream, int chunkSize) {
        AtomicInteger index = new AtomicInteger(0);

        return stream.collect(Collectors.groupingBy(x -> index.getAndIncrement() / chunkSize))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey()).map(Map.Entry::getValue);
    }
}
