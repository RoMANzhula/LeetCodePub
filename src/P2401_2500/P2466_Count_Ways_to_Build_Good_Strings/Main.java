package P2401_2500.P2466_Count_Ways_to_Build_Good_Strings;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.countGoodString(3, 3, 1, 1)); // Output: 8

        System.out.println(solution.countGoodString(2, 3, 1, 2)); // Output: 5
    }

    public int countGoodString(int low, int high, int zero, int one) {
        int MOD = 1_000_000_007;

        int[] dp = new int[high + 1];
        dp[0] = 1; //base case: one way to make an empty string

        for (int i = 1; i <= high; i++) {
            if (i >= zero) {
                dp[i] = (dp[i] + dp[i - zero]) % MOD;
            }

            if (i >= one) {
                dp[i] = (dp[i] + dp[i - one]) % MOD;
            }
        }

        int result = 0;
        for (int i = low; i <= high; i++) {
            result = (result + dp[i]) % MOD;
        }

        return result;
    }
}

//Explanation:
//Base Case: dp[0] = 1 because the empty string is the only way to have length 0.
//Building the DP Array: for each length i, add contributions from dp[i - zero] and dp[i - one] (if valid).
//Summing Valid Results: sum all dp[i] for i in the range [low, high] to get the total count of good strings.
//Complexity:
//Time Complexity: O(high), as we iterate through lengths up to high.
//Space Complexity: O(high), for the dp array.


//Given the integers zero, one, low, and high, we can construct a string by starting with an empty string, and
// then at each step perform either of the following:
//Append the character '0' zero times.
//Append the character '1' one times.
//This can be performed any number of times.
//A good string is a string constructed by the above process having a length between low and high (inclusive).
//Return the number of different good strings that can be constructed satisfying these properties. Since the
// answer can be large, return it modulo 109 + 7.
//
//Example 1:
//Input: low = 3, high = 3, zero = 1, one = 1
//Output: 8
//Explanation:
//One possible valid good string is "011".
//It can be constructed as follows: "" -> "0" -> "01" -> "011".
//All binary strings from "000" to "111" are good strings in this example.

//Example 2:
//Input: low = 2, high = 3, zero = 1, one = 2
//Output: 5
//Explanation: The good strings are "00", "11", "000", "110", and "011".
//
//Constraints:
//1 <= low <= high <= 105
//1 <= zero, one <= low
