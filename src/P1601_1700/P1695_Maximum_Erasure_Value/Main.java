package P1601_1700.P1695_Maximum_Erasure_Value;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.maximumUniqueSubarray(new int[]{4, 2, 4, 5, 6})); // Output: 17
        System.out.println(solution.maximumUniqueSubarray(new int[]{5, 2, 1, 2, 5, 2, 1, 2, 5})); // Output: 8
    }

    public int maximumUniqueSubarray(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int maxSum = 0;
        int currentSum = 0;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            while (set.contains(nums[right])) {
                set.remove(nums[left]);
                currentSum -= nums[left];
                left++;
            }

            set.add(nums[right]);
            currentSum += nums[right];
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

}

//Complexity:
// time and space - O(n)


//You are given an array of positive integers nums and want to erase a subarray containing unique elements. The
// score you get by erasing the subarray is equal to the sum of its elements.
//Return the maximum score you can get by erasing exactly one subarray.
//An array b is called to be a subarray of a if it forms a contiguous subsequence of a, that is, if it is
// equal to a[l],a[l+1],...,a[r] for some (l,r).

//Example 1:
//Input: nums = [4,2,4,5,6]
//Output: 17
//Explanation: The optimal subarray here is [2,4,5,6].

//Example 2:
//Input: nums = [5,2,1,2,5,2,1,2,5]
//Output: 8
//Explanation: The optimal subarray here is [5,2,1] or [1,2,5].

//Constraints:
//1 <= nums.length <= 105
//1 <= nums[i] <= 104
