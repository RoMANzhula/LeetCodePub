package P2001_2100.P2058_Find_the_Minimum_and_Maximum_Number_of_Nodes_Between_Critical_Points;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        // Example 1
        int[] input1 = {3, 1};
        ListNode head1 = createLinkedList(input1);
        int[] result1 = solution.nodesBetweenCriticalPoints(head1);
        System.out.println("Example 1: " + java.util.Arrays.toString(result1)); // Output: [-1, -1]

        // Example 2
        int[] input2 = {5, 3, 1, 2, 5, 1, 2};
        ListNode head2 = createLinkedList(input2);
        int[] result2 = solution.nodesBetweenCriticalPoints(head2);
        System.out.println("Example 2: " + java.util.Arrays.toString(result2)); // Output: [1, 3]

        // Example 3
        int[] input3 = {1, 3, 2, 2, 3, 2, 2, 2, 7};
        ListNode head3 = createLinkedList(input3);
        int[] result3 = solution.nodesBetweenCriticalPoints(head3);
        System.out.println("Example 3: " + java.util.Arrays.toString(result3)); // Output: [3, 3]
    }

//    public int[] nodesBetweenCriticalPoints(ListNode head) {
//        List<Integer> criticalPoints = new ArrayList<>();
//
//        ListNode prev = null;
//        ListNode current = head;
//        int index = 0;
//
//        while (current != null && current.next != null) {
//            if (prev != null && current.next != null) {
//                if ((current.val > prev.val && current.val > current.next.val) ||
//                        (current.val < prev.val && current.val < current.next.val)) {
//                    criticalPoints.add(index);
//                }
//            }
//            prev = current;
//            current = current.next;
//            index++;
//        }
//
//        if (criticalPoints.size() < 2) {
//            return new int[]{-1, -1};
//        }
//
//        int minDistance = Integer.MAX_VALUE;
//        int maxDistance = criticalPoints.get(criticalPoints.size() - 1) - criticalPoints.get(0);
//
//        for (int i = 1; i < criticalPoints.size(); i++) {
//            int distance = criticalPoints.get(i) - criticalPoints.get(i - 1);
//            minDistance = Math.min(minDistance, distance);
//        }
//
//        return new int[]{minDistance, maxDistance};
//    }

    //faster solution
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        ListNode prev = head;
        ListNode current = head.next;
        int index = 1;
        int firstCriticalPoint = -1;
        int lastCriticalPoint = -1;
        int minDistance = Integer.MAX_VALUE;

        while (current.next != null) {
            if ((current.val > prev.val && current.val > current.next.val) ||
                    (current.val < prev.val && current.val < current.next.val)) {
                if (firstCriticalPoint == -1) {
                    firstCriticalPoint = index;
                } else {
                    minDistance = Math.min(minDistance, index - lastCriticalPoint);
                }
                lastCriticalPoint = index;
            }
            prev = current;
            current = current.next;
            index++;
        }

        if (firstCriticalPoint == lastCriticalPoint) {
            return new int[]{-1, -1};
        }

        int maxDistance = lastCriticalPoint - firstCriticalPoint;
        return new int[]{minDistance, maxDistance};
    }

    // Helper method to create a linked list from an array
    public static ListNode createLinkedList(int[] arr) {
        if (arr.length == 0) return null;
        ListNode head = new ListNode(arr[0]);
        ListNode current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
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

//Explanation of Optimizations:
//Single Traversal:
//The list is traversed only once.
//During the traversal, the code identifies critical points and immediately calculates the minimum and
// maximum distances between them.
//Immediate Calculation:
//Instead of storing all critical points in a list and then iterating through that list, the code keeps track
// of the first and last critical points.
//The minimum distance is updated whenever a new critical point is found, by comparing it to
// the previous critical point.
//The maximum distance is calculated at the end by subtracting the index of the first critical point
// from the index of the last critical point.
//This approach reduces the time complexity and improves efficiency by minimizing the number of
// iterations and operations.


//A critical point in a linked list is defined as either a local maxima or a local minima.
//A node is a local maxima if the current node has a value strictly greater than the previous node and the next node.
//A node is a local minima if the current node has a value strictly smaller than the previous node and the next node.
//Note that a node can only be a local maxima/minima if there exists both a previous node and a next node.
//Given a linked list head, return an array of length 2 containing [minDistance, maxDistance] where minDistance is
// the minimum distance between any two distinct critical points and maxDistance is the maximum distance between any
// two distinct critical points. If there are fewer than two critical points, return [-1, -1].
//
//Example 1:
//Input: head = [3,1]
//Output: [-1,-1]
//Explanation: There are no critical points in [3,1].

//Example 2:
//Input: head = [5,3,1,2,5,1,2]
//Output: [1,3]
//Explanation: There are three critical points:
//- [5,3,1,2,5,1,2]: The third node is a local minima because 1 is less than 3 and 2.
//- [5,3,1,2,5,1,2]: The fifth node is a local maxima because 5 is greater than 2 and 1.
//- [5,3,1,2,5,1,2]: The sixth node is a local minima because 1 is less than 5 and 2.
//The minimum distance is between the fifth and the sixth node. minDistance = 6 - 5 = 1.
//The maximum distance is between the third and the sixth node. maxDistance = 6 - 3 = 3.

//Example 3:
//Input: head = [1,3,2,2,3,2,2,2,7]
//Output: [3,3]
//Explanation: There are two critical points:
//- [1,3,2,2,3,2,2,2,7]: The second node is a local maxima because 3 is greater than 1 and 2.
//- [1,3,2,2,3,2,2,2,7]: The fifth node is a local maxima because 3 is greater than 2 and 2.
//Both the minimum and maximum distances are between the second and the fifth node.
//Thus, minDistance and maxDistance is 5 - 2 = 3.
//Note that the last node is not considered a local maxima because it does not have a next node.
//
//Constraints:
//The number of nodes in the list is in the range [2, 105].
//1 <= Node.val <= 105