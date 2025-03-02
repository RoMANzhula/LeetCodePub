package P2501_2600.P2570_Merge_Two_2D_Arrays_by_Summing_Values;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] nums1 = {{1, 2}, {2, 3}, {4, 5}};
        int[][] nums2 = {{1, 4}, {3, 2}, {4, 1}};

        int[][] result = solution.mergeArrays(nums1, nums2);

        for (int[] pair : result) {
            System.out.println(Arrays.toString(pair));
        }
    }

    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        TreeMap<Integer, Integer> map = new TreeMap<>();

        // add values from nums1
        for (int[] pair : nums1) {
            map.put(pair[0], map.getOrDefault(pair[0], 0) + pair[1]);
        }

        // add values from nums2
        for (int[] pair : nums2) {
            map.put(pair[0], map.getOrDefault(pair[0], 0) + pair[1]);
        }

        // convert map to result array
        int[][] result = new int[map.size()][2];
        int index = 0;

        for (Map.Entry<Integer, Integer> integerIntegerEntry : map.entrySet()) {
            result[index++] = new int[]{integerIntegerEntry.getKey(), integerIntegerEntry.getValue()};
        }

        return result;
    }

}

//Explanation:
//We use a TreeMap<Integer, Integer> to store id as the key and the sum of values as the value.
//We iterate over both arrays (nums1 and nums2), adding values to the map using map.getOrDefault(id, 0) + value.
//Since TreeMap keeps keys sorted, the final result is directly in sorted order.
//Convert the map into a 2D array as required.
//Complexity Analysis:
//Time Complexity: O(N+M) (where N and M are the lengths of nums1 and nums2)
//Space Complexity: O(N+M) (for storing merged elements)


//You are given two 2D integer arrays nums1 and nums2.
//nums1[i] = [idi, vali] indicate that the number with the id idi has a value equal to vali.
//nums2[i] = [idi, vali] indicate that the number with the id idi has a value equal to vali.
//Each array contains unique ids and is sorted in ascending order by id.

//Merge the two arrays into one array that is sorted in ascending order by id, respecting the following conditions:

//Only ids that appear in at least one of the two arrays should be included in the resulting array.
//Each id should be included only once and its value should be the sum of the values of this id in the two
// arrays. If the id does not exist in one of the two arrays, then assume its value in that array to be 0.
//Return the resulting array. The returned array must be sorted in ascending order by id.
//
//Example 1:
//Input: nums1 = [[1,2],[2,3],[4,5]], nums2 = [[1,4],[3,2],[4,1]]
//Output: [[1,6],[2,3],[3,2],[4,6]]
//Explanation: The resulting array contains the following:
//- id = 1, the value of this id is 2 + 4 = 6.
//- id = 2, the value of this id is 3.
//- id = 3, the value of this id is 2.
//- id = 4, the value of this id is 5 + 1 = 6.

//Example 2:
//Input: nums1 = [[2,4],[3,6],[5,5]], nums2 = [[1,3],[4,3]]
//Output: [[1,3],[2,4],[3,6],[4,3],[5,5]]
//Explanation: There are no common ids, so we just include each id with its value in the resulting list.
//
//Constraints:
//1 <= nums1.length, nums2.length <= 200
//nums1[i].length == nums2[j].length == 2
//1 <= idi, vali <= 1000
//Both arrays contain unique ids.
//Both arrays are in strictly ascending order by id.