package P3217_Dele_Nodes_From_Linked_List_Present_in_Array;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 2, 3};
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);
        ListNode result1 = solution.modifiedList(nums1, head1);
        solution.printList(result1); // Output: 4 5

        int[] nums2 = {1};
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(1);
        head2.next.next.next = new ListNode(2);
        head2.next.next.next.next = new ListNode(1);
        head2.next.next.next.next.next = new ListNode(2);
        ListNode result2 = solution.modifiedList(nums2, head2);
        solution.printList(result2); // Output: 2 2 2

        int[] nums3 = {5};
        ListNode head3 = new ListNode(1);
        head3.next = new ListNode(2);
        head3.next.next = new ListNode(3);
        head3.next.next.next = new ListNode(4);
        ListNode result3 = solution.modifiedList(nums3, head3);
        solution.printList(result3); // Output: 1 2 3 4
    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public ListNode modifiedList(int[] nums, ListNode head) {
        // Step 1: Store nums in a HashSet for O(1) lookups
        HashSet<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        // Step 2: Create a dummy node to simplify the removal of head nodes
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Step 3: Traverse the list and remove nodes
        ListNode current = dummy;

        while (current.next != null) {
            if (numSet.contains(current.next.val)) {
                // Remove the node by skipping it
                current.next = current.next.next;
            } else {
                // Move to the next node
                current = current.next;
            }
        }

        // Return the new head of the list
        return dummy.next;
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

//Explanation:
//HashSet Creation: We create a HashSet from the nums array to store the values that need to be removed.
//Dummy Node: A dummy node (dummy) is used to handle cases where the head of the list needs to be removed. The
// dummy node's next pointer initially points to the head of the linked list.
//Traversal and Removal: We traverse the linked list starting from the dummy node. If the next node’s value is in
// the HashSet, we skip that node by adjusting the next pointer. Otherwise, we move to the next node.
//Return the Modified List: After the traversal, we return dummy.next, which is the new head of the modified list.
//Time Complexity:
//Creating the HashSet takes O(n), where n is the number of elements in nums.
//Traversing the linked list takes O(m), where m is the number of nodes in the linked list.
//Thus, the overall time complexity is O(n + m).
//Space Complexity:
//O(n) is required for storing the elements of nums in the HashSet.


//You are given an array of integers nums and the head of a linked list. Return the head of the modified linked
// list after removing all nodes from the linked list that have a value that exists in nums.
//
//Example 1:
//Input: nums = [1,2,3], head = [1,2,3,4,5]
//Output: [4,5]
//Explanation:
//image here
//Remove the nodes with values 1, 2, and 3.
//
//Example 2:
//Input: nums = [1], head = [1,2,1,2,1,2]
//Output: [2,2,2]
//Explanation:
//image here
//Remove the nodes with value 1.
//
//Example 3:
//Input: nums = [5], head = [1,2,3,4]
//Output: [1,2,3,4]
//Explanation:
//image here
//No node has value 5.
//
//Constraints:
//1 <= nums.length <= 105
//1 <= nums[i] <= 105
//All elements in nums are unique.
//The number of nodes in the given list is in the range [1, 105].
//1 <= Node.val <= 105
//The input is generated such that there is at least one node in the linked list that has a value not present in nums.