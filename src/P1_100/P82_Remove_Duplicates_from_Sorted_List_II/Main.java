package P1_100.P82_Remove_Duplicates_from_Sorted_List_II;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        // [1,2,3,3,4,4,5]
        ListNode head = new ListNode(1, new ListNode(2,
                new ListNode(3, new ListNode(3,
                        new ListNode(4, new ListNode(4,
                                new ListNode(5)))))));
        System.out.print("Input: ");
        printList(head);
        head = solution.deleteDuplicates(head);
        System.out.print("Output: ");
        printList(head);

        // [1,1,1,2,3]
        ListNode head2 = new ListNode(1, new ListNode(1,
                new ListNode(1, new ListNode(2,
                        new ListNode(3)))));
        System.out.print("\nInput: ");
        printList(head2);
        head2 = solution.deleteDuplicates(head2);
        System.out.print("Output: ");
        printList(head2);
    }

    public ListNode deleteDuplicates(ListNode head) {
        // dummy node to handle edge cases easily
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy; // previous distinct node

        while (head != null) {
            // iif current node has duplicates
            if (head.next != null && head.val == head.next.val) {
                // skip all nodes with this duplicate value
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
                // link prev node to the node after duplicates
                prev.next = head.next;
            } else {
                // move prev pointer if current node is unique
                prev = prev.next;
            }
            // move head forward
            head = head.next;
        }

        return dummy.next;
    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + (head.next != null ? " -> " : ""));
            head = head.next;
        }
        System.out.println();
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

//Complexity:
// time - O(n)
// space - O(1)


//Given the head of a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct
// numbers from the original list. Return the linked list sorted as well.

//Example 1:
//Input: head = [1,2,3,3,4,4,5]
//Output: [1,2,5]

//Example 2:
//Input: head = [1,1,1,2,3]
//Output: [2,3]

//Constraints:
//The number of nodes in the list is in the range [0, 300].
//-100 <= Node.val <= 100
//The list is guaranteed to be sorted in ascending order.