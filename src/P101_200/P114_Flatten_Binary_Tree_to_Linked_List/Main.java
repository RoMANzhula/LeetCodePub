package P101_200.P114_Flatten_Binary_Tree_to_Linked_List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        /*
                1
               / \
              2   5
             / \   \
            3   4   6
        */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);

        solution.flatten(root);

        printFlattened(root);  // Expected: 1 2 3 4 5 6
    }

    public static void printFlattened(TreeNode root) {
        while (root != null) {
            System.out.print(root.val + " ");
            root = root.right;
        }
        System.out.println();
    }

    public void flatten(TreeNode root) {
        TreeNode current = root;

        while (current != null) {

            if (current.left != null) {

                // find rightmost node in left subtree
                TreeNode rightMost = current.left;
                while (rightMost.right != null) {
                    rightMost = rightMost.right;
                }

                // connect right subtree to rightmost node
                rightMost.right = current.right;

                // move left subtree to right
                current.right = current.left;
                current.left = null;
            }

            // move forward
            current = current.right;
        }

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
// space - O(1)


//Given the root of a binary tree, flatten the tree into a "linked list":
//The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the
// list and the left child pointer is always null.
//The "linked list" should be in the same order as a pre-order traversal of the binary tree.

//Example 1:
//Input: root = [1,2,5,3,4,null,6]
//Output: [1,null,2,null,3,null,4,null,5,null,6]

//Example 2:
//Input: root = []
//Output: []
//Example 3:
//Input: root = [0]
//Output: [0]

//Constraints:
//The number of nodes in the tree is in the range [0, 2000].
//-100 <= Node.val <= 100
