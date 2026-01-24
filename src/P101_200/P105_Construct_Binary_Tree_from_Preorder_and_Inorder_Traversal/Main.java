package P101_200.P105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};

        TreeNode root = solution.buildTree(preorder, inorder);

        System.out.print("Level-order output: "); //3 9 20 null null 15 7 null null null null
        printLevelOrder(root);
    }

    static void printLevelOrder(TreeNode root) {
        if (root == null) return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node != null) {
                System.out.print(node.val + " ");
                queue.offer(node.left);
                queue.offer(node.right);
            } else {
                System.out.print("null ");
            }
        }
    }

    private int preorderIndex = 0;
    private Map<Integer, Integer> inorderIndexMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //build value -> index map for inorder traversal
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        return build(preorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int left, int right) {
        if (left > right) {
            return null;
        }

        // pick root from preorder
        int rootValue = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootValue);

        // find root index in inorder
        int inorderIndex = inorderIndexMap.get(rootValue);

        // build left and right subtrees
        root.left = build(preorder, left, inorderIndex - 1);
        root.right = build(preorder, inorderIndex + 1, right);

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

//Complexity:
// time and space - O(n)


//Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and
// inorder is the inorder traversal of the same tree, construct and return the binary tree.

//Example 1:
//Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
//Output: [3,9,20,null,null,15,7]

//Example 2:
//Input: preorder = [-1], inorder = [-1]
//Output: [-1]

//Constraints:
//1 <= preorder.length <= 3000
//inorder.length == preorder.length
//-3000 <= preorder[i], inorder[i] <= 3000
//preorder and inorder consist of unique values.
//Each value of inorder also appears in preorder.
//preorder is guaranteed to be the preorder traversal of the tree.
//inorder is guaranteed to be the inorder traversal of the tree.
