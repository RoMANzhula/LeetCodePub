package P1701_1800.P1793_Maximum_Score_of_a_Good_Subarray;

import static java.lang.Integer.max;
import static java.lang.Integer.min;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 4, 3, 7, 4, 5};
        int k1 = 3;
        System.out.println(solution.maxGoodSubarrayScore(nums1, k1)); // Output: 15

        int[] nums2 = {5, 5, 4, 5, 4, 1, 1, 1};
        int k2 = 0;
        System.out.println(solution.maxGoodSubarrayScore(nums2, k2)); // output: 20
    }

    public int maxGoodSubarrayScore(int[] nums, int k) {
        int minimumNumber = nums[k];
        int result = minimumNumber;

        //indexes for moving on the right and on the left side from position "k"
        int i = k;
        int j = k;

        while (i > 0 || j < nums.length - 1) { //while the end or begin of array comes
            if (i == 0 || (j+1 < nums.length && nums[i-1] <= nums[j+1])) {
                j++;
                minimumNumber = min(minimumNumber, nums[j]);
            } else {
                i--;
                minimumNumber = min(minimumNumber, nums[i]);
            }
            result = max(result, minimumNumber * (j - i + 1));
        }

        return result; //bingo
    }

}

//You are given an array of integers nums (0-indexed) and an integer k.
//The score of a subarray (i, j) is defined as min(nums[i], nums[i+1], ..., nums[j]) * (j - i + 1). A good subarray is
// a subarray where i <= k <= j.
//Return the maximum possible score of a good subarray.

//Example 1:
//Input: nums = [1,4,3,7,4,5], k = 3
//Output: 15
//Explanation: The optimal subarray is (1, 5) with a score of min(4,3,7,4,5) * (5-1+1) = 3 * 5 = 15.

//Example 2:
//Input: nums = [5,5,4,5,4,1,1,1], k = 0
//Output: 20
//Explanation: The optimal subarray is (0, 4) with a score of min(5,5,4,5,4) * (4-0+1) = 4 * 5 = 20.

//Constraints:
//1 <= nums.length <= 105
//1 <= nums[i] <= 2 * 104
//0 <= k < nums.length
