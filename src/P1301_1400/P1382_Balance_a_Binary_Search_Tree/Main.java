package P1301_1400.P1382_Balance_a_Binary_Search_Tree;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        // Example 1: [1,null,2,null,3,null,4,null,null]
        TreeNode root1 = new TreeNode(1);
        root1.right = new TreeNode(2);
        root1.right.right = new TreeNode(3);
        root1.right.right.right = new TreeNode(4);
        TreeNode balancedRoot1 = solution.balanceBST(root1);
        solution.printTree(balancedRoot1);

        // Example 2: [2,1,3]
        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(3);
        TreeNode balancedRoot2 = solution.balanceBST(root2);
        solution.printTree(balancedRoot2);

    }

    // Function to convert a BST to a balanced BST
    public TreeNode balanceBST(TreeNode root) {
        List<Integer> sortedNodes = new ArrayList<>();
        inOrderTraversal(root, sortedNodes);
        return buildBalancedBST(sortedNodes, 0, sortedNodes.size() - 1);
    }

    // Function to perform in-order traversal and collect nodes in sorted order
    private void inOrderTraversal(TreeNode node, List<Integer> sortedNodes) {
        if (node == null) {
            return;
        }
        inOrderTraversal(node.left, sortedNodes);
        sortedNodes.add(node.val);
        inOrderTraversal(node.right, sortedNodes);
    }

    // Function to build a balanced BST from the sorted list of node values
    private TreeNode buildBalancedBST(List<Integer> sortedNodes, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = start + (end - start) / 2;
        TreeNode node = new TreeNode(sortedNodes.get(mid));
        node.left = buildBalancedBST(sortedNodes, start, mid - 1);
        node.right = buildBalancedBST(sortedNodes, mid + 1, end);
        return node;
    }

    // Helper function to print the BST in level order for testing
    public void printTree(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();
        traverseLevels(root, 0, levels);
        for (List<Integer> level : levels) {
            for (Integer val : level) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    // Helper function to traverse tree levels
    private void traverseLevels(TreeNode node, int level, List<List<Integer>> levels) {
        if (node == null) {
            return;
        }
        if (levels.size() == level) {
            levels.add(new ArrayList<>());
        }
        levels.get(level).add(node.val);
        traverseLevels(node.left, level + 1, levels);
        traverseLevels(node.right, level + 1, levels);
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
//TreeNode Class: This class defines the structure of each node in the BST.
//balanceBST Function: This function initiates the process of balancing the BST.
//inOrderTraversal Function: This function performs an in-order traversal of the BST and stores the
// node values in a list.
//buildBalancedBST Function: This function recursively builds a balanced BST from the sorted node values.
//printTree Function: This helper function prints the BST in level order for testing purposes.
//The code includes a main method with example inputs to test the functionality. You can run the code and
// check the output to ensure it balances the BST correctly.


//Given the root of a binary search tree, return a balanced binary search tree with the same node values. If there
// is more than one answer, return any of them.
//A binary search tree is balanced if the depth of the two subtrees of every node never differs by more than 1.
//
//Example 1:
//Input: root = [1,null,2,null,3,null,4,null,null]
//Output: [2,1,3,null,null,null,4]
//Explanation: This is not the only correct answer, [3,1,4,null,2] is also correct.

//Example 2:
//Input: root = [2,1,3]
//Output: [2,1,3]
//
//Constraints:
//The number of nodes in the tree is in the range [1, 104].
//1 <= Node.val <= 105