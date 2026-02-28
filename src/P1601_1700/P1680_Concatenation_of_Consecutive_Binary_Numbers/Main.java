package P1601_1700.P1680_Concatenation_of_Consecutive_Binary_Numbers;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.concatenatedBinary(1));   // 1
        System.out.println(solution.concatenatedBinary(3));   // 27
        System.out.println(solution.concatenatedBinary(12));  // 505379714
    }

    public int concatenatedBinary(int n) {
        final int MOD = 1_000_000_007;
        long result = 0;
        int bitLength = 0;

        for (int i = 1; i <= n; i++) {
            // if i is power of 2, increase bit length
            if ((i & (i - 1)) == 0) {
                bitLength++;
            }

            // shift left and add current number
            result = ((result << bitLength) + i) % MOD;
        }

        return (int) result;
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//Given an integer n, return the decimal value of the binary string formed by concatenating the binary
// representations of 1 to n in order, modulo 109 + 7.

//Example 1:
//Input: n = 1
//Output: 1
//Explanation: "1" in binary corresponds to the decimal value 1.

//Example 2:
//Input: n = 3
//Output: 27
//Explanation: In binary, 1, 2, and 3 corresponds to "1", "10", and "11".
//After concatenating them, we have "11011", which corresponds to the decimal value 27.

//Example 3:
//Input: n = 12
//Output: 505379714
//Explanation: The concatenation results in "1101110010111011110001001101010111100".
//The decimal value of that is 118505380540.
//After modulo 109 + 7, the result is 505379714.

//Constraints:
//1 <= n <= 105
