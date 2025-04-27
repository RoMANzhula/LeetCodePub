package P3301_3400.P3392_Count_Subarrays_of_Length_Three_With_a_Condition;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 2, 1, 4, 1};
        System.out.println(solution.countSubarrays(nums1)); // Output: 1

        int[] nums2 = {1, 1, 1};
        System.out.println(solution.countSubarrays(nums2)); // Output: 0
    }

    public int countSubarrays(int[] nums) {
        int count = 0;

        for (int i = 0; i <= nums.length - 3; i++) {
            int first = nums[i];
            int middle = nums[i + 1];
            int third = nums[i + 2];

            if (first + third == middle / 2 && middle % 2 == 0) count++;
        }

        return count;
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//Given an integer array nums, return the number of subarrays of length 3 such that the sum of the first and third
// numbers equals exactly half of the second number.

//Example 1:
//Input: nums = [1,2,1,4,1]
//Output: 1
//Explanation:
//Only the subarray [1,4,1] contains exactly 3 elements where the sum of the first and third numbers equals half
// the middle number.

//Example 2:
//Input: nums = [1,1,1]
//Output: 0
//Explanation:
//[1,1,1] is the only subarray of length 3. However, its first and third numbers do not add to half the middle number.

//Constraints:
//3 <= nums.length <= 100
//-100 <= nums[i] <= 100
