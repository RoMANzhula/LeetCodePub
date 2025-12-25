package P1_100.P98_Validate_Binary_Search_Tree;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(3);
        System.out.println(solution.isValidBST(root1)); // true

        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(4);
        root2.right.left = new TreeNode(3);
        root2.right.right = new TreeNode(6);
        System.out.println(solution.isValidBST(root2)); // false
    }

    public boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean validate(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }

        // current node must be strictly between min and max
        if (node.val <= min || node.val >= max) {
            return false;
        }

        // left subtree: max becomes current node's value
        // right subtree: min becomes current node's value
        return validate(node.left, min, node.val)
                && validate(node.right, node.val, max)
        ;
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
// time - O(n)
// space - O(height of tree)


//Given the root of a binary tree, determine if it is a valid binary search tree (BST).
//A valid BST is defined as follows:
//The left subtree of a node contains only nodes with keys strictly less than the node's key.
//The right subtree of a node contains only nodes with keys strictly greater than the node's key.
//Both the left and right subtrees must also be binary search trees.

//Example 1:
//Input: root = [2,1,3]
//Output: true

//Example 2:
//Input: root = [5,1,4,null,null,3,6]
//Output: false
//Explanation: The root node's value is 5 but its right child's value is 4.

//Constraints:
//The number of nodes in the tree is in the range [1, 104].
//-231 <= Node.val <= 231 - 1
