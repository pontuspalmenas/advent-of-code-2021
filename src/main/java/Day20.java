import util.FileUtil;
import util.Position;

import java.util.*;

public class Day20 {
    public static void main(String[] args) {
        var in = FileUtil.readBlocks("input/day20.txt");
        System.out.println(solve1(in.get(0).get(0), in.get(1)));
        System.out.println(solve2(in.get(0).get(0), in.get(1)));
    }

    private static int solve1(String algo, List<String> l) {
        return solve(algo, l, 2);
    }

    private static int solve2(String algo, List<String> l) {
        return solve(algo, l, 50);
    }

    private static int solve(String algo, List<String> l, int times) {
        var image = startingImage(l);
        var alg = algo(algo);

        for (int i = 0; i < times; i++) {
            image = enhance(alg, image, i % 2 != 0);
        }

        int counter = 0;
        for (int y = 0; y < image.length; y++) {
            for (int x = 0; x < image.length; x++) {
                if (image[y][x]) counter++;
            }
        }

        return counter;
    }

    private static boolean[] algo(String s) {
        var a = new boolean[512];
        for (int i = 0; i < s.length(); i++) {
            a[i] = s.charAt(i) == '#';
        }
        return a;
    }

    private static boolean[][] startingImage(List<String> l) {
        final int h = l.size();
        final int w = l.get(0).length();
        var data = new boolean[h][w];
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                data[y][x] = (l.get(y).charAt(x) == '#');
            }
        }
        return data;
    }

    // Enhance by padding the old image and use algorithm to lookup pixels for the new image
    private static boolean[][] enhance(boolean[] algo, boolean[][] image, boolean flip_infinity) {
        var paddedOld = pad(image, flip_infinity);
        final int len = paddedOld.length;
        var enhanced = new boolean[len][len];
        for (int y = 0; y < len; y++) {
            for (int x = 0; x < len; x++) {
                final Position p = new Position(x, y);
                enhanced[y][x] = algo[algoPos(p, paddedOld, flip_infinity)];
            }
        }

        return enhanced;
    }

    // Returns the position in the algo given a position in the image
    private static int algoPos(Position p, boolean[][] image, boolean flip_infinity) {
        StringBuilder bits = new StringBuilder();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (p.y() + i > 0 && p.y() + i < image.length - 1 && p.x() + j > 0 && p.x() + j < image.length - 1) {
                    bits.append(image[p.y() + i][p.x() + j] ? "1" : "0");
                } else {
                    bits.append(flip_infinity ? "1" : "0");
                }
            }
        }
        return Integer.parseInt(bits.toString(), 2);
    }

    // Returns an expanded grid with a border of 'padding'
    private static boolean[][] pad(boolean[][] map, boolean padding) {
        int h = map.length;
        int w = map[0].length;
        var padded = new boolean[h + 2][w + 2];
        for (boolean[] bs : padded) Arrays.fill(bs, padding);
        for (int i = 0; i < h; i++) System.arraycopy(map[i], 0, padded[i + 1], 1, w);
        return padded;
    }
}
