import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day16Test {
    @Test
    void hex2bin() {
        assertEquals("110100101111111000101000", Day16.hex2bin("D2FE28"));
    }

    @Test
    void version() {
        var p = new Day16.Packet("110100101111111000101000");
        assertEquals(6, p.version());
    }

    @Test
    void type() {
        var p = new Day16.Packet("110100101111111000101000");
        assertEquals(4, p.type());
    }

    @Test
    void value() {
        var p = new Day16.Packet("110100101111111000101000");
        assertEquals(2021, p.value());
    }
}