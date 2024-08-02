package P2101_2200.P2134_Minimum_Swaps_to_Group_All_1s_Together_ll;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {0, 1, 0, 1, 1, 0, 0};
        int[] nums2 = {0, 1, 1, 1, 0, 0, 1, 1, 0};
        int[] nums3 = {1, 1, 0, 0, 1};

        System.out.println(solution.minSwaps(nums1)); // Output: 1
        System.out.println(solution.minSwaps(nums2)); // Output: 2
        System.out.println(solution.minSwaps(nums3)); // Output: 0
    }

//    public int minSwaps(int[] nums) {
//        int n = nums.length;
//        int totalOnes = 0;
//
//        // Count total number of 1s in the array
//        for (int num : nums) {
//            totalOnes += (num == 1) ? 1 : 0;
//        }
//
//        // If there are no 1s or the entire array is 1s, no swaps are needed
//        if (totalOnes == 0 || totalOnes == n) {
//            return 0;
//        }
//
//        // Create a new array to simulate the circular nature
//        int[] extendedNums = new int[2 * n];
//        for (int i = 0; i < n; i++) {
//            extendedNums[i] = nums[i];
//            extendedNums[i + n] = nums[i];
//        }
//
//        // Use sliding window to find the minimum number of 0s in a window of size totalOnes
//        int minZeros = Integer.MAX_VALUE;
//        int currentZeros = 0;
//
//        // Initialize the first window
//        for (int i = 0; i < totalOnes; i++) {
//            if (extendedNums[i] == 0) {
//                currentZeros++;
//            }
//        }
//        minZeros = currentZeros;
//
//        // Slide the window over the extended array
//        for (int i = totalOnes; i < 2 * n; i++) {
//            // Remove the element that is sliding out
//            if (extendedNums[i - totalOnes] == 0) {
//                currentZeros--;
//            }
//            // Add the new element that is sliding in
//            if (extendedNums[i] == 0) {
//                currentZeros++;
//            }
//            // Update the minimum number of zeros found
//            minZeros = Math.min(minZeros, currentZeros);
//        }
//
//        // The minimum number of swaps required to group all 1s together
//        return minZeros;
//    }

    //faster solution
    public int minSwaps(int[] nums) {
        int n = nums.length;
        int totalOnes = 0;

        // Count total number of 1s in the array
        for (int num : nums) {
            totalOnes += num;
        }

        // If there are no 1s or the entire array is 1s, no swaps are needed
        if (totalOnes == 0 || totalOnes == n) {
            return 0;
        }

        // Initialize the first window of size totalOnes
        int currentZeros = 0;
        for (int i = 0; i < totalOnes; i++) {
            if (nums[i] == 0) {
                currentZeros++;
            }
        }
        int minZeros = currentZeros;

        // Slide the window over the array
        for (int i = totalOnes; i < n + totalOnes; i++) {
            if (nums[i % n] == 0) {
                currentZeros++;
            }
            if (nums[(i - totalOnes) % n] == 0) {
                currentZeros--;
            }
            minZeros = Math.min(minZeros, currentZeros);
        }

        // The minimum number of swaps required to group all 1s together
        return minZeros;
    }

}

//Explanation:
//Count Total Ones: Calculate the total number of 1s in the array to determine the size of the window.
//Initial Window: Count the number of zeros in the initial window of size totalOnes.
//Sliding Window: Slide the window across the array. For each new position, update the number of zeros in the
// window by adding the new element coming into the window and removing the element going out of the window, using
// modulo operation to handle the circular nature.
//Result: The minimum number of zeros encountered in any window gives the minimum number of swaps needed to group
// all 1s together.
//This approach is more efficient because it avoids the need to extend the array and directly uses the circular
// property with modulo operations, resulting in a time complexity of O(n).


//A swap is defined as taking two distinct positions in an array and swapping the values in them.
//A circular array is defined as an array where we consider the first element and the last element to be adjacent.
//Given a binary circular array nums, return the minimum number of swaps required to group all 1's present in the
// array together at any location.
//
//Example 1:
//Input: nums = [0,1,0,1,1,0,0]
//Output: 1
//Explanation: Here are a few of the ways to group all the 1's together:
//[0,0,1,1,1,0,0] using 1 swap.
//[0,1,1,1,0,0,0] using 1 swap.
//[1,1,0,0,0,0,1] using 2 swaps (using the circular property of the array).
//There is no way to group all 1's together with 0 swaps.
//Thus, the minimum number of swaps required is 1.

//Example 2:
//Input: nums = [0,1,1,1,0,0,1,1,0]
//Output: 2
//Explanation: Here are a few of the ways to group all the 1's together:
//[1,1,1,0,0,0,0,1,1] using 2 swaps (using the circular property of the array).
//[1,1,1,1,1,0,0,0,0] using 2 swaps.
//There is no way to group all 1's together with 0 or 1 swaps.
//Thus, the minimum number of swaps required is 2.

//Example 3:
//Input: nums = [1,1,0,0,1]
//Output: 0
//Explanation: All the 1's are already grouped together due to the circular property of the array.
//Thus, the minimum number of swaps required is 0.
//
//Constraints:
//1 <= nums.length <= 105
//nums[i] is either 0 or 1.