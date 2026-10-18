package P1501_1600.P1569_Number_of_Ways_to_Reorder_Array_to_Get_Same_BST;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.numOfWays(new int[]{2, 1, 3}));
        System.out.println(solution.numOfWays(new int[]{3, 4, 5, 1, 2}));
        System.out.println(solution.numOfWays(new int[]{1, 2, 3}));
    }

    private static final long MOD = 1_000_000_007L;
    private long[][] combinations;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        combinations = new long[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            combinations[i][0] = 1;
            combinations[i][i] = 1;

            for (int j = 1; j < i; j++) {
                combinations[i][j] =
                        (combinations[i - 1][j - 1]
                                + combinations[i - 1][j]) % MOD;
            }
        }

        List<Integer> values = new ArrayList<>();
        for (int num : nums) {
            values.add(num);
        }

        return (int) ((countWays(values) - 1 + MOD) % MOD);
    }

    private long countWays(List<Integer> nums) {
        int n = nums.size();

        if (n <= 2) {
            return 1;
        }

        int root = nums.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int i = 1; i < n; i++) {
            int value = nums.get(i);

            if (value < root) {
                left.add(value);
            } else {
                right.add(value);
            }
        }

        long leftWays = countWays(left);
        long rightWays = countWays(right);

        long interleavings =
                combinations[n - 1][left.size()];

        return (((interleavings * leftWays) % MOD)
                * rightWays) % MOD;
    }

}

//Complexity:
// time and space - O(n^2)


//Given an array nums that represents a permutation of integers from 1 to n. We are going to construct a binary
// search tree (BST) by inserting the elements of nums in order into an initially empty BST. Find the number of
// different ways to reorder nums so that the constructed BST is identical to that formed from the original array nums.
//For example, given nums = [2,1,3], we will have 2 as the root, 1 as a left child, and 3 as a right child. The
// array [2,3,1] also yields the same BST but [3,2,1] yields a different BST.
//Return the number of ways to reorder nums such that the BST formed is identical to the original BST formed from nums.
//Since the answer may be very large, return it modulo 109 + 7.

//Example 1:
//Input: nums = [2,1,3]
//Output: 1
//Explanation: We can reorder nums to be [2,3,1] which will yield the same BST. There are no other ways to reorder
// nums which will yield the same BST.

//Example 2:
//Input: nums = [3,4,5,1,2]
//Output: 5
//Explanation: The following 5 arrays will yield the same BST:
//[3,1,2,4,5]
//[3,1,4,2,5]
//[3,1,4,5,2]
//[3,4,1,2,5]
//[3,4,1,5,2]

//Example 3:
//Input: nums = [1,2,3]
//Output: 0
//Explanation: There are no other orderings of nums that will yield the same BST.

//Constraints:
//1 <= nums.length <= 1000
//1 <= nums[i] <= nums.length
//All integers in nums are distinct.
