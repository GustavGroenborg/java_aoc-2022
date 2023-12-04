package Parser;

import java.util.List;
import java.util.ArrayList;

import ElfSystem.ElfDirectory;
import ElfSystem.ElfFile;

public class Visitor {
    public List<ElfDirectory> directories = new ArrayList<>();
    public List<ElfFile> files = new ArrayList<>();
    public void visit(ElfDirectory e) {
        this.directories.add(e);
    }

    public void visit(ElfFile f) {
        this.files.add(f);
    }
}
