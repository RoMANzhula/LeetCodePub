package P2971_Find_Polygon_With_the_Largest_Perimeter;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {5, 5, 5};
        int[] nums2 = {1, 12, 1, 2, 5, 50, 3};
        int[] nums3 = {5, 5, 50};

        System.out.println("Example 1: " + solution.largestPerimeter(nums1)); // Output: 15
        System.out.println("Example 2: " + solution.largestPerimeter(nums2)); // Output: 12
        System.out.println("Example 3: " + solution.largestPerimeter(nums3)); // Output: -1
    }

    public long largestPerimeter(int[] nums) {
        long sum = 0; // Initialize a variable to store the sum of all elements in the array
        Arrays.sort(nums); // Sort the array in non-decreasing order
        // Calculate the sum of all elements in the array
        for (long i : nums) {
            sum += i;
        }
        int n = nums.length; // Get the size of the array
        // Iterate through the sorted array in reverse order
        for (int i = n - 1; i >= 2; i--) {
            sum -= nums[i]; // Remove the current element (considered as the longest side) from the sum
            // Check if the sum of the remaining elements is greater than the current element
            if (sum > nums[i]) {
                // If so, return the sum of the remaining elements plus the current element
                // This represents the largest possible perimeter of a polygon
                return (long) sum + nums[i];
            }
        }
        // If no valid polygon can be formed, return -1
        return -1;
    }

}

//You are given an array of positive integers nums of length n.
//A polygon is a closed plane figure that has at least 3 sides. The longest side of a polygon is smaller than
// the sum of its other sides.
//Conversely, if you have k (k >= 3) positive real numbers a1, a2,
// a3, ..., ak where a1 <= a2 <= a3 <= ... <= ak and a1 + a2 + a3 + ... + ak-1 > ak, then there always exists
// a polygon with k sides whose lengths are a1, a2, a3, ..., ak.
//The perimeter of a polygon is the sum of lengths of its sides.
//Return the largest possible perimeter of a polygon whose sides can be formed from nums, or -1 if it is not
// possible to create a polygon.

//Example 1:
//Input: nums = [5,5,5]
//Output: 15
//Explanation: The only possible polygon that can be made from nums has 3 sides: 5, 5, and 5. The
// perimeter is 5 + 5 + 5 = 15.

//Example 2:
//Input: nums = [1,12,1,2,5,50,3]
//Output: 12
//Explanation: The polygon with the largest perimeter which can be made from nums has 5 sides: 1, 1, 2, 3, and 5. The
// perimeter is 1 + 1 + 2 + 3 + 5 = 12.
//We cannot have a polygon with either 12 or 50 as the longest side because it is not possible to include 2 or
// more smaller sides that have a greater sum than either of them.
//It can be shown that the largest possible perimeter is 12.

//Example 3:
//Input: nums = [5,5,50]
//Output: -1
//Explanation: There is no possible way to form a polygon from nums, as a polygon has at least 3 sides and 50 > 5 + 5.

//Constraints:
//3 <= n <= 105
//1 <= nums[i] <= 109