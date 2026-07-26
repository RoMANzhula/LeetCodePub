package P601_700.P628_Maximum_Product_of_Three_Numbers;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 2, 3};
        System.out.println(solution.maximumProduct(nums1)); // 6

        int[] nums2 = {1, 2, 3, 4};
        System.out.println(solution.maximumProduct(nums2)); // 24

        int[] nums3 = {-1, -2, -3};
        System.out.println(solution.maximumProduct(nums3)); // -6

        int[] nums4 = {-100,-98,-1,2,3,4};
        System.out.println(solution.maximumProduct(nums4)); // 39200
    }

    public int maximumProduct(int[] nums) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;

        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for (int num : nums) {
            // three largest numbers
            if (num >= max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num >= max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3) {
                max3 = num;
            }

            // two smallest numbers
            if (num <= min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }
        }

        return Math.max(max1 * max2 * max3,
                max1 * min1 * min2);
    }

}

//Complexity:
// time - O(n log n)
// space - O(1)


//Given an integer array nums, find three numbers whose product is maximum and return the maximum product.

//Example 1:
//Input: nums = [1,2,3]
//Output: 6

//Example 2:
//Input: nums = [1,2,3,4]
//Output: 24

//Example 3:
//Input: nums = [-1,-2,-3]
//Output: -6

//Constraints:
//3 <= nums.length <= 104
//-1000 <= nums[i] <= 1000
