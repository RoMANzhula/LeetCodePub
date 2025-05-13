package P3301_3400.P3335_Total_Characters_in_String_After_Transformations_I;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.lengthAfterTransformations("abcyy", 2)); // Output: 7
        System.out.println(solution.lengthAfterTransformations("azbk", 1));  // Output: 5
    }


    static final int MOD = 1_000_000_007;

    public int lengthAfterTransformations(String s, int t) {
        int[][] dp = new int[26][t + 1];

        // initialize: 0 transformations → each char becomes 1
        for (int i = 0; i < 26; i++) {
            dp[i][0] = 1;
        }

        // build dp table
        for (int time = 1; time <= t; time++) {
            for (int c = 0; c < 26; c++) {
                if (c == 25) { // 'z'
                    dp[c][time] = (dp[0][time - 1] + dp[1][time - 1]) % MOD;
                } else {
                    dp[c][time] = dp[c + 1][time - 1];
                }
            }
        }

        // compute result
        long result = 0;
        for (char ch : s.toCharArray()) {
            result = (result + dp[ch - 'a'][t]) % MOD;
        }

        return (int) result;
    }

}

//Complexity:
// time - O(t) (t = 10^5)
// space - O(t x 26)


//You are given a string s and an integer t, representing the number of transformations to perform. In one
// transformation, every character in s is replaced according to the following rules:
//If the character is 'z', replace it with the string "ab".
//Otherwise, replace it with the next character in the alphabet. For example, 'a' is replaced with 'b', 'b' is
// replaced with 'c', and so on.
//Return the length of the resulting string after exactly t transformations.
//Since the answer may be very large, return it modulo 109 + 7.

//Example 1:
//Input: s = "abcyy", t = 2
//Output: 7
//Explanation:
//First Transformation (t = 1):
//'a' becomes 'b'
//'b' becomes 'c'
//'c' becomes 'd'
//'y' becomes 'z'
//'y' becomes 'z'
//String after the first transformation: "bcdzz"
//Second Transformation (t = 2):
//'b' becomes 'c'
//'c' becomes 'd'
//'d' becomes 'e'
//'z' becomes "ab"
//'z' becomes "ab"
//String after the second transformation: "cdeabab"
//Final Length of the string: The string is "cdeabab", which has 7 characters.

//Example 2:
//Input: s = "azbk", t = 1
//Output: 5
//Explanation:
//First Transformation (t = 1):
//'a' becomes 'b'
//'z' becomes "ab"
//'b' becomes 'c'
//'k' becomes 'l'
//String after the first transformation: "babcl"
//Final Length of the string: The string is "babcl", which has 5 characters.

//Constraints:
//1 <= s.length <= 105
//s consists only of lowercase English letters.
//1 <= t <= 105