package P2901_3000.P2940_Find_Building_Where_Alice_and_Bob_Can_Meet;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] heights1 = {6, 4, 8, 5, 2, 7};
        int[][] queries1 = {{0, 1}, {0, 3}, {2, 4}, {3, 4}, {2, 2}};
        System.out.println(Arrays.toString(solution.leftmostBuildingQueries(heights1, queries1))); // [2, 5, -1, 5, 2]

        int[] heights2 = {5, 3, 8, 2, 6, 1, 4, 6};
        int[][] queries2 = {{0, 7}, {3, 5}, {5, 2}, {3, 0}, {1, 6}};
        System.out.println(Arrays.toString(solution.leftmostBuildingQueries(heights2, queries2))); // [7, 6, -1, 4, 6]
    }

    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        int buildingsLength = heights.length;
        int queriesLength = queries.length;

        // Ensure the start of the range is less than the end of the range
        for (int i = 0; i < queriesLength; ++i) {
            if (queries[i][0] > queries[i][1]) {
                queries[i] = new int[] {queries[i][1], queries[i][0]};
            }
        }

        // Sort query indices based on the right bound of queries in descending order
        Integer[] indices = new Integer[queriesLength];
        for (int i = 0; i < queriesLength; ++i) {
            indices[i] = i;
        }

        Arrays.sort(indices, (i, j) -> Integer.compare(queries[j][1], queries[i][1]));

        // Copy and sort building heights for binary search usage
        int[] sortedHeights = heights.clone();
        Arrays.sort(sortedHeights);

        // Initialize the result array and the Binary Indexed Tree
        int[] results = new int[queriesLength];
        BinaryIndexedTree tree = new BinaryIndexedTree(buildingsLength);

        int j = buildingsLength - 1;

        // Process queries based on sorted indices
        for (int index : indices) {
            int leftBound = queries[index][0], rightBound = queries[index][1];

            // Update the BIT with buildings in the range that are not yet processed
            while (j > rightBound) {
                int position = buildingsLength - Arrays.binarySearch(sortedHeights, heights[j]) + 1;
                tree.update(position, j);
                --j;
            }

            // Direct visibility check or use the Binary Indexed Tree
            if (leftBound == rightBound || heights[leftBound] < heights[rightBound]) {
                results[index] = rightBound;
            } else {
                int k = buildingsLength - Arrays.binarySearch(sortedHeights, heights[leftBound]);
                results[index] = tree.query(k);
            }
        }
        return results;
    }
}

class BinaryIndexedTree {
    int size;
    int[] treeArray;

    public BinaryIndexedTree(int size) {
        this.size = size;
        this.treeArray = new int[size + 1];
        Arrays.fill(this.treeArray, Integer.MAX_VALUE);  // Initialize with 'inf' (Integer.MAX_VALUE)
    }

    public void update(int index, int value) {
        // Update the tree with the new value at the given index
        while (index <= size) {
            treeArray[index] = Math.min(treeArray[index], value);
            index += index & -index;  // Move to the next index
        }
    }

    public int query(int index) {
        // Retrieve the minimum value up to the given index
        int minValue = Integer.MAX_VALUE;
        while (index > 0) {
            minValue = Math.min(minValue, treeArray[index]);
            index -= index & -index;  // Move to the parent index
        }
        return minValue == Integer.MAX_VALUE ? -1 : minValue;
    }
}

// I gave this solution from algo.monster site


//You are given a 0-indexed array heights of positive integers, where heights[i] represents the height of
// the ith building.
//If a person is in building i, they can move to any other building j if and only if i < j and heights[i] < heights[j].
//You are also given another array queries where queries[i] = [ai, bi]. On the ith query, Alice is in building ai
// while Bob is in building bi.
//Return an array ans where ans[i] is the index of the leftmost building where Alice and Bob can meet on
// the ith query. If Alice and Bob cannot move to a common building on query i, set ans[i] to -1.
//
//Example 1:
//Input: heights = [6,4,8,5,2,7], queries = [[0,1],[0,3],[2,4],[3,4],[2,2]]
//Output: [2,5,-1,5,2]
//Explanation: In the first query, Alice and Bob can move to building 2 since
// heights[0] < heights[2] and heights[1] < heights[2].
//In the second query, Alice and Bob can move to building 5 since heights[0] < heights[5] and heights[3] < heights[5].
//In the third query, Alice cannot meet Bob since Alice cannot move to any other building.
//In the fourth query, Alice and Bob can move to building 5 since heights[3] < heights[5] and heights[4] < heights[5].
//In the fifth query, Alice and Bob are already in the same building.
//For ans[i] != -1, It can be shown that ans[i] is the leftmost building where Alice and Bob can meet.
//For ans[i] == -1, It can be shown that there is no building where Alice and Bob can meet.

//Example 2:
//Input: heights = [5,3,8,2,6,1,4,6], queries = [[0,7],[3,5],[5,2],[3,0],[1,6]]
//Output: [7,6,-1,4,6]
//Explanation: In the first query, Alice can directly move to Bob's building since heights[0] < heights[7].
//In the second query, Alice and Bob can move to building 6 since heights[3] < heights[6] and heights[5] < heights[6].
//In the third query, Alice cannot meet Bob since Bob cannot move to any other building.
//In the fourth query, Alice and Bob can move to building 4 since heights[3] < heights[4] and heights[0] < heights[4].
//In the fifth query, Alice can directly move to Bob's building since heights[1] < heights[6].
//For ans[i] != -1, It can be shown that ans[i] is the leftmost building where Alice and Bob can meet.
//For ans[i] == -1, It can be shown that there is no building where Alice and Bob can meet.
//
//Constraints:
//1 <= heights.length <= 5 * 104
//1 <= heights[i] <= 109
//1 <= queries.length <= 5 * 104
//queries[i] = [ai, bi]
//0 <= ai, bi <= heights.length - 1
