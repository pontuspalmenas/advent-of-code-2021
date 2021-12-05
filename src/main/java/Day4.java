import util.FileUtil;
import util.Util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day4 {
    private static class Cell {
        private final int num;
        private boolean marked;

        Cell(int num) {
            this.num = num;
        }
    }
    private record Board(Cell[][] cells) {
        private int Score() {
            int score = 0;
            for (int i=0; i<5;i++) {
                for (int j=0; j<5; j++) {
                    if (!cells[i][j].marked) score += cells[i][j].num;
                }
            }

            return score;
        }

        private boolean Bingo() {
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

        private void Mark(int num) {
            for (int i=0;i<5;i++) {
                for (int j=0;j<5;j++) {
                    if (cells[i][j].num == num) cells[i][j].marked = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        var in = FileUtil.ReadBlocks("input/day4.txt");
        System.out.println(Solve1(in));
        System.out.println(Solve2(in));
    }

    private static int Solve1(List<List<String>> ll) {
        var lottoRad = Util.Ints(ll.get(0).get(0));
        var boards = parseBoards(ll);

        for (int n : lottoRad) {
            for (Board b : boards) {
                b.Mark(n);
                if (b.Bingo()) {
                    return b.Score() * n;
                }
            }
        }

        throw new RuntimeException("No solution found");
    }

    private static int Solve2(List<List<String>> ll) {
        var lottoRad = Util.Ints(ll.get(0).get(0));
        var boards = parseBoards(ll);

        for (int n : lottoRad) {
            var it = boards.iterator();
            while (it.hasNext()) {
                Board b = it.next();
                b.Mark(n);
                if (b.Bingo()) {
                    if (boards.size() == 1) return b.Score() * n;
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
                var row = Util.Ints(l.get(j));
                for (int k=0;k<5;k++) {
                    cells[j][k] = new Cell(row.get(k));
                }
            }
            boards.add(new Board(cells));
        }

        return boards;
    }
}
