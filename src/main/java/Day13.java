import util.FileUtil;
import util.Position;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day13 {
    public static void main(String[] args) {
        var in = FileUtil.readBlocks("input/day13.txt");
        System.out.println(solve1(in));
        solve2(in);
    }

    private static int solve1(List<List<String>> ll) {
        var paper = paper(ll.get(0));
        var fold = ll.get(1).get(0).split(" ")[2];
        paper = fold(paper, fold);
        return paper.size();
    }

    private static void solve2(List<List<String>> ll) {
        var paper = paper(ll.get(0));
        var folds = ll.get(1).stream().map(s -> s.split(" ")[2]).toList();

        for (String f : folds) {
            paper = fold(paper, f);
        }
        debug(paper);
    }

    private static Set<Position> paper(List<String> l) {
        return l.stream().map(t -> {
                    var ss = t.split(",");
                    return new Position(Integer.parseInt(ss[0]), Integer.parseInt(ss[1]));
                }
        ).collect(Collectors.toSet());
    }

    private static void debug(Set<Position> paper) {
        int w = paper.stream().mapToInt(Position::x).max().orElseThrow();
        int h = paper.stream().mapToInt(Position::y).max().orElseThrow();

        for (int y=0;y<h+1;y++) {
            for (int x=0;x<w+1;x++) {
                System.out.print(paper.contains(new Position(x,y)) ? "#" : ".");
            }
            System.out.println();
        }
    }

    private static Set<Position> fold(Set<Position> paper, String how) {
        var s = how.split("=");
        var n = Integer.parseInt(s[1]);
        return s[0].equals("y") ? foldY(paper, n) : foldX(paper, n);
    }

    private static Set<Position> foldY(Set<Position> paper, int at) {
        // first add all points from the first half of the original paper
        var folded = new HashSet<>(paper.stream().filter(p -> p.y() < at).toList());

        // now add all other points but inverted y
        folded.addAll(paper.stream().filter(p -> p.y() > at).map(p -> new Position(p.x(), at-(p.y()-at))).toList());

        return folded;
    }

    private static Set<Position> foldX(Set<Position> paper, int at) {
        // first add all points from the first half of the original paper
        var folded = new HashSet<>(paper.stream().filter(p -> p.x() < at).toList());

        // now add all other points but inverted y
        folded.addAll(paper.stream().filter(p -> p.x() > at).map(p -> new Position(at-(p.x()-at), p.y())).toList());

        return folded;
    }
}
