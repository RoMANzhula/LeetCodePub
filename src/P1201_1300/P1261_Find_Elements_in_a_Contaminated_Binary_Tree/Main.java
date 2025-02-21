package P1201_1300.P1261_Find_Elements_in_a_Contaminated_Binary_Tree;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        TreeNode root1 = new TreeNode(-1);
        root1.right = new TreeNode(-1);
        Main findElements1 = new Main(root1);
        System.out.println(findElements1.find(1)); // false
        System.out.println(findElements1.find(2)); // true

        TreeNode root2 = new TreeNode(-1);
        root2.left = new TreeNode(-1);
        root2.right = new TreeNode(-1);
        root2.left.left = new TreeNode(-1);
        root2.left.right = new TreeNode(-1);
        Main findElements2 = new Main(root2);
        System.out.println(findElements2.find(1)); // true
        System.out.println(findElements2.find(3)); // true
        System.out.println(findElements2.find(5)); // false

        TreeNode root3 = new TreeNode(-1);
        root3.right = new TreeNode(-1);
        root3.right.left = new TreeNode(-1);
        root3.right.left.left = new TreeNode(-1);
        Main findElements3 = new Main(root3);
        System.out.println(findElements3.find(2)); // true
        System.out.println(findElements3.find(3)); // false
        System.out.println(findElements3.find(4)); // false
        System.out.println(findElements3.find(5)); // tru
    }

    private Set<Integer> values = new HashSet<>();

    public Main(TreeNode root) {
        recoverTree(root, 0);
    }

    private void recoverTree(TreeNode node, int value) {
        if (node == null) return;

        node.val = value;
        values.add(value);
        recoverTree(node.left, 2 * value + 1);
        recoverTree(node.right, 2 * value + 2);
    }

    public boolean find(int target) {
        return values.contains(target);
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
//FindElements(TreeNode root):
//Calls recoverTree(root, 0) to reconstruct the tree from the contaminated state.
//Stores valid node values in a HashSet for quick lookup.
//recoverTree(TreeNode node, int value):
//Recursively updates each node's value based on the formula.
//Inserts values into HashSet.
//find(int target):
//Simply checks if target is in the HashSet, running in O(1).
//Complexity Analysis:
//Tree Recovery (O(n)): Each node is visited once in DFS.
//Find Operation (O(1)): HashSet allows constant-time lookup.


//Given a binary tree with the following rules:
//root.val == 0
//For any treeNode:
//If treeNode.val has a value x and treeNode.left != null, then treeNode.left.val == 2 * x + 1
//If treeNode.val has a value x and treeNode.right != null, then treeNode.right.val == 2 * x + 2
//Now the binary tree is contaminated, which means all treeNode.val have been changed to -1.
//Implement the FindElements class:
//FindElements(TreeNode* root) Initializes the object with a contaminated binary tree and recovers it.
//bool find(int target) Returns true if the target value exists in the recovered binary tree.
//
//Example 1:
//Input
//["FindElements","find","find"]
//[[[-1,null,-1]],[1],[2]]
//Output
//[null,false,true]
//Explanation
//FindElements findElements = new FindElements([-1,null,-1]);
//findElements.find(1); // return False
//findElements.find(2); // return True

//Example 2:
//Input
//["FindElements","find","find","find"]
//[[[-1,-1,-1,-1,-1]],[1],[3],[5]]
//Output
//[null,true,true,false]
//Explanation
//FindElements findElements = new FindElements([-1,-1,-1,-1,-1]);
//findElements.find(1); // return True
//findElements.find(3); // return True
//findElements.find(5); // return False

//Example 3:
//Input
//["FindElements","find","find","find","find"]
//[[[-1,null,-1,-1,null,-1]],[2],[3],[4],[5]]
//Output
//[null,true,false,false,true]
//Explanation
//FindElements findElements = new FindElements([-1,null,-1,-1,null,-1]);
//findElements.find(2); // return True
//findElements.find(3); // return False
//findElements.find(4); // return False
//findElements.find(5); // return True
//
//Constraints:
//TreeNode.val == -1
//The height of the binary tree is less than or equal to 20
//The total number of nodes is between [1, 104]
//Total calls of find() is between [1, 104]
//0 <= target <= 106
