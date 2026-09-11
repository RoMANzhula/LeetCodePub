package P3401_3500.P3483_Unique_3_Digit_Even_Numbers;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] digits1 = {1, 2, 3, 4};
        int[] digits2 = {0, 2, 2};
        int[] digits3 = {6, 6, 6};
        int[] digits4 = {1, 3, 5};

        System.out.println(solution.totalNumbers(digits1)); // 12
        System.out.println(solution.totalNumbers(digits2)); // 2
        System.out.println(solution.totalNumbers(digits3)); // 1
        System.out.println(solution.totalNumbers(digits4)); // 0
    }

    public int totalNumbers(int[] digits) {

        // count how many times each digit occurs
        int[] count = new int[10];

        for (int digit : digits) {
            count[digit]++;
        }

        int result = 0;

        // check every possible three-digit number
        for (int num = 100; num <= 999; num++) {

            // last digit must be even
            if (num % 2 != 0) {
                continue;
            }

            int a = num / 100;
            int b = (num / 10) % 10;
            int c = num % 10;

            // count digits required by this number
            int[] required = new int[10];

            required[a]++;
            required[b]++;
            required[c]++;

            // check whether we have enough copies
            boolean canForm = true;

            for (int digit = 0; digit <= 9; digit++) {
                if (required[digit] > count[digit]) {
                    canForm = false;
                    break;
                }
            }

            if (canForm) {
                result++;
            }
        }

        return result;
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//You are given an array of digits called digits. Your task is to determine the number of distinct three-digit even
// numbers that can be formed using these digits.
//Note: Each copy of a digit can only be used once per number, and there may not be leading zeros.

//Example 1:
//Input: digits = [1,2,3,4]
//Output: 12
//Explanation: The 12 distinct 3-digit even numbers that can be formed are
// 124, 132, 134, 142, 214, 234, 312, 314, 324, 342, 412, and 432. Note that 222 cannot be formed because there is
// only 1 copy of the digit 2.

//Example 2:
//Input: digits = [0,2,2]
//Output: 2
//Explanation: The only 3-digit even numbers that can be formed are 202 and 220. Note that the digit 2 can be used
// twice because it appears twice in the array.

//Example 3:
//Input: digits = [6,6,6]
//Output: 1
//Explanation: Only 666 can be formed.

//Example 4:
//Input: digits = [1,3,5]
//Output: 0
//Explanation: No even 3-digit numbers can be formed.

//Constraints:
//3 <= digits.length <= 10
//0 <= digits[i] <= 9
