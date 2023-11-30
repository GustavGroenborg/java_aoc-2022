import java.nio.file.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;


public class Dec5 {

    public static void main(String[] args) {
        List<String> puzzleInput = new ArrayList<>(
                Arrays.asList(Dec5.loadPuzzleInput().split("\n")));
        List<String> initialStackInput = new ArrayList<>();
        while (!Objects.equals(puzzleInput.getFirst(), ""))
            initialStackInput.add(puzzleInput.removeFirst());
        // Removing the empty string.
        puzzleInput.removeFirst();

        // Part 1
        CargoCrane crane = new CargoCrane(initialStackInput.toArray(String[]::new));
        for (String unparsedInstruction : puzzleInput)
            crane.doInstructionFrom(new Instruction(unparsedInstruction));

        AtomicReference<String> craneTOS = new AtomicReference<>("");
        crane.stacks
                .forEach(stack -> craneTOS.updateAndGet(str -> str + stack.peek()));

        System.out.println("After the rearrangement procedure complete, the following crates are on top of the crane's stacks "
                + craneTOS);

        // Part 2
        CrateMover9001 crateMover9001 = new CrateMover9001(initialStackInput.toArray(String[]::new));
        for (String unparsedInstruction : puzzleInput)
            crateMover9001.doInstructionFrom(new Instruction(unparsedInstruction));

        AtomicReference<String> crateMover9001TOS = new AtomicReference<>("");
        crateMover9001.stacks
                        .forEach(stack -> crateMover9001TOS
                                .updateAndGet(str -> str + stack.peek()));

        System.out.println("After the rearrangement with CrateMover9001 the following on top of the stack: "
                + crateMover9001TOS);
    }

    public static String loadPuzzleInput() {
        String path = System.getProperty("user.home")
                + "/AdventOfCode/aoc_2022/puzzleInputs/dec5.txt";

        Path fp = Path.of(path);

        try {
            return Files.readString(fp);
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }
}
