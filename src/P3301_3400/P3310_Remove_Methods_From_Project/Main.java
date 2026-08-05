package P3301_3400.P3310_Remove_Methods_From_Project;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int n1 = 4;
        int k1 = 1;
        int[][] invocations1 = {
                {1, 2},
                {0, 1},
                {3, 2}
        };
        System.out.println(solution.remainingMethods(n1, k1, invocations1)); // [0, 1, 2, 3]

        int n2 = 5;
        int k2 = 0;
        int[][] invocations2 = {
                {1, 2},
                {0, 2},
                {0, 1},
                {3, 4}
        };
        System.out.println(solution.remainingMethods(n2, k2, invocations2)); // [3, 4]

        int n3 = 3;
        int k3 = 2;
        int[][] invocations3 = {
                {1, 2},
                {0, 1},
                {2, 0}
        };
        System.out.println(solution.remainingMethods(n3, k3, invocations3)); // []
    }

    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : invocations) {
            graph.get(edge[0]).add(edge[1]);
        }

        // find all suspicious methods
        boolean[] suspicious = new boolean[n];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(k);
        suspicious[k] = true;

        while (!stack.isEmpty()) {
            int cur = stack.pop();

            for (int next : graph.get(cur)) {
                if (!suspicious[next]) {
                    suspicious[next] = true;
                    stack.push(next);
                }
            }
        }

        // check whether any outside method invokes a suspicious method
        for (int[] edge : invocations) {
            int from = edge[0];
            int to = edge[1];

            if (!suspicious[from] && suspicious[to]) {
                List<Integer> all = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    all.add(i);
                }
                return all;
            }
        }

        // rremove suspicious methods
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) {
                ans.add(i);
            }
        }

        return ans;
    }

}

// Complexity:
// time and space - O(n + m)
// n - num of methods, m - num of invocations


//You are maintaining a project that has n methods numbered from 0 to n - 1.
//You are given two integers n and k, and a 2D integer array invocations, where invocations[i] = [ai, bi] indicates
// that method ai invokes method bi.
//There is a known bug in method k. Method k, along with any method invoked by it, either directly or indirectly,
// are considered suspicious and we aim to remove them.
//A group of methods can only be removed if no method outside the group invokes any methods within it.
//Return an array containing all the remaining methods after removing all the suspicious methods. You may return the
// answer in any order. If it is not possible to remove all the suspicious methods, none should be removed.

//Example 1:
//Input: n = 4, k = 1, invocations = [[1,2],[0,1],[3,2]]
//Output: [0,1,2,3]
//Explanation:
//Method 2 and method 1 are suspicious, but they are directly invoked by methods 3 and 0, which are not suspicious. We
// return all elements without removing anything.

//Example 2:
//Input: n = 5, k = 0, invocations = [[1,2],[0,2],[0,1],[3,4]]
//Output: [3,4]
//Explanation:
//Methods 0, 1, and 2 are suspicious and they are not directly invoked by any other method. We can remove them.

//Example 3:
//Input: n = 3, k = 2, invocations = [[1,2],[0,1],[2,0]]
//Output: []
//Explanation:
//All methods are suspicious. We can remove them.

//Constraints:
//1 <= n <= 105
//0 <= k <= n - 1
//0 <= invocations.length <= 2 * 105
//invocations[i] == [ai, bi]
//0 <= ai, bi <= n - 1
//ai != bi
//invocations[i] != invocations[j]
