package P2801_2900.P2807_Insert_Greatest_Common_Divisirs_in_Linked_List;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        // Example 1: Input [18, 6, 10, 3]
        ListNode head1 = new ListNode(18, new ListNode(6, new ListNode(10, new ListNode(3))));
        System.out.print("Original List 1: ");
        printList(head1);

        // Process the list
        ListNode result1 = solution.insertGreatestCommonDivisors(head1);
        System.out.print("Modified List 1: ");
        printList(result1);

        // Example 2: Input [7]
        ListNode head2 = new ListNode(7);
        System.out.print("Original List 2: ");
        printList(head2);

        // Process the list
        ListNode result2 = solution.insertGreatestCommonDivisors(head2);
        System.out.print("Modified List 2: ");
        printList(result2);
    }

    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }

    public ListNode insertGreatestCommonDivisors(ListNode head) {
        // Edge case: If the list has only one node, return it directly
        if (head == null || head.next == null) {
            return head;
        }

        // Start from the head of the linked list
        ListNode current = head;

        // Traverse the list while there's at least one next node
        while (current != null && current.next != null) {
            // Calculate the GCD of current node's value and next node's value
            int gcdValue = gcd(current.val, current.next.val);

            // Create a new node with the GCD value
            ListNode newNode = new ListNode(gcdValue);

            // Insert the new node between current and current.next
            newNode.next = current.next;
            current.next = newNode;

            // Move to the node after the newly inserted node
            current = newNode.next;
        }

        return head;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
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


//Given the head of a linked list head, in which each node contains an integer value.
//Between every pair of adjacent nodes, insert a new node with a value equal to the greatest common divisor of them.
//Return the linked list after insertion.
//The greatest common divisor of two numbers is the largest positive integer that evenly divides both numbers.

//Example 1:
//Input: head = [18,6,10,3]
//Output: [18,6,6,2,10,1,3]
//Explanation: The 1st diagram denotes the initial linked list and the 2nd diagram denotes the linked list after
// inserting the new nodes (nodes in blue are the inserted nodes).
//- We insert the greatest common divisor of 18 and 6 = 6 between the 1st and the 2nd nodes.
//- We insert the greatest common divisor of 6 and 10 = 2 between the 2nd and the 3rd nodes.
//- We insert the greatest common divisor of 10 and 3 = 1 between the 3rd and the 4th nodes.
//There are no more adjacent nodes, so we return the linked list.

//Example 2:
//Input: head = [7]
//Output: [7]
//Explanation: The 1st diagram denotes the initial linked list and the 2nd diagram denotes the linked list after
// inserting the new nodes.
//There are no pairs of adjacent nodes, so we return the initial linked list.
//
//Constraints:
//The number of nodes in the list is in the range [1, 5000].
//1 <= Node.val <= 1000