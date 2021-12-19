import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Day14Test {
    @Test
    void extend() {
        var rules = exampleRulesMap();
        assertEquals("NCNBCHB",Day14.extend("NNCB", rules));
        assertEquals("NBCCNBBBCBHCB",Day14.extend("NCNBCHB", rules));
        assertEquals("NBBBCNCCNBBNBNBBCHBHHBCHB",Day14.extend("NBCCNBBBCBHCB", rules));
        assertEquals("NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB",Day14.extend("NBBBCNCCNBBNBNBBCHBHHBCHB", rules));
    }

    private Map<String,String> exampleRulesMap() {
        var map = new HashMap<String,String>();
        map.put("CH","B");
        map.put("HH","N");
        map.put("CB","H");
        map.put("NH","C");
        map.put("HB","C");
        map.put("HC","B");
        map.put("HN","C");
        map.put("NN","C");
        map.put("BH","H");
        map.put("NC","B");
        map.put("NB","B");
        map.put("BN","B");
        map.put("BB","N");
        map.put("BC","B");
        map.put("CC","N");
        map.put("CN","C");
        return map;
    }
}