package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class FileUtil {
    private static final Pattern PARAGRAPH = Pattern.compile("\\s*^\\s*$\\s*", Pattern.MULTILINE);

    public static List<String> read(String path) {
        try {
            return Files.lines(Path.of(path)).toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Reads the input as blocks separated by empty line
    public static List<List<String>> readBlocks(String path) {
        try {
            var out = new ArrayList<List<String>>();
            var lines = Files.readString(Path.of(path));

            for (String s : PARAGRAPH.split(lines)) {
                out.add(s.lines().filter(l -> !l.isBlank()).toList());
            }

            return out;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
