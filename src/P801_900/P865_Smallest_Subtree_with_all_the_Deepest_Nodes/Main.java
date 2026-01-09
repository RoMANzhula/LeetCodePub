package P801_900.P865_Smallest_Subtree_with_all_the_Deepest_Nodes;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        /*
            Input: [3,5,1,6,2,0,8,null,null,7,4]
            Output subtree root value = 2
         */

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);

        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);

        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        TreeNode result = solution.subtreeWithAllDeepest(root);

        System.out.println("Output: " + result.val); // output 2
    }

    class Result {
        TreeNode node;
        int depth;

        Result(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return dfs(root).node;
    }

    private Result dfs(TreeNode root) {
        if (root == null) {
            return new Result(null, 0);
        }

        Result left = dfs(root.left);
        Result right = dfs(root.right);

        if (left.depth > right.depth) {
            return new Result(left.node, left.depth + 1);
        } else if (right.depth > left.depth) {
            return new Result(right.node, right.depth + 1);
        } else {
            // same depth → current node is LCA of deepest nodes
            return new Result(root, left.depth + 1);
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
// space - O(heigh of the tree)


//Given the root of a binary tree, the depth of each node is the shortest distance to the root.
//Return the smallest subtree such that it contains all the deepest nodes in the original tree.
//A node is called the deepest if it has the largest depth possible among any node in the entire tree.
//The subtree of a node is a tree consisting of that node, plus the set of all descendants of that node.

//Example 1:
//Input: root = [3,5,1,6,2,0,8,null,null,7,4]
//Output: [2,7,4]
//Explanation: We return the node with value 2, colored in yellow in the diagram.
//The nodes coloured in blue are the deepest nodes of the tree.
//Notice that nodes 5, 3 and 2 contain the deepest nodes in the tree but node 2 is the smallest subtree among them, so
// we return it.

//Example 2:
//Input: root = [1]
//Output: [1]
//Explanation: The root is the deepest node in the tree.

//Example 3:
//Input: root = [0,1,3,null,2]
//Output: [2]
//Explanation: The deepest node in the tree is 2, the valid subtrees are the subtrees of nodes 2, 1 and 0 but the
// subtree of node 2 is the smallest.

//Constraints:
//The number of nodes in the tree will be in the range [1, 500].
//0 <= Node.val <= 500
//The values of the nodes in the tree are unique.
