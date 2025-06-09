package P1_100.P24_Swap_Nodes_in_Pairs;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] arr1 = {1, 2, 3, 4};
        ListNode list1 = buildList(arr1);
        System.out.print("Original List 1: ");
        printList(list1);
        ListNode result1 = solution.swapPairs(list1);
        System.out.print("Swapped List 1: ");
        printList(result1);

        int[] arr2 = {};
        ListNode list2 = buildList(arr2);
        System.out.print("Original List 2: ");
        printList(list2);
        ListNode result2 = solution.swapPairs(list2);
        System.out.print("Swapped List 2: ");
        printList(result2);

        int[] arr3 = {1};
        ListNode list3 = buildList(arr3);
        System.out.print("Original List 3: ");
        printList(list3);
        ListNode result3 = solution.swapPairs(list3);
        System.out.print("Swapped List 3: ");
        printList(result3);

        int[] arr4 = {1, 2, 3};
        ListNode list4 = buildList(arr4);
        System.out.print("Original List 4: ");
        printList(list4);
        ListNode result4 = solution.swapPairs(list4);
        System.out.print("Swapped List 4: ");
        printList(result4);
    }

    // helper to build a list from array
    public static ListNode buildList(int[] values) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int val : values) {
            current.next = new ListNode(val);
            current = current.next;
        }
        return dummy.next;
    }

    // helper to print list
    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) System.out.print(" -> ");
            current = current.next;
        }
        System.out.println();
    }


    public ListNode swapPairs(ListNode head) {
        // dummy node to simplify edge cases
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // initialize the pointer to traverse the list
        ListNode current = dummy;

        // loop through the list in pairs
        while (current.next != null && current.next.next != null) {
            // nodes to be swapped
            ListNode first = current.next, second = current.next.next;

            // swapping
            first.next = second.next;
            second.next = first;
            current.next = second;

            //move the pointer two nodes ahead
            current = first;
        }

        // and return the new head
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


//Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without
// modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)

//Example 1:
//Input: head = [1,2,3,4]
//Output: [2,1,4,3]
//Explanation: look for the image

//Example 2:
//Input: head = []
//Output: []

//Example 3:
//Input: head = [1]
//Output: [1]

//Example 4:
//Input: head = [1,2,3]
//Output: [2,1,3]

//Constraints:
//The number of nodes in the list is in the range [0, 100].
//0 <= Node.val <= 100
