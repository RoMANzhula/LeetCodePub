package P2201_2300.P2265_Count_Nodes_Equal_to_Average_of_Subtree;


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

public class Main {

    public int averageOfSubtree(TreeNode root) {
        return getSum(root).ans;
    }

    private Result getSum(TreeNode root) {
        if (root == null) {
            return new Result(0, 0, 0);
        }

        Result left = getSum(root.left);
        Result right = getSum(root.right);

        int sum = left.sum + right.sum + root.val;
        int count = left.count + right.count + 1;
        int ans = left.ans + right.ans + (root.val == sum / count ? 1 : 0);

        return new Result(sum, count, ans);
    }

    public static class Result {
        int sum;
        int count;
        int ans;

        Result(int sum, int count, int ans) {
            this.sum = sum;
            this.count = count;
            this.ans = ans;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(8);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(1);
        root.right.right = new TreeNode(6);

        Main solution = new Main();
        int result = solution.averageOfSubtree(root);
        System.out.println("Output: " + result);
    }

}

//Given the root of a binary tree, return the number of nodes where the value of the node is equal to the
// average of the values in its subtree.
//Note:
//The average of n elements is the sum of the n elements divided by n and rounded down to the nearest integer.
//A subtree of root is a tree consisting of root and all of its descendants.

//Example 1:
//Input: root = [4,8,5,0,1,null,6]
//Output: 5
//Explanation:
//For the node with value 4: The average of its subtree is (4 + 8 + 5 + 0 + 1 + 6) / 6 = 24 / 6 = 4.
//For the node with value 5: The average of its subtree is (5 + 6) / 2 = 11 / 2 = 5.
//For the node with value 0: The average of its subtree is 0 / 1 = 0.
//For the node with value 1: The average of its subtree is 1 / 1 = 1.
//For the node with value 6: The average of its subtree is 6 / 1 = 6.

//Example 2:
//Input: root = [1]
//Output: 1
//Explanation: For the node with value 1: The average of its subtree is 1 / 1 = 1.

//Constraints:
//The number of nodes in the tree is in the range [1, 1000].
//0 <= Node.val <= 1000

