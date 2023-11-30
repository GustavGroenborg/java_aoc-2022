import java.util.ArrayList;
import java.util.regex.*;
import java.util.List;

public class Instruction {
    private final int move;
    private final int from;
    private final int to;

    public Instruction(String instruction) {
        final int EXPECTED_MATCHES = 3;
        Pattern pattern = Pattern.compile("(?<=[a-z]{0,4}\\s)[0-9]+");
        Matcher matcher = pattern.matcher(instruction);
        List<Integer> matches = new ArrayList<>();

        while (matcher.find())
            matches.add(Integer.parseInt(matcher.group(0)));

        if (matches.size() != EXPECTED_MATCHES)
            throw new Error("Unexpected number of matches. Expected: " + EXPECTED_MATCHES
                    + " " + "got: " + matches.size()
                    + " " + "in: " + instruction);

        move = matches.get(0);
        from = matches.get(1);
        to = matches.get(2);
    }


    public int getMove() { return this.move; }
    public int getFrom() { return this.from; }
    public int getTo() { return this.to; }
}
