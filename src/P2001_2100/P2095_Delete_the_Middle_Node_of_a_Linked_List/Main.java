package P2001_2100.P2095_Delete_the_Middle_Node_of_a_Linked_List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 3, 4, 7, 1, 2, 6};
        ListNode head1 = buildList(nums1);

        System.out.print("Original: ");
        printList(head1);

        head1 = solution.deleteMiddle(head1);

        System.out.print("After deletion: ");
        printList(head1);


        int[] nums2 = {1, 2, 3, 4};
        ListNode head2 = buildList(nums2);

        System.out.print("\nOriginal: ");
        printList(head2);

        head2 = solution.deleteMiddle(head2);

        System.out.print("After deletion: ");
        printList(head2);


        int[] nums3 = {2, 1};
        ListNode head3 = buildList(nums3);

        System.out.print("\nOriginal: ");
        printList(head3);

        head3 = solution.deleteMiddle(head3);

        System.out.print("After deletion: ");
        printList(head3);


        int[] nums4 = {5};
        ListNode head4 = buildList(nums4);

        System.out.print("\nOriginal: ");
        printList(head4);

        head4 = solution.deleteMiddle(head4);

        System.out.print("After deletion: ");
        printList(head4);
    }

    public ListNode deleteMiddle(ListNode head) {
        // if there is only one node, remove it.
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // delete the middle node.
        prev.next = slow.next;

        return head;
    }

    // print linked list
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) {
                System.out.print(" -> ");
            }
            head = head.next;
        }
        System.out.println();
    }

    // build linked list from array
    public static ListNode buildList(int[] arr) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        for (int num : arr) {
            curr.next = new ListNode(num);
            curr = curr.next;
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


//You are given the head of a linked list. Delete the middle node, and return the head of the modified linked list.
//The middle node of a linked list of size n is the ⌊n / 2⌋th node from the start using 0-based indexing, where ⌊x⌋
// denotes the largest integer less than or equal to x.
//For n = 1, 2, 3, 4, and 5, the middle nodes are 0, 1, 1, 2, and 2, respectively.

//Example 1:
//Input: head = [1,3,4,7,1,2,6]
//Output: [1,3,4,1,2,6]
//Explanation:
//The above figure represents the given linked list. The indices of the nodes are written below.
//Since n = 7, node 3 with value 7 is the middle node, which is marked in red.
//We return the new list after removing this node.

//Example 2:
//Input: head = [1,2,3,4]
//Output: [1,2,4]
//Explanation:
//The above figure represents the given linked list.
//For n = 4, node 2 with value 3 is the middle node, which is marked in red.

//Example 3:
//Input: head = [2,1]
//Output: [2]
//Explanation:
//The above figure represents the given linked list.
//For n = 2, node 1 with value 1 is the middle node, which is marked in red.
//Node 0 with value 2 is the only node remaining after removing node 1.

//Constraints:
//The number of nodes in the list is in the range [1, 105].
//1 <= Node.val <= 105
