package util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectionTest {
    @Test
    void testMove() {
        var p = new Position(0,0);
        p = p.Move(Direction.UP);
        p = p.Move(Direction.LEFT);
        p = p.Move(Direction.RIGHT);
        p = p.Move(Direction.RIGHT);
        p = p.Move(Direction.DOWN);
        p = p.Move(Direction.DOWN);
        assertEquals(1, p.x());
        assertEquals(-1, p.y());
    }
}
