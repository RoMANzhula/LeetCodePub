package P2601_2700.P2641_Cousins_in_Binary_Tree_II;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        // Example 1
        TreeNode root1 = new TreeNode(5,
                new TreeNode(4,
                        new TreeNode(1),
                        new TreeNode(10)),
                new TreeNode(9,
                        null,
                        new TreeNode(7)));

        System.out.println("Original Tree (Example 1): ");
        printTree(root1);

        TreeNode newRoot1 = solution.replaceValueInTree(root1);
        System.out.println("Modified Tree (Example 1): ");
        printTree(newRoot1);

        // Example 2
        TreeNode root2 = new TreeNode(3,
                new TreeNode(1),
                new TreeNode(2));

        System.out.println("Original Tree (Example 2): ");
        printTree(root2);

        TreeNode newRoot2 = solution.replaceValueInTree(root2);
        System.out.println("Modified Tree (Example 2): ");
        printTree(newRoot2);
    }

    public static void printTree(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                System.out.print(node.val + " ");
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        System.out.println();
    }

    public TreeNode replaceValueInTree(TreeNode root) {
        if (root == null) return null;

        // Queue for BFS traversal
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // Set root value to 0 because it has no cousins
        root.val = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            int levelSum = 0;
            List<TreeNode> currentLevel = new ArrayList<>();

            // First pass: calculate total level sum and collect nodes
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node);

                if (node.left != null) {
                    levelSum += node.left.val;
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    levelSum += node.right.val;
                    queue.offer(node.right);
                }
            }

            // Second pass: update node values to sum of cousins
            for (TreeNode node : currentLevel) {
                int siblingSum = 0;

                if (node.left != null && node.right != null) {
                    siblingSum = node.left.val + node.right.val;
                } else if (node.left != null) {
                    siblingSum = node.left.val;
                } else if (node.right != null) {
                    siblingSum = node.right.val;
                }

                // Update left and right children with cousin sums
                if (node.left != null) {
                    node.left.val = levelSum - siblingSum;
                }
                if (node.right != null) {
                    node.right.val = levelSum - siblingSum;
                }
            }
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

//Explanation of the Solution
//The problem asks to replace the value of each node in a binary tree with the sum of all its cousins' values. Cousins
// are defined as nodes at the same depth in the tree but with different parents. The solution uses
// Breadth-First Search (BFS) (also known as level-order traversal) to solve this problem.
//Key Steps in the Solution
//BFS Traversal (Level-Order Traversal):
//We traverse the tree level by level. At each level, we compute the total sum of node values and
// collect all nodes in that level.
//By traversing level by level, we can easily access all nodes at the same depth, which helps in finding the
// sum of cousins for each node.
//Calculate the Total Sum at Each Level:
//At each level, we calculate the total sum of the node values. This is important because the cousin's value
// for any node is determined by excluding the values of its siblings from the total level sum.
//Sibling Sum:
//For each node, we calculate the sum of its siblings' values. The sibling sum is:
//If a node has both left and right children, the sibling sum is the sum of both children.
//If it has only one child (either left or right), the sibling sum is the value of that child.
//Cousins' Sum:
//The value of a node is updated to the total sum of its cousins. This is done by subtracting the sibling sum
// from the total sum of nodes at the same level.
//If the node has no siblings, the entire level sum becomes the new value for that node.


//Given the root of a binary tree, replace the value of each node in the tree with the sum of all its cousins' values.
//Two nodes of a binary tree are cousins if they have the same depth with different parents.
//Return the root of the modified tree.
//Note that the depth of a node is the number of edges in the path from the root node to it.
//
//Example 1:
//Input: root = [5,4,9,1,10,null,7]
//Output: [0,0,0,7,7,null,11]
//Explanation: The diagram above shows the initial binary tree and the binary tree after changing the value of each node.
//- Node with value 5 does not have any cousins so its sum is 0.
//- Node with value 4 does not have any cousins so its sum is 0.
//- Node with value 9 does not have any cousins so its sum is 0.
//- Node with value 1 has a cousin with value 7 so its sum is 7.
//- Node with value 10 has a cousin with value 7 so its sum is 7.
//- Node with value 7 has cousins with values 1 and 10 so its sum is 11.

//Example 2:
//Input: root = [3,1,2]
//Output: [0,0,0]
//Explanation: The diagram above shows the initial binary tree and the binary tree after changing the value of each node.
//- Node with value 3 does not have any cousins so its sum is 0.
//- Node with value 1 does not have any cousins so its sum is 0.
//- Node with value 2 does not have any cousins so its sum is 0.
//
//Constraints:
//The number of nodes in the tree is in the range [1, 105].
//1 <= Node.val <= 104