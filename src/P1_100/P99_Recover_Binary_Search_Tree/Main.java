package P1_100.P99_Recover_Binary_Search_Tree;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        /*
            Example 2:
            Input: [3,1,4,null,null,2]

                  3
                 / \
                1   4
                   /
                  2
         */

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);

        System.out.println("Before recovery (inorder):");
        printInorder(root);

        solution.recoverTree(root);

        System.out.println("\nAfter recovery (inorder):");
        printInorder(root);
    }

    static void printInorder(TreeNode root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    private TreeNode first = null;
    private TreeNode second = null;
    private TreeNode prev = null;

    public void recoverTree(TreeNode root) {
        inorder(root);

        // sswap the values of the two incorrect nodes
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    private void inorder(TreeNode node) {
        if (node == null) return;

        inorder(node.left);

        // detect swapped nodes
        if (prev != null && prev.val > node.val) {
            if (first == null) {
                first = prev;   // first violation
            }

            second = node;      // second violation (or update)
        }

        prev = node;

        inorder(node.right);
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
// space - O(height of the tree)


//You are given the root of a binary search tree (BST), where the values of exactly two nodes of the tree were
// swapped by mistake. Recover the tree without changing its structure.

//Example 1:
//Input: root = [1,3,null,null,2]
//Output: [3,1,null,null,2]
//Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3 makes the BST valid.

//Example 2:
//Input: root = [3,1,4,null,null,2]
//Output: [2,1,4,null,null,3]
//Explanation: 2 cannot be in the right subtree of 3 because 2 < 3. Swapping 2 and 3 makes the BST valid.

//Constraints:
//The number of nodes in the tree is in the range [2, 1000].
//-231 <= Node.val <= 231 - 1
