import util.Direction;
import util.FileUtil;
import util.Position;
import util.Util;

public class Day17 {
    record Target(int x1, int x2, int y1, int y2) {
        boolean within(Position p) {
            return p.x() >= x1 && p.x() <= x2 &&
                    p.y() >= y1 && p.y() <= y2;
        }
        boolean missed(Position p, int xv) {
            return (p.x() < x1 && xv == 0) || p.x() > x2 || p.y() < y1;
        }
    }

    public static void main(String[] args) {
        var in = FileUtil.read("input/day17.txt").get(0);
        System.out.println(solve1(in));
        System.out.println(solve2(in));
    }

    private static int solve1(String s) {
        var target = target(s);
        int maxY = 0;

        for (int xv=0; xv<500; xv++) {
            for (int yv=0; yv<500; yv++) {
                int y = shoot(xv, yv, target);
                maxY = Math.max(maxY, y);
            }
        }
        return maxY;
    }

    private static int solve2(String s) {
        var target = target(s);
        int hits = 0;
        for (int xv=0; xv<1000; xv++) {
            for (int yv=target.y1; yv<1000; yv++) {
                if (shoot(xv, yv, target) > -1) hits++;
            }
        }
        return hits;
    }

    // Returns max y if target hit, -1 otherwise
    private static int shoot(int xv, int yv, Target target) {
        var p = new Position(0,0);
        int maxY = 0;
        while (true) {
            p = p.move(Direction.RIGHT, xv);
            p = p.move(Direction.DOWN, yv);
            if (p.y() > maxY) maxY = p.y();
            if (target.within(p)) return maxY;
            if (target.missed(p, xv)) return -1;

            if (xv > 0) xv--;
            else if (xv<0) xv++;
            yv--;
        }
    }

    private static Target target(String s) {
        var in = Util.ints(s);
        return new Target(in.get(0), in.get(1), in.get(2), in.get(3));
    }
}
