public class Rucksack {
    String compartment0;
    String compartment1;

    public Rucksack(String items) {
        int mid = items.length() / 2;
        this.compartment0 = items.substring(0, mid);
        this.compartment1 = items.substring(mid);
    }

    public static Integer computePriority(Character item) {
        return ((int) item - ((Character.isLowerCase(item)) ? 96 : 38));
    }


    public Character findOneCommonItem() {
        char[] chars = this.compartment0.toCharArray();

        for (char c : chars) {
            if (this.compartment1.indexOf(c) != -1)
                return Character.valueOf(c);
        }

        throw new Error("Found no common characters in rucksack compartments: "
                + "\n" + this.compartment0
                + "\n" + this.compartment1);
    }


    public static Character findCommonGroupItem(String[] group) {
        if (group.length != 3)
            throw new Error("group too long. Expected 3, got: " + group.length);

        char[] chars = group[0].toCharArray();

        for (char aChar : chars) {
            if (group[1].indexOf(aChar) != -1 && group[2].indexOf(aChar) != -1)
                return Character.valueOf(aChar);
        }

        throw new Error("Found no common characters in group:"
                + "\n" + group[0]
                + "\n" + group[1]
                + "\n" + group[2]);
    }
}
