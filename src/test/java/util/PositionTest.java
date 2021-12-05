package util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PositionTest {
    @Test
    void position() {
        var p = new Position(1,2);
        assertEquals(1, p.x());
        assertEquals(2, p.y());
    }
}
