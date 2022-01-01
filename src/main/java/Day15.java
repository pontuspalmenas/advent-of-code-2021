import util.FileUtil;
import util.GridUtil;

import java.util.*;

public class Day15 {
    record Node(int y, int x, int risk) {}

    public static void main(String[] args) {
        var in = FileUtil.read("input/day15.txt");
        System.out.println(solve1(in));
    }

    private static int solve1(List<String> l) {
        var grid = GridUtil.parseIntGrid(l);
        return search(grid);
    }

    private static int search(int[][] grid) {
        var pq = new PriorityQueue<Node>(Comparator.comparingInt(i -> i.risk));
        var start = new Node(0,0,0);
        var riskSoFar = new HashMap<Node, Integer>();
        pq.add(start);
        riskSoFar.put(start, 0);
        while (!pq.isEmpty()) {
            var current = pq.poll();
            if (current.y == grid.length-1 && current.x == grid[0].length-1) {
                return riskSoFar.get(current);
            }

            for (var n : GridUtil.cardinalAdjacent(current.y, current.x, grid)) {
                Node next = new Node(n.y(), n.x(), grid[n.y()][n.x()]);
                int newCost = riskSoFar.get(current) + next.risk;
                if (!riskSoFar.containsKey(next) || newCost < riskSoFar.get(next)) {
                    riskSoFar.put(next, newCost);
                    pq.add(next);
                }
            }
        }

        return -1;
    }
}
