package P101_200.P117_Populating_Next_Right_Pointers_in_Each_Node_II;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        //        1
        //       / \
        //      2   3
        //     / \   \
        //    4   5   7

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(7);

        solution.connect(root);
        printLevels(root);
    }

    public static void printLevels(Node root) {
        while (root != null) {
            Node current = root;
            while (current != null) {
                System.out.print(current.val + " ");
                current = current.next;
            }
            System.out.println("#");
            root = root.left != null ? root.left : root.right;
        }
    }

    public Node connect(Node root) {
        if (root == null) return null;

        Node current = root;  // start

        while (current != null) {
            Node dummy = new Node(0); // dummy node for next level
            Node tail = dummy; // tail to build next level

            // traverse current level
            while (current != null) {
                if (current.left != null) {
                    tail.next = current.left;
                    tail = tail.next;
                }

                if (current.right != null) {
                    tail.next = current.right;
                    tail = tail.next;
                }

                current = current.next; // move across level
            }

            // move to next level
            current = dummy.next;
        }

        return root;
    }

}

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}

//Complexity:
// time - O(n)
// space - O(!)


//Given a binary tree
//
//struct Node {
//  int val;
//  Node *left;
//  Node *right;
//  Node *next;
//}
//Populate each next pointer to point to its next right node. If there is no next right node, the next pointer
// should be set to NULL.
//Initially, all next pointers are set to NULL.

//Example 1:
//Input: root = [1,2,3,4,5,null,7]
//Output: [1,#,2,3,#,4,5,7,#]
//Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to point to
// its next right node, just like in Figure B. The serialized output is in level order as connected by the next
// pointers, with '#' signifying the end of each level.

//Example 2:
//Input: root = []
//Output: []

//Constraints:
//The number of nodes in the tree is in the range [0, 6000].
//-100 <= Node.val <= 100

//Follow-up:
//You may only use constant extra space.
//The recursive approach is fine. You may assume implicit stack space does not count as extra space for this problem.
