import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;


public class Dec6Tests {
    @Test
    public void testStartOfPacket4Characters() {
        // Given
        final int BUFFER_SIZE = 4;
        String[] input = {
                "bvwbjplbgvbhsrlpgdmjqwftvncz",
                "nppdvjthqldpwncqszvftbrmjlhg",
                "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg",
                "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw",
        };

        Integer[] targets = { 5, 6, 10, 11 };

        // When
        Integer[] outputs = Arrays.stream(input)
                .map(str -> str.chars()
                        .mapToObj(i -> Character.valueOf((char) i))
                        .toList())
                .mapToInt(charList -> Dec6.findFirstStartOfPacket(charList, BUFFER_SIZE))
                .boxed()
                .toArray(Integer[]::new);

        // Then
        for (int i = 0; i < targets.length; i++)
            assertEquals(targets[i], outputs[i]);
    }
}
