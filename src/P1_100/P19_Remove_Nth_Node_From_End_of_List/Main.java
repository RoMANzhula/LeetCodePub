package P1_100.P19_Remove_Nth_Node_From_End_of_List;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        // Example 1
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);
        int n1 = 2;
        ListNode result1 = solution.removeNthFromEnd(head1, n1);
        printLinkedList(result1); // Expected output: [1, 2, 3, 5]

        // Example 2
        ListNode head2 = new ListNode(1);
        int n2 = 1;
        ListNode result2 = solution.removeNthFromEnd(head2, n2);
        printLinkedList(result2); // Expected output: []

        // Example 3
        ListNode head3 = new ListNode(1);
        head3.next = new ListNode(2);
        int n3 = 1;
        ListNode result3 = solution.removeNthFromEnd(head3, n3);
        printLinkedList(result3); // Expected output: [1]
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;

        // Move the fast pointer n+1 steps ahead
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // Move fast to the end, maintaining the gap of n nodes with slow
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // Remove the nth node from the end
        slow.next = slow.next.next;

        return dummy.next;
    }

    public static void printLinkedList(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null)
                System.out.print(" -> ");
            head = head.next;
        }
        System.out.println();
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

//У цьому рішенні ми використовуємо два вказівники, fast і slow, щоб визначити потрібну позицію елемента для видалення.
//Спочатку ми створюємо фіктивний вузол dummy, який допоможе нам обробити випадок видалення першого елемента списку.
//Потім ми рухаємо вказівник fast на n+1 кроків вперед.
//Після цього ми рухаємо обидва вказівники (fast і slow) до тих пір, поки fast не дійде до кінця списку. При
// цьому вказівник slow буде вказувати на елемент, який знаходиться на один вузол перед тим, який потрібно видалити.
//За допомогою slow.next = slow.next.next ми видаляємо потрібний елемент.
//Нарешті, ми повертаємо dummy.next як новий початок списку, оскільки вихідний вузол може бути видалений.
//Це рішення працює ефективно та має часову складність O(N), де N - кількість вузлів у списку.

//Given the head of a linked list, remove the nth node from the end of the list and return its head.
//
//Example 1:
//Input: head = [1,2,3,4,5], n = 2
//Output: [1,2,3,5]

//Example 2:
//Input: head = [1], n = 1
//Output: []

//Example 3:
//Input: head = [1,2], n = 1
//Output: [1]
//

//Constraints:
//The number of nodes in the list is sz.
//1 <= sz <= 30
//0 <= Node.val <= 100
//1 <= n <= sz