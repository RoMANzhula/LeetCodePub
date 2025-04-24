package P2701_2800.P2799_Count_Complete_Subarrays_in_an_Array;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 3, 1, 2, 2};
        System.out.println(solution.countCompleteSubarrays(nums1)); // Output: 4

        int[] nums2 = {5, 5, 5, 5};
        System.out.println(solution.countCompleteSubarrays(nums2)); // Output: 10
    }

    public int countCompleteSubarrays(int[] nums) {
        int count = 0;
        int len = nums.length;
        int totalDistinct = countDistinct(nums);

        for (int start = 0; start < len; start++) {
            Set<Integer> seen = new HashSet<>();

            for (int end = start; end < len; end++) {
                seen.add(nums[end]);

                if (seen.size() == totalDistinct) count++;
            }
        }

        return count;
    }

    private int countDistinct(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        return set.size();
    }
}

//Explanation:
//countDistinct() gets the number of unique elements in the array.
//For each start index, we go through all end positions to form subarrays.
//Use a Set to count how many distinct numbers are in the current subarray.
//Once it matches the total distinct count, it's a "complete subarray".
//Complexity:
// time - O(n^2)
// space - O(k), k is the number of distinct elements in nums


//You are given an array nums consisting of positive integers.
//We call a subarray of an array complete if the following condition is satisfied:
//The number of distinct elements in the subarray is equal to the number of distinct elements in the whole array.
//Return the number of complete subarrays.
//A subarray is a contiguous non-empty part of an array.

//Example 1:
//Input: nums = [1,3,1,2,2]
//Output: 4
//Explanation: The complete subarrays are the following: [1,3,1,2], [1,3,1,2,2], [3,1,2] and [3,1,2,2].

//Example 2:
//Input: nums = [5,5,5,5]
//Output: 10
//Explanation: The array consists only of the integer 5, so any subarray is complete. The number of subarrays that
// we can choose is 10.

//Constraints:
//1 <= nums.length <= 1000
//1 <= nums[i] <= 2000
