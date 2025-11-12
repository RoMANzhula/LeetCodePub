package P2601_2700.P2654_Minimun_Number_of_Operations_to_Make_All_Array_Elements_Equal_to_1;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.minOperations(new int[]{2,6,3,4})); // Output: 4
        System.out.println(solution.minOperations(new int[]{2,10,6,14})); // Output: -1
    }

    public int minOperations(int[] nums) {
        int n = nums.length;

        // if overall GCD > 1 -> impossible
        int overallGcd = nums[0];

        for (int i = 1; i < n; i++) {
            overallGcd = gcd(overallGcd, nums[i]);
        }

        if (overallGcd > 1) return -1;

        // count how many 1's
        int countOf1 = 0;

        for (int num : nums) {
            if (num == 1) countOf1++;
        }

        if (countOf1 > 0) return n - countOf1;

        // find the smallest subarray with GCD == 1
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int currGcd = nums[i];

            for (int j = i + 1; j < n; j++) {
                currGcd = gcd(currGcd, nums[j]);

                if (currGcd == 1) {
                    minLen = Math.min(minLen, j - i + 1);
                    break; // no need to extend this subarray further
                }
            }
        }

        // total operations
        return (minLen == Integer.MAX_VALUE) ? -1 : (n + minLen - 2);
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }

        return a;
    }

}

//Complexity:
// time - O(n^2 * log(max(nums)))
// space - O(1)


//You are given a 0-indexed array nums consisiting of positive integers. You can do the following operation on the
// array any number of times:
//Select an index i such that 0 <= i < n - 1 and replace either of nums[i] or nums[i+1] with their gcd value.
//Return the minimum number of operations to make all elements of nums equal to 1. If it is impossible, return -1.
//The gcd of two integers is the greatest common divisor of the two integers.

//Example 1:
//Input: nums = [2,6,3,4]
//Output: 4
//Explanation: We can do the following operations:
//- Choose index i = 2 and replace nums[2] with gcd(3,4) = 1. Now we have nums = [2,6,1,4].
//- Choose index i = 1 and replace nums[1] with gcd(6,1) = 1. Now we have nums = [2,1,1,4].
//- Choose index i = 0 and replace nums[0] with gcd(2,1) = 1. Now we have nums = [1,1,1,4].
//- Choose index i = 2 and replace nums[3] with gcd(1,4) = 1. Now we have nums = [1,1,1,1].

//Example 2:
//Input: nums = [2,10,6,14]
//Output: -1
//Explanation: It can be shown that it is impossible to make all the elements equal to 1.

//Constraints:
//2 <= nums.length <= 50
//1 <= nums[i] <= 106
