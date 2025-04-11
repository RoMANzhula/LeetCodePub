package P2801_2900.P2843_Count_Symmetric_Integers;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.countSymmetricIntegers(1, 100));      // Output: 9
        System.out.println(solution.countSymmetricIntegers(1200, 1230));  // Output: 4
    }

    public int countSymmetricIntegers(int low, int high) {
        int count = 0;

        for (int num = low; num <= high; num++) {
            String str = String.valueOf(num);
            int len = str.length();

            if (len % 2 != 0) continue; // skip if not even number of digits

            int sum1 = 0, sum2 = 0, half = len / 2;

            for (int i = 0; i < half; i++) {
                sum1 += str.charAt(i) - '0';
                sum2 += str.charAt(i + half) - '0';
            }

            if (sum1 == sum2) count++;
        }

        return count;
    }

}

//Explanation:
//We convert each number to a string to easily access digits.
//We skip numbers with an odd number of digits.
//We calculate the sum of digits for the first and second halves.
//If both sums are equal, we count it as symmetric.
//Time Complexity: O(N * D)
//N is the number of integers in the range: high - low + 1
//D is the number of digits in each number (at most 5 because high <= 10^4)
//Space Complexity: O(D) (almost O(1) because D is small(max5))
//We store the string representation of a number → takes up O(D) space


//You are given two positive integers low and high.
//An integer x consisting of 2 * n digits is symmetric if the sum of the first n digits of x is equal to the
// sum of the last n digits of x. Numbers with an odd number of digits are never symmetric.
//Return the number of symmetric integers in the range [low, high].

//Example 1:
//Input: low = 1, high = 100
//Output: 9
//Explanation: There are 9 symmetric integers between 1 and 100: 11, 22, 33, 44, 55, 66, 77, 88, and 99.

//Example 2:
//Input: low = 1200, high = 1230
//Output: 4
//Explanation: There are 4 symmetric integers between 1200 and 1230: 1203, 1212, 1221, and 1230.

//Constraints:
//1 <= low <= high <= 104
