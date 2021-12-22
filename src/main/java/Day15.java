import util.FileUtil;
import util.Util;

public class Day15 {
    class MazeSolver {
        private int[][] grid;
        private int height;
        private int width;

        private int[][] map;

        MazeSolver(int[][] grid) {
            this.grid = grid;
            this.height = grid.length;
            this.width = grid[0].length;

            map = new int[height][width];
        }

        private boolean solve() {
            
        }
    }

    public static void main(String[] args) {
        var in = FileUtil.read("input/day15.txt");
        var grid = Util.parseIntGrid(in);
        System.out.println(solve1(grid));
    }

    private static int solve1(int[][] grid) {
        Util.debug(grid);

        return -1;
    }

    private static

}
