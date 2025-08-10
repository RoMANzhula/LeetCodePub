package P801_900.P869_Reordered_Power_of_2;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.reorderedPowerOf2(1));   // true
        System.out.println(solution.reorderedPowerOf2(10));  // false
        System.out.println(solution.reorderedPowerOf2(46));  // true (46 → 64)
    }

    public boolean reorderedPowerOf2(int n) {
        String sortedN = sortDigits(n);

        for (int i = 0; i < 31; i++) { // 2^30 is just above 10^9
            int pow = 1 << i;

            if (sortedN.equals(sortDigits(pow))) {
                return true;
            }
        }

        return false;
    }

    private String sortDigits(int num) {
        char[] arr = String.valueOf(num).toCharArray();

        Arrays.sort(arr);

        return new String(arr);
    }

}

//Complexity:
// time - O(31 * k log k)
// space - O(1)


//You are given an integer n. We reorder the digits in any order (including the original order) such that
// the leading digit is not zero.
//Return true if and only if we can do this so that the resulting number is a power of two.

//Example 1:
//Input: n = 1
//Output: true

//Example 2:
//Input: n = 10
//Output: false

//Constraints:
//1 <= n <= 109
