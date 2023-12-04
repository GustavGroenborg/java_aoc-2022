import org.junit.Test;
import static org.junit.Assert.*;

import java.util.regex.*;

import Parser.Patterns;


public class PatternsTests {

    @Test
    public void cd_alphabeticName() {
        // Given
        String input = " cd gts\n";
        String target = "gts";

        // When
        Matcher m = Pattern.compile(Patterns.cd).matcher(input);

        // Then
        assertTrue(m.find());
        assertEquals(target, m.group());
    }

    @Test
    public void cd_parentDirectory() {
        // Given
        String input = " cd ..\n";
        String target = "..";

        // When
        Matcher m = Pattern.compile(Patterns.cd).matcher(input);

        // Then
        assertTrue(m.find());
        assertEquals(target, m.group());
    }
}
