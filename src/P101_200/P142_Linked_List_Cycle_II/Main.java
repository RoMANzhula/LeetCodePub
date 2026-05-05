package P101_200.P142_Linked_List_Cycle_II;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        ListNode head = new ListNode(3);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(0);
        ListNode node3 = new ListNode(-4);

        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node1; // cycle here

        ListNode result = solution.detectCycle(head);

        if (result != null) {
            System.out.println("Cycle starts at node with value: " + result.val);
        } else {
            System.out.println("No cycle");
        }
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        // detect if cycle exists
        while (fast != null && fast.next != null) {
            slow = slow.next; // move 1 step
            fast = fast.next.next; // move 2 steps

            if (slow == fast) { // cycle detected
                // find cycle start
                ListNode pointer = head;

                while (pointer != slow) {
                    pointer = pointer.next;
                    slow = slow.next;
                }

                return pointer; // start of cycle
            }
        }

        return null; // no cycle
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}


//Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.
//There is a cycle in a linked list if there is some node in the list that can be reached again by continuously
// following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is
// connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.
//Do not modify the linked list.

//Example 1:
//Input: head = [3,2,0,-4], pos = 1
//Output: tail connects to node index 1
//Explanation: There is a cycle in the linked list, where tail connects to the second node.

//Example 2:
//Input: head = [1,2], pos = 0
//Output: tail connects to node index 0
//Explanation: There is a cycle in the linked list, where tail connects to the first node.

//Example 3:
//Input: head = [1], pos = -1
//Output: no cycle
//Explanation: There is no cycle in the linked list.

//Constraints:
//The number of the nodes in the list is in the range [0, 104].
//-105 <= Node.val <= 105
//pos is -1 or a valid index in the linked-list.
