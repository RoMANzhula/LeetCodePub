package P801_900.P876_Middle_of_the_Linked_List;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        // Example 1 - output 3 4 5
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);
        ListNode middle1 = solution.middleNode(head1);
        while (middle1 != null) {
            System.out.print(middle1.val + " ");
            middle1 = middle1.next;
        }
        System.out.println();

        // Example 2 - output 4 5 6
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);
        head2.next.next.next = new ListNode(4);
        head2.next.next.next.next = new ListNode(5);
        head2.next.next.next.next.next = new ListNode(6);
        ListNode middle2 = solution.middleNode(head2);
        while (middle2 != null) {
            System.out.print(middle2.val + " ");
            middle2 = middle2.next;
        }
        System.out.println();
    }

    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
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

//Задача вирішена за допомогою техніки двох вказівників. Ми ініціалізуємо два вказівники, slow і fast, обидва
// вказівники починають з голови зв'язаного списку. Fast переміщуємо удвічі швидше, ніж slow. Коли fast досягне
// кінця списку, slow буде у середині списку. Якщо кількість вузлів парна, то slow буде вказувати на перший
// середній вузол, і для отримання другого середнього вузла, ми повернемо slow.next.

//Given the head of a singly linked list, return the middle node of the linked list.
//If there are two middle nodes, return the second middle node.
//
//Example 1:
//Input: head = [1,2,3,4,5]
//Output: [3,4,5]
//Explanation: The middle node of the list is node 3.

//Example 2:
//Input: head = [1,2,3,4,5,6]
//Output: [4,5,6]
//Explanation: Since the list has two middle nodes with values 3 and 4, we return the second one.
//
//Constraints:
//The number of nodes in the list is in the range [1, 100].
//1 <= Node.val <= 100