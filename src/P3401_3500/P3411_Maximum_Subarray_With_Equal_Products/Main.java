package P3401_3500.P3411_Maximum_Subarray_With_Equal_Products;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 2, 1, 2, 1, 1, 1};
        System.out.println(solution.maxLength(nums1)); // 5

        int[] nums2 = {2, 3, 4, 5, 6};
        System.out.println(solution.maxLength(nums2)); // 3

        int[] nums3 = {1, 2, 3, 1, 4, 5, 1};
        System.out.println(solution.maxLength(nums3)); // 5
    }

    public int maxLength(int[] nums) {
        int n = nums.length;
        int maxLength = 0;

        for (int i = 0; i < n; i++) {
            long product = 1;
            long gcd = nums[i];
            long lcm = nums[i];

            for (int j = i; j < n; j++) {
                product *= nums[j];

                gcd = gcd(gcd, nums[j]);
                lcm = lcm(lcm, nums[j]);

                if (product == lcm * gcd) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }

        return maxLength;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }

    private long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

}

//Complexity:
// time - O(n^2 log M)      M = max(nums[i])
// spase - O(1)


//You are given an array of positive integers nums.
//An array arr is called product equivalent if prod(arr) == lcm(arr) * gcd(arr), where:
//prod(arr) is the product of all elements of arr.
//gcd(arr) is the GCD of all elements of arr.
//lcm(arr) is the LCM of all elements of arr.
//Return the length of the longest product equivalent subarray of nums.

//Example 1:
//Input: nums = [1,2,1,2,1,1,1]
//Output: 5
//Explanation:
//The longest product equivalent subarray is [1, 2, 1, 1, 1], where prod([1, 2, 1, 1, 1]) = 2,
// gcd([1, 2, 1, 1, 1]) = 1, and lcm([1, 2, 1, 1, 1]) = 2.

//Example 2:
//Input: nums = [2,3,4,5,6]
//Output: 3
//Explanation:
//The longest product equivalent subarray is [3, 4, 5].

//Example 3:
//Input: nums = [1,2,3,1,4,5,1]
//Output: 5

//Constraints:
//2 <= nums.length <= 100
//1 <= nums[i] <= 10
