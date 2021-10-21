import util.FileUtil;
import util.Util;

import java.util.List;

public class Day0 {
    public static void main(String[] args) {
        for (int i : Util.Ints(FileUtil.Read("input/day0.txt"))) {
            System.out.println(i);
        }

        List<List<String>> ll = FileUtil.ReadBlocks("input/day0.txt");
        System.out.println(ll.get(0).get(0));
        System.out.println(ll.get(0).get(1));
        System.out.println(ll.get(1).get(0));
    }
}
