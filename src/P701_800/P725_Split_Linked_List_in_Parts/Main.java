package P701_800.P725_Split_Linked_List_in_Parts;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        // Example 1
        int[] list1 = {1, 2, 3};
        int k1 = 5;
        ListNode head1 = createLinkedList(list1);
        ListNode[] result1 = solution.splitListToParts(head1, k1);
        System.out.print("Example 1 Output: ");
        printLinkedListParts(result1);

        // Example 2
        int[] list2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int k2 = 3;
        ListNode head2 = createLinkedList(list2);
        ListNode[] result2 = solution.splitListToParts(head2, k2);
        System.out.print("Example 2 Output: ");
        printLinkedListParts(result2);
    }

    public static ListNode createLinkedList(int[] values) {
        if (values.length == 0) return null;
        ListNode head = new ListNode(values[0]);
        ListNode curr = head;
        for (int i = 1; i < values.length; i++) {
            curr.next = new ListNode(values[i]);
            curr = curr.next;
        }
        return head;
    }

    public static void printLinkedListParts(ListNode[] parts) {
        for (ListNode part : parts) {
            System.out.print("[");
            ListNode curr = part;
            while (curr != null) {
                System.out.print(curr.val);
                if (curr.next != null) System.out.print(",");
                curr = curr.next;
            }
            System.out.print("] ");
        }
        System.out.println();
    }

    public ListNode[] splitListToParts(ListNode head, int k) {
        //find the length of the linked list
        int length = 0;
        ListNode current = head;
        while (current != null) {
            length++;
            current = current.next;
        }

        //calculate the size of each part and the remainder
        int partSize = length / k;
        int remainder = length % k;

        ListNode[] result = new ListNode[k];
        current = head;

        for (int i = 0; i < k; i++) {
            ListNode part = current;
            //calculate the size of this part
            int partLength = partSize + (i < remainder ? 1 : 0);

            //traverse the part and cut the connection
            for (int j = 0; j < partLength - 1 && current != null; j++) {
                current = current.next;
            }

            if (current != null) {
                ListNode nextNode = current.next;
                current.next = null;
                current = nextNode;
            }

            result[i] = part;
        }

        return result;
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


//Given the head of a singly linked list and an integer k, split the linked list into k consecutive linked list parts.
//The length of each part should be as equal as possible: no two parts should have a size differing by more than
// one. This may lead to some parts being null.
//The parts should be in the order of occurrence in the input list, and parts occurring earlier should always have a
// size greater than or equal to parts occurring later.
//Return an array of the k parts.
//
//Example 1:
//Input: head = [1,2,3], k = 5
//Output: [[1],[2],[3],[],[]]
//Explanation:
//The first element output[0] has output[0].val = 1, output[0].next = null.
//The last element output[4] is null, but its string representation as a ListNode is [].

//Example 2:
//Input: head = [1,2,3,4,5,6,7,8,9,10], k = 3
//Output: [[1,2,3,4],[5,6,7],[8,9,10]]
//Explanation:
//The input has been split into consecutive parts with size difference at most 1, and earlier parts are a larger
// size than the later parts.
//
//Constraints:
//The number of nodes in the list is in the range [0, 1000].
//0 <= Node.val <= 1000
//1 <= k <= 50
