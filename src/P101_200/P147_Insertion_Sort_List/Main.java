package P101_200.P147_Insertion_Sort_List;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        // Example 1
        ListNode head1 = new ListNode(4);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(1);
        head1.next.next.next = new ListNode(3);

        ListNode sortedHead1 = solution.insertionSortList(head1);
        solution.printList(sortedHead1); // Output: 1 2 3 4

        // Example 2
        ListNode head2 = new ListNode(-1);
        head2.next = new ListNode(5);
        head2.next.next = new ListNode(3);
        head2.next.next.next = new ListNode(4);
        head2.next.next.next.next = new ListNode(0);

        ListNode sortedHead2 = solution.insertionSortList(head2);
        solution.printList(sortedHead2); // Output: -1 0 3 4 5
    }

    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode tail = head;
        ListNode curr = head.next;

        while (curr != null) {
            if (curr.val >= tail.val) {
                tail = curr;
                curr = curr.next;
            } else {
                ListNode prev = dummy;
                while (prev.next.val < curr.val) {
                    prev = prev.next;
                }
                tail.next = curr.next;
                curr.next = prev.next;
                prev.next = curr;
                curr = tail.next;
            }
        }

        return dummy.next;
    }

    // Helper method to print the linked list
    public void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
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

//Задача полягає у сортуванні зв'язаного списку за допомогою алгоритму сортування вставками. У цьому варіанті ми
// застосовуємо оптимізований підхід, щоб зменшити кількість проходів по відсортованому списку.
//Основна ідея полягає в тому, що ми будемо проходити вхідний список, і кожен елемент додаємо в відсортований
// список на потрібну позицію. Для цього ми маємо певний вказівник, що вказує на кінець відсортованої
// частини списку. Кожен новий елемент порівнюється з елементами відсортованої частини, і він вставляється
// між елементами, які менші і більші за нього.
//Основна робота відбувається в циклі, де ми перебираємо кожен елемент вхідного списку. Якщо поточний елемент
// відсортованої частини менший за поточний, ми шукаємо відповідне місце для вставки цього елемента відповідно
// до його значення. Якщо ж поточний елемент відсортованої частини більший або рівний за останній елемент, то
// ми просто переходимо до наступного елемента.
//Цей підхід значно прискорює процес сортування, оскільки ми уникнули повторних проходів по відсортованому списку
// при кожній ітерації. В результаті ми отримуємо відсортований зв'язаний список.

//Given the head of a singly linked list, sort the list using insertion sort, and return the sorted list's head.
//The steps of the insertion sort algorithm:
//Insertion sort iterates, consuming one input element each repetition and growing a sorted output list.
//At each iteration, insertion sort removes one element from the input data, finds the location it belongs within
// the sorted list and inserts it there.
//It repeats until no input elements remain.
//The following is a graphical example of the insertion sort algorithm. The partially sorted list (black) initially
// contains only the first element in the list. One element (red) is removed from the input data and inserted
// in-place into the sorted list with each iteration.

//Example 1:
//Input: head = [4,2,1,3]
//Output: [1,2,3,4]

//Example 2:
//Input: head = [-1,5,3,4,0]
//Output: [-1,0,3,4,5]
//
//Constraints:
//The number of nodes in the list is in the range [1, 5000].
//-5000 <= Node.val <= 5000