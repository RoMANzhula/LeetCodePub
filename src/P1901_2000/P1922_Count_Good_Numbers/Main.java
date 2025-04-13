package P1901_2000.P1922_Count_Good_Numbers;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.countGoodNumbers(1));   // Output: 5
        System.out.println(solution.countGoodNumbers(4));   // Output: 400
        System.out.println(solution.countGoodNumbers(50));  // Output: 564908303
    }

    private static final int MOD = 1_000_000_007;

    public int countGoodNumbers(long n) {
        long evenCount = (n + 1) >> 1;
        long oddCount = n >> 1;

        long powerOf4 = modPow(4, oddCount, MOD);
        long powerOf5 = modPow(5, evenCount, MOD);

        return (int) ((powerOf4 * powerOf5) % MOD);
    }

    private long modPow(long base, long exp, int mod) {
        long result = 1;
        base = base % mod;

        while (exp > 0) {
            if ((exp & 1) == 1) result = (result * base) % mod;

            base = (base * base) % mod;
            exp >>= 1;
        }

        return result;
    }

}

//Explanation:
//modPow efficiently calculates (base^exp) % mod even for very large n (up to 10^15).
//We split the positions based on parity and raise 5 and 4 to the appropriate powers.
//Final result is (5^evenCount * 4^oddCount) % MOD
//Complexity:
//time: O(log n)
//space: O(n)


//A digit string is good if the digits (0-indexed) at even indices are even and the digits at odd indices are
// prime (2, 3, 5, or 7).
//For example, "2582" is good because the digits (2 and 8) at even positions are even and the digits (5 and 2) at
// odd positions are prime. However, "3245" is not good because 3 is at an even index but is not even.
//Given an integer n, return the total number of good digit strings of length n. Since the answer may be large,
// return it modulo 109 + 7.
//A digit string is a string consisting of digits 0 through 9 that may contain leading zeros.

//Example 1:
//Input: n = 1
//Output: 5
//Explanation: The good numbers of length 1 are "0", "2", "4", "6", "8".

//Example 2:
//Input: n = 4
//Output: 400

//Example 3:
//Input: n = 50
//Output: 564908303

//Constraints:
//1 <= n <= 1015
