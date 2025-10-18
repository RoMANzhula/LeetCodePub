package P3301_3400.P3397_Maximum_Number_of_Distinct_Elements_After_Operations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.maxDistinctElements(new int[]{1, 2, 2, 3, 3, 4}, 2)); // 6
        System.out.println(solution.maxDistinctElements(new int[]{4, 4, 4, 4}, 1));       // 3
    }

    public int maxDistinctElements(int[] nums, int k) {
        Arrays.sort(nums);
        Set<Long> used = new HashSet<>();
        long nextAvailable = Long.MIN_VALUE;
        int count = 0;

        for (int num : nums) {
            long low = (long) num - k;
            long high = (long) num + k;

            // start from max(low, nextAvailable)
            long assign = Math.max(low, nextAvailable);

            if (assign <= high) {
                used.add(assign);
                count++;
                nextAvailable = assign + 1; // move pointer
            }
        }

        return count;
    }

}

//Complexity:
// time - O(n log n)
// space - O(n)


//You are given an integer array nums and an integer k.
//You are allowed to perform the following operation on each element of the array at most once:
//Add an integer in the range [-k, k] to the element.
//Return the maximum possible number of distinct elements in nums after performing the operations.

//Example 1:
//Input: nums = [1,2,2,3,3,4], k = 2
//Output: 6
//Explanation:
//nums changes to [-1, 0, 1, 2, 3, 4] after performing operations on the first four elements.

//Example 2:
//Input: nums = [4,4,4,4], k = 1
//Output: 3
//Explanation:
//By adding -1 to nums[0] and 1 to nums[1], nums changes to [3, 5, 4, 4].

//Constraints:
//1 <= nums.length <= 105
//1 <= nums[i] <= 109
//0 <= k <= 109
