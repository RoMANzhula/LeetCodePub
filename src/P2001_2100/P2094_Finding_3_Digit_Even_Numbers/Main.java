package P2001_2100.P2094_Finding_3_Digit_Even_Numbers;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] digits1 = {2, 1, 3, 0};
        int[] digits2 = {2, 2, 8, 8, 2};
        int[] digits3 = {3, 7, 5};

        System.out.println(Arrays.toString(solution.findEvenNumbers(digits1))); // [102, 120, 130, 132, 210, 230, 302, 310, 312, 320]
        System.out.println(Arrays.toString(solution.findEvenNumbers(digits2))); // [222, 228, 282, 288, 822, 828, 882]
        System.out.println(Arrays.toString(solution.findEvenNumbers(digits3))); // []
    }

    public int[] findEvenNumbers(int[] digits) {
        Set<Integer> result = new TreeSet<>(); // to keep results sorted and unique

        int len = digits.length;

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (j == i) continue;
                for (int k = 0; k < len; k++) {
                    if (k == i || k == j) continue;

                    int d1 = digits[i];
                    int d2 = digits[j];
                    int d3 = digits[k];

                    // skip leading zero
                    if (d1 == 0) continue;

                    // check if the number is even
                    if (d3 % 2 != 0) continue;

                    int number = d1 * 100 + d2 * 10 + d3;
                    result.add(number);
                }
            }
        }

        // convert to int array
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

}

//Complexity:
// time - O(n^3)
// space - O(1)


//You are given an integer array digits, where each element is a digit. The array may contain duplicates.
//You need to find all the unique integers that follow the given requirements:
//The integer consists of the concatenation of three elements from digits in any arbitrary order.
//The integer does not have leading zeros.
//The integer is even.
//For example, if the given digits were [1, 2, 3], integers 132 and 312 follow the requirements.
//Return a sorted array of the unique integers.

//Example 1:
//Input: digits = [2,1,3,0]
//Output: [102,120,130,132,210,230,302,310,312,320]
//Explanation: All the possible integers that follow the requirements are in the output array.
//Notice that there are no odd integers or integers with leading zeros.

//Example 2:
//Input: digits = [2,2,8,8,2]
//Output: [222,228,282,288,822,828,882]
//Explanation: The same digit can be used as many times as it appears in digits.
//In this example, the digit 8 is used twice each time in 288, 828, and 882.

//Example 3:
//Input: digits = [3,7,5]
//Output: []
//Explanation: No even integers can be formed using the given digits.

//Constraints:
//3 <= digits.length <= 100
//0 <= digits[i] <= 9
