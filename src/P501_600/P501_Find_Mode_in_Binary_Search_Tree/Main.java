package P501_600.P501_Find_Mode_in_Binary_Search_Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}
    TreeNode(int val) {
        this.val = val;
    }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Main {

    private Map<Integer, Integer> valueCount = new HashMap<>();
    private int maxCount = 0;

    public int[] findMode(TreeNode root) {
        traverse(root);

        List<Integer> modes = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : valueCount.entrySet()) {
            if (entry.getValue() == maxCount) {
                modes.add(entry.getKey());
            }
        }

        int[] result = new int[modes.size()];
        for (int i = 0; i < modes.size(); i++) {
            result[i] = modes.get(i);
        }

        return result;
    }

    private void traverse(TreeNode node) {
        if (node == null) {
            return;
        }

        int count = valueCount.getOrDefault(node.val, 0) + 1;
        valueCount.put(node.val, count);
        maxCount = Math.max(maxCount, count);

        traverse(node.left);
        traverse(node.right);
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        root1.right = new TreeNode(2);
        root1.right.left = new TreeNode(2);

        Main solution = new Main();
        int[] result1 = solution.findMode(root1);
        for (int mode : result1) {
            System.out.print(mode + " ");
        }
        System.out.println();

        TreeNode root2 = new TreeNode(0);

        int[] result2 = solution.findMode(root2);
        for (int mode : result2) {
            System.out.print(mode + " ");
        }
    }
}


//Given the root of a binary search tree (BST) with duplicates, return all the mode(s) (i.e., the most
// frequently occurred element) in it.
//If the tree has more than one mode, return them in any order.
//Assume a BST is defined as follows:
//The left subtree of a node contains only nodes with keys less than or equal to the node's key.
//The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
//Both the left and right subtrees must also be binary search trees.

//Example 1:
//Input: root = [1,null,2,2]
//Output: [2]

//Example 2:
//Input: root = [0]
//Output: [0]

//Constraints:
//The number of nodes in the tree is in the range [1, 104].
//-105 <= Node.val <= 105
