import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InstructionTests {

    @Test
    public void testInitiateInstruction() {
        // Given
        String[] exampleInstructions = {
                "move 1 from 2 to 1",
                "move 3 from 1 to 3",
                "move 2 from 2 to 1",
                "move 1 from 1 to 2"
        };
        int[][] targets = {
                {1, 2, 1},
                {3, 1, 3},
                {2, 2, 1},
                {1, 1, 2}
        };

        // When
        List<Instruction> outputs = Arrays.stream(exampleInstructions)
                .map(Instruction::new)
                .toList();

        // Then
        int i = 0;
        for (Instruction instruction : outputs) {
            assertEquals(targets[i][0], instruction.getMove());
            assertEquals(targets[i][1], instruction.getFrom());
            assertEquals(targets[i][2], instruction.getTo());
            i++;
        }
    }

    @Test
    public void regexTestBasic() {
        String string = "move 1 from 2 to 3";
        Pattern pattern = Pattern.compile("[a-z]{1,4}\\s[0-9]*");
        Matcher matcher = pattern.matcher(string);

        while (matcher.find())
            System.out.println(matcher.group(0));

    }

    @Test
    public void testRegexLookBehind() {
        String s = "move 1 from 2 to 3";
        Pattern pattern = Pattern.compile("(?<=[a-z]{1,4}\\s)[0-9]+");
        Matcher matcher = pattern.matcher(s);

        while (matcher.find())
            System.out.println(matcher.group(0));
    }
}
