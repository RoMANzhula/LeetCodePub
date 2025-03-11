package P101_200.P110_Balanced_Binary_Tree;

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

public class Main {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true; // An empty tree is balanced.
        }

        // Check the height difference between left and right subtrees.
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        // If the height difference is greater than 1, the tree is not balanced.
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return false;
        }

        // Recursively check left and right subtrees.
        return isBalanced(root.left) && isBalanced(root.right);
    }

    // Helper function to calculate the height of a tree.
    private int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }
}


//Given a binary tree, determine if it is height-balanced.

//Example 1:
//Input: root = [3,9,20,null,null,15,7]
//Output: true

//Example 2:
//Input: root = [1,2,2,3,3,null,null,4,4]
//Output: false

//Example 3:
//Input: root = []
//Output: true

//Constraints:
//The number of nodes in the tree is in the range [0, 5000].
//-104 <= Node.val <= 104
