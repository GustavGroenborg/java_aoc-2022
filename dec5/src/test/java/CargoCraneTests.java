import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.util.regex.*;

public class CargoCraneTests {
    @Test
    public void testInitiateStacks() {
        // Given
        String[] input = {
                "    [D]    ",
                "[N] [C]    ",
                "[Z] [M] [P]",
                " 1   2   3 ",
        };
        Stack<Character> target1 = new Stack<>();
        target1.push('Z');
        target1.push('N');

        Stack<Character> target2 = new Stack<>();
        target2.push('M');
        target2.push('C');
        target2.push('D');

        Stack<Character> target3 = new Stack<>();
        target3.push('P');

        // When
        CargoCrane crane = new CargoCrane(input);
        if (crane.stacks.size() != 3)
            fail("Unexpected amount of stacks. Expected 3, got: "
                    + crane.stacks.size());

        Stack<Character> output1 = crane.stacks.get(0);
        Stack<Character> output2 = crane.stacks.get(1);
        Stack<Character> output3 = crane.stacks.get(2);


        // Then
        for (int i = 0; i < target1.size(); i++)
            assertEquals(target1.pop(), output1.pop());

        for (int i = 0; i < target2.size(); i++)
            assertEquals(target2.pop(), output2.pop());

        for (int i = 0; i < target3.size(); i++)
            assertEquals(target3.pop(), output3.pop());
    }


    @Test
    public void testDoInstructionFourInstructions() {
        // Given
        String[] stackInput = {
                "    [D]    ",
                "[N] [C]    ",
                "[Z] [M] [P]",
                " 1   2   3 ",
        };

        String[] unparsedInstructions = {
                "move 1 from 2 to 1",
                "move 3 from 1 to 3",
                "move 2 from 2 to 1",
                "move 1 from 1 to 2"
        };

        List<Stack<Character>> targetStacks = new ArrayList<>();
        targetStacks.add(new Stack<>());
        targetStacks.add(new Stack<>());
        targetStacks.add(new Stack<>());

        targetStacks.get(0).add('C');

        targetStacks.get(1).add('M');

        targetStacks.get(2).add('P');
        targetStacks.get(2).add('D');
        targetStacks.get(2).add('N');
        targetStacks.get(2).add('Z');

        // When
        CargoCrane crane = new CargoCrane(stackInput);
        for (String str : unparsedInstructions) {
            crane.doInstructionFrom(new Instruction(str));
        }

        // Then
        if (crane.stacks.size() != targetStacks.size())
            fail("Unexpected number of crane stacks."
                    + " " + "Expected: " + targetStacks.size()
                    + ", " + "got: " + crane.stacks.size());

        // Testing first stack.
        while(!targetStacks.get(0).empty())
            assertEquals(targetStacks.get(0).pop(), crane.stacks.get(0).pop());
        assertTrue(crane.stacks.get(0).isEmpty());

        // Testing second stack.
        while(!targetStacks.get(1).empty())
            assertEquals(targetStacks.get(1).pop(), crane.stacks.get(1).pop());
        assertTrue(crane.stacks.get(1).isEmpty());

        // Testing third stack.
        while(!targetStacks.get(2).empty())
            assertEquals(targetStacks.get(2).pop(), crane.stacks.get(2).pop());
        assertTrue(crane.stacks.get(2).isEmpty());
    }

}
