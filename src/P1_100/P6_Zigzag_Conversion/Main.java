package P1_100.P6_Zigzag_Conversion;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String s = "PAYPALISHIRING";
        int numRows = 3;
        System.out.println(solution.convert(s, numRows)); // "PAHNAPLSIIGYIR"
    }

    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        String[] rows = new String[numRows];
        Arrays.fill(rows, "");
        int currRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows[currRow] += c;
            if (currRow == 0 || currRow == numRows - 1) {
                goingDown = !goingDown;
            }
            currRow += goingDown ? 1 : -1;
        }

        StringBuilder sb = new StringBuilder();
        for (String row : rows) {
            sb.append(row);
        }

        return sb.toString();
    }


}


//The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to
// display this pattern in a fixed font for better legibility)
//P   A   H   N
//A P L S I I G
//Y   I   R
//And then read line by line: "PAHNAPLSIIGYIR"
//Write the code that will take a string and make this conversion given a number of rows:
//string convert(string s, int numRows);

//Example 1:
//Input: s = "PAYPALISHIRING", numRows = 3
//Output: "PAHNAPLSIIGYIR"

//Example 2:
//Input: s = "PAYPALISHIRING", numRows = 4
//Output: "PINALSIGYAHRPI"
//Explanation:
//P     I    N
//A   L S  I G
//Y A   H R
//P     I

//Example 3:
//Input: s = "A", numRows = 1
//Output: "A"

//Constraints:
//1 <= s.length <= 1000
//s consists of English letters (lower-case and upper-case), ',' and '.'.
//1 <= numRows <= 1000
