package P3401_3500.P3488_Closest_Equal_Element_Queries;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums = {1, 3, 1, 4, 1, 3, 2};
        int[] queries = {0, 3, 5};

        List<Integer> result = solution.solvedQueries(nums, queries);

        System.out.println(result); // [2, -1, 3]
    }

    public List<Integer> solvedQueries(int[] nums, int[] queries) {
        int n = nums.length;

        // value -> indices
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        List<Integer> result = new ArrayList<>();

        // process queries
        for (int idx : queries) {
            int value = nums[idx];
            List<Integer> list = map.get(value);

            // only one occurrence
            if (list.size() == 1) {
                result.add(-1);
                continue;
            }

            int pos = Collections.binarySearch(list, idx);

            // neighbors (circular in the list)
            int left = list.get((pos - 1 + list.size()) % list.size());
            int right = list.get((pos + 1) % list.size());

            int minDist = Math.min(
                    circularDist(idx, left, n),
                    circularDist(idx, right, n)
            );

            result.add(minDist);
        }

        return result;
    }

    private int circularDist(int i, int j, int n) {
        int diff = Math.abs(i - j);
        return Math.min(diff, n - diff);
    }

}

//Complexity:
// time - O(n + q log n)
// space - O(n + q)


//You are given a circular array nums and an array queries.
//For each query i, you have to find the following:
//The minimum distance between the element at index queries[i] and any other index j in the circular array, where
// nums[j] == nums[queries[i]]. If no such index exists, the answer for that query should be -1.
//Return an array answer of the same size as queries, where answer[i] represents the result for query i.

//Example 1:
//Input: nums = [1,3,1,4,1,3,2], queries = [0,3,5]
//Output: [2,-1,3]
//Explanation:
//Query 0: The element at queries[0] = 0 is nums[0] = 1. The nearest index with the same value is 2, and the
// distance between them is 2.
//Query 1: The element at queries[1] = 3 is nums[3] = 4. No other index contains 4, so the result is -1.
//Query 2: The element at queries[2] = 5 is nums[5] = 3. The nearest index with the same value is 1, and the
// distance between them is 3 (following the circular path: 5 -> 6 -> 0 -> 1).

//Example 2:
//Input: nums = [1,2,3,4], queries = [0,1,2,3]
//Output: [-1,-1,-1,-1]
//Explanation:
//Each value in nums is unique, so no index shares the same value as the queried element. This results in -1 for
// all queries.

//Constraints:
//1 <= queries.length <= nums.length <= 105
//1 <= nums[i] <= 106
//0 <= queries[i] < nums.length
