package P1901_2000.P1903_Largest_Odd_Number_in_String;

import java.time.chrono.MinguoChronology;

public class Main {

    public static void main(String[] args) {
        System.out.println(largestOddNumber("52")); // 5
        System.out.println(largestOddNumber("4206")); // ""
        System.out.println(largestOddNumber("35427")); // 35427
    }

//    public static String largestOddNumber(String num) {
//        int bufNumber = Integer.parseInt(num);
//        int maxOdd =  Integer.MIN_VALUE;
//
//        if (bufNumber % 2 == 0) {
//            for (int i = 0; i < num.length(); i++) {
//                char digit = num.charAt(i);
//                if (digit % 2 != 0) {
//                    if (digit > maxOdd) return "" + digit;
//                } else {
//                    return "";
//                }
//            }
//        } else {
//            return num;
//        }
//
//        return "" + maxOdd;
//    }

    public static String largestOddNumber(String num) {
        int n = num.length();
        int lastOddIndex = -1;

        for (int i = n - 1; i >= 0; i--) {
            int digit = num.charAt(i) - '0';

            if (digit % 2 != 0) {
                lastOddIndex = i;
                break;
            }
        }

        if (lastOddIndex == -1) {
            return ""; // Жодного непарного числа не знайдено
        } else {
            return num.substring(0, lastOddIndex + 1);
        }
    }

}

//You are given a string num, representing a large integer. Return the largest-valued odd integer (as a string)
// that is a non-empty substring of num, or an empty string "" if no odd integer exists.
//A substring is a contiguous sequence of characters within a string.

//Example 1:
//Input: num = "52"
//Output: "5"
//Explanation: The only non-empty substrings are "5", "2", and "52". "5" is the only odd number.

//Example 2:
//Input: num = "4206"
//Output: ""
//Explanation: There are no odd numbers in "4206".

//Example 3:
//Input: num = "35427"
//Output: "35427"
//Explanation: "35427" is already an odd number.

//Constraints:
//1 <= num.length <= 105
//num only consists of digits and does not contain any leading zeros.
