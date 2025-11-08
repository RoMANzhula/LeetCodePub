package P101_200.P108_Convert_Sorted_Array_to_Binary_Search_Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {-10, -3, 0, 5, 9};
        int[] nums2 = {1, 3};

        TreeNode bst1 = solution.sortedArrayToBST(nums1);
        TreeNode bst2 = solution.sortedArrayToBST(nums2);

        System.out.print("BST from nums1: ");
        printTree(bst1); // [0, -10, 5, null, -3, null, 9]

        System.out.print("BST from nums2: ");
        printTree(bst2); // [1, null, 3]
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] nums, int left, int right) {
        if (left > right) return null;

        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        root.left = build(nums, left, mid - 1);
        root.right = build(nums, mid + 1, right);

        return root;
    }

    public static void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("[]");
            return;
        }

        List<String> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node == null) {
                result.add("null");
                continue;
            }

            result.add(String.valueOf(node.val));
            queue.offer(node.left);
            queue.offer(node.right);
        }

        // remove trailing "null" values
        while (result.size() > 0 && result.get(result.size() - 1).equals("null")) {
            result.remove(result.size() - 1);
        }

        System.out.println(result);
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
// space - O(log n)


//Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced
// binary search tree.

//Example 1:
//Input: nums = [-10,-3,0,5,9]
//Output: [0,-3,9,-10,null,5]
//Explanation: [0,-10,5,null,-3,null,9] is also accepted:

//Example 2:
//Input: nums = [1,3]
//Output: [3,1]
//Explanation: [1,null,3] and [3,1] are both height-balanced BSTs.

//Constraints:
//1 <= nums.length <= 104
//-104 <= nums[i] <= 104
//nums is sorted in a strictly increasing order.
