package util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectionTest {
    @Test
    void testMove() {
        var p = new Position(0,0);
        p = p.move(Direction.DOWN);
        p = p.move(Direction.LEFT);
        p = p.move(Direction.RIGHT);
        p = p.move(Direction.RIGHT);
        p = p.move(Direction.UP);
        p = p.move(Direction.UP);
        assertEquals(1, p.x());
        assertEquals(-1, p.y());
    }
}
