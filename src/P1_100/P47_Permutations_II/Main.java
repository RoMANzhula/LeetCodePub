package P1_100.P47_Permutations_II;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] input1 = {1, 1, 2};
        System.out.println("Input: [1, 1, 2]");
        System.out.println("Output: " + solution.permuteUnique(input1));

        int[] input2 = {1, 2, 3};
        System.out.println("Input: [1, 2, 3]");
        System.out.println("Output: " + solution.permuteUnique(input2));
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(nums); // sort to handle duplicates
        boolean[] used = new boolean[nums.length];
        backtrack(nums, new ArrayList<>(), used, results);

        return results;
    }

    private void backtrack(int[] nums, List<Integer> current, boolean[] used, List<List<Integer>> results) {
        if (current.size() == nums.length) {
            results.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // skip used numbers
            if (used[i]) continue;
            //skip duplicates
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;

            current.add(nums[i]);
            used[i] = true;
            backtrack(nums, current, used, results);
            used[i] = false;
            current.remove(current.size() - 1);
        }
    }

}

//Complexity:
// time - O(n * n)
// space - O(n)


//Given a collection of numbers, nums, that might contain duplicates, return all possible unique
// permutations in any order.

//Example 1:
//Input: nums = [1,1,2]
//Output:
//[[1,1,2],
// [1,2,1],
// [2,1,1]]

//Example 2:
//Input: nums = [1,2,3]
//Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

//Constraints:
//1 <= nums.length <= 8
//-10 <= nums[i] <= 10