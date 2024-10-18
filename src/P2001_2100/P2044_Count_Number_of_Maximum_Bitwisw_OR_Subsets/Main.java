package P2001_2100.P2044_Count_Number_of_Maximum_Bitwisw_OR_Subsets;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {3, 1};
        System.out.println(solution.countMaxOrSubsets(nums1)); // Output: 2

        int[] nums2 = {2, 2, 2};
        System.out.println(solution.countMaxOrSubsets(nums2)); // Output: 7

        int[] nums3 = {3, 2, 1, 5};
        System.out.println(solution.countMaxOrSubsets(nums3)); // Output: 6
    }

//    public int countMaxOrSubsets(int[] nums) {
//        // Step 1: Find the maximum possible OR of all elements in the array
//        int maxOR = 0;
//        for (int num : nums) {
//            maxOR |= num;
//        }
//
//        // Step 2: Initialize the count for subsets that have the maximum OR
//        int count = 0;
//
//        // Step 3: Iterate over all possible subsets using bitmasking
//        int totalSubsets = 1 << nums.length; // 2^n subsets
//        for (int mask = 1; mask < totalSubsets; mask++) {
//            int currentOR = 0;
//            for (int i = 0; i < nums.length; i++) {
//                // Check if the i-th element is in the current subset
//                if ((mask & (1 << i)) != 0) {
//                    currentOR |= nums[i];
//                }
//            }
//            // Step 4: If the OR of this subset equals maxOR, increment the count
//            if (currentOR == maxOR) {
//                count++;
//            }
//        }
//
//        return count;
//    }

    //faster solution
    public int countMaxOrSubsets(int[] nums) {
        // Step 1: Calculate the maximum bitwise OR for the entire array
        int maxOR = 0;
        for (int num : nums) {
            maxOR |= num;
        }

        // Step 2: Initialize count to store the number of subsets with the maximum OR
        int count = 0;

        // Create a recursive function to generate subsets
        count = dfs(nums, 0, 0, maxOR);

        return count;
    }

    // Helper function for DFS
    private int dfs(int[] nums, int index, int currentOR, int maxOR) {
        // Base case: if we've processed all elements
        if (index == nums.length) {
            return (currentOR == maxOR) ? 1 : 0;
        }

        // Case 1: Include the current element in the subset
        int include = dfs(nums, index + 1, currentOR | nums[index], maxOR);

        // Case 2: Exclude the current element from the subset
        int exclude = dfs(nums, index + 1, currentOR, maxOR);

        // Return the total count of valid subsets
        return include + exclude;
    }


}

//Explanation of Optimizations:
//DFS (Depth-First Search):
//We use a recursive depth-first search (dfs) instead of explicit bitmasking and loops. This avoids redundant
// re-calculations of subsets' OR values.
//The recursive function tries two things at each index:
//Include the current element in the subset (OR it with the current OR result).
//Exclude the current element from the subset.
//If the OR result of a subset matches the maximum OR value, we count it.
//Incremental OR Calculation:
//As we recurse through the array, we build up the OR value incrementally. This avoids the overhead of
// repeatedly calculating the OR from scratch for every subset.
//Short-Circuiting:
//We terminate recursion early if the subset has been fully processed. This avoids the need to explicitly handle
// bitmask generation and looping over indices.
//Time Complexity:
//This DFS approach still processes all subsets and checks their bitwise OR, so the overall time complexity
// remains O(n * 2^n), but the recursive solution can be more efficient due to reduced overhead from avoiding
// manual bitmask operations and redundant OR calculations.

//In this specific problem, reducing the overhead of the loops through recursion and memoization should give a
// slight performance boost. However, because of the exponential number of subsets, the overall complexity
// cannot be reduced further.


//Given an integer array nums, find the maximum possible bitwise OR of a subset of nums and return the number of
// different non-empty subsets with the maximum bitwise OR.
//An array a is a subset of an array b if a can be obtained from b by deleting some (possibly zero) elements of b. Two
// subsets are considered different if the indices of the elements chosen are different.
//The bitwise OR of an array a is equal to a[0] OR a[1] OR ... OR a[a.length - 1] (0-indexed).
//
//Example 1:
//Input: nums = [3,1]
//Output: 2
//Explanation: The maximum possible bitwise OR of a subset is 3. There are 2 subsets with a bitwise OR of 3:
//- [3]
//- [3,1]

//Example 2:
//Input: nums = [2,2,2]
//Output: 7
//Explanation: All non-empty subsets of [2,2,2] have a bitwise OR of 2. There are 23 - 1 = 7 total subsets.

//Example 3:
//Input: nums = [3,2,1,5]
//Output: 6
//Explanation: The maximum possible bitwise OR of a subset is 7. There are 6 subsets with a bitwise OR of 7:
//- [3,5]
//- [3,1,5]
//- [3,2,5]
//- [3,2,1,5]
//- [2,5]
//- [2,1,5]
//
//Constraints:
//1 <= nums.length <= 16
//1 <= nums[i] <= 105
