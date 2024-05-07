package P2801_2900.P2816_Double_a_Number_Represented_as_a_Linked_List;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        // Example 1
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(8);
        head1.next.next = new ListNode(9);
        ListNode result1 = solution.doubleIt(head1);
        solution.printLinkedList(result1); // Output: 3 7 8

        // Example 2
        ListNode head2 = new ListNode(9);
        head2.next = new ListNode(9);
        head2.next.next = new ListNode(9);
        ListNode result2 = solution.doubleIt(head2);
        solution.printLinkedList(result2); // Output: 1 9 9 8
    }

    public void printLinkedList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }

    public ListNode doubleIt(ListNode head) {
        // Reverse the linked list
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }
        head = prev;

        // Double the linked list
        current = head;
        int carry = 0;
        while (current != null) {
            int newValue = current.val * 2 + carry;
            current.val = newValue % 10;
            carry = newValue / 10;
            if (current.next == null && carry > 0) {
                current.next = new ListNode(carry);
                break;
            }
            current = current.next;
        }

        // Reverse the linked list back
        prev = null;
        current = head;
        while (current != null) {
            ListNode nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }
        head = prev;

        return head;
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

//У даному завданні ми отримуємо на вхід початок непустого зв'язаного списку, який представляє невід'ємне ціле
// число без ведучих нулів. Нам потрібно повернути початок зв'язаного списку після подвоєння кожного значення.
//Наш підхід такий:
//Зворотний обхід зв'язаного списку: Спочатку ми здійснюємо зворотний обхід зв'язаного списку, оскільки нам
// потрібно спочатку змінити значення кожного вузла.
//Подвоєння значень: Проходячи по списку, ми подвоюємо значення кожного вузла і обробляємо перенос, якщо
// потрібно. Якщо досягаємо кінця списку і є перенос, ми створюємо новий вузол з залишковим переносом.
//Знову зворотний обхід: Після того як ми подвоїли значення і обробили переноси, ми знову зворотньо проходимося
// по списку, щоб повернути його в початковий порядок.
//Це дозволяє нам отримати правильний результат для заданого вхідного списку.

//You are given the head of a non-empty linked list representing a non-negative integer without leading zeroes.
//Return the head of the linked list after doubling it.
//
//Example 1:
//Input: head = [1,8,9]
//Output: [3,7,8]
//Explanation: The figure above corresponds to the given linked list which represents the number 189. Hence, the
// returned linked list represents the number 189 * 2 = 378.

//Example 2:
//Input: head = [9,9,9]
//Output: [1,9,9,8]
//Explanation: The figure above corresponds to the given linked list which represents the number 999. Hence, the
// returned linked list reprersents the number 999 * 2 = 1998.
//
//Constraints:
//The number of nodes in the list is in the range [1, 104]
//0 <= Node.val <= 9
//The input is generated such that the list represents a number that does not have leading zeros, except
// the number 0 itself.