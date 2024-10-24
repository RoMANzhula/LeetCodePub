package P901_1000.P951_Flip_Wquivalent_Binary_Trees;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        // Tree 1: root1 = [1,2,3,4,5,6,null,null,null,7,8]
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        root1.right.left = new TreeNode(6);
        root1.left.right.left = new TreeNode(7);
        root1.left.right.right = new TreeNode(8);

        // Tree 2: root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(3);
        root2.right = new TreeNode(2);
        root2.left.right = new TreeNode(6);
        root2.right.left = new TreeNode(4);
        root2.right.right = new TreeNode(5);
        root2.right.right.left = new TreeNode(8);
        root2.right.right.right = new TreeNode(7);

        boolean result = solution.flipEquiv(root1, root2);

        System.out.println("Are the two trees flip equivalent? " + result);
    }

    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        // Base cases
        if (root1 == null && root2 == null) return true; // Both trees are null
        if (root1 == null || root2 == null) return false; // One of them is null
        if (root1.val != root2.val) return false; // The root values are different

        // Recursively check the subtrees in two scenarios:
        // 1. No flip: compare left with left and right with right
        // 2. Flip: compare left with right and right with left
        boolean noFlip = flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right);
        boolean flip = flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left);

        return noFlip || flip;
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
//Base Cases:
//If both root1 and root2 are null, they are trivially equivalent.
//If only one of them is null, they are not equivalent.
//If the values at the roots are different, they cannot be equivalent.
//Recursive Cases:
//
//Check if the trees are equivalent without flipping (i.e., compare left subtree with left subtree and
// right subtree with right subtree).
//Check if the trees are equivalent with flipping (i.e., compare left subtree with right subtree and
// right subtree with left subtree).
//Return true if either of the above conditions is met.
//Time Complexity:
//The time complexity is O(N), where N is the number of nodes in the tree, because in the
// worst case, we visit every node once.


//For a binary tree T, we can define a flip operation as follows: choose any node, and swap the
// left and right child subtrees.
//A binary tree X is flip equivalent to a binary tree Y if and only if we can make X equal to Y after
// some number of flip operations.
//Given the roots of two binary trees root1 and root2, return true if the two trees are flip
// equivalent or false otherwise.
//
//Example 1:
//Flipped Trees Diagram
//Input: root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]
//Output: true
//Explanation: We flipped at nodes with values 1, 3, and 5.

//Example 2:
//Input: root1 = [], root2 = []
//Output: true

//Example 3:
//Input: root1 = [], root2 = [1]
//Output: false
//
//Constraints:
//The number of nodes in each tree is in the range [0, 100].
//Each tree will have unique node values in the range [0, 99].