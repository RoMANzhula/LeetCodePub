package P1201_1300.P1262_Greatest_Sum_Divisible_by_Three;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.maxSumDivThree(new int[]{3,6,5,1,8})); // 18
        System.out.println(solution.maxSumDivThree(new int[]{4})); // 0
        System.out.println(solution.maxSumDivThree(new int[]{1,2,3,4,4})); // 12
    }

    public int maxSumDivThree(int[] nums) {
        int sum = 0;
        List<Integer> mod1 = new ArrayList<>();
        List<Integer> mod2 = new ArrayList<>();

        for (int num : nums) {
            sum += num;

            if (num % 3 == 1) mod1.add(num);
            else if (num % 3 == 2) mod2.add(num);
        }

        Collections.sort(mod1);
        Collections.sort(mod2);

        if (sum % 3 == 0) return sum;

        int result = 0;

        if (sum % 3 == 1) {
            int op1 = Integer.MIN_VALUE;
            int op2 = Integer.MIN_VALUE;

            if (!mod1.isEmpty()) op1 = sum - mod1.getFirst();

            if (mod2.size() >= 2) op2 = sum - mod2.get(0) - mod2.get(1);

            result = Math.max(op1, op2);
        } else {  // sum % 3 == 2
            int op1 = Integer.MIN_VALUE;
            int op2 = Integer.MIN_VALUE;

            if (!mod2.isEmpty()) op1 = sum - mod2.getFirst();

            if (mod1.size() >= 2) op2 = sum - mod1.get(0) - mod1.get(1);

            result = Math.max(op1, op2);
        }

        return Math.max(result, 0);
    }

}

//Complexity:
// time - O(n log n)
// space - O(n)


//Given an integer array nums, return the maximum possible sum of elements of the array such that it is
// divisible by three.

//Example 1:
//Input: nums = [3,6,5,1,8]
//Output: 18
//Explanation: Pick numbers 3, 6, 1 and 8 their sum is 18 (maximum sum divisible by 3).

//Example 2:
//Input: nums = [4]
//Output: 0
//Explanation: Since 4 is not divisible by 3, do not pick any number.

//Example 3:
//Input: nums = [1,2,3,4,4]
//Output: 12
//Explanation: Pick numbers 1, 3, 4 and 4 their sum is 12 (maximum sum divisible by 3).

//Constraints:
//1 <= nums.length <= 4 * 104
//1 <= nums[i] <= 104
