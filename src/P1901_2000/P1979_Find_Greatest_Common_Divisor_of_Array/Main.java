package P1901_2000.P1979_Find_Greatest_Common_Divisor_of_Array;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.findGCD(new int[]{2, 5, 6, 9, 10})); // 2
        System.out.println(solution.findGCD(new int[]{7, 5, 6, 8, 3}));  // 1
        System.out.println(solution.findGCD(new int[]{3, 3}));            // 3
    }

    public int findGCD(int[] nums) {
        int min = nums[0];
        int max = nums[0];

        // find minimum and maximum
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        return gcd(min, max);
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int remainder = a % b;
            a = b;
            b = remainder;
        }

        return a;

    }

}

//Complexity:
// time - O(n)
// space - O(1)


//Given an integer array nums, return the greatest common divisor of the smallest number and largest number in nums.
//The greatest common divisor of two numbers is the largest positive integer that evenly divides both numbers.

//Example 1:
//Input: nums = [2,5,6,9,10]
//Output: 2
//Explanation:
//The smallest number in nums is 2.
//The largest number in nums is 10.
//The greatest common divisor of 2 and 10 is 2.

//Example 2:
//Input: nums = [7,5,6,8,3]
//Output: 1
//Explanation:
//The smallest number in nums is 3.
//The largest number in nums is 8.
//The greatest common divisor of 3 and 8 is 1.

//Example 3:
//Input: nums = [3,3]
//Output: 3
//Explanation:
//The smallest number in nums is 3.
//The largest number in nums is 3.
//The greatest common divisor of 3 and 3 is 3.

//Constraints:
//2 <= nums.length <= 1000
//1 <= nums[i] <= 1000
