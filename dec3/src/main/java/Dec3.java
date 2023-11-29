import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dec3 {
    public static void main(String args[]) {
        String[] input = loadPuzzleInput("dec3.txt")
                .split("\n");

        System.out.println("The sum of priorities appearing in both compartments is: "
                + part1(input));

        System.out.println("The sum of priorities of group items is: "
                + part2(input));
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


    public static Integer part2(String[] input) {
        int sum = Dec3.groupStrings(input, 3).stream()
                .map(Rucksack::findCommonGroupItem)
                .mapToInt(Rucksack::computePriority)
                .sum();

        return sum;
    }


    public static List<String[]> groupStrings(String[] strs, int num) {
        List<String[]> result = new ArrayList<String[]>();
        List<String> holder = new ArrayList<String>();
        int count = 0;

        for (String str : strs) {
            count++;
            holder.add(str);
            if (count == num) {
                result.add(holder.toArray(String[]::new));
                holder.clear();
                count = 0;
            }
        }

        return result;
    }
}