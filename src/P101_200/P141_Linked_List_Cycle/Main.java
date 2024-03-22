package P101_200.P141_Linked_List_Cycle;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        // приклад 1
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;

        System.out.println(solution.hasCycle(node1)); // true

        // приклад 2
        ListNode node5 = new ListNode(1);
        ListNode node6 = new ListNode(2);

        node5.next = node6;
        node6.next = node5;

        System.out.println(solution.hasCycle(node5)); // true

        // приклад 3
        ListNode node7 = new ListNode(1);

        System.out.println(solution.hasCycle(node7)); // false
    }

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false; //якщо список пустий або має всього один вузол, то немає циклу
        }

        ListNode slow = head; //повільний показник
        ListNode fast = head.next; //швидкий показник

        while (slow != fast) { //продовжуємо, поки показники не зустрінуться
            if (fast == null || fast.next == null) {
                return false; //якщо швидкий показник дійшов до кінця списку, то немає циклу
            }

            slow = slow.next; //повільний показник переходить на наступний вузол
            fast = fast.next.next; //швидкий показник переходить через один вузол
        }

        return true; //якщо показники зустрілися, то є цикл
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

//Given head, the head of a linked list, determine if the linked list has a cycle in it.
//There is a cycle in a linked list if there is some node in the list that can be reached again by continuously
// following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer
// is connected to. Note that pos is not passed as a parameter.
//Return true if there is a cycle in the linked list. Otherwise, return false.

//Example 1:
//Input: head = [3,2,0,-4], pos = 1
//Output: true
//Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).

//Example 2:
//Input: head = [1,2], pos = 0
//Output: true
//Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.

//Example 3:
//Input: head = [1], pos = -1
//Output: false
//Explanation: There is no cycle in the linked list.
//
//Constraints:
//The number of the nodes in the list is in the range [0, 104].
//-105 <= Node.val <= 105
//pos is -1 or a valid index in the linked-list.