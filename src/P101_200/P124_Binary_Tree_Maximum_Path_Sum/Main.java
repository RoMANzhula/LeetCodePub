package P101_200.P124_Binary_Tree_Maximum_Path_Sum;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        /*
                   1
                  / \
                 2   3
        */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);


        int result = solution.maxPathSum(root);

        System.out.println("Maximum Path Sum: " + result); // 6

        /*
                   -10
                   /  \
                  9   20
                     /  \
                    15   7
        */

        TreeNode root2 = new TreeNode(-10);
        root2.left = new TreeNode(9);
        root2.right = new TreeNode(20);
        root2.right.left = new TreeNode(15);
        root2.right.right = new TreeNode(7);

        int result2 = solution.maxPathSum(root2);
        System.out.println("Maximum Path Sum (Example 2): " + result2); // 42
    }

    private int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxSum;
    }

    private int dfs(TreeNode node) {
        if (node == null) return 0;

        // get max contribution from left and right subtrees
        int leftGain = Math.max(dfs(node.left), 0);
        int rightGain = Math.max(dfs(node.right), 0);

        // path passing through this node (could be the answer)
        int currentPathSum = node.val + leftGain + rightGain;

        // up global maximum
        maxSum = Math.max(maxSum, currentPathSum);

        // max gain if continuing path to parent
        return node.val + Math.max(leftGain, rightGain);
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
// space - O(trre height)


// A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge
// connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass
// through the root.
//The path sum of a path is the sum of the node's values in the path.
//Given the root of a binary tree, return the maximum path sum of any non-empty path.

//Example 1:
//Input: root = [1,2,3]
//Output: 6
//Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.

//Example 2:
//Input: root = [-10,9,20,null,null,15,7]
//Output: 42
//Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.

//Constraints:
//The number of nodes in the tree is in the range [1, 3 * 104].
//-1000 <= Node.val <= 1000
