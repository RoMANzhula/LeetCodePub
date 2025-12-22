package P901_1000.P960_Delete_Columns_to_Make_Sorted_III;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.minDeletionSize(new String[]{"babca", "bbazb"})); // 3
        System.out.println(solution.minDeletionSize(new String[]{"edcba"}));          // 4
        System.out.println(solution.minDeletionSize(new String[]{"ghi", "def", "abc"})); // 0
    }

    public int minDeletionSize(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();

        int[] dp = new int[m];
        int maxKeep = 1;

        // each column alone is valid
        for (int j = 0; j < m; j++) {
            dp[j] = 1;
        }

        // DP over columns
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < j; i++) {

                boolean valid = true;
                for (int r = 0; r < n; r++) {
                    if (strs[r].charAt(i) > strs[r].charAt(j)) {
                        valid = false;
                        break;
                    }
                }

                if (valid) {
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                }
            }

            maxKeep = Math.max(maxKeep, dp[j]);
        }

        // minimum deletions
        return m - maxKeep;
    }

}

//Complexity:
// time - O(n * m^2)
// space - O(m)


//You are given an array of n strings strs, all of the same length.
//We may choose any deletion indices, and we delete all the characters in those indices for each string.
//For example, if we have strs = ["abcdef","uvwxyz"] and deletion indices {0, 2, 3}, then the final array after
// deletions is ["bef", "vyz"].
//Suppose we chose a set of deletion indices answer such that after deletions, the final array has every
// string (row) in lexicographic order. (i.e., (strs[0][0] <= strs[0][1] <= ... <= strs[0][strs[0].length - 1]), and
// (strs[1][0] <= strs[1][1] <= ... <= strs[1][strs[1].length - 1]), and so on). Return the minimum possible value of
// answer.length.

//Example 1:
//Input: strs = ["babca","bbazb"]
//Output: 3
//Explanation: After deleting columns 0, 1, and 4, the final array is strs = ["bc", "az"].
//Both these rows are individually in lexicographic order (ie. strs[0][0] <= strs[0][1] and strs[1][0] <= strs[1][1]).
//Note that strs[0] > strs[1] - the array strs is not necessarily in lexicographic order.

//Example 2:
//Input: strs = ["edcba"]
//Output: 4
//Explanation: If we delete less than 4 columns, the only row will not be lexicographically sorted.

//Example 3:
//Input: strs = ["ghi","def","abc"]
//Output: 0
//Explanation: All rows are already lexicographically sorted.

//Constraints:
//n == strs.length
//1 <= n <= 100
//1 <= strs[i].length <= 100
//strs[i] consists of lowercase English letters.
