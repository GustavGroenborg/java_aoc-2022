package ElfSystem;

import Parser.Visitor;

import java.util.ArrayList;
import java.util.List;

public class ElfDirectory extends SystemEntry {
    private int size;
    private String name;
    private ElfDirectory parent = null;
    private List<SystemEntry> contents = new ArrayList<>();
    public ElfDirectory(int size, String name) {
        this.size = size;
        this.name = name;
    }

    @Override
    public int getSize() { return this.size; }

    @Override
    public String getName() { return this.name; }


    void updateSizeWith(int size) {
        this.size += size;
        if (parent != null)
            parent.updateSizeWith(size);
    }

    @Override
    public ElfDirectory getParent() { return this.parent; }

    @Override
    void setParentTo(ElfDirectory parent) { this.parent = parent; }

    @Override
    public void accept(Visitor v) { v.visit(this); }

    public void addChild(ElfFile file) {
        contents.add(file);
        file.setParentTo(this);
        this.updateSizeWith(file.getSize());
    }

    public void addChild(ElfDirectory directory) {
        contents.add(directory);
        directory.setParentTo(this);
        this.updateSizeWith(directory.getSize());
    }

    public List<SystemEntry> getChildren() { return this.contents; }
}
