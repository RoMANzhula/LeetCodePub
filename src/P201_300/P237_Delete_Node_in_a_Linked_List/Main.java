package P201_300.P237_Delete_Node_in_a_Linked_List;

public class Main {

    public static void main(String[] args) {
        // Example 1
        ListNode head1 = new ListNode(4);
        head1.next = new ListNode(5);
        head1.next.next = new ListNode(1);
        head1.next.next.next = new ListNode(9);

        System.out.print("Input: ");
        printLinkedList(head1);

        Main solution = new Main();
        solution.deleteNode(head1.next); // Deleting node with value 5

        System.out.print("Output: ");
        printLinkedList(head1);

        // Example 2
        ListNode head2 = new ListNode(4);
        head2.next = new ListNode(5);
        head2.next.next = new ListNode(1);
        head2.next.next.next = new ListNode(9);

        System.out.print("\nInput: ");
        printLinkedList(head2);

        solution.deleteNode(head2.next.next); // Deleting node with value 1

        System.out.print("Output: ");
        printLinkedList(head2);
    }
    public void deleteNode(ListNode node) {
        //since the node is not the last, we can copy the next node's value and skip the next node
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public static void printLinkedList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }
}

class  ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
    }
}

//У цій задачі ми маємо однозв'язний список із числами. Потрібно видалити певний вузол у списку, знаючи лише
// сам вузол, а не початок списку.
//Оскільки ми не маємо доступу до початку списку, ми не можемо просто змінити посилання попереднього
// вузла на наступний віддаленого вузла. Замість цього, ми можемо скопіювати значення наступного вузла в
// поточний вузол та замінити посилання наступного вузла на вузол, який знаходиться після наступного. Це
// дозволяє "видалити" поточний вузол, оскільки він тепер містить значення та посилання на вузол, який
// знаходиться після наступного.
//Отже, виконуючи метод deleteNode, ми присвоюємо значення наступного вузла поточному вузлу та змінюємо
// посилання на наступний вузол так, щоб воно вказувало на вузол після наступного. Таким чином, поточний
// вузол "видаляється" зі списку.
//Після виконання методу вузол буде видалено зі списку, і ми можемо вивести оновлений список на екран.

//There is a singly-linked list head and we want to delete a node node in it.
//You are given the node to be deleted node. You will not be given access to the first node of head.
//All the values of the linked list are unique, and it is guaranteed that the given node node is not the last
// node in the linked list.
//Delete the given node. Note that by deleting the node, we do not mean removing it from memory. We mean:
//The value of the given node should not exist in the linked list.
//The number of nodes in the linked list should decrease by one.
//All the values before node should be in the same order.
//All the values after node should be in the same order.
//Custom testing:
//For the input, you should provide the entire linked list head and the node to be given node. node should not be the last node of the list and should be an actual node in the list.
//We will build the linked list and pass the node to your function.
//The output will be the entire list after calling your function.
//
//Example 1:
//Input: head = [4,5,1,9], node = 5
//Output: [4,1,9]
//Explanation: You are given the second node with value 5, the linked list should become 4 -> 1 -> 9 after
// calling your function.

//Example 2:
//Input: head = [4,5,1,9], node = 1
//Output: [4,5,9]
//Explanation: You are given the third node with value 1, the linked list should become 4 -> 5 -> 9 after
// calling your function.
//
//Constraints:
//The number of the nodes in the given list is in the range [2, 1000].
//-1000 <= Node.val <= 1000
//The value of each node in the list is unique.
//The node to be deleted is in the list and is not a tail node.
