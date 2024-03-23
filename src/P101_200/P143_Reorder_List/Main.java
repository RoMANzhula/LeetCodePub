package P101_200.P143_Reorder_List;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        System.out.println("Original List:");
        solution.printList(head);

        solution.reorderList(head);

        System.out.println("Reordered List:");
        solution.printList(head);

        System.out.println("--------------------");

        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);
        head2.next.next.next = new ListNode(4);

        System.out.println("Original List:");
        solution.printList(head2);

        solution.reorderList(head2);

        System.out.println("Reordered List:");
        solution.printList(head2);
    }

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        // step 1: Find the middle of the linked list
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse the second half of the linked list
        ListNode prev = null;
        ListNode curr = slow.next;
        slow.next = null; // break the list into two halves
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }

        // step 3: Merge the first half and the reversed second half alternatively
        ListNode first = head;
        ListNode second = prev;
        while (second != null) {
            ListNode nextFirst = first.next;
            ListNode nextSecond = second.next;
            first.next = second;
            second.next = nextFirst;
            first = nextFirst;
            second = nextSecond;
        }
    }

    // utility method to print the linked list
    public void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
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

//Задача полягає в перенесенні елементів зв'язаного списку у вказаному порядку. Для досягнення цієї мети ми
// можемо використовувати кілька кроків:
//Знаходження середини списку: Спочатку потрібно знайти середину списку. Це можна зробити за допомогою двох
// вказівників: одного, який рухається на один елемент за раз, іншого - на два. Коли другий вказівник дійде
// до кінця списку, перший вказівник буде в середині.
//Реверс другої половини списку: Після знаходження середини списку ми реверсуємо другу половину. Це означає,
// що елементи, які раніше йшли у напрямку від середини до кінця списку, тепер йдуть у зворотньому порядку.
//Злиття двох половин: Після реверсу другої половини списку ми зливаємо першу половину з реверсованою другою
// половиною. Це означає, що елементи почергово беруться з кожної половини і додаються до нового списку.
//Це дозволяє нам отримати відсортований список у вказаному порядку. Ця задача вимагає обережності при роботі
// з вказівниками та врахуванні випадків крайніх точок (наприклад, коли список має парну або непарну
// кількість елементів).

//You are given the head of a singly linked-list. The list can be represented as:
//L0 → L1 → … → Ln - 1 → Ln
//Reorder the list to be on the following form:
//L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
//You may not modify the values in the list's nodes. Only nodes themselves may be changed.
//
//Example 1:
//Input: head = [1,2,3,4]
//Output: [1,4,2,3]

//Example 2:
//Input: head = [1,2,3,4,5]
//Output: [1,5,2,4,3]
//
//Constraints:
//The number of nodes in the list is in the range [1, 5 * 104].
//1 <= Node.val <= 1000
