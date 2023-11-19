import java.nio.file.*;
import java.util.*;
import java.util.stream.IntStream;

public class Dec2 {
    public static void main(String args[]) {
        String input = loadPuzzleInput("dec2.txt");
        var bar = calculateScore(input);
        System.out.println(bar);
    }

    public static Integer calculateScore(String input) {
        List<Round> rounds = Arrays.stream(input.split("\n"))
                .map(Round::new)
                .toList();

        Integer score = rounds.stream()
                .mapToInt(round -> round.getScore())
                .sum();

        return score;
    }

    public static String loadPuzzleInput(String day) {
        String inputPath = System.getProperty("user.home") + "/AdventOfCode/aoc_2022/puzzleInputs/" + day;
        Path fp = Path.of(inputPath);
        try {
            String lines = Files.readString(fp);
            return lines;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return "";
        }

    }
}
