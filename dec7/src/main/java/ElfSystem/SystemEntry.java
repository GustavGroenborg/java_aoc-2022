package ElfSystem;
import Parser.Visitor;

public abstract class SystemEntry {
    abstract public String getName();
    abstract public int getSize();
    abstract public ElfDirectory getParent();
    abstract void setParentTo(ElfDirectory parent);

    abstract public void accept(Visitor v);


}
