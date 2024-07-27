package P2901_3000.P2976_Minimum_Cost_to_Convert_String_l;

import java.util.Arrays;

public class Main {
    private static final int INF = (int) 1e9;

    public static void main(String[] args) {
        Main solution = new Main();

        String source1 = "abcd";
        String target1 = "acbe";
        char[] original1 = {'a', 'b', 'c', 'c', 'e', 'd'};
        char[] changed1 = {'b', 'c', 'b', 'e', 'b', 'e'};
        int[] cost1 = {2, 5, 5, 1, 2, 20};
        System.out.println(solution.minimumCost(source1, target1, original1, changed1, cost1));  // Output: 28

        String source2 = "aaaa";
        String target2 = "bbbb";
        char[] original2 = {'a', 'c'};
        char[] changed2 = {'c', 'b'};
        int[] cost2 = {1, 2};
        System.out.println(solution.minimumCost(source2, target2, original2, changed2, cost2));  // Output: 12

        String source3 = "abcd";
        String target3 = "abce";
        char[] original3 = {'a'};
        char[] changed3 = {'e'};
        int[] cost3 = {10000};
        System.out.println(solution.minimumCost(source3, target3, original3, changed3, cost3));  // Output: -1
    }

    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        int n = source.length();
        int[][] minCost = new int[26][26];

        // Initialize the cost array
        for (int[] row : minCost) {
            Arrays.fill(row, INF);
        }
        for (int i = 0; i < 26; i++) {
            minCost[i][i] = 0;  // Cost to transform a character to itself is 0
        }

        // Fill the minCost array with given transformations
        for (int i = 0; i < original.length; i++) {
            int from = original[i] - 'a';
            int to = changed[i] - 'a';
            minCost[from][to] = Math.min(minCost[from][to], cost[i]);
        }

        // Apply Floyd-Warshall algorithm to find all pairs shortest path
        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    if (minCost[i][k] < INF && minCost[k][j] < INF) {
                        minCost[i][j] = Math.min(minCost[i][j], minCost[i][k] + minCost[k][j]);
                    }
                }
            }
        }

        long totalCost = 0;

        // Calculate the minimum cost to transform source to target
        for (int i = 0; i < n; i++) {
            int srcChar = source.charAt(i) - 'a';
            int tgtChar = target.charAt(i) - 'a';
            if (minCost[srcChar][tgtChar] == INF) {
                return -1;  // Transformation is impossible
            }
            totalCost += minCost[srcChar][tgtChar];
        }

        return totalCost;
    }
}

//Explanation:
//Initialization: We initialize a minCost array where minCost[i][j] represents the minimum cost to transform
// character i to character j.
//Setting Direct Transformations: We fill this array with the given direct transformation costs.
//Floyd-Warshall Algorithm: This step ensures we find the minimum cost to transform any character to any
// other character using potentially intermediate transformations.
//Cost Calculation: For each character in the source string, we determine the cost to transform it to the
// corresponding character in the target string. If any transformation is impossible, we return -1. Otherwise,
// we sum up the costs.
//This approach ensures that we consider all possible transformation paths and find the minimum cost efficiently.


//You are given two 0-indexed strings source and target, both of length n and consisting of lowercase English
// letters. You are also given two 0-indexed character arrays original and changed, and an integer array cost,
// where cost[i] represents the cost of changing the character original[i] to the character changed[i].
//You start with the string source. In one operation, you can pick a character x from the string and change it to
// the character y at a cost of z if there exists any index j such that cost[j] == z, original[j] == x,
// and changed[j] == y.
//Return the minimum cost to convert the string source to the string target using any number of operations. If it
// is impossible to convert source to target, return -1.
//Note that there may exist indices i, j such that original[j] == original[i] and changed[j] == changed[i].
//
//Example 1:
//Input: source = "abcd", target = "acbe", original = ["a","b","c","c","e","d"],
// changed = ["b","c","b","e","b","e"], cost = [2,5,5,1,2,20]
//Output: 28

//Explanation: To convert the string "abcd" to string "acbe":
//- Change value at index 1 from 'b' to 'c' at a cost of 5.
//- Change value at index 2 from 'c' to 'e' at a cost of 1.
//- Change value at index 2 from 'e' to 'b' at a cost of 2.
//- Change value at index 3 from 'd' to 'e' at a cost of 20.
//The total cost incurred is 5 + 1 + 2 + 20 = 28.
//It can be shown that this is the minimum possible cost.

//Example 2:
//Input: source = "aaaa", target = "bbbb", original = ["a","c"], changed = ["c","b"], cost = [1,2]
//Output: 12
//Explanation: To change the character 'a' to 'b' change the character 'a' to 'c' at a cost of 1, followed by
// changing the character 'c' to 'b' at a cost of 2, for a total cost of 1 + 2 = 3. To change all occurrences of
// 'a' to 'b', a total cost of 3 * 4 = 12 is incurred.

//Example 3:
//Input: source = "abcd", target = "abce", original = ["a"], changed = ["e"], cost = [10000]
//Output: -1
//Explanation: It is impossible to convert source to target because the value at index 3 cannot be
// changed from 'd' to 'e'.
//
//Constraints:
//1 <= source.length == target.length <= 105
//source, target consist of lowercase English letters.
//1 <= cost.length == original.length == changed.length <= 2000
//original[i], changed[i] are lowercase English letters.
//1 <= cost[i] <= 106
//original[i] != changed[i]
