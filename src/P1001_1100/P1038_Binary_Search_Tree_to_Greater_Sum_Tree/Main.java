package P1001_1100.P1038_Binary_Search_Tree_to_Greater_Sum_Tree;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(1);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(2);
        root.left.right.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        root.right.right.right = new TreeNode(8);

        TreeNode newRoot = solution.bstToGst(root);

        // Function to print the tree in-order to check the result
        printInOrder(newRoot);
    }

    private static void printInOrder(TreeNode node) {
        if (node == null) return;
        printInOrder(node.left);
        System.out.print(node.val + " ");
        printInOrder(node.right);
    }

    int sum = 0;

    public TreeNode bstToGst(TreeNode root) {
        reverseInOrder(root);
        return root;
    }

    private void reverseInOrder(TreeNode node) {
        if (node == null) return;

        reverseInOrder(node.right);

        sum += node.val;
        node.val = sum;

        reverseInOrder(node.left);
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

//Explanation:
//TreeNode Class: This class represents each node in the BST with an integer value and references to
// left and right child nodes.
//Solution Class:
//sum: A class-level variable to keep track of the cumulative sum of node values during the reverse in-order traversal.
//convertBST(TreeNode root): This method initiates the reverse in-order traversal starting from the root of the tree.
//reverseInOrder(TreeNode node): This private helper method performs a reverse in-order traversal (visits right
// subtree, node, then left subtree).
//If the current node is null, it returns immediately.
//Recursively visits the right subtree.
//Adds the current node's value to the cumulative sum and updates the node's value to this new sum.
//Recursively visits the left subtree.
//main: The main method constructs an example tree, converts it using convertBST, and prints the in-order
// traversal of the resulting tree to verify the transformation.
//How It Works:
//By performing a reverse in-order traversal, we ensure that we visit nodes with greater values first.
//We maintain a cumulative sum of node values encountered so far.
//As we visit each node, we update its value to this cumulative sum, thus transforming the BST into the
// desired Greater Tree.


//Given the root of a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the
// original BST is changed to the original key plus the sum of all keys greater than the original key in BST.
//As a reminder, a binary search tree is a tree that satisfies these constraints:
//The left subtree of a node contains only nodes with keys less than the node's key.
//The right subtree of a node contains only nodes with keys greater than the node's key.
//Both the left and right subtrees must also be binary search trees.
//
//Example 1:
//Input: root = [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
//Output: [30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]

//Example 2:
//Input: root = [0,null,1]
//Output: [1,null,1]
//
//Constraints:
//The number of nodes in the tree is in the range [1, 100].
//0 <= Node.val <= 100
//All the values in the tree are unique.
