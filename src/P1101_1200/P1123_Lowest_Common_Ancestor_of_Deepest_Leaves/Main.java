package P1101_1200.P1123_Lowest_Common_Ancestor_of_Deepest_Leaves;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        TreeNode lca = solution.lcaDeepestLeaves(root);
        System.out.println("LCA of deepest leaves: " + lca.val); // Output: 2

    }

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        return solve(root).lca;
    }

    private Result solve(TreeNode node) {
        if (node == null) return new Result(null, 0);

        Result left = solve(node.left);
        Result right = solve(node.right);

        if (left.depth > right.depth) {
            return new Result(left.lca, left.depth + 1);
        } else if (right.depth > left.depth) {
            return new Result(right.lca, right.depth + 1);
        } else {
            // equal depth → this node is the LCA
            return new Result(node, left.depth + 1);
        }
    }

    private static class Result {
        TreeNode lca;
        int depth;

        Result(TreeNode lca, int depth) {
            this.lca = lca;
            this.depth = depth;
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
//Time: O(n) – visits each node once.
//Space: O(h) – recursion stack, where h is the tree height.


//Given the root of a binary tree, return the lowest common ancestor of its deepest leaves.
//Recall that:
//The node of a binary tree is a leaf if and only if it has no children
//The depth of the root of the tree is 0. if the depth of a node is d, the depth of each of its children is d + 1.
//The lowest common ancestor of a set S of nodes, is the node A with the largest depth such that every node in S is
// in the subtree with root A.

//Example 1:
//Input: root = [3,5,1,6,2,0,8,null,null,7,4]
//Output: [2,7,4]
//Explanation: We return the node with value 2, colored in yellow in the diagram.
//The nodes coloured in blue are the deepest leaf-nodes of the tree.
//Note that nodes 6, 0, and 8 are also leaf nodes, but the depth of them is 2, but the depth of nodes 7 and 4 is 3.

//Example 2:
//Input: root = [1]
//Output: [1]
//Explanation: The root is the deepest node in the tree, and it's the lca of itself.

//Example 3:
//Input: root = [0,1,3,null,2]
//Output: [2]
//Explanation: The deepest leaf node in the tree is 2, the lca of one node is itself.

//Constraints:
//The number of nodes in the tree will be in the range [1, 1000].
//0 <= Node.val <= 1000
//The values of the nodes in the tree are unique.