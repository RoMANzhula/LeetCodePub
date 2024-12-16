package P101_200.P171_Excel_Sheet_Column_Number;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.titleToNumber("A")); // 1
        System.out.println(solution.titleToNumber("AB")); // 28
        System.out.println(solution.titleToNumber("ZY")); // 701
    }

    public int titleToNumber(String columnTitle) {
        int result = 0;

        for (int i = 0; i < columnTitle.length(); i++) {
            // calculate value of the current letter
            int value = columnTitle.charAt(i) - 'A' + 1;
            // add value to result taking into account base 26
            result = result * 26 + value;
        }

        return result;
    }
}
