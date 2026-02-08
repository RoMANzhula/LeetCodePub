package P101_200.P111_Minimum_Depth_of_Binary_Tree;

import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);

        System.out.println(solution.minDepth(root1)); // Output: 2

        TreeNode root2 = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.right.right = new TreeNode(4);
        root2.right.right.right = new TreeNode(5);
        root2.right.right.right.right = new TreeNode(6);

        System.out.println(solution.minDepth(root2)); // output: 5
    }

    public int minDepth(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                // first leaf found → minimum depth
                if (node.left == null && node.right == null) {
                    return depth;
                }

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            depth++;
        }

        return depth;
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

//Complexity:
// time and space - O(n)


//Given a binary tree, find its minimum depth.
//The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
//Note: A leaf is a node with no children.

//Example 1:
//Input: root = [3,9,20,null,null,15,7]
//Output: 2

//Example 2:
//Input: root = [2,null,3,null,4,null,5,null,6]
//Output: 5

//Constraints:
//The number of nodes in the tree is in the range [0, 105].
//-1000 <= Node.val <= 1000
