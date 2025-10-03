package P1_100.P77_Combinations;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.combine(4, 2));
        // Output: [[1, 2], [1, 3], [1, 4], [2, 3], [2, 4], [3, 4]]

        System.out.println(solution.combine(1, 1));
        // Output: [[1]]
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(1, n, k, new ArrayList<>(), result);

        return result;
    }

    private void backtrack(int start, int n, int k, List<Integer> current, List<List<Integer>> result) {
        // if the combination is complete
        if (current.size() == k) {
            result.add(new ArrayList<>(current));

            return;
        }

        // try each number in the range [start, n]
        for (int i = start; i <= n; i++) {
            current.add(i);                 // choose i
            backtrack(i + 1, n, k, current, result); // explore further
            current.remove(current.size() - 1); // undo the choice
        }
    }

}

//Complexity:
// time - O(C(n, k)  - C-binominal coefficient (number of combinations)
// space - O(k)


//Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].
//You may return the answer in any order.

//Example 1:
//Input: n = 4, k = 2
//Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
//Explanation: There are 4 choose 2 = 6 total combinations.
//Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.

//Example 2:
//Input: n = 1, k = 1
//Output: [[1]]
//Explanation: There is 1 choose 1 = 1 total combination.

//Constraints:
//1 <= n <= 20
//1 <= k <= n
