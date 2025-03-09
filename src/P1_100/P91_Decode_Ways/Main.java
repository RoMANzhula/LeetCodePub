package P1_100.P91_Decode_Ways;

public class Main {
    public static void main(String[] args) {
        Main decoder = new Main();

        System.out.println(decoder.numDecodings("12"));  // Output: 2
        System.out.println(decoder.numDecodings("226")); // Output: 3
        System.out.println(decoder.numDecodings("06"));  // Output: 0
    }

    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;

        for (int i = 2; i <= n; i++) {
            int oneDigit = Integer.parseInt(s.substring(i - 1, i));
            int twoDigits = Integer.parseInt(s.substring(i - 2, i));

            if (oneDigit >= 1 && oneDigit <= 9) {
                dp[i] += dp[i - 1];
            }

            if (twoDigits >= 10 && twoDigits <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[n];
    }
}

// We use dynamic programming to construct the solution. We create an array dp, where dp[i] corresponds
// to the number of possible decodings of the substring up to the i-th character in the input string s.
// We initialize dp[0] and dp[1]. dp[0] is equal to 1 because an empty string has one possible way
// to be decoded. dp[1] is 1 if the first character is not '0' and 0 if it is '0'.
// We iterate through the rest of the string, considering one or two digits at each step.
// If considering a single digit (from i-1 to i), we check if this digit is not '0'. If it is not,
// the number of possible decodings at the current position increases by the number of decodings
// at the previous position.
// If considering two digits (from i-2 to i), we check whether the two-digit number falls within
// the range [10, 26]. If so, the number of possible decodings at the current position increases
// by the number of decodings at the position two steps back.
// We return dp[n], where n is the length of the string s. This is the final answer.
// Dynamic programming allows us to efficiently compute the number of possible decodings by
// leveraging previously computed results for complex strings.


//A message containing letters from A-Z can be encoded into numbers using the following mapping:
//'A' -> "1"
//'B' -> "2"
//...
//'Z' -> "26"
//To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse
// of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:
//"AAJF" with the grouping (1 1 10 6)
//"KJF" with the grouping (11 10 6)
//Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".
//Given a string s containing only digits, return the number of ways to decode it.
//The test cases are generated so that the answer fits in a 32-bit integer.

//Example 1:
//Input: s = "12"
//Output: 2
//Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).

//Example 2:
//Input: s = "226"
//Output: 3
//Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).

//Example 3:
//Input: s = "06"
//Output: 0
//Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06").

//Constraints:
//1 <= s.length <= 100
//s contains only digits and may contain leading zero(s).