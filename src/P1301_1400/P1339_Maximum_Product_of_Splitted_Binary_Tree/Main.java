package P1301_1400.P1339_Maximum_Product_of_Splitted_Binary_Tree;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

         /*
            Tree: [1,2,3,4,5,6]

                  1
                 / \
                2   3
               / \  /
              4  5 6
        */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);

        System.out.println(solution.maxProduct(root)); // output: 110
    }

    private long totalSum = 0;
    private long maxProduct = 0;
    private static final int MOD = 1_000_000_007;

    public int maxProduct(TreeNode root) {
        //calculate total sum of the tree
        totalSum = getTotalSum(root);

        // compute max product by splitting
        computeSubtreeSum(root);

        return (int) (maxProduct % MOD);
    }

    private long getTotalSum(TreeNode node) {
        if (node == null) return 0;
        return node.val + getTotalSum(node.left) + getTotalSum(node.right);
    }

    private long computeSubtreeSum(TreeNode node) {
        if (node == null) return 0;

        long leftSum = computeSubtreeSum(node.left);
        long rightSum = computeSubtreeSum(node.right);

        long subSum = node.val + leftSum + rightSum;

        long product = subSum * (totalSum - subSum);
        maxProduct = Math.max(maxProduct, product);

        return subSum;
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
// space - O(h)


//Given the root of a binary tree, split the binary tree into two subtrees by removing one edge such that the
// product of the sums of the subtrees is maximized.
//Return the maximum product of the sums of the two subtrees. Since the answer may be too large, return it
// modulo 109 + 7.
//Note that you need to maximize the answer before taking the mod and not after taking it.

//Example 1:
//Input: root = [1,2,3,4,5,6]
//Output: 110
//Explanation: Remove the red edge and get 2 binary trees with sum 11 and 10. Their product is 110 (11*10)

//Example 2:
//Input: root = [1,null,2,3,4,null,null,5,6]
//Output: 90
//Explanation: Remove the red edge and get 2 binary trees with sum 15 and 6.Their product is 90 (15*6)

//Constraints:
//The number of nodes in the tree is in the range [2, 5 * 104].
//1 <= Node.val <= 104
