package P101_200.P109_Convert_Sorted_List_to_Binary_Search_Tree;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        ListNode head = new ListNode(-10);
        head.next = new ListNode(-3);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(5);
        head.next.next.next.next = new ListNode(9);

        TreeNode root = solution.sortedListToBST(head);

        inorder(root);
    }

    private static void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    private ListNode current; // pointer to traverse list

    public TreeNode sortedListToBST(ListNode head) {
        int size = getSize(head);
        current = head;
        return buildBST(0, size - 1);
    }

    private int getSize(ListNode head) {
        int size = 0;
        while (head != null) {
            size++;
            head = head.next;
        }
        return size;
    }

    private TreeNode buildBST(int left, int right) {
        if (left > right) return null;

        int mid = left + (right - left) / 2;

        // build left subtree
        TreeNode leftChild = buildBST(left, mid - 1);

        // root node
        TreeNode root = new TreeNode(current.val);
        root.left = leftChild;

        // move list pointer
        current = current.next;

        // build right subtree
        root.right = buildBST(mid + 1, right);

        return root;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
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


//Given the head of a singly linked list where elements are sorted in ascending order, convert it to a height-balanced
// binary search tree.

//Example 1:
//Input: head = [-10,-3,0,5,9]
//Output: [0,-3,9,-10,null,5]
//Explanation: One possible answer is [0,-3,9,-10,null,5], which represents the shown height balanced BST.

//Example 2:
//Input: head = []
//Output: []

//Constraints:
//The number of nodes in head is in the range [0, 2 * 104].
//-105 <= Node.val <= 105
