import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day25Test {
    @Test
    void ref() {
        char[] c = new char[]{'x','x'};
        update(c);
        assertEquals('o', c[0]);
    }

    boolean update(char[] c) {
        var cc = c.clone();
        cc[0]='o';
        System.arraycopy(cc,0,c,0,cc.length);
        return true;
    }

}