package P1101_1200.P1143_Longest_Common_Subsequence;

public class Main {
    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence("abcde", "ace")); //output 3
        System.out.println(longestCommonSubsequence("abc", "abc")); //output 3
        System.out.println(longestCommonSubsequence("abc", "def")); //output 0

    }

    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }
}

//Ми використовуємо динамічне програмування для розв'язання цієї задачі. Створюємо матрицю dp, де dp[i][j] буде
// містити довжину найдовшої спільної підпослідовності рядків text1 довжини i та text2 довжини j.
//Ми проходимо через обидва рядки text1 та text2 і визначаємо довжину найдовшої спільної підпослідовності на
// кожному кроці. Якщо символи відповідних позицій однакові, то збільшуємо довжину підпослідовності на 1; в
// іншому випадку використовуємо максимальне значення з попередніх кроків. На кінцевому етапі отримуємо довжину
// найдовшої спільної підпослідовності в dp[m][n].

//Given two strings text1 and text2, return the length of their longest common subsequence. If there is no
// common subsequence, return 0.
//
//A subsequence of a string is a new string generated from the original string with some characters (can be none)
// deleted without changing the relative order of the remaining characters.
//
//For example, "ace" is a subsequence of "abcde".
//A common subsequence of two strings is a subsequence that is common to both strings.

//Example 1:
//Input: text1 = "abcde", text2 = "ace"
//Output: 3
//Explanation: The longest common subsequence is "ace" and its length is 3.

//Example 2:
//Input: text1 = "abc", text2 = "abc"
//Output: 3
//Explanation: The longest common subsequence is "abc" and its length is 3.

//Example 3:
//Input: text1 = "abc", text2 = "def"
//Output: 0
//Explanation: There is no such common subsequence, so the result is 0.

//Constraints:
//1 <= text1.length, text2.length <= 1000
//text1 and text2 consist of only lowercase English characters.
