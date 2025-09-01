package P1_100.P61_Rotate_List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        // head = [1,2,3,4,5], k = 2
        ListNode head1 = new ListNode(1, new ListNode(2, new ListNode(3,
                new ListNode(4, new ListNode(5)))));
        ListNode result1 = solution.rotateRight(head1, 2);
        printList(result1); // 4 -> 5 -> 1 -> 2 -> 3

        // head = [0,1,2], k = 4
        ListNode head2 = new ListNode(0, new ListNode(1, new ListNode(2)));
        ListNode result2 = solution.rotateRight(head2, 4);
        printList(result2); // 2 -> 0 -> 1
    }

    static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) System.out.print(" -> ");
            head = head.next;
        }
        System.out.println();
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // find the length and the tail
        ListNode tail = head;
        int length = 1;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        // make it circular
        tail.next = head;

        // find new head (length - k % length steps ahead)
        k = k % length;
        int stepsToNewHead = length - k;

        ListNode newTail = tail;
        while (stepsToNewHead-- > 0) {
            newTail = newTail.next;
        }

        // break the circle
        ListNode newHead = newTail.next;
        newTail.next = null;

        return newHead;
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


//Given the head of a linked list, rotate the list to the right by k places.

//Example 1:
//Input: head = [1,2,3,4,5], k = 2
//Output: [4,5,1,2,3]

//Example 2:
//Input: head = [0,1,2], k = 4
//Output: [2,0,1]

//Constraints:
//The number of nodes in the list is in the range [0, 500].
//-100 <= Node.val <= 100
//0 <= k <= 2 * 109
