import java.nio.file.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Dec6 {
    public static void main(String[] args) {
        List<Character> puzzleInput = loadPuzzleInput();

        System.out.println(part1(puzzleInput) + " " + "characters needs to be processed before the first start-of-packet marker is detected.");
    }

    public static int part1(List<Character> puzzleInput) {
        final int BUFFER_SIZE = 4;

        return findFirstStartOfPacket(puzzleInput, BUFFER_SIZE);
    }

    public static int findFirstStartOfPacket(List<Character> dataStream, int bufferSize) {
        List<Character> buffer = new ArrayList<>();
        Iterator<Character> dataStreamIterator = dataStream.stream().iterator();
        int charactersProcessed = 0;

        while (dataStreamIterator.hasNext() && buffer.size() < bufferSize) {
            Character nextChar = dataStreamIterator.next();
            charactersProcessed++;

            while (buffer.contains(nextChar))
                buffer.removeFirst();

            if (buffer.size() == 3 && !buffer.contains(nextChar))
                break;

            buffer.add(nextChar);
        }

        return charactersProcessed;
    }

    public static List<Character> loadPuzzleInput() {
        String path = System.getProperty("user.home")
                + "/AdventOfCode/aoc_2022/puzzleInputs/dec6.txt";

        Path fp = Path.of(path);

        try {
            List<Character> puzzleInput = Files.readString(fp).chars()
                    .mapToObj(i -> Character.valueOf((char) i))
                    .toList();

            return puzzleInput;

        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }
}
