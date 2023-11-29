import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;
import java.util.stream.*;

public class Dec3Tests {
    @Test
    public void testExampleInputPart1() {
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

    @Test
    public void testExampleInputPart2() {
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

        final int TARGET_SUM = 70;

        // When
        int outputSum = Dec3.part2(parts);

        // Then
        assertEquals(TARGET_SUM, outputSum);
    }


    @Test
    public void testCollectorCollection() {
        // Given
        Stream<Integer> data = IntStream.range(0, 9).boxed();
        final int BATCH_SIZE = 3;

        final List<Integer> firstBatch = List.of(0, 1, 2);
        final List<Integer> secondBatch = List.of(3, 4, 5);
        final List<Integer> thirdBatch = List.of(6, 7, 8);

        // When
        Collection<List<Integer>> result = data.collect(Collectors.groupingBy(i -> i / BATCH_SIZE))
                .values();

        // System.out.println(result);
        // [[0, 1, 2], [3, 4, 5], [6, 7, 8]]

        // Then
        assertTrue(result.contains(firstBatch));
        assertTrue(result.contains(secondBatch));
        assertTrue(result.contains(thirdBatch));
    }


    @Test
    public void testCollectionGroupManipulation() {
        // Given
        Stream<Integer> data = IntStream.range(0, 9).boxed();
        final int BATCH_SIZE = 3;

        final List<Integer> firstBatch = List.of(0, 1, 2);
        final List<Integer> secondBatch = List.of(3, 4, 5);
        final List<Integer> thirdBatch = List.of(6, 7, 8);

        // When
        Collection<List<Integer>> result = data.collect(Collectors.groupingBy(i -> i / BATCH_SIZE))
                .values();

        System.out.println(result);
    }


    @Test
    public void testGroupStringsInto3() {
        // Given
        String[] strings = {
                "abc",
                "def",
                "ghi",
                "jkl",
                "mno",
                "pqr",
                "stu",
                "vxy",
                "zæø"
        };
        String[] firstTaget = {
                "abc",
                "def",
                "ghi"
        };
        String[] secondTarget = {
                "jkl",
                "mno",
                "pqr"
        };
        String[] thirdTarget = {
                "stu",
                "vxy",
                "zæø"
        };

        // When
        List<String[]> outputs = Dec3.groupStrings(strings, 3);

        if (outputs.size() != strings.length / 3)
            fail("Unexpected length of outputs. Expected: " + strings.length / 3
                    + " " + "Got: " + outputs.size());

        // Then
        assertEquals(firstTaget, outputs.get(0));
        assertEquals(secondTarget, outputs.get(1));
        assertEquals(thirdTarget, outputs.get(2));
    }

}
