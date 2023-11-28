import org.junit.Test;
import static org.junit.Assert.*;


public class Dec3Tests {
    @Test
    public void testExampleInput() {

        // Given
        String input = """
                vJrwpWtwJgWrhcsFMMfFFhFp
                jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
                PmmdzqPrVvPwwTWBwg
                wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
                ttgJtRGJQctTZtZT
                CrZsJsPPZsGzwwsLwLmpwMDw
                """;
        String[] parts = input.split("\n");
        Integer expectedSum = 157;

        // When
        Integer outputSum = Dec3.part1(parts);

        // Then
        assertEquals(expectedSum, outputSum);
    }
}
