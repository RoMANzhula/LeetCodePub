package P1_100.P25_Reverse_Nodes_in_K_Group;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] vals1 = {1, 2, 3, 4, 5};
        int k1 = 2;
        ListNode head1 = buildList(vals1);
        System.out.print("Original List 1: "); // Original List 1: 1 -> 2 -> 3 -> 4 -> 5
        printList(head1);
        ListNode result1 = solution.reverseKGroup(head1, k1);
        System.out.print("Reversed in k=2: "); // Reversed in k=2: 2 -> 1 -> 4 -> 3 -> 5
        printList(result1);

        System.out.println();

        int[] vals2 = {1, 2, 3, 4, 5};
        int k2 = 3;
        ListNode head2 = buildList(vals2);
        System.out.print("Original List 2: "); // Original List 2: 1 -> 2 -> 3 -> 4 -> 5
        printList(head2);
        ListNode result2 = solution.reverseKGroup(head2, k2);
        System.out.print("Reversed in k=3: "); // Reversed in k=3: 3 -> 2 -> 1 -> 4 -> 5
        printList(result2);
    }

    public static ListNode buildList(int[] vals) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int v : vals) {
            current.next = new ListNode(v);
            current = current.next;
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


    public ListNode reverseKGroup(ListNode head, int k) {
        // edge case check
        if (head == null || k == 1) return head;

        // dummy node simplifies handling head reversals
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prevGroupEnd = dummy;

        while (true) {
            // find the kth node
            ListNode kth = getKthNode(prevGroupEnd, k);
            if (kth == null) break; // not enough nodes left to reverse

            ListNode groupStart = prevGroupEnd.next;
            ListNode nextGroupStart = kth.next;

            // reverse current k-group
            ListNode prev = kth.next;
            ListNode curr = groupStart;

            while (curr != nextGroupStart) {
                ListNode tmp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = tmp;
            }

            // reconnect reversed group to previous part
            prevGroupEnd.next = kth;
            prevGroupEnd = groupStart;
        }

        return dummy.next;
    }

    private ListNode getKthNode(ListNode curr, int k) {
        while (curr != null && k > 0) {
            curr = curr.next;
            k--;
        }

        return curr;
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
