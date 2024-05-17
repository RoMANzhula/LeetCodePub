package P1301_1400.P1325_Delete_Leaves_Wiat_a_Given_Value;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        // Example 1
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(2);
        root1.right.left = new TreeNode(2);
        root1.right.right = new TreeNode(4);
        root1 = solution.removeLeafNodes(root1, 2);
        solution.printTree(root1); // Output: [1, null, 3, null, 4]

        System.out.println();
        System.out.println("----------------------------------");

        // Example 2
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(3);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(3);
        root2.left.right = new TreeNode(2);
        root2 = solution.removeLeafNodes(root2, 3);
        solution.printTree(root2); // Output: [1, 3, null, null, 2]

        System.out.println();
        System.out.println("----------------------------------");

        // Example 3
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);
        root3.left.left = new TreeNode(2);
        root3.left.left.left = new TreeNode(2);
        root3 = solution.removeLeafNodes(root3, 2);
        solution.printTree(root3); // Output: [1]

    }

    private void printTree(TreeNode node) {
        if (node == null) {
            System.out.print("null ");
            return;
        }
        System.out.print(node.val + " ");
        printTree(node.left);
        printTree(node.right);
    }

    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root == null) {
            return null;
        }

        // Recursively process the left and right subtrees
        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);

        // If the current node is a leaf and its value is equal to target, delete it
        if (root.left == null && root.right == null && root.val == target) {
            return null;
        }

        // Return the possibly updated node
        return root;
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

//Given a binary tree root and an integer target, delete all the leaf nodes with value target.
//Note that once you delete a leaf node with value target, if its parent node becomes a leaf node and has the
// value target, it should also be deleted (you need to continue doing that until you cannot).
//
//Example 1:
//Input: root = [1,2,3,2,null,2,4], target = 2
//Output: [1,null,3,null,4]
//Explanation: Leaf nodes in green with value (target = 2) are removed (Picture in left).
//After removing, new nodes become leaf nodes with value (target = 2) (Picture in center).

//Example 2:
//Input: root = [1,3,3,3,2], target = 3
//Output: [1,3,null,null,2]

//Example 3:
//Input: root = [1,2,null,2,null,2], target = 2
//Output: [1]
//Explanation: Leaf nodes in green with value (target = 2) are removed at each step.
//
//Constraints:
//The number of nodes in the tree is in the range [1, 3000].
//1 <= Node.val, target <= 1000