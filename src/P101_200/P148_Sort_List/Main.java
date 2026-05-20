package P101_200.P148_Sort_List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        ListNode head = new ListNode(4,
                new ListNode(2,
                        new ListNode(1,
                                new ListNode(3))))
        ;

        ListNode sorted = solution.sortList(head);

        printList(sorted);
    }

    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // split list into two halves
        ListNode mid = getMiddle(head);
        ListNode rightHead = mid.next;
        mid.next = null;

        // sort each half
        ListNode left = sortList(head);
        ListNode right = sortList(rightHead);

        // sorted halves
        return merge(left, right);
    }

    private ListNode getMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }

        curr.next = (l1 != null) ? l1 : l2;

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
// time - O(n log n)
// space - O(log n)


//Given the head of a linked list, return the list after sorting it in ascending order.

//Example 1:
//Input: head = [4,2,1,3]
//Output: [1,2,3,4]

//Example 2:
//Input: head = [-1,5,3,4,0]
//Output: [-1,0,3,4,5]

//Example 3:
//Input: head = []
//Output: []

//Constraints:
//The number of nodes in the list is in the range [0, 5 * 104].
//-105 <= Node.val <= 105
