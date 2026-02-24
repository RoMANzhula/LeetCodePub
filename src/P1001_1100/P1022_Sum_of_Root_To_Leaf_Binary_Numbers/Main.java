package P1001_1100.P1022_Sum_of_Root_To_Leaf_Binary_Numbers;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

         /*
                1
               / \
              0   1
             / \ / \
            0  1 0  1

            Expected: 22
        */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(1);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(1);

        int result = solution.sumRootToLeaf(root);
        System.out.println(result); // 22
    }

    public int sumRootToLeaf(TreeNode root) {
        return dfs(root, 0);
    }

    private static int dfs(TreeNode node, int current) {
        if (node == null) {
            return 0;
        }

        // shift left and add current node value
        current = (current << 1) | node.val;

        //if leaf node → return current number
        if (node.left == null && node.right == null) {
            return current;
        }

        // sum from left and right subtree
        return dfs(node.left, current) + dfs(node.right, current);
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
// space - O(tree height)


//You are given the root of a binary tree where each node has a value 0 or 1. Each root-to-leaf path represents a
// binary number starting with the most significant bit.
//For example, if the path is 0 -> 1 -> 1 -> 0 -> 1, then this could represent 01101 in binary, which is 13.
//For all leaves in the tree, consider the numbers represented by the path from the root to that leaf. Return the sum
// of these numbers.
//The test cases are generated so that the answer fits in a 32-bits integer.

//Example 1:
//Input: root = [1,0,1,0,1,0,1]
//Output: 22
//Explanation: (100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22

//Example 2:
//Input: root = [0]
//Output: 0

//Constraints:
//The number of nodes in the tree is in the range [1, 1000].
//Node.val is 0 or 1.
