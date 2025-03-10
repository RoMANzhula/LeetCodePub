package P1_100.P46_Permutation;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] nums, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int num : nums) {
            if (!current.contains(num)) { // Avoid duplicates in the permutation
                current.add(num);
                backtrack(nums, current, result);
                current.remove(current.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Main solution = new Main();
        int[] nums = {1, 2, 3};
        List<List<Integer>> permutations = solution.permute(nums);
        System.out.println(permutations);
    }
}

//Given an array nums of distinct integers, return all the possible permutations. You can return the
// answer in any order.

//Example 1:
//Input: nums = [1,2,3]
//Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

//Example 2:
//Input: nums = [0,1]
//Output: [[0,1],[1,0]]

//Example 3:
//Input: nums = [1]
//Output: [[1]]

//Constraints:
//1 <= nums.length <= 6
//-10 <= nums[i] <= 10
//All the integers of nums are unique.
