package P1601_1700.P1639_Number_of_Ways_to_Form_aTarget_String_Given_a_Dictionary;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        String[] words1 = {"acca", "bbbb", "caca"};
        String target1 = "aba";
        System.out.println(solution.numWays(words1, target1)); // Output: 6

        String[] words2 = {"abba", "baab"};
        String target2 = "bab";
        System.out.println(solution.numWays(words2, target2)); // Output: 4
    }

    public int numWays(String[] words, String target) {
        final int MOD = 1_000_000_007;
        int wordLength = words[0].length();
        int targetLength = target.length();

        // precompute the frequency of each character at each index across all words
        int[][] charFreq = new int[wordLength][26];
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                charFreq[i][word.charAt(i) - 'a']++;
            }
        }

        //DP array: dp[i][j] = number of ways to form the first i characters of target using the first j columns of words
        long[][] dp = new long[targetLength + 1][wordLength + 1];
        dp[0][0] = 1; // Base case: 1 way to form an empty target

        // fill the DP table
        for (int i = 0; i <= targetLength; i++) {
            for (int j = 0; j < wordLength; j++) {
                // Carry over the number of ways from the previous column
                dp[i][j + 1] = (dp[i][j + 1] + dp[i][j]) % MOD;

                // If forming the next character of the target is possible
                if (i < targetLength) {
                    char targetChar = target.charAt(i);
                    dp[i + 1][j + 1] = (dp[i + 1][j + 1] + dp[i][j] * charFreq[j][targetChar - 'a']) % MOD;
                }
            }
        }

        // result is the number of ways to form the entire target using all columns of words
        return (int) dp[targetLength][wordLength];
    }
}

//Explanation:
//Character Frequency Precomputation:
//charFreq[i][c] stores the number of occurrences of the character c at position i across all strings in words.
//Dynamic Programming Table:
//dp[i][j] represents the number of ways to form the first i characters of the target using the first j columns of words.
//The dp array is updated column by column, ensuring that earlier characters cannot be reused incorrectly.
//Transitions:
//If you skip the current column: dp[i][j + 1] += dp[i][j].
//If you use the current column for the next character of the
// target: dp[i + 1][j + 1] += dp[i][j] * charFreq[j][target[i]].
//Result:
//The answer is stored in dp[targetLength][wordLength].
//Complexity:
//Time: O(wordLength×(targetLength+26)), where 26 is the size of the English alphabet.
//Space: O(targetLength×wordLength), which can be optimized further if needed.


//You are given a list of strings of the same length words and a string target.
//Your task is to form target using the given words under the following rules:
//target should be formed from left to right.
//To form the ith character (0-indexed) of target, you can choose the kth character of the jth string in
// words if target[i] = words[j][k].
//Once you use the kth character of the jth string of words, you can no longer use the xth character of any
// string in words where x <= k. In other words, all characters to the left of or at index k become
// unusuable for every string.
//Repeat the process until you form the string target.
//Notice that you can use multiple characters from the same string in words provided the conditions above are met.
//Return the number of ways to form target from words. Since the answer may be too large, return it modulo 109 + 7.
//
//Example 1:
//Input: words = ["acca","bbbb","caca"], target = "aba"
//Output: 6
//Explanation: There are 6 ways to form target.
//"aba" -> index 0 ("acca"), index 1 ("bbbb"), index 3 ("caca")
//"aba" -> index 0 ("acca"), index 2 ("bbbb"), index 3 ("caca")
//"aba" -> index 0 ("acca"), index 1 ("bbbb"), index 3 ("acca")
//"aba" -> index 0 ("acca"), index 2 ("bbbb"), index 3 ("acca")
//"aba" -> index 1 ("caca"), index 2 ("bbbb"), index 3 ("acca")
//"aba" -> index 1 ("caca"), index 2 ("bbbb"), index 3 ("caca")

//Example 2:
//Input: words = ["abba","baab"], target = "bab"
//Output: 4
//Explanation: There are 4 ways to form target.
//"bab" -> index 0 ("baab"), index 1 ("baab"), index 2 ("abba")
//"bab" -> index 0 ("baab"), index 1 ("baab"), index 3 ("baab")
//"bab" -> index 0 ("baab"), index 2 ("baab"), index 3 ("baab")
//"bab" -> index 1 ("abba"), index 2 ("baab"), index 3 ("baab")
//
//Constraints:
//1 <= words.length <= 1000
//1 <= words[i].length <= 1000
//All strings in words have the same length.
//1 <= target.length <= 1000
//words[i] and target contain only lowercase English letters.