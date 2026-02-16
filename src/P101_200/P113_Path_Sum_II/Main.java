package P101_200.P113_Path_Sum_II;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        /*
                5
               / \
              4   8
             /   / \
            11  13  4
           /  \     / \
          7    2   5   1
        */

        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);

        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);

        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);

        int targetSum = 22;

        List<List<Integer>> result = solution.pathSum(root, targetSum);
        System.out.println(result);
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();

        dfs(root, targetSum, currentPath, result);

        return result;
    }

    private void dfs(
            TreeNode node,
            int remainingSum,
            List<Integer> currentPath,
            List<List<Integer>> result
    ) {

        if (node == null) {
            return;
        }

        //add current node to path
        currentPath.add(node.val);

        // check if it's a leaf AND sum matches
        if (node.left == null && node.right == null
                && remainingSum == node.val) {

            result.add(new ArrayList<>(currentPath));
        } else {
            // continue DFS
            dfs(node.left, remainingSum - node.val, currentPath, result);
            dfs(node.right, remainingSum - node.val, currentPath, result);
        }

        // backtrack (remove last element)
        currentPath.remove(currentPath.size() - 1);
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


//Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where the sum of the node
// values in the path equals targetSum. Each path should be returned as a list of the node values, not node references.
//A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.

//Example 1:
//Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
//Output: [[5,4,11,2],[5,8,4,5]]
//Explanation: There are two paths whose sum equals targetSum:
//5 + 4 + 11 + 2 = 22
//5 + 8 + 4 + 5 = 22

//Example 2:
//Input: root = [1,2,3], targetSum = 5
//Output: []

//Example 3:
//Input: root = [1,2], targetSum = 0
//Output: []

//Constraints:
//The number of nodes in the tree is in the range [0, 5000].
//-1000 <= Node.val <= 1000
//-1000 <= targetSum <= 1000
