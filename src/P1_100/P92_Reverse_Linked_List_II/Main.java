package P1_100.P92_Reverse_Linked_List_II;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class Main {
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right) { //check of random inputs and the base case
            return head;
        }

        //creating a dummy node to handle list boundary cases more easily
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevLeft = dummy;

        //move prevLeft to the node just before position 'left'
        for (int i = 1; i < left; i++) {
            prevLeft = prevLeft.next;
        }

        ListNode curr = prevLeft.next;
        ListNode prev = null;
        ListNode nextTemp;

        //reverse the nodes from position 'left' to 'right'
        for (int i = left; i <= right; i++) {
            nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }

        //connect the reversed portion back to the list
        prevLeft.next.next = curr;
        prevLeft.next = prev;

        return dummy.next;
    }

    //helper function to create a linked list from an array of values
    public static ListNode createLinkedList(int[] values) {
        ListNode dummy = new ListNode();
        ListNode current = dummy;
        for (int val : values) {
            current.next = new ListNode(val);
            current = current.next;
        }
        return dummy.next;
    }

    //helper function to print a linked list
    public static void printLinkedList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        int[] values = {1, 2, 3, 4, 5};
        int left = 2;
        int right = 4;

        ListNode inputList = createLinkedList(values);
        ListNode outputList = reverseBetween(inputList, left, right);
        printLinkedList(outputList);  // Output: 1 -> 4 -> 3 -> 2 -> 5 -> null
    }
}

//Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of
// the list from position left to position right, and return the reversed list.
//
//Example 1:
//Input: head = [1,2,3,4,5], left = 2, right = 4
//Output: [1,4,3,2,5]

//Example 2:
//Input: head = [5], left = 1, right = 1
//Output: [5]
//
//Constraints:
//The number of nodes in the list is n.
//1 <= n <= 500
//-500 <= Node.val <= 500
//1 <= left <= right <= n