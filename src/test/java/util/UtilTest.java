package util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    @Test
    void testIntsWithList() {
        var input = List.of("123","abc,-444,hej","","''***%%%%%555+1%%%***''\\");
        var expected = List.of(123,-444,555,1);
        var actual = Util.ints(input);
        assertEquals(expected, actual);
    }

    @Test
    void testIntsWithString() {
        var input = "123abc-444hej***%%%%%555+1%%%***";
        var expected = List.of(123,-444,555,1);
        var actual = Util.ints(input);
        assertEquals(expected, actual);
    }

    @Test
    void minOfListInts() {
        assertEquals(-444, Util.min(Arrays.asList(123,-444,555,1)));
    }

    @Test
    void maxOfListInts() {
        assertEquals(555, Util.max(Arrays.asList(123,-444,555,1)));
    }

    @Test
    void sumOfListInts() {
        assertEquals(2, Util.sum(Arrays.asList(1,2,3,-4)));
    }

    @Test
    void regex() {
        var r = "(\\d+)(\\d+)";
        var s = "fisk 123, fisk 456";
        assertEquals(List.of("123","456"), Util.regex(r, s));
    }

    @Test
    void parseIntGrid() {
        var g = Util.parseIntGrid(grid());
        assertEquals(3, Util.h(g));
        assertEquals(3, Util.w(g));
        assertArrayEquals(new int[]{1,2,3},g[0]);
        assertArrayEquals(new int[]{4,5,6},g[1]);
        assertArrayEquals(new int[]{7,8,9},g[2]);
    }

    @Test
    void neighbors() {
        var g = Util.parseIntGrid(grid());
        var ns = Util.neighbors(0, 2, g); // top right
        assertEquals(3, ns.size());

        ns = Util.neighbors(1,1, g); // middle
        assertEquals(8, ns.size());
        assertTrue(ns.containsAll(List.of(
                new Position(0,0),
                new Position(1,0),
                new Position(2,0),
                new Position(0,1),
                new Position(2,1),
                new Position(0,2),
                new Position(1,2),
                new Position(2,2)
        )));

    }

    private List<String> grid() {
        var s =
"""
123
456
789
""";
       return Arrays.stream(s.split("\n")).toList();
    }

}