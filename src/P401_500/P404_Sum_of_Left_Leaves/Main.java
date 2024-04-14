package P401_500.P404_Sum_of_Left_Leaves;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println(solution.sumOfLeftLeaves(root)); // Output: 24
    }

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int sum = 0;

        if (root.left != null && root.left.left == null && root.left.right == null) {
            sum += root.left.val; // if the left child is a leaf node, add its value to the sum
        }

        // recursively traverse the left and right subtrees
        sum += sumOfLeftLeaves(root.left);
        sum += sumOfLeftLeaves(root.right);

        return sum;
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

//Given the root of a binary tree, return the sum of all left leaves.
//A leaf is a node with no children. A left leaf is a leaf that is the left child of another node.
//
//Example 1:
//Input: root = [3,9,20,null,null,15,7]
//Output: 24
//Explanation: There are two left leaves in the binary tree, with values 9 and 15 respectively.

//Example 2:
//Input: root = [1]
//Output: 0
//
//
//Constraints:
//
//The number of nodes in the tree is in the range [1, 1000].
//-1000 <= Node.val <= 1000
