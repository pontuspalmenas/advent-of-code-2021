import util.FileUtil;
import util.GridUtil;

import java.util.Arrays;
import java.util.List;

// Sea Cucumber
public class Day25 {
    public static void main(String[] args) {
        var in = FileUtil.read("input/day25.txt");
        System.out.println(solve1(in));
    }

    private static long solve1(List<String> l) {
        var g = GridUtil.parseCharGrid(l);
        int steps=0;
        while (true) {
            var old = GridUtil.copy(g);
            g = east(g);
            g = south(g);

            steps++;

            if (equals(old, g)) {
                break;
            }
        }

        return steps;
    }

    private static char[][] east(char[][] g) {
        int w = g[0].length;
        int h = g.length;
        var gt = GridUtil.copy(g);
        for (int i = 0; i<h; i++) {
            for (int j = 0; j<w; j++) {
                if (g[i][j]=='>') {
                    if (j< w-1 && g[i][j+1]=='.') {
                        gt[i][j] = '.';
                        gt[i][j+1] = '>';
                    } else if (j==w-1 && g[i][0]=='.') {
                        gt[i][j] = '.';
                        gt[i][0] = '>';
                    }
                }
            }
        }
        return gt;
    }

    private static char[][] south(char[][] g) {
        int w = g[0].length;
        int h = g.length;
        var gt = GridUtil.copy(g);
        for (int i = 0; i<h; i++) {
            for (int j = 0; j<w; j++) {
                if (g[i][j]=='v') {
                    if (i< h-1 && g[i+1][j]=='.') {
                        gt[i][j] = '.';
                        gt[i+1][j] = 'v';
                    } else if (i==h-1 && g[0][j]=='.') {
                        gt[i][j] = '.';
                        gt[0][j] = 'v';
                    }
                }
            }
        }
        return gt;
    }

    private static boolean equals(char[][] a, char[][] b) {
        for (int i=0; i<a.length;i++) {
            if (!Arrays.equals(a[i],b[i])) return false;
        }
        return true;
    }


}
