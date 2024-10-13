package P601_700.P632_Smallest_Range_Covering_Elements_from_K_Lists;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        List<List<Integer>> nums1 = Arrays.asList(
                Arrays.asList(4, 10, 15, 24, 26),
                Arrays.asList(0, 9, 12, 20),
                Arrays.asList(5, 18, 22, 30)
        );
        System.out.println(Arrays.toString(solution.smallestRange(nums1))); // Output: [20, 24]

        List<List<Integer>> nums2 = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(1, 2, 3),
                Arrays.asList(1, 2, 3)
        );
        System.out.println(Arrays.toString(solution.smallestRange(nums2))); // Output: [1, 1]
    }

    public int[] smallestRange(List<List<Integer>> nums) {
        // Min-heap to store the current smallest element from each list
        PriorityQueue<Element> minHeap = new PriorityQueue<>(Comparator.comparingInt(e -> e.value));

        int currentMax = Integer.MIN_VALUE; // Keep track of the current maximum element
        int startRange = 0, endRange = Integer.MAX_VALUE;

        // Initialize the heap with the first element of each list
        for (int i = 0; i < nums.size(); i++) {
            int value = nums.get(i).get(0);
            minHeap.offer(new Element(value, i, 0));
            currentMax = Math.max(currentMax, value); // Update the maximum value
        }

        // Process the heap until one of the lists is exhausted
        while (minHeap.size() == nums.size()) {
            Element minElement = minHeap.poll(); // Get the smallest element from the heap
            int currentMin = minElement.value;

            // Update the range if it's smaller
            if (currentMax - currentMin < endRange - startRange ||
                    (currentMax - currentMin == endRange - startRange && currentMin < startRange)) {
                startRange = currentMin;
                endRange = currentMax;
            }

            // Move to the next element in the same list as the minElement
            if (minElement.elementIndex + 1 < nums.get(minElement.listIndex).size()) {
                int nextValue = nums.get(minElement.listIndex).get(minElement.elementIndex + 1);
                minHeap.offer(new Element(nextValue, minElement.listIndex, minElement.elementIndex + 1));
                currentMax = Math.max(currentMax, nextValue); // Update the current maximum
            } else {
                break; // If one list is exhausted, we can't cover all lists anymore
            }
        }

        return new int[]{startRange, endRange};
    }
}

class Element {
    int value;
    int listIndex;
    int elementIndex;

    Element(int value, int listIndex, int elementIndex) {
        this.value = value;
        this.listIndex = listIndex;
        this.elementIndex = elementIndex;
    }
}

//Explanation:
//Priority Queue (Min-Heap): We use the heap to always pull the smallest element from the current window. The
// window is formed by taking one element from each list.
//Sliding Window: By pulling the smallest element and pushing the next one from the same list, we effectively
// slide the window across the k lists. The max element is tracked to calculate the range at each step.
//Updating the Range: Every time we find a new valid range (covering one element from each list), we compare it
// to the smallest found range and update accordingly.
//Time Complexity:
//Building the initial heap takes O(k log k), where k is the number of lists.
//Each insertion and deletion from the heap takes O(log k).
//In total, the complexity is approximately
//O(n log k), where n is the total number of elements across all lists.


//You have k lists of sorted integers in non-decreasing order. Find the smallest range that includes at
// least one number from each of the k lists.
//We define the range [a, b] is smaller than range [c, d] if b - a < d - c or a < c if b - a == d - c.
//
//Example 1:
//Input: nums = [[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]
//Output: [20,24]
//Explanation:
//List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
//List 2: [0, 9, 12, 20], 20 is in range [20,24].
//List 3: [5, 18, 22, 30], 22 is in range [20,24].

//Example 2:
//Input: nums = [[1,2,3],[1,2,3],[1,2,3]]
//Output: [1,1]
//
//Constraints:
//nums.length == k
//1 <= k <= 3500
//1 <= nums[i].length <= 50
//-105 <= nums[i][j] <= 105
//nums[i] is sorted in non-decreasing order.