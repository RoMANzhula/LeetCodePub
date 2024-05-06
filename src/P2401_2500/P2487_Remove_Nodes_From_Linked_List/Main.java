package P2401_2500.P2487_Remove_Nodes_From_Linked_List;

public class Main {
    public static void main(String[] args) {
        ListNode head1 = new ListNode(5);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(13);
        head1.next.next.next = new ListNode(3);
        head1.next.next.next.next = new ListNode(8);

        Main solution = new Main();
        ListNode result1 = solution.removeNodes(head1);
        printList(result1);  // Expected output: 13 -> 8

        // Example 2: [1,1,1,1]
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(1);
        head2.next.next = new ListNode(1);
        head2.next.next.next = new ListNode(1);

        ListNode result2 = solution.removeNodes(head2);
        printList(result2);  // Expected output: 1 -> 1 -> 1 -> 1
    }

    // Helper method to print the linked list
    private static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println();
    }

    public ListNode removeNodes(ListNode head) {
        if (head == null) return null;

        head.next = removeNodes(head.next);

        if (head.next != null && head.val < head.next.val) {
            return head.next;
        } else {
            return head;
        }
            //with ternary operator
//        return (head.next != null && head.val < head.next.val) ? head.next : head;
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

//Ця програма призначена для видалення елементів зі зв'язаного списку, які мають більше значення, ніж елементи,
// які знаходяться праворуч від них.
//Ми рекурсивно спускаємося по всіх елементах списку, змінюючи їх наступний елемент за допомогою рекурсії.
//Потім перевіряємо, чи поточний елемент має наступний елемент (head.next != null) і чи він менший за свого
// наступника (head.val < head.next.val).
//Якщо умова виконується, ми просто повертаємо наступний елемент, щоб він замінив поточний у списку.
//Якщо умова не виконується, ми повертаємо поточний елемент, оскільки він залишається у списку.
//Це дозволяє нам видаляти елементи зі списку, коли їхні значення менші за значення наступного елемента.

//You are given the head of a linked list.
//Remove every node which has a node with a greater value anywhere to the right side of it.
//Return the head of the modified linked list.
//
//Example 1:
//Input: head = [5,2,13,3,8]
//Output: [13,8]
//Explanation: The nodes that should be removed are 5, 2 and 3.
//- Node 13 is to the right of node 5.
//- Node 13 is to the right of node 2.
//- Node 8 is to the right of node 3.

//Example 2:
//Input: head = [1,1,1,1]
//Output: [1,1,1,1]
//Explanation: Every node has value 1, so no nodes are removed.
//
//Constraints:
//The number of the nodes in the given list is in the range [1, 105].
//1 <= Node.val <= 105