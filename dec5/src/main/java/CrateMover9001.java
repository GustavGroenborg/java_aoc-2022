import java.util.Stack;

public class CrateMover9001 extends CargoCrane {
    public CrateMover9001(String[] stackInput) {
        super(stackInput);
    }

    @Override public void doInstructionFrom(Instruction instruction) {
        Stack<Character> movingStack = new Stack<>();

        for (int i = 0; i < instruction.getMove(); i++)
            movingStack.push(this.stacks.get(instruction.getFrom() - 1).pop());

        while (!movingStack.empty())
            this.stacks.get(instruction.getTo() - 1)
                    .push(movingStack.pop());
    }
}
