package P101_200.P106_Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};

        TreeNode root = solution.buildTree(inorder, postorder);

        System.out.println("Level-order traversal:");
        printLevelOrder(root);
    }

    public static void printLevelOrder(TreeNode root) {
        if (root == null) return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                System.out.print("null ");
                continue;
            }
            System.out.print(node.val + " ");
            queue.offer(node.left);
            queue.offer(node.right);
        }
    }

    private int postIndex;
    private Map<Integer, Integer> inorderIndexMap;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postIndex = postorder.length - 1;

        inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        return build(inorder, postorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] inorder, int[] postorder, int left, int right) {
        if (left > right) {
            return null;
        }

        // root is the last unused element in postorder
        int rootVal = postorder[postIndex--];
        TreeNode root = new TreeNode(rootVal);

        int index = inorderIndexMap.get(rootVal);

        // build right subtree first
        root.right = build(inorder, postorder, index + 1, right);
        root.left = build(inorder, postorder, left, index - 1);

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


//Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and
// postorder is the postorder traversal of the same tree, construct and return the binary tree.

//Example 1:
//Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
//Output: [3,9,20,null,null,15,7]

//Example 2:
//Input: inorder = [-1], postorder = [-1]
//Output: [-1]

//Constraints:
//1 <= inorder.length <= 3000
//postorder.length == inorder.length
//-3000 <= inorder[i], postorder[i] <= 3000
//inorder and postorder consist of unique values.
//Each value of postorder also appears in inorder.
//inorder is guaranteed to be the inorder traversal of the tree.
//postorder is guaranteed to be the postorder traversal of the tree.
