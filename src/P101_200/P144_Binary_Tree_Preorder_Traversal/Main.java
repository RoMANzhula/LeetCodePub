package P101_200.P144_Binary_Tree_Preorder_Traversal;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        /*
            Example:
                1
                 \
                  2
                 /
                3

            Input: [1,null,2,3]
            Output: [1,2,3]
        */

        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        List<Integer> result = solution.preorderTraversal(root);

        System.out.println(result); // [1, 2, 3]
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traverse(root, result);
        return result;
    }

    private void traverse(TreeNode node, List<Integer> result) {
        if (node == null) return;

        result.add(node.val);        // root
        traverse(node.left, result); // left
        traverse(node.right, result);// right
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
// space - O(height of tree)


//Given the root of a binary tree, return the preorder traversal of its nodes' values.

//Example 1:
//Input: root = [1,null,2,3]
//Output: [1,2,3]
//Explanation:

//Example 2:
//Input: root = [1,2,3,4,5,null,8,null,null,6,7,9]
//Output: [1,2,4,5,6,7,3,8,9]
//Explanation:

//Example 3:
//Input: root = []
//Output: []
//Example 4:
//Input: root = [1]
//Output: [1]

//Constraints:
//The number of nodes in the tree is in the range [0, 100].
//-100 <= Node.val <= 100
