package P201_300.P206_Reverse_Linked_List;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        // Example 1
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);
        ListNode reversed1 = solution.reverseListIterative(head1);
        solution.printList(reversed1);

        // Example 2
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        ListNode reversed2 = solution.reverseListRecursive(head2);
        solution.printList(reversed2);

        // Example 3
        ListNode head3 = null;
        ListNode reversed3 = solution.reverseListIterative(head3);
        solution.printList(reversed3);
    }

    //recursive method
    public ListNode reverseListRecursive(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode reversedList = reverseListRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return reversedList;
    }

    //iterative method (for saving memory)
    public ListNode reverseListIterative(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }
        return prev;
    }

    public void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
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

//Given the head of a singly linked list, reverse the list, and return the reversed list.
//
//Example 1:
//Input: head = [1,2,3,4,5]
//Output: [5,4,3,2,1]

//Example 2:
//Input: head = [1,2]
//Output: [2,1]

//Example 3:
//Input: head = []
//Output: []
//
//
//Constraints:
//The number of nodes in the list is the range [0, 5000].
//-5000 <= Node.val <= 5000
//
//Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?
