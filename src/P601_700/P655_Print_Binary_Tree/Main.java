package P601_700.P655_Print_Binary_Tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        // Example 1
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        System.out.println("Example 1 Output:");
        List<List<String>> result1 = solution.printTree(root1);
        for (List<String> row : result1) {
            System.out.println(row);
        }

        // Example 2
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.right = new TreeNode(4);
        System.out.println("Example 2 Output:");
        List<List<String>> result2 = solution.printTree(root2);
        for (List<String> row : result2) {
            System.out.println(row);
        }
    }

    public List<List<String>> printTree(TreeNode root) {
        int height = getHeight(root);  // Calculate the height of the tree
        int rows = height + 1;         // Rows = height + 1
        int cols = (int) Math.pow(2, height + 1) - 1;  // Columns = 2^(height+1) - 1

        // Initialize the result matrix with empty strings
        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            List<String> row = new ArrayList<>(Collections.nCopies(cols, ""));
            res.add(row);
        }

        // Place the root and its children in the matrix
        populateMatrix(res, root, 0, 0, cols - 1);

        return res;
    }

    // Method to calculate the height of the tree
    public static int getHeight(TreeNode root) {
        if (root == null) {
            return -1; // Return -1 so that height of tree with no nodes is 0.
        }
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

    // Recursive method to populate the matrix
    public static void populateMatrix(List<List<String>> res, TreeNode node, int row, int left, int right) {
        if (node == null) {
            return;
        }

        // Place the current node in the middle of the current segment
        int col = (left + right) / 2;
        res.get(row).set(col, Integer.toString(node.val));

        // Recursively place the left and right children
        populateMatrix(res, node.left, row + 1, left, col - 1);
        populateMatrix(res, node.right, row + 1, col + 1, right);
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
//TreeNode class: The TreeNode class is used to represent each node in the binary tree. Each node contains an
// integer value (val), and references to its left and right children (left, right).
//getHeight method: Recursively calculates the height of the tree, which is essential for determining the number of
// rows and columns in the matrix.
//printTree method:
//Calculates the number of rows and columns for the matrix based on the tree's height.
//Initializes the matrix with empty strings.
//Calls populateMatrix to fill the matrix.
//populateMatrix method:
//Recursively places each node in its correct position in the matrix.
//The position is calculated by taking the middle of the current segment for each node, ensuring the left child
// goes to the left half and the right child goes to the right half.
//This solution efficiently constructs the matrix with a time complexity of O(n), where n is the number of
// nodes in the tree.


//Given the root of a binary tree, construct a 0-indexed m x n string matrix res that represents a formatted layout
// of the tree. The formatted layout matrix should be constructed using the following rules:
//The height of the tree is height and the number of rows m should be equal to height + 1.
//The number of columns n should be equal to 2height+1 - 1.
//Place the root node in the middle of the top row (more formally, at location res[0][(n-1)/2]).
//For each node that has been placed in the matrix at position res[r][c], place its left child at
// res[r+1][c-2height-r-1] and its right child at res[r+1][c+2height-r-1].
//Continue this process until all the nodes in the tree have been placed.
//Any empty cells should contain the empty string "".
//Return the constructed matrix res.
//

//Example 1:
//Input: root = [1,2]
//Output:
//[["","1",""],
// ["2","",""]]

//Example 2:
//Input: root = [1,2,3,null,4]
//Output:
//[["","","","1","","",""],
// ["","2","","","","3",""],
// ["","","4","","","",""]]
//
//Constraints:
//The number of nodes in the tree is in the range [1, 210].
//-99 <= Node.val <= 99
//The depth of the tree will be in the range [1, 10].