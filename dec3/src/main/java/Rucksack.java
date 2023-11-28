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

        for (int i = 0; i < this.compartment0.length(); i++) {
            if (this.compartment1.indexOf(chars[i]) != -1 )
                return Character.valueOf(chars[i]);
        }

        throw new Error("Found no common characters in: "
                + "\n" + this.compartment0
                + "\n" + this.compartment1);
    }
}
