package P1301_1400.P1317_Convert_Integer_to_the_Sum_of_Two_No_Zero_Integers;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int n1 = 2;
        System.out.println(Arrays.toString(solution.getNoZeroIntegers(n1))); // [1, 1]

        int n2 = 11;
        System.out.println(Arrays.toString(solution.getNoZeroIntegers(n2))); // [2, 9] or [8, 3]
    }

    private static boolean isNoZero(int num) {
        while (num > 0) {
            if (num % 10 == 0) return false;

            num /= 10;
        }

        return true;
    }

    public static int[] getNoZeroIntegers(int n) {
        for (int a = 1; a < n; a++) {
            int b = n - a;
            if (isNoZero(a) && isNoZero(b)) {
                return new int[]{a, b};
            }
        }

        return new int[]{}; // problem guarantees a solution exists
    }

}

//Complexity:
// time - O(n * log(n))
// space - O(1)


//No-Zero integer is a positive integer that does not contain any 0 in its decimal representation.
//Given an integer n, return a list of two integers [a, b] where:
//a and b are No-Zero integers.
//a + b = n
//The test cases are generated so that there is at least one valid solution. If there are many valid solutions, you
// can return any of them.

//Example 1:
//Input: n = 2
//Output: [1,1]
//Explanation: Let a = 1 and b = 1.
//Both a and b are no-zero integers, and a + b = 2 = n.

//Example 2:
//Input: n = 11
//Output: [2,9]
//Explanation: Let a = 2 and b = 9.
//Both a and b are no-zero integers, and a + b = 11 = n.
//Note that there are other valid answers as [8, 3] that can be accepted.

//Constraints:
//2 <= n <= 104
