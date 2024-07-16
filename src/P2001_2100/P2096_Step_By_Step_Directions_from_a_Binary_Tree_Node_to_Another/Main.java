package P2001_2100.P2096_Step_By_Step_Directions_from_a_Binary_Tree_Node_to_Another;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        // Create the binary tree for the example: [5, 1, 2, 3, null, 6, 4]
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(4);

        // Test case 1
        int startValue = 3;
        int destValue = 6;
        String directions = solution.getDirections(root, startValue, destValue);
        System.out.println("Output: " + directions); // Expected: "UURL"

        // Create another binary tree for the second example: [2, 1]
        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(1);

        // Test case 2
        startValue = 2;
        destValue = 1;
        directions = solution.getDirections(root2, startValue, destValue);
        System.out.println("Output: " + directions); // Expected: "L"
    }

    public String getDirections(TreeNode root, int startValue, int destValue) {
        StringBuilder pathToStart = new StringBuilder();
        StringBuilder pathToDest = new StringBuilder();

        // Find paths from the root to the start and destination nodes
        findPath(root, startValue, pathToStart);
        findPath(root, destValue, pathToDest);

        // Convert the paths to strings
        String startPath = pathToStart.toString();
        String destPath = pathToDest.toString();

        // Find the first non-common ancestor in both paths
        int i = 0;
        while (i < startPath.length() && i < destPath.length() && startPath.charAt(i) == destPath.charAt(i)) {
            i++;
        }

        // Calculate the steps to go up to the LCA
        StringBuilder result = new StringBuilder();
        for (int j = i; j < startPath.length(); j++) {
            result.append('U');
        }

        // Append the steps to go down to the destination
        result.append(destPath.substring(i));

        return result.toString();
    }

    private boolean findPath(TreeNode root, int value, StringBuilder path) {
        if (root == null) {
            return false;
        }
        if (root.val == value) {
            return true;
        }
        path.append('L');
        if (findPath(root.left, value, path)) {
            return true;
        }
        path.setCharAt(path.length() - 1, 'R');
        if (findPath(root.right, value, path)) {
            return true;
        }
        path.setLength(path.length() - 1);
        return false;
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
//TreeNode Class: Defines the structure of each node in the binary tree.
//getDirections Method:
//Initializes StringBuilder objects for paths from the root to the start and destination nodes.
//Uses the findPath method to fill these paths.
//Finds the LCA by comparing paths character by character until a difference is found.
//Constructs the result by appending 'U' for each step up to the LCA and then appending the remaining path to the destination node.
//findPath Method: A recursive helper method to find the path from the root to a given node.
//This solution efficiently finds the shortest path and constructs the required direction string using the properties of binary trees and common ancestors.


//You are given the root of a binary tree with n nodes. Each node is uniquely assigned a value from 1 to n. You are
// also given an integer startValue representing the value of the start node s, and a different integer destValue
// representing the value of the destination node t.
//Find the shortest path starting from node s and ending at node t. Generate step-by-step directions of such path
// as a string consisting of only the uppercase letters 'L', 'R', and 'U'. Each letter indicates a specific direction:
//'L' means to go from a node to its left child node.
//'R' means to go from a node to its right child node.
//'U' means to go from a node to its parent node.
//Return the step-by-step directions of the shortest path from node s to node t.
//
//Example 1:
//Input: root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
//Output: "UURL"
//Explanation: The shortest path is: 3 → 1 → 5 → 2 → 6.

//Example 2:
//Input: root = [2,1], startValue = 2, destValue = 1
//Output: "L"
//Explanation: The shortest path is: 2 → 1.
//
//Constraints:
//The number of nodes in the tree is n.
//2 <= n <= 105
//1 <= Node.val <= n
//All the values in the tree are unique.
//1 <= startValue, destValue <= n
//startValue != destValue
