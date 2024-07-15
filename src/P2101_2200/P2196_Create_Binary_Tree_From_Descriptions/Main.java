package P2101_2200.P2196_Create_Binary_Tree_From_Descriptions;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        // Example 1
        int[][] descriptions1 = {
                {20, 15, 1},
                {20, 17, 0},
                {50, 20, 1},
                {50, 80, 0},
                {80, 19, 1}
        };
        TreeNode root1 = solution.createBinaryTree(descriptions1);
        printTree(root1);

        // Example 2
        int[][] descriptions2 = {
                {1, 2, 1},
                {2, 3, 0},
                {3, 4, 1}
        };
        TreeNode root2 = solution.createBinaryTree(descriptions2);
        printTree(root2);
    }

    private static void printTree(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (current != null) {
                System.out.print(current.val + " ");
                queue.add(current.left);
                queue.add(current.right);
            } else {
                System.out.print("null ");
            }
        }
        System.out.println();
    }

//    public TreeNode createBinaryTree(int[][] descriptions) {
//        Map<Integer, TreeNode> nodes = new HashMap<>();
//        Set<Integer> children = new HashSet<>();
//
//        // Create all nodes and establish child-parent relationships
//        for (int[] description : descriptions) {
//            int parentVal = description[0];
//            int childVal = description[1];
//            boolean isLeft = description[2] == 1;
//
//            // Get or create parent node
//            TreeNode parentNode = nodes.computeIfAbsent(parentVal, k -> new TreeNode(parentVal));
//
//            // Get or create child node
//            TreeNode childNode = nodes.computeIfAbsent(childVal, k -> new TreeNode(childVal));
//
//            // Establish the relationship
//            if (isLeft) {
//                parentNode.left = childNode;
//            } else {
//                parentNode.right = childNode;
//            }
//
//            // Mark this node as a child
//            children.add(childVal);
//        }
//
//        // The root node is the one which is not in the set of children
//        for (int parentVal : nodes.keySet()) {
//            if (!children.contains(parentVal)) {
//                return nodes.get(parentVal);
//            }
//        }
//
//        return null; // This should never happen since the input guarantees a valid tree
//    }

    //faster solution
    public TreeNode createBinaryTree(int[][] descriptions) {
        TreeNode[] nodes = new TreeNode[100001]; // Array to hold nodes (indexed by value)
        boolean[] isChild = new boolean[100001]; // Array to mark nodes as children

        // Create all nodes and establish child-parent relationships
        for (int[] description : descriptions) {
            int parentVal = description[0];
            int childVal = description[1];
            boolean isLeft = description[2] == 1;

            // Get or create parent node
            if (nodes[parentVal] == null) {
                nodes[parentVal] = new TreeNode(parentVal);
            }
            TreeNode parentNode = nodes[parentVal];

            // Get or create child node
            if (nodes[childVal] == null) {
                nodes[childVal] = new TreeNode(childVal);
            }
            TreeNode childNode = nodes[childVal];

            // Establish the relationship
            if (isLeft) {
                parentNode.left = childNode;
            } else {
                parentNode.right = childNode;
            }

            // Mark this node as a child
            isChild[childVal] = true;
        }

        // The root node is the one which is not marked as a child
        for (int i = 1; i < nodes.length; i++) {
            if (nodes[i] != null && !isChild[i]) {
                return nodes[i];
            }
        }

        return null; // This should never happen since the input guarantees a valid tree
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
//TreeNode class: Same as before, representing a node in the binary tree.
//createBinaryTree method:
//Uses a TreeNode array of size 100,001 to store nodes by their values.
//Uses a boolean array isChild to track which nodes are children.
//Iterates over each description to build the tree structure.
//Finds the root by checking which node is not marked as a child.
//main method:
//Tests the method with the given examples and prints the resulting binary tree in a level-order manner.
//By using arrays, we achieve faster access times for node retrieval and creation, making the program more efficient.


//You are given a 2D integer array descriptions where descriptions[i] = [parenti, childi, isLefti] indicates
// that parenti is the parent of childi in a binary tree of unique values. Furthermore,
//If isLefti == 1, then childi is the left child of parenti.
//If isLefti == 0, then childi is the right child of parenti.
//Construct the binary tree described by descriptions and return its root.
//The test cases will be generated such that the binary tree is valid.
//
//Example 1:
//Input: descriptions = [[20,15,1],[20,17,0],[50,20,1],[50,80,0],[80,19,1]]
//Output: [50,20,80,15,17,19]
//Explanation: The root node is the node with value 50 since it has no parent.
//The resulting binary tree is shown in the diagram.

//Example 2:
//Input: descriptions = [[1,2,1],[2,3,0],[3,4,1]]
//Output: [1,2,null,null,3,4]
//Explanation: The root node is the node with value 1 since it has no parent.
//The resulting binary tree is shown in the diagram.
//
//Constraints:
//1 <= descriptions.length <= 104
//descriptions[i].length == 3
//1 <= parenti, childi <= 105
//0 <= isLefti <= 1
//The binary tree described by descriptions is valid.