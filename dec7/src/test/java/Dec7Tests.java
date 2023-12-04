import org.junit.Test;
import static org.junit.Assert.*;

import java.util.regex.*;

public class Dec7Tests {

    @Test
    public void test_regexDigitsOneOrMoreTimes() {
        // Given
        String input = "12345 file.txt";
        String target = "12345";

        // When
        Matcher matcher = Pattern.compile("[\\d]+").matcher(input);

        // Then
        assertTrue(matcher.find());

        assertEquals(target, matcher.group());
    }

    @Test
    public void test_regexDotDot() {
        // Given
        String input = "cd ..";
        String target = "..";

        // When
        Matcher matcher = Pattern.compile("[\\.]{2}").matcher(input);

        // Then
        assertTrue(matcher.find());
        assertEquals(target, matcher.group());
    }

    @Test
    public void test_regexMatchFileName() {
        // Given
        String string1 = "12345 file.txt";
        String string2 = "67889 file";
        String target1 = "file.txt";
        String target2 = "file";

        // When
        Pattern pattern = Pattern.compile("[a-zA-Z]+(\\.[a-zA-Z]+)?");
        Matcher matcher1 = pattern.matcher(string1);
        Matcher matcher2 = pattern.matcher(string2);

        // Then
        assertTrue(matcher1.find());
        assertTrue(matcher2.find());

        assertEquals(target1, matcher1.group());
        assertEquals(target2, matcher2.group());
    }

}
