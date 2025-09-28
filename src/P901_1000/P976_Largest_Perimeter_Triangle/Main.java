package P901_1000.P976_Largest_Perimeter_Triangle;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {2, 1, 2};
        System.out.println(solution.largestPerimeter(nums1)); // Output: 5

        int[] nums2 = {1, 2, 1, 10};
        System.out.println(solution.largestPerimeter(nums2)); // Output: 0
    }

    public int largestPerimeter(int[] nums) {
        Arrays.sort(nums);

        for (int i = nums.length - 1; i >= 2; i--) {
            if (nums[i - 2] + nums[i - 1] > nums[i]) {
                return nums[i - 2] + nums[i - 1] + nums[i];
            }
        }

        return 0;
    }

}

//Complexity:
// time - O(n log n)
// space - O(1)


//Given an integer array nums, return the largest perimeter of a triangle with a non-zero area, formed from
// three of these lengths. If it is impossible to form any triangle of a non-zero area, return 0.

//Example 1:
//Input: nums = [2,1,2]
//Output: 5
//Explanation: You can form a triangle with three side lengths: 1, 2, and 2.

//Example 2:
//Input: nums = [1,2,1,10]
//Output: 0
//Explanation:
//You cannot use the side lengths 1, 1, and 2 to form a triangle.
//You cannot use the side lengths 1, 1, and 10 to form a triangle.
//You cannot use the side lengths 1, 2, and 10 to form a triangle.
//As we cannot use any three side lengths to form a triangle of non-zero area, we return 0.

//Constraints:
//3 <= nums.length <= 104
//1 <= nums[i] <= 106
