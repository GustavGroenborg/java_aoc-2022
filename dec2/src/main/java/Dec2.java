import java.nio.file.*;
import java.util.*;
import java.util.stream.IntStream;

public class Dec2 {
    public static void main(String args[]) {
        String input = loadPuzzleInput("dec2.txt");
        var part1 = calculateScore(input);
        System.out.println("Part 1 score: " + part1);

        var part2 = part2score(input);
        System.out.println("Part 2 score: " + part2);
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

    public static Integer part2score(String input) {
        Integer score = Arrays.stream(input.split("\n"))
                .map(Round::new)
                .mapToInt(Round::getPart2Score)
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
