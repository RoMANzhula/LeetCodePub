package P1_100.P86_Partition_List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

    }

    public ListNode partition(ListNode head, int x) {
        //dummy heads for two lists
        ListNode lessDummy = new ListNode(0);
        ListNode greaterDummy = new ListNode(0);

        ListNode less = lessDummy;
        ListNode greater = greaterDummy;

        // traverse the original list
        while (head != null) {
            if (head.val < x) {
                less.next = head;
                less = less.next;
            } else {
                greater.next = head;
                greater = greater.next;
            }
            head = head.next;
        }

        // terminate the greater list
        greater.next = null;

        // connect the two lists
        less.next = greaterDummy.next;

        return lessDummy.next;
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


//Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes
// greater than or equal to x.
//You should preserve the original relative order of the nodes in each of the two partitions.

//Example 1:
//Input: head = [1,4,3,2,5,2], x = 3
//Output: [1,2,2,4,3,5]

//Example 2:
//Input: head = [2,1], x = 2
//Output: [1,2]

//Constraints:
//The number of nodes in the list is in the range [0, 200].
//-100 <= Node.val <= 100
//-200 <= x <= 200