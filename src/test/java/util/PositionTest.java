package util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class PositionTest {
    @Test
    void position() {
        var p = new Position(1,2);
        assertEquals(1, p.x());
        assertEquals(2, p.y());
    }

    @Test
    void equality() {
        var p1 = new Position(12,34);
        var p2 = new Position(12,34);
        assertEquals(p1, p2);
    }
}
