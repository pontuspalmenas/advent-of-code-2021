package util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    @Test
    void setAndGet() {
        var g = grid2x2();
        assertEquals(1+3+5+7, g.getV(0,0)+g.getV(0,1)+g.getV(1,0)+g.getV(1,1));
    }

    @Test
    void find() {
        var g = new Grid<Integer>(1,1);
        g.set(0,0,5);
        assertTrue(g.find(0,0).isPresent());
        assertTrue(g.find(1,1).isEmpty());
    }

    @Test
    void neighbors() {
        var g = grid3x3();

        // test corner
        var s = g.neighbors(2,0); // start at val=5
        assertEquals(3, s.size());
        assertEquals(3+9+11, s.stream().mapToInt(Grid.Tile::value).sum());

        // test middle
        var s2 = g.neighbors(1,1); // start at val=9
        assertEquals(8, s2.size());
        assertEquals(1+3+5+7+11+13+15+17, s2.stream().mapToInt(Grid.Tile::value).sum());
    }

    /*
      ---------
      | 1 | 3 |
      ---------
      | 5 | 7 |
      ---------
     */
    private Grid<Integer> grid2x2() {
        var g = new Grid<Integer>(2,2);
        g.set(0,0,1);
        g.set(1,0,3);
        g.set(0,1,5);
        g.set(1,1,7);
        return g;
    }

    /*
      -------------
      | 1 | 3 | 5 |
      -------------
      | 7 | 9 | 11|
      -------------
      | 13| 15| 17|
      -------------
    */
    private Grid<Integer> grid3x3() {
        var g = new Grid<Integer>(3,3);
        g.set(0,0,1);
        g.set(1,0,3);
        g.set(2,0,5);
        g.set(0,1,7);
        g.set(1,1,9);
        g.set(2,1,11);
        g.set(0,2,13);
        g.set(1,2,15);
        g.set(2,2,17);

        return g;
    }
}