package P1_100.P95_Unique_Binary_Search_Trees_II;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int n = 3;
        List<TreeNode> trees = solution.generateTrees(n);

        System.out.println("Total trees: " + trees.size());
        for (TreeNode tree : trees) {
            System.out.println(serialize(tree));
        }
    }

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<>();
        return buildTrees(1, n);
    }

    private static List<TreeNode> buildTrees(int start, int end) {
        List<TreeNode> result = new ArrayList<>();

        // base case: empty tree
        if (start > end) {
            result.add(null);
            return result;
        }

        //try each value as root
        for (int rootVal = start; rootVal <= end; rootVal++) {
            List<TreeNode> leftTrees = buildTrees(start, rootVal - 1);
            List<TreeNode> rightTrees = buildTrees(rootVal + 1, end);

            // combine left & right subtrees
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode root = new TreeNode(rootVal);
                    root.left = left;
                    root.right = right;
                    result.add(root);
                }
            }
        }
        return result;
    }

    // sserialize tree into level-order format
    private static List<String> serialize(TreeNode root) {
        List<String> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                result.add("null");
            } else {
                result.add(String.valueOf(node.val));
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }

        // remove trailing "null"s
        int i = result.size() - 1;
        while (i >= 0 && result.get(i).equals("null")) {
            result.remove(i--);
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


//Given an integer n, return all the structurally unique BST's (binary search trees), which has exactly n nodes of
// unique values from 1 to n. Return the answer in any order.

//Example 1:
//Input: n = 3
//Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]

//Example 2:
//Input: n = 1
//Output: [[1]]

//Constraints:
//1 <= n <= 8
