import util.FileUtil;
import util.Position;

import java.util.*;

public class Day20 {
    public static void main(String[] args) {
        var in = FileUtil.readBlocks("input/day20.txt");
        System.out.println(solve1(in.get(0).get(0), in.get(1)));
    }

    record Image(boolean[][] data) { }

    private static int solve1(String algo, List<String> l) {
        var image = image(l);
        var alg = algo(algo);
        debug(image);
        //image = enhance(alg, image);
        //debug(image);

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
        var data = new boolean[104][104];
        final int h = l.size();
        final int w = l.get(0).length();
        for (int y = 0; y< h; y++) {
            for (int x=0;x<w;x++) {
                data[y][x] = (l.get(y).charAt(x) == '#');
            }
        }
        return new Image(data);
    }

    private static void debug(Image i) {
        for (int y = 0; y < i.data.length; y++) {
            for (int x = 0; x < i.data.length; x++) {
                System.out.print(i.data[y][x] ? "#" : ".");
            }
            System.out.println();
        }
    }

    private static Image enhance(Set<Integer> algo, Image i) {
        final int len = i.data.length;
        var data = new boolean[104][104];
        for (int y = 0; y < 104; y++) {
            for (int x = 0; x < 104; x++) {
                final Position p = new Position(x, y);
                data[y][x] = algo.contains(algoPos(p, i));
            }
        }

        return new Image(data);
    }

    // Returns the position in the algo given a position in the image
    private static int algoPos(Position p, Image image) {
        var bits = "";
        for (int i=-1; i<=1; i++) {
            for (int j = -1; j <= 1; j++) {
                bits += image.data[p.y()+i][p.x()+j] ? "1" : "0";
            }
        }
        return Integer.parseInt(bits, 2);
    }
}
