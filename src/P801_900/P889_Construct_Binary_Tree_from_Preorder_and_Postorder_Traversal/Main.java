package P801_900.P889_Construct_Binary_Tree_from_Preorder_and_Postorder_Traversal;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] preorder1 = {1, 2, 4, 5, 3, 6, 7};
        int[] postorder1 = {4, 5, 2, 6, 7, 3, 1};
        TreeNode root1 = solution.constructFromPrePost(preorder1, postorder1);
        System.out.print("Example 1 Output: ");
        Main.printTree(root1); // Output: 1 2 4 5 3 6 7

        int[] preorder2 = {1};
        int[] postorder2 = {1};
        TreeNode root2 = solution.constructFromPrePost(preorder2, postorder2);
        System.out.print("Example 2 Output: ");
        Main.printTree(root2); // Output:
    }

    public static void printTree(TreeNode root) {
        printPreorder(root);
        System.out.println();
    }

    private static void printPreorder(TreeNode node) {
        if (node == null) return;
        System.out.print(node.val + " ");
        printPreorder(node.left);
        printPreorder(node.right);
    }


    private int preIndex = 0;
    private Map<Integer, Integer> postIndexMap = new HashMap<>();

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        for (int i = 0; i < postorder.length; i++) {
            postIndexMap.put(postorder[i], i);
        }
        return construct(preorder, postorder, 0, postorder.length - 1);
    }

    private TreeNode construct(int[] preorder, int[] postorder, int left, int right) {
        if (preIndex >= preorder.length || left > right) return null; // prevent out-of-bounds access

        TreeNode root = new TreeNode(preorder[preIndex++]);

        if (left == right || preIndex >= preorder.length) return root; // leaf node or end of array

        int leftSubtreeRootIdx = postIndexMap.get(preorder[preIndex]); // find left subtree boundary

        // recursively build left and right subtrees
        root.left = construct(preorder, postorder, left, leftSubtreeRootIdx);
        root.right = construct(preorder, postorder, leftSubtreeRootIdx + 1, right - 1);

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

//Complexity (time and space): O(n)


//Given two integer arrays, preorder and postorder where preorder is the preorder traversal of a binary tree of
// distinct values and postorder is the postorder traversal of the same tree, reconstruct and return the binary tree.
//If there exist multiple answers, you can return any of them.
//
//Example 1:
//Input: preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
//Output: [1,2,3,4,5,6,7]

//Example 2:
//Input: preorder = [1], postorder = [1]
//Output: [1]
//
//Constraints:
//1 <= preorder.length <= 30
//1 <= preorder[i] <= preorder.length
//All the values of preorder are unique.
//postorder.length == preorder.length
//1 <= postorder[i] <= postorder.length
//All the values of postorder are unique.
//It is guaranteed that preorder and postorder are the preorder traversal and postorder traversal of
// the same binary tree.
