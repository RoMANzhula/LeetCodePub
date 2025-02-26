package P1701_1800.P1749_Maximum_Absolute_of_Any_Subarray;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, -3, 2, 3, -4};
        int[] nums2 = {2, -5, 1, -4, 3, -2};

        System.out.println(solution.maxAbsoluteSum(nums1)); // Output: 5
        System.out.println(solution.maxAbsoluteSum(nums2)); // Output: 8
    }

    public int maxAbsoluteSum(int[] nums) {
        int maxSum = 0, minSum = 0;
        int currentMax = 0, currentMin = 0;

        for (int num : nums) {
            currentMax = Math.max(num, currentMax + num);
            maxSum = Math.max(maxSum, currentMax);

            currentMin = Math.min(num, currentMin + num);
            minSum = Math.min(minSum, currentMin);
        }

        return Math.max(maxSum, Math.abs(minSum));
    }

}

//Explanation:
//currentMax keeps track of the running maximum sum of a subarray.
//currentMin keeps track of the running minimum sum of a subarray.
//maxSum stores the maximum subarray sum.
//minSum stores the minimum subarray sum.
//We return Math.max(maxSum, Math.abs(minSum)).
//Complexity:
//Time Complexity: O(n) → We traverse the array once.
//Space Complexity: O(1) → We use only a few integer variables.


//You are given an integer array nums. The absolute sum of a subarray [numsl, numsl+1, ..., numsr-1, numsr] is
// abs(numsl + numsl+1 + ... + numsr-1 + numsr).
//Return the maximum absolute sum of any (possibly empty) subarray of nums.
//Note that abs(x) is defined as follows:
//If x is a negative integer, then abs(x) = -x.
//If x is a non-negative integer, then abs(x) = x.
//
//Example 1:
//Input: nums = [1,-3,2,3,-4]
//Output: 5
//Explanation: The subarray [2,3] has absolute sum = abs(2+3) = abs(5) = 5.

//Example 2:
//Input: nums = [2,-5,1,-4,3,-2]
//Output: 8
//Explanation: The subarray [-5,1,-4] has absolute sum = abs(-5+1-4) = abs(-8) = 8.
//
//Constraints:
//1 <= nums.length <= 105
//-104 <= nums[i] <= 104
