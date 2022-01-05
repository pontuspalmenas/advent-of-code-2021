import util.FileUtil;

import java.util.List;

public class Day24 {
    /*
    0   inp w
    1   mul x 0
    2   add x z
    3   mod x 26
    4   div z 1     z_trunc
    5   add x 11    x_inc
    6   eql x w
    7   eql x 0
    8   mul y 0
    9   add y 25
    10  mul y x
    11  add y 1
    12  mul z y
    13  mul y 0
    14  add y w
    15  add y 8     y_inc
    16  mul y x
    17  add z y
     */

    record Instruction(String type, String reg, String operand) {}
    record Operation(String op, String operand) {}

    public static void main(String[] args) {
        var in = FileUtil.read("input/day24.txt");
        System.out.println(solve1(in));
    }

    private static long solve1(List<String> l) {
        var instructions = parse(l);
        var ops = ops(instructions);
        return -1;
    }

    private static List<Instruction> parse(List<String> l) {
        return l.stream().map(s -> s.split(" "))
                .map(a -> new Instruction(a[0], a[1], a.length == 3 ? a[2] : "")
                ).toList();
    }

    private static List<Operation> ops(List<Instruction> l) {
        boolean truncateZ = l.get(4).operand.equals("26");
        var incX = l.get(5).operand;
        var incY = l.get(15).operand;

        return l.stream()
                .map(i -> new Operation(truncateZ ? "pop" : "push", truncateZ ? incX : incY))
                .toList();
    }
}
