package P2101_2200.P2197_Replace_Non_Coprime_Numbers_in_Array;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {6, 4, 3, 2, 7, 6, 2};
        System.out.println(solution.replaceNonCoprimes(nums1)); // [12, 7, 6]

        int[] nums2 = {2, 2, 1, 1, 3, 3, 3};
        System.out.println(solution.replaceNonCoprimes(nums2)); // [2, 1, 1, 3]
    }

    public List<Integer> replaceNonCoprimes(int[] nums) {
        Deque<Long> stack = new ArrayDeque<>();

        for (int num : nums) {
            long cur = num;

            // merge with stack top while non-coprime
            while (!stack.isEmpty() && gcd(stack.peekLast(), cur) > 1) {
                cur = lcm(stack.pollLast(), cur);
            }

            stack.addLast(cur);
        }

        // convert back to List<Integer>
        List<Integer> result = new ArrayList<>();
        for (long val : stack) {
            result.add((int) val);
        }
        return result;
    }

    private static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

}

//Complexity:
// time - O(n log(max(nums[i])))
// space - O(n)


//You are given an array of integers nums. Perform the following steps:
//Find any two adjacent numbers in nums that are non-coprime.
//If no such numbers are found, stop the process.
//Otherwise, delete the two numbers and replace them with their LCM (Least Common Multiple).
//Repeat this process as long as you keep finding two adjacent non-coprime numbers.
//Return the final modified array. It can be shown that replacing adjacent non-coprime numbers in any arbitrary
// order will lead to the same result.
//The test cases are generated such that the values in the final array are less than or equal to 108.
//Two values x and y are non-coprime if GCD(x, y) > 1 where GCD(x, y) is the Greatest Common Divisor of x and y.

//Example 1:
//Input: nums = [6,4,3,2,7,6,2]
//Output: [12,7,6]
//Explanation:
//- (6, 4) are non-coprime with LCM(6, 4) = 12. Now, nums = [12,3,2,7,6,2].
//- (12, 3) are non-coprime with LCM(12, 3) = 12. Now, nums = [12,2,7,6,2].
//- (12, 2) are non-coprime with LCM(12, 2) = 12. Now, nums = [12,7,6,2].
//- (6, 2) are non-coprime with LCM(6, 2) = 6. Now, nums = [12,7,6].
//There are no more adjacent non-coprime numbers in nums.
//Thus, the final modified array is [12,7,6].
//Note that there are other ways to obtain the same resultant array.

//Example 2:
//Input: nums = [2,2,1,1,3,3,3]
//Output: [2,1,1,3]
//Explanation:
//- (3, 3) are non-coprime with LCM(3, 3) = 3. Now, nums = [2,2,1,1,3,3].
//- (3, 3) are non-coprime with LCM(3, 3) = 3. Now, nums = [2,2,1,1,3].
//- (2, 2) are non-coprime with LCM(2, 2) = 2. Now, nums = [2,1,1,3].
//There are no more adjacent non-coprime numbers in nums.
//Thus, the final modified array is [2,1,1,3].
//Note that there are other ways to obtain the same resultant array.

//Constraints:
//1 <= nums.length <= 105
//1 <= nums[i] <= 105
//The test cases are generated such that the values in the final array are less than or equal to 108.
