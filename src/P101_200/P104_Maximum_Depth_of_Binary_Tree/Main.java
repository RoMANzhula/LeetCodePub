package P101_200.P104_Maximum_Depth_of_Binary_Tree;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        /*
                [3,9,20,null,null,15,7]

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

        int result = solution.maxDepth(root);
        System.out.println("Maximum depth = " + result); // Expected: 3
    }

    public int maxDepth(TreeNode root) {
        // ifempty tree
        if (root == null) {
            return 0;
        }

        // recursive depth calculation
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        // current node adds 1 to the max depth of its subtrees
        return 1 + Math.max(leftDepth, rightDepth);
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
// space - O(log n)


//Given the root of a binary tree, return its maximum depth.
//A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the
// farthest leaf node.

//Example 1:
//Input: root = [3,9,20,null,null,15,7]
//Output: 3

//Example 2:
//Input: root = [1,null,2]
//Output: 2

//Constraints:
//The number of nodes in the tree is in the range [0, 104].
//-100 <= Node.val <= 100
