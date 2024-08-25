package P101_200.P145_Binary_Tree_Postorder_Traversal;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        // Example 1: root = [1, null, 2, 3]
        TreeNode root1 = new TreeNode(1);
        root1.right = new TreeNode(2);
        root1.right.left = new TreeNode(3);

        System.out.println("Postorder Traversal of root1: " + solution.postorderTraversal(root1));
        // Output: [3, 2, 1]

        // Example 2: root = []
        TreeNode root2 = null;
        System.out.println("Postorder Traversal of root2: " + solution.postorderTraversal(root2));
        // Output: []

        // Example 3: root = [1]
        TreeNode root3 = new TreeNode(1);
        System.out.println("Postorder Traversal of root3: " + solution.postorderTraversal(root3));
        // Output: [1]
    }

    //iterative approach
//    public List<Integer> postorderTraversal(TreeNode root) {
//        List<Integer> result = new ArrayList<>();
//        if (root == null) {
//            return result;
//        }
//
//        Stack<TreeNode> stack = new Stack<>();
//        stack.push(root);
//
//        while (!stack.isEmpty()) {
//            TreeNode node = stack.pop();
//            result.add(node.val);
//            if (node.left != null) {
//                stack.push(node.left);
//            }
//            if (node.right != null) {
//                stack.push(node.right);
//            }
//        }
//
//        // Reverse the result to get the correct postorder traversal
//        Collections.reverse(result);
//        return result;
//    }


    //and recursive approach
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorderHelper(root, result);
        return result;
    }

    private void postorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        // Traverse the left subtree
        postorderHelper(node.left, result);
        // Traverse the right subtree
        postorderHelper(node.right, result);
        // Visit the root node
        result.add(node.val);
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

  //Explanation:
//TreeNode Class: This class defines the structure of a binary tree node, including its value (val) and pointers to
// the left and right children.
//postorderTraversal Method: This method implements the postorder traversal using a recursive helper method.


//Given the root of a binary tree, return the postorder traversal of its nodes' values.
//
//Example 1:
//Input: root = [1,null,2,3]
//Output: [3,2,1]

//Example 2:
//Input: root = []
//Output: []

//Example 3:
//Input: root = [1]
//Output: [1]
//
//Constraints:
//The number of the nodes in the tree is in the range [0, 100].
//-100 <= Node.val <= 100