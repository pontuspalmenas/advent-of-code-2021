import util.FileUtil;

import java.util.ArrayList;
import java.util.List;

public class Day23 {

    /*
        #############
        #...........#
        ###B#C#B#D###
          #A#D#C#A#     depth
          #########
           r
           o
           o
           m

     */

    record State(int type, int room, int depth) {
        int cost() {
            return switch (type) {
                case 0 -> 1;
                case 1 -> 10;
                case 2 -> 100;
                case 3 -> 1000;
                default -> throw new RuntimeException("Unhandled");
            };
        }
    }

    private static boolean solved(List<State> states) {
        return states.stream().noneMatch(p -> p.type != p.room)
                && states.stream().filter(p -> p.depth == 0).count() == states.size()/2;
    }

    public static void main(String[] args) {
        var in = FileUtil.read("input/day23.txt");
        System.out.println(solve1(in));
    }

    private static long solve1(List<String> l) {
        var states = parse(l);
        System.out.println(solved(states));
        return -1;
    }

    private static List<State> parse(List<String> l) {
        var states = new ArrayList<State>();
        // first row
        states.add(new State(l.get(2).charAt(3)-65, 0, 0));
        states.add(new State(l.get(2).charAt(5)-65, 1, 0));
        states.add(new State(l.get(2).charAt(7)-65, 2, 0));
        states.add(new State(l.get(2).charAt(9)-65, 3, 0));
        // second row
        states.add(new State(l.get(3).charAt(3)-65, 0, 1));
        states.add(new State(l.get(3).charAt(5)-65, 1, 1));
        states.add(new State(l.get(3).charAt(7)-65, 2, 1));
        states.add(new State(l.get(3).charAt(9)-65, 3, 1));

        return states;
    }
}
