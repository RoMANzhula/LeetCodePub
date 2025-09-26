package P601_700.P611_Valid_Triangle_Number;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {2, 2, 3, 4};
        System.out.println(solution.triangleNumber(nums1)); // Output: 3

        int[] nums2 = {4, 2, 3, 4};
        System.out.println(solution.triangleNumber(nums2)); // Output: 4
    }

    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;
        int count = 0;

        for (int k = n - 1; k >= 2; k--) { // pick largest side
            int i = 0, j = k - 1;

            while (i < j) {
                if (nums[i] + nums[j] > nums[k]) {
                    count += (j - i);  // all pairs between i..j-1 are valid
                    j--;
                } else {
                    i++;
                }
            }
        }

        return count;
    }

}

//Complexity:
// time - O(n^2)
// space - O(1)


//Given an integer array nums, return the number of triplets chosen from the array that can make triangles if we
// take them as side lengths of a triangle.

//Example 1:
//Input: nums = [2,2,3,4]
//Output: 3
//Explanation: Valid combinations are:
//2,3,4 (using the first 2)
//2,3,4 (using the second 2)
//2,2,3

//Example 2:
//Input: nums = [4,2,3,4]
//Output: 4

//Constraints:
//1 <= nums.length <= 1000
//0 <= nums[i] <= 1000
