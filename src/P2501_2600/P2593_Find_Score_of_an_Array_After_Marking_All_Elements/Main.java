package P2501_2600.P2593_Find_Score_of_an_Array_After_Marking_All_Elements;

import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();


        int[] nums1 = {2, 1, 3, 4, 5, 2};
        System.out.println(solution.findScore(nums1)); // Output: 7

        int[] nums2 = {2, 3, 5, 1, 3, 2};
        System.out.println(solution.findScore(nums2)); // Output: 5
    }

    public long findScore(int[] nums) {
        int n = nums.length;
        boolean[] marked = new boolean[n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        // Add all elements with their indices to the priority queue
        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{nums[i], i});
        }

        long score = 0;

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int value = current[0];
            int index = current[1];

            // If the current element is already marked, skip it
            if (marked[index]) continue;

            // Add value to the score
            score += value;

            // Mark the current element and its two adjacent elements if they exist
            marked[index] = true;
            if (index > 0) marked[index - 1] = true;
            if (index < n - 1) marked[index + 1] = true;
        }

        return score;
    }
}

//Explanation of the Code:
//-Priority Queue: Stores elements as pairs of value and index, sorted first by value and then by index.
//-Marking Logic: When an element is selected, its adjacent elements (if within bounds) are marked.
//Efficiency:
//Inserting into and removing from the priority queue is O(log n).
//Processing all elements results in a time complexity of O(n log n).


//You are given an array nums consisting of positive integers.
//Starting with score = 0, apply the following algorithm:
//Choose the smallest integer of the array that is not marked. If there is a tie, choose the one with the smallest index.
//Add the value of the chosen integer to score.
//Mark the chosen element and its two adjacent elements if they exist.
//Repeat until all the array elements are marked.
//Return the score you get after applying the above algorithm.
//
//Example 1:
//Input: nums = [2,1,3,4,5,2]
//Output: 7
//Explanation: We mark the elements as follows:
//- 1 is the smallest unmarked element, so we mark it and its two adjacent elements: [2,1,3,4,5,2].
//- 2 is the smallest unmarked element, so we mark it and its left adjacent element: [2,1,3,4,5,2].
//- 4 is the only remaining unmarked element, so we mark it: [2,1,3,4,5,2].
//Our score is 1 + 2 + 4 = 7.

//Example 2:
//Input: nums = [2,3,5,1,3,2]
//Output: 5
//Explanation: We mark the elements as follows:
//- 1 is the smallest unmarked element, so we mark it and its two adjacent elements: [2,3,5,1,3,2].
//- 2 is the smallest unmarked element, since there are two of them, we choose the left-most one, so we mark the
// one at index 0 and its right adjacent element: [2,3,5,1,3,2].
//- 2 is the only remaining unmarked element, so we mark it: [2,3,5,1,3,2].
//Our score is 1 + 2 + 2 = 5.
//
//Constraints:
//1 <= nums.length <= 105
//1 <= nums[i] <= 106
