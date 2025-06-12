package P3401_3500.P3423_Maximum_Difference_Between_Adjecent_Elements_in_a_Circular_Array;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 2, 4};
        int[] nums2 = {-5, -10, -5};

        System.out.println("Output: " + solution.maxAdjacentDistance(nums1)); // Output: 3
        System.out.println("Output: " + solution.maxAdjacentDistance(nums2)); // Output: 5
    }

    public int maxAdjacentDistance(int[] nums) {
        int maxDiff = 0;
        int n = nums.length;

        // traverse each pair of adjacent elements (circularly)
        for (int i = 0; i < n; i++) {
            int nextIndex = (i + 1) % n; // circular: after last index goes to 0
            int diff = Math.abs(nums[i] - nums[nextIndex]);

            maxDiff = Math.max(maxDiff, diff);
        }

        return maxDiff;
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//Given a circular array nums, find the maximum absolute difference between adjacent elements.
//Note: In a circular array, the first and last elements are adjacent.

//Example 1:
//Input: nums = [1,2,4]
//Output: 3
//Explanation:
//Because nums is circular, nums[0] and nums[2] are adjacent. They have the maximum absolute difference of |4 - 1| = 3.

//Example 2:
//Input: nums = [-5,-10,-5]
//Output: 5
//Explanation:
//The adjacent elements nums[0] and nums[1] have the maximum absolute difference of |-5 - (-10)| = 5.

//Constraints:
//2 <= nums.length <= 100
//-100 <= nums[i] <= 100
