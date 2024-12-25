package P501_600.P515_Find_Largest_Value_in_Each_Tree_Row;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

    }

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int step = 1;  // first step

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            int maxInSubArray = Integer.MIN_VALUE;  // initial(first) value of the maximum

            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                maxInSubArray = Math.max(maxInSubArray, current.val);

                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }

            result.add(maxInSubArray);

            step *= 2;  // double the step at each iteration
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

//Given the root of a binary tree, return an array of the largest value in each row of the tree (0-indexed).
//
//Example 1:
//Input: root = [1,3,2,5,3,null,9]
//Output: [1,3,9]

//Example 2:
//Input: root = [1,2,3]
//Output: [1,3]
//
//Constraints:
//The number of nodes in the tree will be in the range [0, 104].
//-231 <= Node.val <= 231 - 1
