package P1_100.P40_Combination_Sum_II;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] candidates = {10,1,2,7,6,1,5};
        int target = 8;

        System.out.println(solution.combinationSum2(candidates, target));

    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>(); //container for result
        Arrays.sort(candidates); //sort the candidates to handle duplicates

        backtrack(result, new ArrayList<>(), candidates, target, 0); //major method for calculate elements to == target

        return result;
    }

    //a recursive function that will generate combinations
    private void backtrack(
            List<List<Integer>> result, //list for uniques number combinations
            List<Integer> tempList, //transport list for currant combination
            int[] candidates, //input array
            int remain, //remain of ending sum that == target
            int start //index == starting number for calculate
    ) {
        if (remain < 0) { //if negation value
            return; //end calculate
        } else if (remain == 0) { //if target finish
            result.add(new ArrayList<>(tempList)); //to transport result
        } else { //if process calculating for getting target
            for (int i = start; i < candidates.length; i++) { //start from start to last element of candidates
                if (i > start && candidates[i] == candidates[i - 1]) { //if we have duplicate
                    continue; //skip duplicates
                }
                tempList.add(candidates[i]); //added to transport's list
                backtrack(result, tempList, candidates, remain - candidates[i], i + 1); //recurs call for next element
                tempList.remove(tempList.size() - 1); //delete last added element from list for next iterations
            }
        }
    }

}


//Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in
// candidates where the candidate numbers sum to target.
//Each number in candidates may only be used once in the combination.
//Note: The solution set must not contain duplicate combinations.
//
//Example 1:
//Input: candidates = [10,1,2,7,6,1,5], target = 8
//Output:
//[
//[1,1,6],
//[1,2,5],
//[1,7],
//[2,6]
//]

//Example 2:
//Input: candidates = [2,5,2,1,2], target = 5
//Output:
//[
//[1,2,2],
//[5]
//]
//
//Constraints:
//1 <= candidates.length <= 100
//1 <= candidates[i] <= 50
//1 <= target <= 30