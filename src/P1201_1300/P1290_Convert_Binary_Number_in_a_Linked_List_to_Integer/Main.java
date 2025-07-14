package P1201_1300.P1290_Convert_Binary_Number_in_a_Linked_List_to_Integer;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();


        // exm: head = [1, 0, 1]
        ListNode head = new ListNode(1);
        head.next = new ListNode(0);
        head.next.next = new ListNode(1);

        int decimalValue = solution.getDecimalValue(head);
        System.out.println("Decimal value: " + decimalValue); // Output: 5
    }

    public int getDecimalValue(ListNode head) {
        int result = 0;

        while (head != null) {
            result = (result << 1) | head.val; // shift left and add current bit
            head = head.next;
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

//Complexity:
// time - O(n)
// space - O(1)


//Given head which is a reference node to a singly-linked list. The value of each node in the linked list is
// either 0 or 1. The linked list holds the binary representation of a number.
//Return the decimal value of the number in the linked list.
//The most significant bit is at the head of the linked list.

//Example 1:
//Input: head = [1,0,1]
//Output: 5
//Explanation: (101) in base 2 = (5) in base 10

//Example 2:
//Input: head = [0]
//Output: 0

//Constraints:
//The Linked List is not empty.
//Number of nodes will not exceed 30.
//Each node's value is either 0 or 1.