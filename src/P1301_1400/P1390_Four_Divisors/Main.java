package P1301_1400.P1390_Four_Divisors;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {21, 4, 7};
        int[] nums2 = {21, 21};
        int[] nums3 = {1, 2, 3, 4, 5};

        System.out.println(solution.sumFourDivisors(nums1)); // 32
        System.out.println(solution.sumFourDivisors(nums2)); // 64
        System.out.println(solution.sumFourDivisors(nums3)); // 0
    }

    public int sumFourDivisors(int[] nums) {
        int totalSum = 0;

        for (int n : nums) {
            List<Integer> divisors = new ArrayList<>();

            for (int i = 1; i * i <= n; i++) {
                if (n % i == 0) {
                    divisors.add(i);
                    if (i != n / i) {
                        divisors.add(n / i);
                    }
                }
                // eearly stop if more than 4 divisors
                if (divisors.size() > 4) {
                    break;
                }
            }

            if (divisors.size() == 4) {
                for (int d : divisors) {
                    totalSum += d;
                }
            }
        }

        return totalSum;
    }

}

//Complexity:
// time - O(nums.length * max(nums[i]))
// space - O(1)


//Given an integer array nums, return the sum of divisors of the integers in that array that have exactly four
// divisors. If there is no such integer in the array, return 0.

//Example 1:
//Input: nums = [21,4,7]
//Output: 32
//Explanation:
//21 has 4 divisors: 1, 3, 7, 21
//4 has 3 divisors: 1, 2, 4
//7 has 2 divisors: 1, 7
//The answer is the sum of divisors of 21 only.

//Example 2:
//Input: nums = [21,21]
//Output: 64

//Example 3:
//Input: nums = [1,2,3,4,5]
//Output: 0

//Constraints:
//1 <= nums.length <= 104
//1 <= nums[i] <= 105
