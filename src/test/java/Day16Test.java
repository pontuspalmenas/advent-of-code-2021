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

    @Test
    void lengthTypeID() {
        var p = Day16.Packet.fromHex("38006F45291200");
        assertEquals(0, p.lengthTypeID());
    }

    @Test
    void subPacketsLength() {
        var p = Day16.Packet.fromHex("38006F45291200");
        assertEquals(27, p.subPacketsLength());
    }
}