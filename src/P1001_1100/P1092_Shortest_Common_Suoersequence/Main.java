package P1001_1100.P1092_Shortest_Common_Suoersequence;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String str1 = "abac", str2 = "cab";
        System.out.println(solution.shortestCommonSupersequence(str1, str2)); // Output: "cabac"

        String str3 = "aaaaaaaa", str4 = "aaaaaaaa";
        System.out.println(solution.shortestCommonSupersequence(str3, str4)); // Output: "aaaaaaaa"
    }

    public String shortestCommonSupersequence(String str1, String str2) {
        int m = str1.length(), n = str2.length();
        int[][] dp = new int[m + 1][n + 1];

        // step 1: Compute LCS using DP
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Step 2: Build the SCS using LCS table
        StringBuilder sb = new StringBuilder();
        int i = m, j = n;

        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                sb.append(str1.charAt(i - 1)); // take common character
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                sb.append(str1.charAt(i - 1)); // take from str1
                i--;
            } else {
                sb.append(str2.charAt(j - 1)); // take from str2
                j--;
            }
        }

        // add remaining characters
        while (i > 0) {
            sb.append(str1.charAt(i - 1));
            i--;
        }
        while (j > 0) {
            sb.append(str2.charAt(j - 1));
            j--;
        }

        return sb.reverse().toString();
    }

}

//Explanation:
//Find LCS (ab for "abac" and "cab"):
//Helps determine the longest common part to avoid unnecessary duplication.
//Build the SCS:
//Traverse both strings while inserting non-LCS characters and merging both.
//Example:
//"cabac" is formed by merging "cab" and "abac", keeping "ab" from LCS.
//Complexity:
//Time Complexity: O(m * n)
//Space Complexity: O(m * n)


//Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences. If there
// are multiple valid strings, return any of them.
//A string s is a subsequence of string t if deleting some number of characters from t (possibly 0) results in
// the string s.
//
//Example 1:
//Input: str1 = "abac", str2 = "cab"
//Output: "cabac"
//Explanation:
//str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
//str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
//The answer provided is the shortest such string that satisfies these properties.

//Example 2:
//Input: str1 = "aaaaaaaa", str2 = "aaaaaaaa"
//Output: "aaaaaaaa"
//
//Constraints:
//1 <= str1.length, str2.length <= 1000
//str1 and str2 consist of lowercase English letters.
