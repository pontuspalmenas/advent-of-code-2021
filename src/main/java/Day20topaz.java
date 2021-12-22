import java.util.Arrays;

public class Day20topaz {
    // initialized in constructor not shown
    private String algorithm;
    private boolean[][] image; // each element is true for "#", false for "."
    private final int BUFFER = 60; // Border created around the original image filled with false


    public void part1() {
        // Enhance the image twice.
        for (int i = 1; i <= 2; i++) {
            image = processImage(i);
        }
        int lightsOn = countLights();
        System.out.println(lightsOn);
    }

    public void part2() {
        // Enhance the image 48 more times.
        // (part 1 already enhanced it twice)
        for (int i = 3; i <= 50; i++) {
            image = processImage(i);
        }
        int lightsOn = countLights();
        System.out.println(lightsOn);
    }

    private boolean[][] processImage(int enhancement) {
        boolean[][] temp = new boolean[image.length][image[0].length];
        // The first character in the algorithm is "#" and the last character is "."
        // Initially, the infinite border is all "."s, so the enhancing algorithm will
        // produce binary strings of "000000000" = 0, therefore a "#".
        // In the next enhancement, the infinite border is all "#"s, so the enhancing
        // algorithm will produce binary strings of "111111111" = 255, therefore ".".
        // This means that the 'infinite' border around the image being process will
        // toggle between "#" and "."
        // The boolean[][] is filled with 'false' by default. We have to manually fill
        // it with 'true' if we are performing an 'odd' enhancement.
        if (enhancement % 2 == 1) {
            for (boolean[] arr : temp)
                Arrays.fill(arr, true);
        }

        // There should be enough of a buffer that we can skip over the outer most edge
        // of the image while traversing. This will make checking neighbors safer.
        for (int r = 1; r <= image.length - 2; r++) {
            for (int c = 1; c <= image[0].length - 2; c++) {
                String bin = "";
                bin += (image[r - 1][c - 1] ? "1" : "0");
                bin += (image[r - 1][c] ? "1" : "0");
                bin += (image[r - 1][c + 1] ? "1" : "0");
                bin += (image[r][c - 1] ? "1" : "0");
                bin += (image[r][c] ? "1" : "0");
                bin += (image[r][c + 1] ? "1" : "0");
                bin += (image[r + 1][c - 1] ? "1" : "0");
                bin += (image[r + 1][c] ? "1" : "0");
                bin += (image[r + 1][c + 1] ? "1" : "0");
                int index = Integer.parseInt(bin, 2);
                char ch = algorithm.charAt(index);
                temp[r][c] = ch == '#';
            }
        }

        return temp;
    }

    private int countLights() {
        int count = 0;
        for (int r = 0; r < image.length; r++) {
            for (int c = 0; c < image[0].length; c++) {
                count += (image[r][c] ? 1 : 0);
            }
        }
        return count;
    }
}
