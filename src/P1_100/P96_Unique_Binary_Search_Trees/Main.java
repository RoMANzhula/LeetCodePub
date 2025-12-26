package P1_100.P96_Unique_Binary_Search_Trees;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.numTrees(3)); // Output: 5
        System.out.println(solution.numTrees(1)); // Output: 1
        System.out.println(solution.numTrees(5)); // Output: 42
    }

    public int numTrees(int n) {
        int[] dp = new int[n + 1];

        // base cases
        dp[0] = 1;
        dp[1] = 1;

        //build the DP table
        for (int nodes = 2; nodes <= n; nodes++) {
            for (int root = 1; root <= nodes; root++) {
                dp[nodes] += dp[root - 1] * dp[nodes - root];
            }
        }

        return dp[n];
    }

}

//Complexity:
// time - O(n^2)
// space - O(n)


//Given an integer n, return the number of structurally unique BST's (binary search trees) which has exactly n nodes
// of unique values from 1 to n.

//Example 1:
//Input: n = 3
//Output: 5

//Example 2:
//Input: n = 1
//Output: 1

//Constraints:
//1 <= n <= 19
