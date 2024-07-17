package P1101_1200.P1110_Delete_Nodes_and_Return_Forest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        // Example 1
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        root1.right.left = new TreeNode(6);
        root1.right.right = new TreeNode(7);
        int[] to_delete1 = {3, 5};

        List<TreeNode> result1 = solution.delNodes(root1, to_delete1);
        for (TreeNode tree : result1) {
            printTree(tree);
            System.out.println();
        }

        // Example 2
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(4);
        root2.left.right = new TreeNode(3);
        int[] to_delete2 = {3};

        List<TreeNode> result2 = solution.delNodes(root2, to_delete2);
        for (TreeNode tree : result2) {
            printTree(tree);
            System.out.println();
        }
    }

    private static void printTree(TreeNode node) {
        if (node == null) {
            System.out.print("null ");
            return;
        }
        System.out.print(node.val + " ");
        printTree(node.left);
        printTree(node.right);
    }

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> toDeleteSet = new HashSet<>();
        for (int val : to_delete) {
            toDeleteSet.add(val);
        }

        List<TreeNode> forest = new ArrayList<>();
        if (!toDeleteSet.contains(root.val)) {
            forest.add(root);
        }

        dfs(root, toDeleteSet, forest);
        return forest;
    }

    private TreeNode dfs(TreeNode node, Set<Integer> toDeleteSet, List<TreeNode> forest) {
        if (node == null) return null;

        node.left = dfs(node.left, toDeleteSet, forest);
        node.right = dfs(node.right, toDeleteSet, forest);

        if (toDeleteSet.contains(node.val)) {
            if (node.left != null) forest.add(node.left);
            if (node.right != null) forest.add(node.right);
            return null;
        }

        return node;
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
//TreeNode Class: Defines the structure of a tree node.
//delNodes Method: Takes the root of the tree and the list of nodes to delete. It initializes a set from the to_delete
// array for quick lookups and an empty list for the resulting forest. It then calls the DFS helper method.
//dfs Method: Recursively traverses the tree. If the current node is in the to_delete set, it processes its children
// as new roots and returns null to effectively delete the current node. If the node is not in the to_delete set,
// it continues processing its children.
//Main Method: Provides test cases and prints the resulting forest for verification.
//This solution ensures that all specified nodes are deleted and the remaining trees are correctly identified and
// returned as a list of roots.


//Given the root of a binary tree, each node in the tree has a distinct value.
//After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
//Return the roots of the trees in the remaining forest. You may return the result in any order.
//
//Example 1:
//Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
//Output: [[1,2,null,4],[6],[7]]

//Example 2:
//Input: root = [1,2,4,null,3], to_delete = [3]
//Output: [[1,2,4]]
//
//Constraints:
//The number of nodes in the given tree is at most 1000.
//Each node has a distinct value between 1 and 1000.
//to_delete.length <= 1000
//to_delete contains distinct values between 1 and 1000.