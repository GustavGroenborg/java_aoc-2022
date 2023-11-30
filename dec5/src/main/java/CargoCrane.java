import java.util.*;

public class CargoCrane {
    public List<Stack<Character>> stacks = new ArrayList<>();

    public CargoCrane(String[] stackInput) {
        List<String> initialStackInput = new ArrayList<>(Arrays.asList(stackInput)).reversed();
        List<Pair> stackIndices = findStackIndicesFrom(initialStackInput.removeFirst());

        for (int i = 0; i < stackIndices.size(); i++)
            stacks.add(new Stack<>());

        initialStackInput.forEach(str -> {
            stackIndices.forEach(pair -> {
                char c = str.charAt(pair.getIndex());
                if (c != ' ')
                    stacks.get(pair.getStackNum()).push(Character.valueOf(c));
            });
        });
    }

    private List<Pair> findStackIndicesFrom(String string) {
        List<Pair> stackIndices = new ArrayList<>();
        char[] chars = string.toCharArray();
        int stackCount = 0;

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                stackIndices.add(new Pair(stackCount, i));
                stackCount++;
            }
        }

        return stackIndices;
    }

    public void doInstructionFrom(Instruction instruction) {
        for (int i = 0; i < instruction.getMove(); i++) {
            this.stacks.get(instruction.getTo() - 1)
                    .push(this.stacks.get(instruction.getFrom() - 1).pop());
        }
    }
}
