import util.FileUtil;
import util.GridUtil;
import util.Position;

import java.util.*;

// Chiton
public class Day15 {
    record Node(Position pos, int cost) {}

    public static void main(String[] args) {
        var in = FileUtil.read("input/day15.txt");
        System.out.println(solve1(in));
        System.out.println(solve2(in));
    }

    private static int solve1(List<String> l) {
        var grid = GridUtil.parseIntGrid(l);
        return search(grid);
    }

    private static int solve2(List<String> l) {
        var grid = GridUtil.parseIntGrid(l);
        int size = grid.length;
        var expanded = new int[size*5][size*5];

        for (int i=0;i<size;i++) {
            for (int j=0;j<size;j++) {
                for (int m=0;m<5;m++) {
                    for (int n=0;n<5;n++) {
                        int v = grid[i][j]+m+n;
                        if (v >= 10) v -= 9;
                        expanded[i+size*m][j+size*n] = v;
                    }
                }
            }
        }

        return search(expanded);
    }

    private static int search(int[][] grid) {
        var pq = new PriorityQueue<Node>(Comparator.comparingInt(i -> i.cost));
        var start = new Node(new Position(0,0),0);
        var end = new Position(grid[0].length-1,grid.length-1);
        var cost = new HashMap<Position, Integer>();
        var visited = new HashSet<Position>();
        pq.add(start);

        while (!pq.isEmpty()) {
            var curr = pq.poll();
            var p = curr.pos;
            if (visited.contains(p)) continue;
            visited.add(p);
            cost.put(p, curr.cost);
            if (p.equals(end)) break;
            for (var n : GridUtil.cardinalAdjacent(p.y(), p.x(), grid)) {
                pq.add(new Node(n, curr.cost + grid[n.y()][n.x()]));
            }
        }

        return cost.get(end);
    }
}
