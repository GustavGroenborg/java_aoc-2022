import java.nio.file.*;
import java.util.List;
import java.util.Arrays;
import org.apache.commons.lang3.Range;

public class Dec4 {
    public static void main(String[] args) {
        String[] input = loadPuzzleInput();

        System.out.println("One range fully contains the other in "
                + Dec4.part1(input) + " " + "assignments.");

        System.out.println("Ranges overlap in "
                + part2(input) + " " + "assignment pairs");
    }

    public static int part1(String[] strings) {
        long count = Arrays.stream(strings)
                .map(pair -> pair.split(","))
                .map(pairs -> Arrays.stream(pairs)
                        .map(Dec4::parseRange)
                        .toList())
                .filter(ranges -> (ranges.get(0).containsRange(ranges.get(1))
                            || ranges.get(1).containsRange(ranges.get(0))))
                .count();

        return (int) count;
    }

    public static int part2(String[] strings) {
        long count = Arrays.stream(strings)
                .map(pair -> pair.split(","))
                .map( pairs -> Arrays.stream(pairs)
                        .map(Dec4::parseRange)
                        .toList())
                .filter(ranges -> ranges.get(0).isOverlappedBy(ranges.get(1)))
                .count();

        return (int) count;
    }

    public static Range<Integer> parseRange(String unparsed) {
        Integer[] nums = Arrays.stream(unparsed.split("-"))
                .mapToInt(Integer::parseInt)
                .boxed()
                .toArray(Integer[]::new);

        return Range.between(nums[0], nums[1]);
    }

    public static String[] loadPuzzleInput() {
        String path = System.getProperty("user.home")
                +"/AdventOfCode/aoc_2022/puzzleInputs/dec4.txt";

        Path fp = Path.of(path);

        try {
            return Files.readString(fp).split("\n");
        } catch(Exception e) {
            throw new Error(e.getMessage());
        }
    }
}
