package P1_100.P2_Add_Two_Numbers;

public class Main {
    public static void main(String[] args) {
        ListNode l1 = new ListNode();
        l1.val = 9;


        ListNode l2 = new ListNode();
        l2.val = 9;



        System.out.println(addTwoNumbers(l1, l2).toString());
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode listNodeResult = new ListNode(0); //create new Node for result

        ListNode current = listNodeResult; //loyal variable for work with ListNode
        int buffer = 0; //carry-over variable when adding nodes

        while (l1 != null || l2 != null) { //if one of Lists has east one Node
            int sum = buffer; //starting our adding Nodes
            if (l1 != null) { //if we have a Node
                sum += l1.val; //adding to sum
                l1 = l1.next; //taking next value of Node from list
            }
            if (l2 != null) { //similarly l1...
                sum += l2.val;
                l2 = l2.next;
            }

            buffer = sum / 10; //checking carry-over
            current.next = new ListNode(sum % 10); //getting remainder from dividing the amount by 10 and add it
            current = current.next; //move the current pointer to a new node (for next iteration)
        }

        if (buffer > 0) { //if our carry-over is more than 0 - create last Node for finishing
            current.next = new ListNode(buffer);
        }

        return listNodeResult.next; //result
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

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}

//You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse
//order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
//You may assume the two numbers do not contain any leading zero, except the number 0 itself.

//Example 1:
//Input: l1 = [2,4,3], l2 = [5,6,4]
//Output: [7,0,8]
//Explanation: 342 + 465 = 807.

//Example 2:
//Input: l1 = [0], l2 = [0]
//Output: [0]

//Example 3:
//Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//Output: [8,9,9,9,0,0,0,1]

//Constraints:
//The number of nodes in each linked list is in the range [1, 100].
//0 <= Node.val <= 9
//It is guaranteed that the list represents a number that does not have leading zeros.
