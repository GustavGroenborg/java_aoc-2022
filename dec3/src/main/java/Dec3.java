import java.nio.file.*;
import java.util.Arrays;
import java.util.List;

public class Dec3 {
    public static void main(String args[]) {
        String[] input = loadPuzzleInput("dec3.txt")
                .split("\n");

        System.out.println("The sum of priorities appearing in both compartments is: "
                + part1(input));
    }

    public static String loadPuzzleInput(String day) {
        String inputPath = System.getProperty("user.home") + "/AdventOfCode/aoc_2022/puzzleInputs/" + day;
        Path fp = Path.of(inputPath);

        try {
            String lines = Files.readString(fp);
            return lines;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
    }

    public static Integer part1(String[] input) {
        List<Rucksack> rucksacks = Arrays.stream(input)
                .map(Rucksack::new)
                .toList();

        Integer sum = rucksacks.stream()
                .mapToInt(r -> Rucksack.computePriority(r.findOneCommonItem()))
                .sum();

        return sum;
    }


    public Integer findPrioritySumButDoItWithStyle(String input) {
        /*
        var prioritySum = Arrays.stream(input.split("\n"))
                .map(Rucksack::new)
                .filter(c -> c.compartment0.chars()
                                .filter(ch -> c.compartment1.indexOf(ch) != -1)
                                .d
        /*
        var prioritySum = compartment0.chars()
                .filter(c -> compartment1.indexOf(c) != -1)
                .mapToObj(Character::toChars)
                .map(c -> Character.valueOf(c[0]))
                .mapToInt(

         */
        return 0;
    }
}