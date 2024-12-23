package P2401_2500.P2471_Minimum_Number_of_Operations_to_Sort_a_Binary_Tree_by_Level;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        TreeNode root1 = new TreeNode(1,
                new TreeNode(4, new TreeNode(7), new TreeNode(6)),
                new TreeNode(
                        3,
                        new TreeNode(8, new TreeNode(9), null),
                        new TreeNode(5, new TreeNode(10), null))
                )
        ;
        System.out.println(solution.minimumOperations(root1)); // Output: 3

        TreeNode root2 = new TreeNode(1,
                new TreeNode(3, new TreeNode(7), new TreeNode(6)),
                new TreeNode(2, new TreeNode(5), new TreeNode(4))
        );
        System.out.println(solution.minimumOperations(root2)); // Output: 3

        TreeNode root3 = new TreeNode(1,
                new TreeNode(2, new TreeNode(4), new TreeNode(5)),
                new TreeNode(3, new TreeNode(6), null)
        );
        System.out.println(solution.minimumOperations(root3)); // Output: 0
    }

    public int minimumOperations(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int totalSwaps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> levelValues = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                levelValues.add(current.val);

                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }

            totalSwaps += countMinSwapsToSort(levelValues);
        }

        return totalSwaps;
    }

    private int countMinSwapsToSort(List<Integer> values) {
        int n = values.size();
        int[] arr = values.stream().mapToInt(Integer::intValue).toArray();

        // Create a pair of value and original index
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i][0] = arr[i];
            pairs[i][1] = i;
        }

        // Sort pairs by value
        Arrays.sort(pairs, Comparator.comparingInt(a -> a[0]));

        // Count cycles in the permutation
        boolean[] visited = new boolean[n];
        int swaps = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i] || pairs[i][1] == i) continue;

            int cycleSize = 0;
            int j = i;

            while (!visited[j]) {
                visited[j] = true;
                j = pairs[j][1];
                cycleSize++;
            }

            if (cycleSize > 1) swaps += (cycleSize - 1);
        }

        return swaps;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

//Explanation:
//Tree Traversal:
//-Perform a level-order traversal of the tree to extract nodes at each level.
//-Use a queue to manage the traversal.
//Count Minimum Swaps:
//-For each level, determine the minimum swaps to sort the values.
//-Create a pair array of values and their original indices.
//-Sort the array by values and detect cycles in the permutation to count swaps.
//Cycle Detection:
//-If a cycle of size k exists, it takes k−1 swaps to resolve it.
//Complexity:
//Tree traversal:
//O(n)
//Sorting each level:
//O(k log k), where k is the number of nodes at that level.
//Overall:
//O(n log k), where k is the maximum number of nodes at any level.


//You are given the root of a binary tree with unique values.
//In one operation, you can choose any two nodes at the same level and swap their values.
//Return the minimum number of operations needed to make the values at each level sorted in a strictly increasing order.
//The level of a node is the number of edges along the path between it and the root node.
//
//Example 1:
//Input: root = [1,4,3,7,6,8,5,null,null,null,null,9,null,10]
//Output: 3
//Explanation:
//- Swap 4 and 3. The 2nd level becomes [3,4].
//- Swap 7 and 5. The 3rd level becomes [5,6,8,7].
//- Swap 8 and 7. The 3rd level becomes [5,6,7,8].
//We used 3 operations so return 3.
//It can be proven that 3 is the minimum number of operations needed.

//Example 2:
//Input: root = [1,3,2,7,6,5,4]
//Output: 3
//Explanation:
//- Swap 3 and 2. The 2nd level becomes [2,3].
//- Swap 7 and 4. The 3rd level becomes [4,6,5,7].
//- Swap 6 and 5. The 3rd level becomes [4,5,6,7].
//We used 3 operations so return 3.
//It can be proven that 3 is the minimum number of operations needed.

//Example 3:
//Input: root = [1,2,3,4,5,6]
//Output: 0
//Explanation: Each level is already sorted in increasing order so return 0.
//
//Constraints:
//The number of nodes in the tree is in the range [1, 105].
//1 <= Node.val <= 105
//All the values of the tree are unique.