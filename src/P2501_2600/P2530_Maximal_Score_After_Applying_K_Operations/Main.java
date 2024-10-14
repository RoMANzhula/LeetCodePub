package P2501_2600.P2530_Maximal_Score_After_Applying_K_Operations;

import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

    }

    public long maxKelements(int[] nums, int k) {
        // Using PriorityQueue (Max-Heap by storing negative values)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        // Add all elements to the max-heap
        for (int num : nums) {
            maxHeap.add(num);
        }

        long score = 0;

        // Perform k operations
        for (int i = 0; i < k; i++) {
            // Get the largest number
            int maxVal = maxHeap.poll();

            // Add it to the score
            score += maxVal;

            // Compute the new value (ceil(maxVal / 3)) and push it back to the heap
            int newVal = (int) Math.ceil(maxVal / 3.0);
            maxHeap.add(newVal);
        }

        return score;
    }
}

//You are given a 0-indexed integer array nums and an integer k. You have a starting score of 0.
//In one operation:
//choose an index i such that 0 <= i < nums.length,
//increase your score by nums[i], and
//replace nums[i] with ceil(nums[i] / 3).
//Return the maximum possible score you can attain after applying exactly k operations.
//The ceiling function ceil(val) is the least integer greater than or equal to val.
//
//Example 1:
//Input: nums = [10,10,10,10,10], k = 5
//Output: 50
//Explanation: Apply the operation to each array element exactly once. The final score is 10 + 10 + 10 + 10 + 10 = 50.

//Example 2:
//Input: nums = [1,10,3,3,3], k = 3
//Output: 17
//Explanation: You can do the following operations:
//Operation 1: Select i = 1, so nums becomes [1,4,3,3,3]. Your score increases by 10.
//Operation 2: Select i = 1, so nums becomes [1,2,3,3,3]. Your score increases by 4.
//Operation 3: Select i = 2, so nums becomes [1,1,1,3,3]. Your score increases by 3.
//The final score is 10 + 4 + 3 = 17.
//
//Constraints:
//1 <= nums.length, k <= 105
//1 <= nums[i] <= 109


//Explanation:
//Priority Queue: We use a max-heap to keep track of the largest element efficiently.
//Main Loop: For k operations, we:
//Pop the largest element (most negative in our case because we're using negative values).
//Add it to the score.
//Push back the result of dividing the largest element by 3 and taking the ceiling.
//Efficiency: Each operation (pop and push) is O(log n), so the time complexity is O(k log n).
