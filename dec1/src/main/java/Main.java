import java.nio.file.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String inputPath = System.getProperty("user.home") + "/AdventOfCode/aoc_2022/puzzleInputs/dec1.txt";
        Path fp = Path.of(inputPath);
        List<String> content = Arrays.asList(Files.readString(fp).split("\n"));
        List<Integer> elves = new ArrayList<>();
        elves.add(0);

        for (var line : content) {
            if (line.isEmpty())
                elves.add(0);
            else {
                var num = elves.removeLast();
                elves.add(num + Integer.parseInt(line));
            }
        }

        Collections.sort(elves);

        System.out.println("Elf carrying most calories: " + elves.getLast());
        System.out.println("Top three elves calories: " + (elves.removeLast() + elves.removeLast() + elves.removeLast()));
    }
}
