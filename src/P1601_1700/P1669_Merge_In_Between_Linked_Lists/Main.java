package P1601_1700.P1669_Merge_In_Between_Linked_Lists;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        // Example 1
        ListNode list1 = new ListNode(10);
        list1.next = new ListNode(1);
        list1.next.next = new ListNode(13);
        list1.next.next.next = new ListNode(6);
        list1.next.next.next.next = new ListNode(9);
        list1.next.next.next.next.next = new ListNode(5);

        ListNode list2 = new ListNode(1000000);
        list2.next = new ListNode(1000001);
        list2.next.next = new ListNode(1000002);

        int a1 = 3;
        int b1 = 4;

        ListNode result1 = solution.mergeInBetween(list1, a1, b1, list2);
        printList(result1); // Output: 10 1 13 1000000 1000001 1000002 5

        // Example 2
        ListNode list3 = new ListNode(0);
        list3.next = new ListNode(1);
        list3.next.next = new ListNode(2);
        list3.next.next.next = new ListNode(3);
        list3.next.next.next.next = new ListNode(4);
        list3.next.next.next.next.next = new ListNode(5);
        list3.next.next.next.next.next.next = new ListNode(6);

        ListNode list4 = new ListNode(1000000);
        list4.next = new ListNode(1000001);
        list4.next.next = new ListNode(1000002);
        list4.next.next.next = new ListNode(1000003);
        list4.next.next.next.next = new ListNode(1000004);

        int a2 = 2;
        int b2 = 5;

        ListNode result2 = solution.mergeInBetween(list3, a2, b2, list4);
        printList(result2); // Output: 0 1 1000000 1000001 1000002 1000003 1000004 6
    }

    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode current = list1;
        ListNode prevA = null;
        ListNode nextB = null;
        int count = 0;

        // traverse list1 to find the "a"th and bth nodes
        while (current != null) {
            if (count == a - 1) {
                prevA = current;
            }
            if (count == b + 1) {
                nextB = current;
                break;
            }
            count++;
            current = current.next;
        }

        // connect the node before "a" to the first node of list2
        if (prevA != null) {
            prevA.next = list2;
        }

        // Traverse list2 to find its last node
        ListNode list2Tail = list2;
        while (list2Tail.next != null) {
            list2Tail = list2Tail.next;
        }

        // connect the last node of list2 to the node after `b`
        list2Tail.next = nextB;

        // if `a` is the first node of list1, return list2's head
        if (a == 0) {
            return list2;
        }

        // otherwise, return the head of list1
        return list1;
    }

    // helper method to print the linked list
    public static void printList(ListNode head) {
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

//Спочатку створюємо клас ListNode, який представляє вузли зв'язаного списку. У кожного вузла є ціле значення val та
// посилання next на наступний вузол у списку.
//У класі Solution ми визначаємо метод mergeInBetween, який приймає зв'язаний список list1, цілі числа a та
// b (індекси вузлів, які потрібно видалити), а також другий зв'язаний список list2, який ми вставимо на місце
// видалених вузлів.
//У методі mergeInBetween ми проходимо по першому списку list1, знаходимо вузли з індексами a-1 та b+1 і зберігаємо
// посилання на них у змінних prevA та nextB відповідно.
//Після цього ми з'єднуємо кінець першого списку з початком другого, якщо відповідні вузли були знайдені.
//Якщо a було рівним нулю (тобто видалявся перший вузол першого списку), то повертаємо голову другого списку, як
// нову голову об'єднаного списку.
//Якщо ж a не було рівним нулю, повертаємо голову першого списку.

//You are given two linked lists: list1 and list2 of sizes n and m respectively.
//Remove list1's nodes from the ath node to the bth node, and put list2 in their place.
//The blue edges and nodes in the following figure indicate the result:
//Build the result list and return its head.

//Example 1:
//Input: list1 = [10,1,13,6,9,5], a = 3, b = 4, list2 = [1000000,1000001,1000002]
//Output: [10,1,13,1000000,1000001,1000002,5]
//Explanation: We remove the nodes 3 and 4 and put the entire list2 in their place. The blue edges and nodes in
// the above figure indicate the result.

//Example 2:
//Input: list1 = [0,1,2,3,4,5,6], a = 2, b = 5, list2 = [1000000,1000001,1000002,1000003,1000004]
//Output: [0,1,1000000,1000001,1000002,1000003,1000004,6]
//Explanation: The blue edges and nodes in the above figure indicate the result.
//
//Constraints:
//3 <= list1.length <= 104
//1 <= a <= b < list1.length - 1
//1 <= list2.length <= 104
