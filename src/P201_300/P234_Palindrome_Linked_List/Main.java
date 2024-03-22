package P201_300.P234_Palindrome_Linked_List;

public class Main {
    public static void main(String[] args) {
        Main palindromeLinkedList = new Main();

        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(2);
        head1.next.next.next = new ListNode(1);

        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);

        System.out.println("Is the first linked list a palindrome? " + palindromeLinkedList.isPalindrome(head1)); // Expected: true
        System.out.println("Is the second linked list a palindrome? " + palindromeLinkedList.isPalindrome(head2)); // Expected: false
    }


    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true; // empty list or single node list is a palindrome
        }

        // find the middle of the linked list
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // reverse the second half of the linked list
        ListNode secondHalf = reverse(slow.next);

        // compare the first half and the reversed second half
        ListNode firstHalf = head;
        while (secondHalf != null) {
            if (firstHalf.val != secondHalf.val) {
                return false; // not a palindrome
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }

        return true; // here is palindrome
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }
        return prev;
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


//Given the head of a singly linked list, return true if it is a
//palindrome
// or false otherwise.
//
//Example 1
//Input: head = [1,2,2,1]
//Output: true

//Example 2:
//Input: head = [1,2]
//Output: false
//
//Constraints:
//The number of nodes in the list is in the range [1, 105].
//0 <= Node.val <= 9
//
//Follow up: Could you do it in O(n) time and O(1) space?
