package Parser;

import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.*;
import java.util.List;
import java.util.Arrays;
import ElfSystem.*;

public class Parser {
    private final ElfDirectory root;
    public ElfDirectory getRoot() { return this.root; }

    private ElfDirectory currentDirectory;

    public Parser(String[] parseInput) {
        this.root = new ElfDirectory(0, "/");
        this.currentDirectory = this.root;
        List<String> strings = new ArrayList<>(List.of(parseInput));
        if (strings.removeFirst().isEmpty())
            strings.removeFirst();
        strings.forEach(this::parse);
    }

    public void parse(String line) {
        if (line.contains(" cd "))
            cd(line);
        else if (line.contains(" ls\n"))
            ls(line);
        else
            throw new UnknownError("Could not match line. Got: " + line);
    }

    private void cd(String line) {
        Pattern pattern = Pattern.compile(Patterns.cd);
        Matcher matcher = pattern.matcher(line);
        if (!matcher.find())
            throw new Error("Found no valid directory name in string: " + line);
        String result = matcher.group();

        if (Objects.equals(result, "..")) {
            this.currentDirectory = this.currentDirectory.getParent();
        } else {
            Visitor visitor = new Visitor();
            currentDirectory.getChildren().forEach(child -> child.accept(visitor));

            while (!visitor.directories.isEmpty()) {
                if (Objects.equals(visitor.directories.getFirst().getName(), result)) {
                    this.currentDirectory = visitor.directories.removeFirst();
                    return;
                } else {
                    visitor.directories.removeFirst();
                }
            }

            // No matching directory was found, meaning it has not been created.
            ElfDirectory newDir = new ElfDirectory(0, result);
            this.currentDirectory.addChild(newDir);
            this.currentDirectory = newDir;
        }
    }


    private void ls(String line) {
        Pattern fileNamePattern = Pattern.compile("[a-zA-z]+(\\.[a-zA-Z]+)?");
        Pattern fileSizePattern = Pattern.compile("\\d+");
        Pattern filePattern = Pattern.compile(fileSizePattern + "\\s" + fileNamePattern);
        Matcher fileMatcher = filePattern.matcher(line);
        List<String[]> matches = new ArrayList<>();

        while (fileMatcher.find())
            matches.add(fileMatcher.group().split("\\s"));

        Visitor visitor = new Visitor();
        this.currentDirectory.getChildren().forEach(child -> child.accept(visitor));
        List<String> existingFileNames = visitor.files.stream()
                .map(ElfFile::getName)
                .toList();

        for (String[] match : matches) {
            if (!existingFileNames.contains(match[1]))
                this.currentDirectory.addChild(new ElfFile(Integer.parseInt(match[0]), match[1]));
        }
    }
}
