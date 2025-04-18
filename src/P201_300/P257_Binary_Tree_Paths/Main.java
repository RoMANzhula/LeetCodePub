package P201_300.P257_Binary_Tree_Paths;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);

        List<String> paths = solution.binaryTreePaths(root);
        System.out.println("Output: " + paths); // output: ["1->2->5", "1->3"]

    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) return result;

        dfs(root, "", result);
        return result;
    }

    private static void dfs(TreeNode node, String path, List<String> result) {
        if (node == null) return;

        // build the current path
        path += node.val;

        //if it's a leaf node, add the path to the result
        if (node.left == null && node.right == null) {
            result.add(path);
            return;
        }

        // continue DFS with children
        path += "->";
        dfs(node.left, path, result);
        dfs(node.right, path, result);
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
//We use Depth-First Search (DFS) to explore all paths from the root to leaf nodes.
//Each time we reach a leaf, we add the built path string to the result list.
//The path is built step-by-step as we move down the tree.
//Complexity:
//time - O(n)
//space - O(n)


//Given the root of a binary tree, return all root-to-leaf paths in any order.
//A leaf is a node with no children.

//Example 1:
//Input: root = [1,2,3,null,5]
//Output: ["1->2->5","1->3"]

//Example 2:
//Input: root = [1]
//Output: ["1"]

//Constraints:
//The number of nodes in the tree is in the range [1, 100].
//-100 <= Node.val <= 100