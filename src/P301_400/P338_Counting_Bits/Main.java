package P301_400.P338_Counting_Bits;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int num1 = 6;
        int num2 = 4;
        int num3 = 0;
        int num4 = 15;

        System.out.println(Arrays.toString(solution.countBits(num1))); //[0, 1, 1, 2, 1, 2, 2]
        System.out.println(Arrays.toString(solution.countBits(num2))); //[0, 1, 1, 2, 1]
        System.out.println(Arrays.toString(solution.countBits(num3))); //[0]
        System.out.println(Arrays.toString(solution.countBits(num4))); //[0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4]


    }

    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        int countOnes = 0;

        for (int i = 1; i < n + 1; i++) {

            String binaryString = Integer.toBinaryString(i);
            for (int j = 0; j < binaryString.length(); j++) {
                if (binaryString.charAt(j) == '1') {
                    countOnes++;
                }
                ans[i] = countOnes;
            }
            countOnes = 0;
        }

        return ans;
    }
}

//Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n), ans[i] is the number
// of 1's in the binary representation of i.

//Example 1:
//Input: n = 2
//Output: [0,1,1]
//Explanation:
//0 --> 0
//1 --> 1
//2 --> 10

//Example 2:
//Input: n = 5
//Output: [0,1,1,2,1,2]
//Explanation:
//0 --> 0
//1 --> 1
//2 --> 10
//3 --> 11
//4 --> 100
//5 --> 101

//Constraints:
//0 <= n <= 105
