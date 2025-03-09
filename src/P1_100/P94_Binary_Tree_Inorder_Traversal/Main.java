package P1_100.P94_Binary_Tree_Inorder_Traversal;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        // Example 1
        TreeNode root1 = new TreeNode(1);
        root1.right = new TreeNode(2);
        root1.right.left = new TreeNode(3);
        System.out.println(solution.inorderTraversal(root1)); // Output: [1, 3, 2]

        // example 2
        TreeNode root2 = null;
        System.out.println(solution.inorderTraversal(root2)); // Output: []

        // Example 3
        TreeNode root3 = new TreeNode(1);
        System.out.println(solution.inorderTraversal(root3)); // Output: [1]
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    private void inorderHelper(TreeNode node, List<Integer> result) {
        if (node != null) {
            inorderHelper(node.left, result);
            result.add(node.val);
            inorderHelper(node.right, result);
        }
    }
}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

// This code defines the TreeNode class to represent a node in a binary tree, the InorderTraversal class
// to perform an in-order traversal, and a helper method inorderHelper for recursive tree traversal.
// The result is stored in a List<Integer>.
//
// The inorderTraversal method calls inorderHelper, which recursively traverses the left subtree,
// adds the current node to the result, and then recursively traverses the right subtree.
// The main method provides example usage to demonstrate how the solution works.


//Given the root of a binary tree, return the inorder traversal of its nodes' values.
//
//Example 1:
//Input: root = [1,null,2,3]
//Output: [1,3,2]

//Example 2:
//Input: root = []
//Output: []

//Example 3:
//Input: root = [1]
//Output: [1]

//Constraints:
//The number of nodes in the tree is in the range [0, 100].
//-100 <= Node.val <= 100
