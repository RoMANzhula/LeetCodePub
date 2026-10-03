package P201_300.P203_Remove_Linked_List_Elements;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next.next = new ListNode(6);

        int val = 6;

        System.out.print("Original list: ");
        printList(head);

        head = solution.removeElements(head, val);

        System.out.print("After removing " + val + ": ");
        printList(head);
    }

    public static void printList(ListNode head) {
        ListNode current = head;

        while (current != null) {
            System.out.print(current.val);

            if (current.next != null) {
                System.out.print(" -> ");
            }

            current = current.next;
        }

        System.out.println();
    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode current = dummy;

        while (current.next != null) {
            if (current.next.val == val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }

        return dummy.next;
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


//Given the head of a linked list and an integer val, remove all the nodes of the linked list that
// has Node.val == val, and return the new head.

//Example 1:
//Input: head = [1,2,6,3,4,5,6], val = 6
//Output: [1,2,3,4,5]

//Example 2:
//Input: head = [], val = 1
//Output: []

//Example 3:
//Input: head = [7,7,7,7], val = 7
//Output: []

//Constraints:
//The number of nodes in the list is in the range [0, 104].
//1 <= Node.val <= 50
//0 <= val <= 50
