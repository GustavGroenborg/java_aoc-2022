import java.nio.file.*;
import ElfSystem.*;
import Parser.*;

import java.util.ArrayList;
import java.util.List;

public class Dec7 {
    public static void main(String[] args) {
        String[] puzzleInput = loadPuzzleInput();

        int part1Limit = 100_000;
        System.out.println("The sum of all directories below"
                + " " + part1Limit
                + " " + "is: " + part1(puzzleInput, part1Limit));

        System.out.println("The size of the smallest directory"
                + " that would free up enough space for the update is: "
                + part2(puzzleInput));
    }

    public static String[] loadPuzzleInput() {
        String path = System.getProperty("user.home")
                + "/AdventOfCode/aoc_2022/puzzleInputs/dec7.txt";

        Path fp = Path.of(path);

        try {
            return Files.readString(fp).split("\\$");
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

    public static int part1(String[] puzzleInput, int sizeLimit) {
        List<ElfDirectory> explored = new ArrayList<>();
        Parser parser = new Parser(puzzleInput);
        Visitor frontier = new Visitor();
        parser.getRoot().accept(frontier);

        while(!frontier.directories.isEmpty()) {
            var v = frontier.directories.removeLast();
            if (v.getSize() < sizeLimit) {
                explored.add(v);
            }
            for (var child : v.getChildren())
                child.accept(frontier);
        }

        return explored.stream().mapToInt(ElfDirectory::getSize).sum();
    }

    public static int part2(String[] pip) {
        final int CAPACITY = 70_000_000;
        final int UPDATE_SIZE = 30_000_000;
        Parser parser = new Parser(pip);
        int spaceNeeded = (parser.getRoot().getSize() + UPDATE_SIZE) - CAPACITY;
        Visitor frontier = new Visitor();
        parser.getRoot().accept(frontier);

        ElfDirectory smallestDirectory = parser.getRoot();
        while(!frontier.directories.isEmpty()) {
            var v = frontier.directories.removeLast();
            if (v.getSize() < smallestDirectory.getSize() && v.getSize() > spaceNeeded)
                smallestDirectory = v;

            for (var child : v.getChildren())
                child.accept(frontier);
        }

        return smallestDirectory.getSize();
    }
}
