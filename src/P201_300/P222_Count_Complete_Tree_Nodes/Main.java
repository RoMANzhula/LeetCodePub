package P201_300.P222_Count_Complete_Tree_Nodes;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        /*
                   1
                 /   \
                2     3
               / \   /
              4   5 6
        */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);

        int result = solution.countNodes(root);

        System.out.println("Number of nodes: " + result); // ooutput: 6
    }

    public int countNodes(TreeNode root) {
        if (root == null) return 0;

        int leftHeight = getLeftHeight(root);
        int rightHeight = getRightHeight(root);

        // ff heights are equal, it's a perfect binary tree
        if (leftHeight == rightHeight) {
            return (1 << leftHeight) - 1;
        }

        // else recurse
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    private int getLeftHeight(TreeNode node) {
        int height = 0;

        while (node != null) {
            height++;
            node = node.left;
        }

        return height;
    }

    private int getRightHeight(TreeNode node) {
        int height = 0;
        while (node != null) {
            height++;
            node = node.right;
        }

        return height;
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
// time - O(log^2 n)
// space - O(log n)


//Given the root of a complete binary tree, return the number of the nodes in the tree.
//According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and
// all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last
// level h.
//Design an algorithm that runs in less than O(n) time complexity.

//Example 1:
//Input: root = [1,2,3,4,5,6]
//Output: 6

//Example 2:
//Input: root = []
//Output: 0

//Example 3:
//Input: root = [1]
//Output: 1

//Constraints:
//The number of nodes in the tree is in the range [0, 5 * 104].
//0 <= Node.val <= 5 * 104
//The tree is guaranteed to be complete.
