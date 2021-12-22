package util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

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

    public static int min(List<Integer> l) {
        return l.stream().mapToInt(v -> v).min().orElseThrow(RuntimeException::new);
    }

    public static int max(List<Integer> l) {
        return l.stream().mapToInt(v -> v).max().orElseThrow(RuntimeException::new);
    }

    public static int sum(List<Integer> l) {
         return l.stream().mapToInt(v -> v).sum();
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

    public static int[][] parseIntGrid(List<String> l) {
        final int h = l.size();
        final int w = l.get(0).length();
        int[][] grid = new int[h][w];
        for (int y=0; y<h; y++) {
            for (int x=0; x<w; x++) {
                grid[y][x] = Character.getNumericValue(l.get(y).charAt(x));
            }
        }

        return grid;
    }

    public static Set<Position> neighbors(int y, int x, int[][] grid) {
        final int h = grid.length;
        final int w = grid[0].length;
        var ns = new HashSet<Position>();

        for (int i=-1; i<=1; i++) {
            for (int j=-1; j<=1; j++) {
                var p = new Position(x+j, y+i);
                if (p.y()>=0 && p.y()<h && p.x()>=0 && p.x()<w && !(p.x() == x && p.y() == y)) {
                    ns.add(p);
                }
            }
        }

        return ns;
    }

    public static void debug(int[][] grid) {
        for (int y=0; y<grid.length; y++) {
            for (int x=0; x<grid[y].length; x++) {
                System.out.print(grid[y][x]);
            }
            System.out.println();
        }
    }

    public static int h(int[][] grid) {
        return grid.length;
    }

    public static int w(int[][] grid) {
        return grid[0].length;
    }
}
