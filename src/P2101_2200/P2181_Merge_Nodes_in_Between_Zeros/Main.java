package P2101_2200.P2181_Merge_Nodes_in_Between_Zeros;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        // Example 1: [0,3,1,0,4,5,2,0]
        ListNode head1 = new ListNode(0);
        head1.next = new ListNode(3);
        head1.next.next = new ListNode(1);
        head1.next.next.next = new ListNode(0);
        head1.next.next.next.next = new ListNode(4);
        head1.next.next.next.next.next = new ListNode(5);
        head1.next.next.next.next.next.next = new ListNode(2);
        head1.next.next.next.next.next.next.next = new ListNode(0);

        ListNode result1 = solution.mergeNodes(head1);

        System.out.print("Example 1 Output: ");
        printList(result1); // Output: 4 11

        // Example 2: [0,1,0,3,0,2,2,0]
        ListNode head2 = new ListNode(0);
        head2.next = new ListNode(1);
        head2.next.next = new ListNode(0);
        head2.next.next.next = new ListNode(3);
        head2.next.next.next.next = new ListNode(0);
        head2.next.next.next.next.next = new ListNode(2);
        head2.next.next.next.next.next.next = new ListNode(2);
        head2.next.next.next.next.next.next.next = new ListNode(0);

        ListNode result2 = solution.mergeNodes(head2);

        System.out.print("Example 2 Output: ");
        printList(result2); // Output: 1 3 4
    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }


    public ListNode mergeNodes(ListNode head) {
        ListNode dummy = new ListNode(0); // Dummy node to simplify the result list creation
        ListNode current = dummy; // Pointer to the current end of the result list

        ListNode temp = head.next; // Skip the initial 0
        int sum = 0;

        while (temp != null) {
            if (temp.val == 0) {
                current.next = new ListNode(sum); // Create a new node with the sum
                current = current.next; // Move the pointer to the new node
                sum = 0; // Reset the sum for the next segment
            } else {
                sum += temp.val; // Add the value to the sum
            }
            temp = temp.next; // Move to the next node
        }

        return dummy.next; // Return the next of dummy as the head of the result list
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

//Explanation:
//ListNode class: A basic definition of the ListNode class for the linked list nodes.
//mergeNodes method:
//Create a dummy node to simplify adding nodes to the result list.
//Use a pointer current to keep track of the end of the result list.
//Skip the initial 0 by starting from head.next.
//Traverse the list with a while loop.
//If a 0 is encountered, create a new node with the accumulated sum and attach it to the result list, then reset the sum.
//If the node value is not 0, add it to sum.
//After the loop, return dummy.next as the head of the modified list.


//You are given the head of a linked list, which contains a series of integers separated by 0's. The beginning and
// end of the linked list will have Node.val == 0.
//For every two consecutive 0's, merge all the nodes lying in between them into a single node whose value is the
// sum of all the merged nodes. The modified list should not contain any 0's.
//Return the head of the modified linked list.
//
//Example 1:
//Input: head = [0,3,1,0,4,5,2,0]
//Output: [4,11]
//Explanation:
//The above figure represents the given linked list. The modified list contains
//- The sum of the nodes marked in green: 3 + 1 = 4.
//- The sum of the nodes marked in red: 4 + 5 + 2 = 11.

//Example 2:
//Input: head = [0,1,0,3,0,2,2,0]
//Output: [1,3,4]
//Explanation:
//The above figure represents the given linked list. The modified list contains
//- The sum of the nodes marked in green: 1 = 1.
//- The sum of the nodes marked in red: 3 = 3.
//- The sum of the nodes marked in yellow: 2 + 2 = 4.
//
//Constraints:
//The number of nodes in the list is in the range [3, 2 * 105].
//0 <= Node.val <= 1000
//There are no two consecutive nodes with Node.val == 0.
//The beginning and end of the linked list have Node.val == 0.