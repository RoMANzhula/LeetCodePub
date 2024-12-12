package P2501_2600.P2558_Take_Gifts_From_the_Richest_Pile;

import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] gifts1 = {25, 64, 9, 4, 100};
        int k1 = 4;
        System.out.println(solution.pickGifts(gifts1, k1)); // Output: 29

        int[] gifts2 = {1, 1, 1, 1};
        int k2 = 4;
        System.out.println(solution.pickGifts(gifts2, k2)); // Output: 4
    }

    public long pickGifts(int[] gifts, int k) {
        // Create a max-heap (using negative values to simulate max-heap)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        // Add all the gifts to the heap
        for (int gift : gifts) {
            maxHeap.add(gift);
        }

        // Perform k operations
        for (int i = 0; i < k; i++) {
            if (maxHeap.isEmpty()) break;

            // Remove the pile with the maximum gifts
            int maxGifts = maxHeap.poll();

            // Calculate the remaining gifts after taking the square root
            int remaining = (int) Math.floor(Math.sqrt(maxGifts));

            // Add the remaining gifts back to the heap
            maxHeap.add(remaining);
        }

        // Calculate the sum of all remaining gifts
        long totalRemaining = 0;
        while (!maxHeap.isEmpty()) {
            totalRemaining += maxHeap.poll();
        }

        return totalRemaining;
    }
}

//Explanation of the Code
//Priority Queue for Max-Heap:
//We use PriorityQueue with a custom comparator to simulate a max-heap.
//Heap Initialization:
//-All the pile sizes are added to the heap.
//Simulate k Operations:
//-For each second, remove the largest pile (poll the max element).
//-Compute the floor of its square root and add it back to the heap.
//Sum Remaining Gifts:
//-After k operations, sum up all the values remaining in the heap.
//Complexity
//Time Complexity:
//Adding to the heap: O(n log n), where n is the number of piles.
//Each operation: O(k log n).
//Total: O((n+k) log n).
//Space Complexity: O(n) for the heap.


//You are given an integer array gifts denoting the number of gifts in various piles. Every second, you do the following:
//Choose the pile with the maximum number of gifts.
//If there is more than one pile with the maximum number of gifts, choose any.
//Leave behind the floor of the square root of the number of gifts in the pile. Take the rest of the gifts.
//Return the number of gifts remaining after k seconds.
//
//Example 1:
//Input: gifts = [25,64,9,4,100], k = 4
//Output: 29
//Explanation:
//The gifts are taken in the following way:
//- In the first second, the last pile is chosen and 10 gifts are left behind.
//- Then the second pile is chosen and 8 gifts are left behind.
//- After that the first pile is chosen and 5 gifts are left behind.
//- Finally, the last pile is chosen again and 3 gifts are left behind.
//The final remaining gifts are [5,8,9,4,3], so the total number of gifts remaining is 29.

//Example 2:
//Input: gifts = [1,1,1,1], k = 4
//Output: 4
//Explanation:
//In this case, regardless which pile you choose, you have to leave behind 1 gift in each pile.
//That is, you can't take any pile with you.
//So, the total gifts remaining are 4.
//
//Constraints:
//1 <= gifts.length <= 103
//1 <= gifts[i] <= 109
//1 <= k <= 103