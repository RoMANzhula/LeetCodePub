package P1_100.P83_Remove_Duplicates_from_Sorted_List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        ListNode head1 = new ListNode(1, new ListNode(1, new ListNode(2)));
        ListNode res1 = solution.deleteDuplicates(head1);
        printList(res1); // Output: 1 2

        // Example 2: head = [1,1,2,3,3]
        ListNode head2 = new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3)))));
        ListNode res2 = solution.deleteDuplicates(head2);
        printList(res2); // Output: 1 2 3
    }

    public static void printList(ListNode head) {
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public ListNode deleteDuplicates(ListNode head) {
        // if list is empty or has only one node, nothing to remove
        if (head == null || head.next == null) {
            return head;
        }

        ListNode current = head;

        // traverse the list
        while (current != null && current.next != null) {
            if (current.val == current.next.val) {
                // skip duplicate
                current.next = current.next.next;
            } else {
                // move forward if not duplicate
                current = current.next;
            }
        }

        return head;
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


//Given the head of a sorted linked list, delete all duplicates such that each element appears only once. Return
// the linked list sorted as well.

//Example 1:
//Input: head = [1,1,2]
//Output: [1,2]

//Example 2:
//Input: head = [1,1,2,3,3]
//Output: [1,2,3]

//Constraints:
//The number of nodes in the list is in the range [0, 300].
//-100 <= Node.val <= 100
//The list is guaranteed to be sorted in ascending order.
