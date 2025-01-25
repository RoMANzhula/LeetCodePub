package P2901_3000.P2948_Make_Lexicographicaly_Smallest_Array_by_Swapping_Elements;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 5, 3, 9, 8};
        int limit1 = 2;
        System.out.println(Arrays.toString(solution.lexicographicallySmallestArray(nums1, limit1))); // [1, 3, 5, 8, 9]

        int[] nums2 = {1, 7, 6, 18, 2, 1};
        int limit2 = 3;
        System.out.println(Arrays.toString(solution.lexicographicallySmallestArray(nums2, limit2))); // [1, 6, 7, 18, 1, 2]

        int[] nums3 = {1, 7, 28, 19, 10};
        int limit3 = 3;
        System.out.println(Arrays.toString(solution.lexicographicallySmallestArray(nums3, limit3))); // [1, 7, 28, 19, 10]
    }

    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int[] ans = new int[nums.length];
        // List of groups, each group contains (num, index) pairs where the difference in each group <= limit
        List<List<Pair>> numAndIndexesGroups = new ArrayList<>();

        // Fill the numAndIndexesGroups
        for (Pair numAndIndex : getNumAndIndexes(nums)) {
            if (numAndIndexesGroups.isEmpty() ||
                    numAndIndex.num - numAndIndexesGroups.get(numAndIndexesGroups.size() - 1).get(numAndIndexesGroups.get(numAndIndexesGroups.size() - 1).size() - 1).num > limit) {
                // Start a new group
                numAndIndexesGroups.add(new ArrayList<>(Arrays.asList(numAndIndex)));
            } else {
                // Append to the existing group
                numAndIndexesGroups.get(numAndIndexesGroups.size() - 1).add(numAndIndex);
            }
        }

        // Process each group
        for (List<Pair> numAndIndexesGroup : numAndIndexesGroups) {
            List<Integer> sortedNums = new ArrayList<>();
            List<Integer> sortedIndices = new ArrayList<>();
            for (Pair pair : numAndIndexesGroup) {
                sortedNums.add(pair.num);
                sortedIndices.add(pair.index);
            }

            // Sort indices based on values
            Collections.sort(sortedIndices);

            // Assign the sorted values back to their original indices
            for (int i = 0; i < sortedNums.size(); i++) {
                ans[sortedIndices.get(i)] = sortedNums.get(i);
            }
        }

        return ans;
    }

    private List<Pair> getNumAndIndexes(int[] nums) {
        List<Pair> numAndIndexes = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            numAndIndexes.add(new Pair(nums[i], i));
        }
        // Sort by value (num)
        numAndIndexes.sort(Comparator.comparingInt(pair -> pair.num));
        return numAndIndexes;
    }

    // Pair class to hold (num, index)
    static class Pair {
        int num;
        int index;

        Pair(int num, int index) {
            this.num = num;
            this.index = index;
        }
    }
}

//Explanation:
//Pair: created a Pair class that contains two fields: value and index.
//getNumAndIndexes: This method returns a list of (num, index) pairs, where each pair is a number from the array
// and its index, sorted by numeric values.
//lexicographicallySmallestArray: In this method, we form groups of pairs where the difference between the
// elements in each group does not exceed limit. Then, for each group, we sort the values by indices and return
// the sorted values at the corresponding indices in the result.
//Complexity:
//Time Complexity: O(n log n) (due to sorting)
//Space Complexity: O(n) (due to storage of intermediate data structures)


//You are given a 0-indexed array of positive integers nums and a positive integer limit.
//In one operation, you can choose any two indices i and j and swap nums[i] and nums[j] if |nums[i] - nums[j]| <= limit.
//Return the lexicographically smallest array that can be obtained by performing the operation any number of times.
//An array a is lexicographically smaller than an array b if in the first position where a and b differ, array a
// has an element that is less than the corresponding element in b. For example, the array [2,10,3] is
// lexicographically smaller than the array [10,2,3] because they differ at index 0 and 2 < 10.
//
//Example 1:
//Input: nums = [1,5,3,9,8], limit = 2
//Output: [1,3,5,8,9]
//Explanation: Apply the operation 2 times:
//- Swap nums[1] with nums[2]. The array becomes [1,3,5,9,8]
//- Swap nums[3] with nums[4]. The array becomes [1,3,5,8,9]
//We cannot obtain a lexicographically smaller array by applying any more operations.
//Note that it may be possible to get the same result by doing different operations.

//Example 2:
//Input: nums = [1,7,6,18,2,1], limit = 3
//Output: [1,6,7,18,1,2]
//Explanation: Apply the operation 3 times:
//- Swap nums[1] with nums[2]. The array becomes [1,6,7,18,2,1]
//- Swap nums[0] with nums[4]. The array becomes [2,6,7,18,1,1]
//- Swap nums[0] with nums[5]. The array becomes [1,6,7,18,1,2]
//We cannot obtain a lexicographically smaller array by applying any more operations.

//Example 3:
//Input: nums = [1,7,28,19,10], limit = 3
//Output: [1,7,28,19,10]
//Explanation: [1,7,28,19,10] is the lexicographically smallest array we can obtain because we cannot apply the
// operation on any two indices.
//
//Constraints:
//1 <= nums.length <= 105
//1 <= nums[i] <= 109
//1 <= limit <= 109
