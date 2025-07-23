package P1_100.P39_Combination_Sum;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] candidates1 = {2, 3, 6, 7};
        int target1 = 7;
        System.out.println(solution.combinationSum(candidates1, target1)); // Output: [[2,2,3],[7]]

        int[] candidates2 = {2, 3, 5};
        int target2 = 8;
        System.out.println(solution.combinationSum(candidates2, target2)); // Output: [[2,2,2,2],[2,3,3],[3,5]]

        int[] candidates3 = {2};
        int target3 = 1;
        System.out.println(solution.combinationSum(candidates3, target3)); // Output: []
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, 0, target, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] candidates, int index, int target, List<Integer> current, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(current)); // found a valid combination
            return;
        }
        if (target < 0 || index == candidates.length) {
            return; // invalid path or end of array
        }

        // choose the current number
        current.add(candidates[index]);
        backtrack(candidates, index, target - candidates[index], current, result);
        current.remove(current.size() - 1); // backtrack

        //  skip to the next number
        backtrack(candidates, index + 1, target, current, result);
    }

}


//Given an array of distinct integers candidates and a target integer target, return a list of all unique
// combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.
//The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if
// the frequency of at least one of the chosen numbers is different.
//The test cases are generated such that the number of unique combinations that sum up to target is less than
// 150 combinations for the given input.

//Example 1:
//Input: candidates = [2,3,6,7], target = 7
//Output: [[2,2,3],[7]]
//Explanation:
//2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
//7 is a candidate, and 7 = 7.
//These are the only two combinations.

//Example 2:
//Input: candidates = [2,3,5], target = 8
//Output: [[2,2,2,2],[2,3,3],[3,5]]

//Example 3:
//Input: candidates = [2], target = 1
//Output: []

//Constraints:
//1 <= candidates.length <= 30
//2 <= candidates[i] <= 40
//All elements of candidates are distinct.
//1 <= target <= 40
