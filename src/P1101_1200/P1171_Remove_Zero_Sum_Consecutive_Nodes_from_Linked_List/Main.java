package P1101_1200.P1171_Remove_Zero_Sum_Consecutive_Nodes_from_Linked_List;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        // Example 1
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(-3);
        head1.next.next.next = new ListNode(3);
        head1.next.next.next.next = new ListNode(1);
        System.out.print("Example 1 Input: ");
        printList(head1);
        ListNode result1 = solution.removeZeroSumSublists(head1);
        System.out.print("Example 1 Output: ");
        printList(result1);

        // Example 2
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);
        head2.next.next.next = new ListNode(-3);
        head2.next.next.next.next = new ListNode(4);
        System.out.print("Example 2 Input: ");
        printList(head2);
        ListNode result2 = solution.removeZeroSumSublists(head2);
        System.out.print("Example 2 Output: ");
        printList(result2);

        // Example 3
        ListNode head3 = new ListNode(1);
        head3.next = new ListNode(2);
        head3.next.next = new ListNode(3);
        head3.next.next.next = new ListNode(-3);
        head3.next.next.next.next = new ListNode(-2);
        System.out.print("Example 3 Input: ");
        printList(head3);
        ListNode result3 = solution.removeZeroSumSublists(head3);
        System.out.print("Example 3 Output: ");
        printList(result3);
    }

    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        HashMap<Integer, ListNode> map = new HashMap<>();
        int sum = 0;
        ListNode curr = dummy;

        while (curr != null) {
            sum += curr.val;

            // Якщо сума вже є у мапі, видаляємо проміжні вузли
            if (map.containsKey(sum)) {
                ListNode prev = map.get(sum);
                ListNode temp = prev.next;
                int tempSum = sum + temp.val;

                while (temp != curr) {
                    // Видаляємо всі проміжні вузли
                    map.remove(tempSum);
                    temp = temp.next;
                    tempSum += temp.val;
                }
                // Видаляємо і встановлюємо відповідно покажчики
                prev.next = curr.next;
            } else {
                map.put(sum, curr);
            }

            curr = curr.next;
        }

        return dummy.next;
    }

    // Utility function to print the linked list
    public static void printList(ListNode head) {
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

//Ця задача полягає в тому, щоб видалити усі підпослідовності зі списку, які мають суму, рівну 0.
//Ми створюємо dummy вузол, який допоможе нам працювати з початковим списком.
//Проходимо по списку, обчислюючи поточну суму елементів.
//Для кожного елементу, який ми зустрічаємо, ми перевіряємо, чи вже є така сума у мапі. Якщо так, це означає,
// що ми знайшли підпослідовність зі сумою 0.
//Якщо така сума зустрічається вперше, ми додаємо її до мапи разом з посиланням на поточний вузол.
//Якщо ми знаходимо суму, яка вже є в мапі, ми видаляємо всі проміжні вузли між вузлом, до якого ми знайшли суму,
// і поточним вузлом.
//Після цього ми оновлюємо посилання на наступний вузол попереднього вузла таким чином, щоб видалити проміжні вузли.
//Продовжуємо цей процес до тих пір, поки не дійдемо до кінця списку.
//Ця модифікація коду дозволяє правильно враховувати випадки, коли сума підпослідовностей зменшується до 0,
// і видаляти їх зі списку.

//Given the head of a linked list, we repeatedly delete consecutive sequences of nodes that sum to 0 until
// there are no such sequences.
//After doing so, return the head of the final linked list.  You may return any such answer.
//(Note that in the examples below, all sequences are serializations of ListNode objects.)
//
//Example 1:
//Input: head = [1,2,-3,3,1]
//Output: [3,1]
//Note: The answer [1,2,1] would also be accepted.

//Example 2:
//Input: head = [1,2,3,-3,4]
//Output: [1,2,4]

//Example 3:
//Input: head = [1,2,3,-3,-2]
//Output: [1]
//
//Constraints:
//The given linked list will contain between 1 and 1000 nodes.
//Each node in the linked list has -1000 <= node.val <= 1000.