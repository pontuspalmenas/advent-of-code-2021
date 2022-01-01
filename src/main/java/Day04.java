import util.FileUtil;
import util.Util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day04 {
    private static class Cell {
        private final int num;
        private boolean marked;

        Cell(int num) {
            this.num = num;
        }
    }
    private record Board(Cell[][] cells) {
        private int score() {
            int score = 0;
            for (int i=0; i<5;i++) {
                for (int j=0; j<5; j++) {
                    if (!cells[i][j].marked) score += cells[i][j].num;
                }
            }

            return score;
        }

        private boolean bingo() {
            for (int i=0;i<5;i++) {
                if (cells[i][0].marked &&
                        cells[i][1].marked &&
                        cells[i][2].marked &&
                        cells[i][3].marked &&
                        cells[i][4].marked) return true;
                if (cells[0][i].marked &&
                        cells[1][i].marked &&
                        cells[2][i].marked &&
                        cells[3][i].marked &&
                        cells[4][i].marked) return true;
            }
            return false;
        }

        private void mark(int num) {
            for (int i=0;i<5;i++) {
                for (int j=0;j<5;j++) {
                    if (cells[i][j].num == num) cells[i][j].marked = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        var in = FileUtil.readBlocks("input/day4.txt");
        System.out.println(solve1(in));
        System.out.println(solve2(in));
    }

    private static int solve1(List<List<String>> ll) {
        var lottoRad = Util.ints(ll.get(0).get(0));
        var boards = parseBoards(ll);

        for (int n : lottoRad) {
            for (Board b : boards) {
                b.mark(n);
                if (b.bingo()) {
                    return b.score() * n;
                }
            }
        }

        throw new RuntimeException("No solution found");
    }

    private static int solve2(List<List<String>> ll) {
        var lottoRad = Util.ints(ll.get(0).get(0));
        var boards = parseBoards(ll);

        for (int n : lottoRad) {
            var it = boards.iterator();
            while (it.hasNext()) {
                Board b = it.next();
                b.mark(n);
                if (b.bingo()) {
                    if (boards.size() == 1) return b.score() * n;
                    it.remove();
                }
            }
        }

        throw new RuntimeException("No solution found");
    }

    private static Set<Board> parseBoards(List<List<String>> ll) {
        var boards = new HashSet<Board>();

        // each board
        for (int i=1; i<ll.size(); i++) {
            var cells = new Cell[5][5];
            var l = ll.get(i);

            // row
            for (int j=0;j<5;j++) {
                var row = Util.ints(l.get(j));
                for (int k=0;k<5;k++) {
                    cells[j][k] = new Cell(row.get(k));
                }
            }
            boards.add(new Board(cells));
        }

        return boards;
    }
}
