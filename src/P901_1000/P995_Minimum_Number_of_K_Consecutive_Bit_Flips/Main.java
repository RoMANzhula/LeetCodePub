package P901_1000.P995_Minimum_Number_of_K_Consecutive_Bit_Flips;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {0, 1, 0};
        int k1 = 1;
        System.out.println(solution.minKBitFlips(nums1, k1)); // Output: 2

        int[] nums2 = {1, 1, 0};
        int k2 = 2;
        System.out.println(solution.minKBitFlips(nums2, k2)); // Output: -1

        int[] nums3 = {0, 0, 0, 1, 0, 1, 1, 0};
        int k3 = 3;
        System.out.println(solution.minKBitFlips(nums3, k3)); // Output: 3
    }

    public int minKBitFlips(int[] nums, int k) {
        int n = nums.length;
        int[] flipCount = new int[n];
        int flips = 0;
        int is_flipped = 0;

        for (int i = 0; i < n; i++) {
            if (i >= k) {
                is_flipped ^= flipCount[i - k];
            }

            if (nums[i] == is_flipped) {
                if (i + k > n) {
                    return -1;
                }
                flips++;
                is_flipped ^= 1;
                flipCount[i] = 1;
            }
        }

        return flips;
    }
}

//Explanation:
//flipCount array: Keeps track of where flips start.
//is_flipped: Tracks the current flip state. It toggles between 0 and 1.
//Main Loop:
//If nums[i] equals is_flipped, it means we need to flip starting from index i.
//If flipping at index i would go out of bounds (i + k > n), return -1 as it's impossible to flip the subarray.
//Update the flip status and the flipCount array to reflect the new flip.
//This algorithm ensures that we flip only when necessary and keeps track of the flipping state efficiently to
// minimize the number of operations and checks.


//You are given a binary array nums and an integer k.
//A k-bit flip is choosing a subarray of length k from nums and simultaneously changing every 0 in the subarray
// to 1, and every 1 in the subarray to 0.
//Return the minimum number of k-bit flips required so that there is no 0 in the array. If it is not
// possible, return -1.
//A subarray is a contiguous part of an array.
//
//Example 1:
//Input: nums = [0,1,0], k = 1
//Output: 2
//Explanation: Flip nums[0], then flip nums[2].

//Example 2:
//Input: nums = [1,1,0], k = 2
//Output: -1
//Explanation: No matter how we flip subarrays of size 2, we cannot make the array become [1,1,1].

//Example 3:
//Input: nums = [0,0,0,1,0,1,1,0], k = 3
//Output: 3
//Explanation:
//Flip nums[0],nums[1],nums[2]: nums becomes [1,1,1,1,0,1,1,0]
//Flip nums[4],nums[5],nums[6]: nums becomes [1,1,1,1,1,0,0,0]
//Flip nums[5],nums[6],nums[7]: nums becomes [1,1,1,1,1,1,1,1]
//
//Constraints:
//1 <= nums.length <= 105
//1 <= k <= nums.length