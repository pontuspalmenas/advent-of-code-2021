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
        assertEquals(-444, Util.min(Arrays.asList(123L,-444L,555L,1L)));
    }

    @Test
    void maxOfListInts() {
        assertEquals(555, Util.max(Arrays.asList(123L,-444L,555L,1L)));
    }

    @Test
    void sumOfListInts() {
        assertEquals(2, Util.sum(Arrays.asList(1L,2L,3L,-4L)));
    }

    @Test
    void regex() {
        var r = "(\\d+)(\\d+)";
        var s = "fisk 123, fisk 456";
        assertEquals(List.of("123","456"), Util.regex(r, s));
    }

}