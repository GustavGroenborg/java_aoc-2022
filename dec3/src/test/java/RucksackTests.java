import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;

public class RucksackTests {
    @Test
    public void testInitRuckSack() {
        // Given
        String strA = "vJrwpWtwJgWrhcsFMMfFFhFp";
        String strB = "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL";

        String[] raCompartments = { "vJrwpWtwJgWr", "hcsFMMfFFhFp" };
        String[] rbCompartments = { "jqHRNqRjqzjGDLGL", "rsFMfFZSrLrFZsSL" };

        // When
        Rucksack ra = new Rucksack(strA);
        Rucksack rb = new Rucksack(strB);


        // Then
        assertEquals(raCompartments[0], ra.compartment0);
        assertEquals(raCompartments[1], ra.compartment1);

        assertEquals(rbCompartments[0], rb.compartment0);
        assertEquals(rbCompartments[1], rb.compartment1);
    }


    @Test
    public void testComputePriority() {
        // Given
        Character[] chars = { 'p', 'L', 'P', 'v', 't', 's' };
        Integer[] expected = { 16, 38, 42, 22, 20, 19 };

        // When
        Integer[] output = Arrays.stream(chars)
                .mapToInt(Rucksack::computePriority)
                .boxed()
                .toArray(Integer[]::new);


        // Then
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], output[i]);
        }
    }


    @Test
    public void testFindOneCommonItem() {
        // Given
        String[] inputs = {
                "vJrwpWtwJgWrhcsFMMfFFhFp",
                "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
                "PmmdzqPrVvPwwTWBwg",
                "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
                "ttgJtRGJQctTZtZT",
                "CrZsJsPPZsGzwwsLwLmpwMDw" };
        Character[] targets = { 'p', 'L', 'P', 'v', 't', 's' };

        // When
        Character[] outputs = Arrays.stream(inputs)
                .map(Rucksack::new)
                .map(Rucksack::findOneCommonItem)
                .toArray(Character[]::new);

        // Then
        if (targets.length != outputs.length)
            fail("Target and output arrays not equal length.\n targets.length = "
                    + targets.length + "\n outputs.length = " + outputs.length);

        for (int i = 0; i < targets.length; i++)
            assertEquals(targets[i], outputs[i]);
    }


    @Test
    public void testExampleInputsIteratively() {
        // Given
        String[] inputs = {
                "vJrwpWtwJgWrhcsFMMfFFhFp",
                "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
                "PmmdzqPrVvPwwTWBwg",
                "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
                "ttgJtRGJQctTZtZT",
                "CrZsJsPPZsGzwwsLwLmpwMDw" };
        Integer[] targets = { 16, 38, 42, 22, 20, 19 };

        // When
        Integer[] outputs = Arrays.stream(inputs)
                .map(Rucksack::new)
                .mapToInt(r -> Rucksack.computePriority(r.findOneCommonItem()))
                .boxed()
                .toArray(Integer[]::new);

        // Then
        if (targets.length != outputs.length)
            fail("Target and output arrays not equal length.\n targets.length = "
                    + targets.length + "\n outputs.length = " + outputs.length);

        for (int i = 0; i < targets.length; i++)
            assertEquals(targets[i], outputs[i]);
    }


    @Test
    public void testFindCommonGroupItem() {
        // Given
        String[] input1 = {
                "vJrwpWtwJgWrhcsFMMfFFhFp",
                "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
                "PmmdzqPrVvPwwTWBwg"
        };
        String[] input2 = {
                "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
                "ttgJtRGJQctTZtZT",
                "CrZsJsPPZsGzwwsLwLmpwMDw"
        };

        Character target1 = 'r';
        Character target2 = 'Z';

        // When
        Character output1 = Rucksack.findCommonGroupItem(input1);
        Character output2 = Rucksack.findCommonGroupItem(input2);

        // Then
        assertEquals(target1, output1);
        assertEquals(target2, output2);
    }

}
