package P701_800.P712_Minimum_ASCII_Delete_Sum_for_Two_Strings;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String s1 = "sea";
        String s2 = "eat";
        System.out.println(solution.minimumDeleteSum(s1, s2)); // 231

        s1 = "delete";
        s2 = "leet";
        System.out.println(solution.minimumDeleteSum(s1, s2)); // 403
    }

    public int minimumDeleteSum(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n + 1][m + 1];

        //delete all remaining chars from s1
        for (int i = n - 1; i >= 0; i--) {
            dp[i][m] = dp[i + 1][m] + s1.charAt(i);
        }

        // delete all remaining chars from s2
        for (int j = m - 1; j >= 0; j--) {
            dp[n][j] = dp[n][j + 1] + s2.charAt(j);
        }

        // fill DP table
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1];
                } else {
                    dp[i][j] = Math.min(
                            s1.charAt(i) + dp[i + 1][j],
                            s2.charAt(j) + dp[i][j + 1]
                    );
                }
            }
        }

        return dp[0][0];
    }

}

//Complexity:
// time and space - O(n * m)


//Given two strings s1 and s2, return the lowest ASCII sum of deleted characters to make two strings equal.

//Example 1:
//Input: s1 = "sea", s2 = "eat"
//Output: 231
//Explanation: Deleting "s" from "sea" adds the ASCII value of "s" (115) to the sum.
//Deleting "t" from "eat" adds 116 to the sum.
//At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum possible to achieve this.

//Example 2:
//Input: s1 = "delete", s2 = "leet"
//Output: 403
//Explanation: Deleting "dee" from "delete" to turn the string into "let",
//adds 100[d] + 101[e] + 101[e] to the sum.
//Deleting "e" from "leet" adds 101[e] to the sum.
//At the end, both strings are equal to "let", and the answer is 100+101+101+101 = 403.
//If instead we turned both strings into "lee" or "eet", we would get answers of 433 or 417, which are higher.

//Constraints:
//1 <= s1.length, s2.length <= 1000
//s1 and s2 consist of lowercase English letters.
