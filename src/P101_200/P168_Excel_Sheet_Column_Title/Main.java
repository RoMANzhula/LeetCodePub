package P101_200.P168_Excel_Sheet_Column_Title;

public class Main {
    public String convertToTitle(int columnNumber) {
        StringBuilder result = new StringBuilder(); //our buffer

        while (columnNumber > 0) {
            //convert the column number to 0-based index
            columnNumber--;

            //calculate the remainder and append the corresponding character
            char charToAdd = (char) ('A' + columnNumber % 26);
//            System.out.println(charToAdd + " " + columnNumber % 26 + " from max number " + (26*27));
            result.insert(0, charToAdd);

            //move to the next quotient (all time minus 26 letters from alphabet)
            columnNumber /= 26;
        }

        return result.toString(); //our coordinate
    }

    public static void main(String[] args) {
        Main converter = new Main();

        int columnNumber1 = 1;
        int columnNumber2 = 28;
        int columnNumber3 = 701;

        String title1 = converter.convertToTitle(columnNumber1);
        String title2 = converter.convertToTitle(columnNumber2);
        String title3 = converter.convertToTitle(columnNumber3);

        System.out.println("Column " + columnNumber1 + " corresponds to title: " + title1);
        System.out.println("Column " + columnNumber2 + " corresponds to title: " + title2);
        System.out.println("Column " + columnNumber3 + " corresponds to title: " + title3);
    }
//    public static void main(String[] args) {
//        char a = 'A';
//        char aa =
//        System.out.println(convertChar(a));
//    }
//
//    public static int convertChar(char ch) {
//        return (int) ch - 64;
//    }
}

//Given an integer columnNumber, return its corresponding column title as it appears in an Excel sheet.
//For example:
//A -> 1
//B -> 2
//C -> 3
//...
//Z -> 26
//AA -> 27
//AB -> 28
//...

//Example 1:
//Input: columnNumber = 1
//Output: "A"

//Example 2:
//Input: columnNumber = 28
//Output: "AB"

//Example 3:
//Input: columnNumber = 701
//Output: "ZY"

//Constraints:
//1 <= columnNumber <= 231 - 1
