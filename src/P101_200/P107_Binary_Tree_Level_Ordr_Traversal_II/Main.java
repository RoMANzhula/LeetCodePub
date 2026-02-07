package P101_200.P107_Binary_Tree_Level_Ordr_Traversal_II;

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

        List<List<Integer>> result = solution.levelOrderBottom(root);
        System.out.println(result); // [[15, 7], [9, 20], [3]]
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            result.add(0, level);
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


//Given the root of a binary tree, return the bottom-up level order traversal of its nodes' values. (i.e., from left to
// right, level by level from leaf to root).

//Example 1:
//Input: root = [3,9,20,null,null,15,7]
//Output: [[15,7],[9,20],[3]]

//Example 2:
//Input: root = [1]
//Output: [[1]]

//Example 3:
//Input: root = []
//Output: []

//Constraints:
//The number of nodes in the tree is in the range [0, 2000].
//-1000 <= Node.val <= 1000
