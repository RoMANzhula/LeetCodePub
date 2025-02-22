package P1001_1100.P1028_Recover_a_Tree_From_Preorder_Traversal;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String input1 = "1-2--3--4-5--6--7";
        TreeNode root1 = solution.recoverFromPreorder(input1);
        System.out.print("Example 1 Output: ");
        solution.printTree(root1);

        // Example 2
        String input2 = "1-2--3---4-5--6---7";
        TreeNode root2 = solution.recoverFromPreorder(input2);
        System.out.print("Example 2 Output: ");
        solution.printTree(root2);

        // Example 3
        String input3 = "1-401--349---90--88";
        TreeNode root3 = solution.recoverFromPreorder(input3);
        System.out.print("Example 3 Output: ");
        solution.printTree(root3);
    }

    public TreeNode recoverFromPreorder(String traversal) {
        Stack<TreeNode> stack = new Stack<>();
        int i = 0, n = traversal.length();

        while (i < n) {
            int depth = 0;

            // count dashes to determine depth
            while (i < n && traversal.charAt(i) == '-') {
                depth++;
                i++;
            }

            // read the number (node value)
            int value = 0;
            while (i < n && Character.isDigit(traversal.charAt(i))) {
                value = value * 10 + (traversal.charAt(i) - '0');
                i++;
            }

            TreeNode node = new TreeNode(value);

            // maintain correct parent-child relationships
            while (stack.size() > depth) {
                stack.pop(); // move back to correct parent level
            }

            // attach the new node to its parent
            if (!stack.isEmpty()) {
                TreeNode parent = stack.peek();
                if (parent.left == null) {
                    parent.left = node;
                } else {
                    parent.right = node;
                }
            }

            stack.push(node); // push the current node onto the stack
        }

        // the root of the tree is the bottom-most element of the stack
        while (stack.size() > 1) {
            stack.pop();
        }

        return stack.peek(); // return the root node
    }

    // helper function to print the tree in level-order format
    public void printTree(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print((node != null ? node.val : "null") + " ");

            if (node != null) {
                queue.add(node.left);
                queue.add(node.right);
            }
        }
        System.out.println();
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


//We run a preorder depth-first search (DFS) on the root of a binary tree.
//At each node in this traversal, we output D dashes (where D is the depth of this node), then we output the
// value of this node.  If the depth of a node is D, the depth of its immediate child is D + 1.  The depth of
// the root node is 0.
//If a node has only one child, that child is guaranteed to be the left child.
//Given the output traversal of this traversal, recover the tree and return its root.
//
//Example 1:
//Input: traversal = "1-2--3--4-5--6--7"
//Output: [1,2,5,3,4,6,7]

//Example 2:
//Input: traversal = "1-2--3---4-5--6---7"
//Output: [1,2,5,3,null,6,null,4,null,7]

//Example 3:
//Input: traversal = "1-401--349---90--88"
//Output: [1,401,null,349,88,90]
//
//Constraints:
//The number of nodes in the original tree is in the range [1, 1000].
//1 <= Node.val <= 109
