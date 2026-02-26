package P101_200.P116_Populating_Next_Right_Pointers_in_Each_Node;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        //        1
        //      /   \
        //     2     3
        //    / \   / \
        //   4  5   6   7

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);

        root.left.left = new Node(4);
        root.left.right = new Node(5);

        root.right.left = new Node(6);
        root.right.right = new Node(7);

        solution.connect(root);

        printUsingNext(root);
    }

    public static void printUsingNext(Node root) {
        Node levelStart = root;

        while (levelStart != null) {
            Node current = levelStart;

            while (current != null) {
                System.out.print(current.val + " -> ");
                current = current.next;
            }

            System.out.println("NULL");
            levelStart = levelStart.left;
        }
    }

    public Node connect(Node root) {
        if (root == null) return null;

        Node leftmost = root;

        // since it's a perfect binary tree, if a node has left child, it also has right child
        while (leftmost.left != null) {

            Node current = leftmost;

            while (current != null) {

                // connect left -> right
                current.left.next = current.right;

                // connect right -> next subtree
                if (current.next != null) {
                    current.right.next = current.next.left;
                }

                current = current.next;
            }

            leftmost = leftmost.left;
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
// space - O(1)


//You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The
// binary tree has the following definition:
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
//Input: root = [1,2,3,4,5,6,7]
//Output: [1,#,2,3,#,4,5,6,7,#]
//Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to
// point to its next right node, just like in Figure B. The serialized output is in level order as connected by
// the next pointers, with '#' signifying the end of each level.

//Example 2:
//Input: root = []
//Output: []

//Constraints:
//The number of nodes in the tree is in the range [0, 212 - 1].
//-1000 <= Node.val <= 1000

//Follow-up:
//You may only use constant extra space.
//The recursive approach is fine. You may assume implicit stack space does not count as extra space for this problem.
