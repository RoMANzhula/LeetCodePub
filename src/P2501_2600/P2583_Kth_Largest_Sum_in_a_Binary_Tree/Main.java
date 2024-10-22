package P2501_2600.P2583_Kth_Largest_Sum_in_a_Binary_Tree;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(8);
        root.right = new TreeNode(9);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(1);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(7);
        root.left.left.left = new TreeNode(4);
        root.left.left.right = new TreeNode(6);

        long result = solution.kthLargestLevelSum(root, 2);
        System.out.println(result); // Output: 13
    }

    public long kthLargestLevelSum(TreeNode root, int k) {
        if (root == null) {
            return -1;
        }

        // Use a queue for BFS
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        // List to store level sums
        List<Long> levelSums = new ArrayList<>();

        // Perform level-order traversal (BFS)
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            long levelSum = 0;

            // Sum the values of nodes at the current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                levelSum += currentNode.val;

                // Add the children to the queue for the next level
                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }

            // Add the sum of the current level to the list
            levelSums.add(levelSum);
        }

        // Sort the level sums in descending order
        Collections.sort(levelSums, Collections.reverseOrder());

        // If k is greater than the number of levels, return -1
        if (k > levelSums.size()) {
            return -1;
        }

        // Return the kth largest sum (1-based index, so k-1)
        return levelSums.get(k - 1);
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
//TreeNode class: Represents a node in the binary tree with a value and left/right children.
//kthLargestLevelSum method: This is where the solution is implemented.
//We start by checking if the root is null. If it is, we return -1 since there are no levels.
//A queue is used for the level-order traversal (BFS).
//For each level, we calculate the sum of the nodes' values and store it in the levelSums list.
//After traversing all levels, we sort the levelSums in descending order.
//If k is greater than the number of levels, we return -1. Otherwise, we return the kth largest sum.
//Time Complexity:
//O(n) for traversing the tree once where n is the number of nodes.
//O(l log l) for sorting the level sums where l is the number of levels in the tree. In the worst case, l is
// proportional to the height of the tree (O(log n)).


//You are given the root of a binary tree and a positive integer k.
//The level sum in the tree is the sum of the values of the nodes that are on the same level.
//Return the kth largest level sum in the tree (not necessarily distinct). If there are fewer than k levels
// in the tree, return -1.
//Note that two nodes are on the same level if they have the same distance from the root.
//
//Example 1:
//Input: root = [5,8,9,2,1,3,7,4,6], k = 2
//Output: 13
//Explanation: The level sums are the following:
//- Level 1: 5.
//- Level 2: 8 + 9 = 17.
//- Level 3: 2 + 1 + 3 + 7 = 13.
//- Level 4: 4 + 6 = 10.
//The 2nd largest level sum is 13.

//Example 2:
//Input: root = [1,2,null,3], k = 1
//Output: 3
//Explanation: The largest level sum is 3.
//
//Constraints:
//The number of nodes in the tree is n.
//2 <= n <= 105
//1 <= Node.val <= 106
//1 <= k <= n
