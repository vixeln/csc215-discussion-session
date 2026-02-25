public class Main {
    public static void main(String[] args) {
        int[] table = {0, 1, 2, 4, 6, 9};

        final String ESC = "\u001B";
        final String Black = ESC + "[30m";
        final String Yellow_Bg = ESC + "[43m";
        final String RESET = ESC + "[0m";
        final String blackbg = ESC + "[40m";
        final String white = ESC + "[37m";

        for (int i = 0; i < table.length; i++) {
            float weight = table[i];

            String l = Yellow_Bg + Black + "LOL" + RESET;

            System.out.println(Yellow_Bg + Black +  "Hi");

            System.out.printf();
////            System.out.printf("\u001B[31m | %8f | %8s |%n", weight, l);
        }

    }
}