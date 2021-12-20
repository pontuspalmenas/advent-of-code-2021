import util.FileUtil;
import util.Position;

import java.util.*;

public class Day20 {
    public static void main(String[] args) {
        var in = FileUtil.readBlocks("input/day20.txt");
        System.out.println(solve1(in.get(0).get(0), in.get(1)));
    }

    record Image(Set<Position> data, int size) { }

    private static int solve1(String algo, List<String> l) {
        var image = image(l);
        var alg = algo(algo);
        debug(image);
        final int pos = lookup(new Position(2, 2), image);
        System.out.println(pos);
        System.out.println(alg.contains(pos) ? "#" : ".");

        return -1;
    }

    private static Set<Integer> algo(String s) {
        var set = new HashSet<Integer>();
        for (int i=0;i<s.length();i++) {
            if (s.charAt(i)=='#') set.add(i);
        }
        return set;
    }

    private static Image image(List<String> l) {
        var data = new HashSet<Position>();
        final int h = l.size();
        final int w = l.get(0).length();
        for (int y = 0; y< h; y++) {
            for (int x=0;x<w;x++) {
                if (l.get(y).charAt(x) == '#') data.add(new Position(x, y));
            }
        }
        return new Image(data, h);
    }

    private static void debug(Image i) {
        for (int y = 0; y < i.size; y++) {
            for (int x = 0; x < i.size; x++) {
                if (i.data.contains(new Position(x,y))) System.out.print("#");
                else System.out.print(".");
            }
            System.out.println();
        }
    }

    private static Image enhance(Set<Integer> algo, Image i) {
        var data = new HashSet<Position>();
        for (int y = 0; y < i.size; y++) {
            for (int x = 0; x < i.size; x++) {
                final Position p = new Position(x, y);
                if (algo.contains(lookup(p, i))) data.add(p);
            }
        }

        return null;
    }

    // Returns the position in the algo given a position in the image
    private static int lookup(Position p, Image image) {
        var bits = "";
        for (int i=-1; i<=1; i++) {
            for (int j = -1; j <= 1; j++) {
                var np = new Position(p.x() + j, p.y() + i);
                bits += image.data.contains(np) ? "1" : "0";
            }
        }
        return Integer.parseInt(bits, 2);
    }
}
