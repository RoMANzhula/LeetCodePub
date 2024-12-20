package P2401_2500.P2415_Reverse_Odd_Levels_of_Binary_Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        // Example 1
        TreeNode root1 = new TreeNode(2,
                new TreeNode(3, new TreeNode(8), new TreeNode(13)),
                new TreeNode(5, new TreeNode(21), new TreeNode(34)));
        TreeNode result1 = solution.reverseOddLevels(root1);
        System.out.println("Example 1 Output:");
        printTree(result1);

        // Example 2
        TreeNode root2 = new TreeNode(7,
                new TreeNode(13),
                new TreeNode(11));
        TreeNode result2 = solution.reverseOddLevels(root2);
        System.out.println("Example 2 Output:");
        printTree(result2);

        // Example 3
        TreeNode root3 = new TreeNode(0,
                new TreeNode(1, new TreeNode(0, new TreeNode(1), new TreeNode(1)), new TreeNode(0, new TreeNode(1), new TreeNode(1))),
                new TreeNode(2, new TreeNode(0, new TreeNode(2), new TreeNode(2)), new TreeNode(0, new TreeNode(2), new TreeNode(2))));
        TreeNode result3 = solution.reverseOddLevels(root3);
        System.out.println("Example 3 Output:");
        printTree(result3);
    }

    private static void printTree(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                System.out.print(node.val + " ");
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            System.out.println();
        }
    }

    public TreeNode reverseOddLevels(TreeNode root) {
        if (root == null) return null;

        // Use a queue to perform level-order traversal
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        boolean isOddLevel = false;

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<TreeNode> currentLevel = new ArrayList<>();

            // Collect all nodes in the current level
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            // Reverse values in the current level if it's an odd level
            if (isOddLevel) {
                int left = 0, right = currentLevel.size() - 1;
                while (left < right) {
                    int temp = currentLevel.get(left).val;
                    currentLevel.get(left).val = currentLevel.get(right).val;
                    currentLevel.get(right).val = temp;
                    left++;
                    right--;
                }
            }

            // Toggle odd/even level
            isOddLevel = !isOddLevel;
        }

        return root;
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
//Level Order Traversal:
//- We use a queue to traverse the tree level by level.
//- For each level, we collect all the nodes into a list currentLevel.
//Reversing Odd Levels:
// If the current level is an odd level, we reverse the values in the currentLevel list using
// two pointers (left and right).
//Queue Operations:
// While traversing, we enqueue the left and right children of the nodes at the current level to process the next level.
//Toggle Odd/Even Levels:
// We use a boolean isOddLevel to keep track of whether the current level is odd or even and toggle it at the
// end of each level.
//Time complexity:
//O(n), where n is the number of nodes in the tree.
//Space complexity:
//O(n) due to the queue and level storage.


//Given the root of a perfect binary tree, reverse the node values at each odd level of the tree.
//For example, suppose the node values at level 3 are [2,1,3,4,7,11,29,18], then it should become [18,29,11,7,4,3,1,2].
//Return the root of the reversed tree.
//A binary tree is perfect if all parent nodes have two children and all leaves are on the same level.
//The level of a node is the number of edges along the path between it and the root node.
//
//Example 1:
//Input: root = [2,3,5,8,13,21,34]
//Output: [2,5,3,8,13,21,34]
//Explanation:
//The tree has only one odd level.
//The nodes at level 1 are 3, 5 respectively, which are reversed and become 5, 3.

//Example 2:
//Input: root = [7,13,11]
//Output: [7,11,13]
//Explanation:
//The nodes at level 1 are 13, 11, which are reversed and become 11, 13.

//Example 3:
//Input: root = [0,1,2,0,0,0,0,1,1,1,1,2,2,2,2]
//Output: [0,2,1,0,0,0,0,2,2,2,2,1,1,1,1]
//Explanation:
//The odd levels have non-zero values.
//The nodes at level 1 were 1, 2, and are 2, 1 after the reversal.
//The nodes at level 3 were 1, 1, 1, 1, 2, 2, 2, 2, and are 2, 2, 2, 2, 1, 1, 1, 1 after the reversal.
//
//Constraints:
//The number of nodes in the tree is in the range [1, 214].
//0 <= Node.val <= 105
//root is a perfect binary tree.
