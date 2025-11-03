package P1_100.P90_Subsets_II;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 2, 2};
        System.out.println("Input: " + Arrays.toString(nums1));
        System.out.println("Output: " + solution.subsetsWithDup(nums1));

        int[] nums2 = {0};
        System.out.println("\nInput: " + Arrays.toString(nums2));
        System.out.println("Output: " + solution.subsetsWithDup(nums2));
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // sort first to handle duplicates
        backtrack(result, new ArrayList<>(), nums, 0);

        return result;
    }

    private static void backtrack(List<List<Integer>> result, List<Integer> temp, int[] nums, int start) {
        result.add(new ArrayList<>(temp));

        for (int i = start; i < nums.length; i++) {
            // skip duplicates
            if (i > start && nums[i] == nums[i - 1]) continue;

            temp.add(nums[i]);
            backtrack(result, temp, nums, i + 1);
            temp.remove(temp.size() - 1);
        }
    }

}

//Complexity:
// time and space - O(n * 2^n)


//Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
//The solution set must not contain duplicate subsets. Return the solution in any order.

//Example 1:
//Input: nums = [1,2,2]
//Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]

//Example 2:
//Input: nums = [0]
//Output: [[],[0]]

//Constraints:
//1 <= nums.length <= 10
//-10 <= nums[i] <= 10
