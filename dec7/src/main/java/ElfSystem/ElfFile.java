package ElfSystem;

import ElfSystem.ElfDirectory;
import Parser.Visitor;

public class ElfFile extends SystemEntry {
    private int size;
    private String name;
    private ElfDirectory parent = null;
    public ElfFile(int size, String name) {
        this.size = size;
        this.name = name;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public String getName() { return name; }

    @Override
    public ElfDirectory getParent() { return this.parent; };

    @Override
    void setParentTo(ElfDirectory parent) { this.parent = parent; }

    @Override
    public void accept(Visitor v) { v.visit(this); }
}
