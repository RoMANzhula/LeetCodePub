package P101_200.P103_Binary_Tree_Zigzag_Level_Order_Traversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        /*
              3
             / \
            9  20
               / \
              15  7
        */

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println(solution.zigzagLevelOrder(root));
        // Output: [[3], [20, 9], [15, 7]]
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            LinkedList<Integer> level = new LinkedList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();

                if (leftToRight) {
                    level.addLast(current.val);
                } else {
                    level.addFirst(current.val);
                }

                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }

            result.add(level);
            leftToRight = !leftToRight; // switch direction
        }

        return result;
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


//Given the root of a binary tree, return the zigzag level order traversal of
// its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).

//Example 1:
//Input: root = [3,9,20,null,null,15,7]
//Output: [[3],[20,9],[15,7]]

//Example 2:
//Input: root = [1]
//Output: [[1]]

//Example 3:
//Input: root = []
//Output: []

//Constraints:
//The number of nodes in the tree is in the range [0, 2000].
//-100 <= Node.val <= 100
