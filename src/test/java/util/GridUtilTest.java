package util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GridUtilTest {
    @Test
    void parseIntGrid() {
        var g = GridUtil.parseIntGrid(grid());
        assertEquals(3, GridUtil.h(g));
        assertEquals(3, GridUtil.w(g));
        assertArrayEquals(new int[]{1,2,3},g[0]);
        assertArrayEquals(new int[]{4,5,6},g[1]);
        assertArrayEquals(new int[]{7,8,9},g[2]);
    }

    @Test
    void cardinalAdjacent() {
        var g = GridUtil.parseIntGrid(grid());
        var ns = GridUtil.cardinalAdjacent(1,1, g); // middle
        assertEquals(4, ns.size());
        assertTrue(ns.containsAll(List.of(
                new Position(1,0),
                new Position(0,1),
                new Position(2,1),
                new Position(1,2)
        )));

    }

    @Test
    void diagAdjacent() {
        var g = GridUtil.parseIntGrid(grid());
        var ns = GridUtil.diagAdjacent(0, 2, g); // top right
        assertEquals(3, ns.size());

        ns = GridUtil.diagAdjacent(1,1, g); // middle
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