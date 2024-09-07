package P1301_1400.P1367_Linked_List_in_Binary_Tree;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        // Example 1:
        // Linked list: [4, 2, 8]
        ListNode head1 = new ListNode(4);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(8);

        // Binary tree for example 1: [1, 4, 4, null, 2, 2, null, 1, null, 6, 8, null, null, null, null, 1, 3]
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(4);
        root1.right = new TreeNode(4);
        root1.left.right = new TreeNode(2);
        root1.left.right.left = new TreeNode(6);
        root1.left.right.right = new TreeNode(8);
        root1.right.left = new TreeNode(2);
        root1.left.right.left.left = new TreeNode(1);
        root1.left.right.right.left = new TreeNode(1);
        root1.left.right.right.right = new TreeNode(3);

        boolean result1 = solution.isSubPath(head1, root1);
        System.out.println("Example 1 result: " + result1); // output: true

        // Example 2:
        // Linked list: [1, 4, 2, 6]
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(4);
        head2.next.next = new ListNode(2);
        head2.next.next.next = new ListNode(6);

        // Binary tree for example 2: [1, 4, 4, null, 2, 2, null, 1, null, 6, 8, null, null, null, null, 1, 3]
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(4);
        root2.right = new TreeNode(4);
        root2.left.right = new TreeNode(2);
        root2.left.right.left = new TreeNode(6);
        root2.left.right.right = new TreeNode(8);
        root2.right.left = new TreeNode(2);
        root2.left.right.left.left = new TreeNode(1);
        root2.left.right.right.left = new TreeNode(1);
        root2.left.right.right.right = new TreeNode(3);

        boolean result2 = solution.isSubPath(head2, root2);
        System.out.println("Example 2 result: " + result2); //output: true

        // Example 3:
        // Linked list: [1, 4, 2, 6, 8]
        ListNode head3 = new ListNode(1);
        head3.next = new ListNode(4);
        head3.next.next = new ListNode(2);
        head3.next.next.next = new ListNode(6);
        head3.next.next.next.next = new ListNode(8);

        // Binary tree for example 3: [1, 4, 4, null, 2, 2, null, 1, null, 6, 8, null, null, null, null, 1, 3]
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(4);
        root3.right = new TreeNode(4);
        root3.left.right = new TreeNode(2);
        root3.left.right.left = new TreeNode(6);
        root3.left.right.right = new TreeNode(8);
        root3.right.left = new TreeNode(2);
        root3.left.right.left.left = new TreeNode(1);
        root3.left.right.right.left = new TreeNode(1);
        root3.left.right.right.right = new TreeNode(3);

        boolean result3 = solution.isSubPath(head3, root3);
        System.out.println("Example 3 result: " + result3); // output: false
    }

    public boolean isSubPath(ListNode head, TreeNode root) {
        // If the root is null, we can't find any path
        if (root == null) {
            return false;
        }

        // Check if the current tree node matches the head of the linked list
        // or recursively check the left and right subtree
        return dfs(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    private boolean dfs(ListNode head, TreeNode node) {
        // If we've reached the end of the list, return true
        if (head == null) {
            return true;
        }
        // If the tree node is null, return false (path can't continue)
        if (node == null) {
            return false;
        }
        // If the values of the current linked list node and tree node don't match, return false
        if (head.val != node.val) {
            return false;
        }

        // Continue checking the left and right subtree for the next node in the list
        return dfs(head.next, node.left) || dfs(head.next, node.right);
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

//Explanation:
//isSubPath Function:
//It takes the head of the linked list and the root of the tree as input.
//For each node in the binary tree, it checks whether there is a valid path that starts from the current node by
// calling the helper function dfs. If not, it recursively checks the left and right subtrees.
//dfs Function (Depth-First Search):
//This function checks if a downward path starting from the current tree node matches the linked list.
//It compares the value of the current list node (head) with the current tree node (node).
//If they match, the function is called recursively for the left and right children of the tree, and the next node
// in the linked list (head.next).
//Base Cases:
//If the head of the linked list becomes null, it means we have matched the entire linked list, so we return true.
//If the current tree node becomes null or the values don’t match, we return false.
//Time Complexity:
//Time Complexity: O(n * m), where n is the number of nodes in the binary tree and m is the number of nodes in the
// linked list. For each node in the tree, we potentially compare up to m nodes in the linked list.
//Space Complexity: O(max(h, m)), where h is the height of the tree and m is the length of the linked list. This is
// the stack space used by recursion.


//Given a binary tree root and a linked list with head as the first node.
//Return True if all the elements in the linked list starting from the head correspond to some downward path
// connected in the binary tree otherwise return False.
//In this context downward path means a path that starts at some node and goes downwards.
//
//Example 1:
//Input: head = [4,2,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
//Output: true
//Explanation: Nodes in blue form a subpath in the binary Tree.

//Example 2:
//Input: head = [1,4,2,6], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
//Output: true

//Example 3:
//Input: head = [1,4,2,6,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
//Output: false
//Explanation: There is no path in the binary tree that contains all the elements of the linked list from head.
//
//Constraints:
//The number of nodes in the tree will be in the range [1, 2500].
//The number of nodes in the list will be in the range [1, 100].
//1 <= Node.val <= 100 for each node in the linked list and binary tree.