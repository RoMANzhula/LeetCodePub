package P2101_2200.P2191_Sort_the_Jumbled_Numbers;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] mapping1 = {8, 9, 4, 0, 2, 1, 3, 5, 7, 6};
        int[] nums1 = {991, 338, 38};
        System.out.println("Sorted nums1: " + Arrays.toString(solution.sortJumbled(mapping1, nums1)));// [338, 38, 991]

        int[] mapping2 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] nums2 = {789, 456, 123};
        System.out.println("Sorted nums2: " + Arrays.toString(solution.sortJumbled(mapping2, nums2)));// [123, 456, 789]
    }

    public int[] sortJumbled(int[] mapping, int[] nums) {
        // Convert each number to its mapped value and store the original index for stable sorting
        int[][] mappedNums = new int[nums.length][2];

        for (int i = 0; i < nums.length; i++) {
            mappedNums[i][0] = mapValue(nums[i], mapping);
            mappedNums[i][1] = i;  // Store original index to maintain stable sort
        }

        // Sort based on the mapped values
        Arrays.sort(mappedNums, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                // Compare mapped values, and if they are equal, compare the original indices
                if (a[0] == b[0]) {
                    return Integer.compare(a[1], b[1]);
                } else {
                    return Integer.compare(a[0], b[0]);
                }
            }
        });

        // Create the result array based on the sorted order
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = nums[mappedNums[i][1]];
        }

        return result;
    }

    private static int mapValue(int num, int[] mapping) {
        // Convert the number to its mapped value according to the provided mapping
        String numStr = String.valueOf(num);
        StringBuilder mappedStr = new StringBuilder();

        for (char c : numStr.toCharArray()) {
            mappedStr.append(mapping[c - '0']);
        }

        // Convert the mapped string back to an integer
        return Integer.parseInt(mappedStr.toString());
    }

}

//Explanation:
//Mapping Function (mapValue): This function converts a number to its mapped value using the provided mapping
// array. It converts the number to a string, replaces each digit with its mapped value, and then converts the
// resulting string back to an integer.
//Sorting (sortMapped): This function sorts the numbers based on their mapped values. It uses an array mappedNums
// where each entry is a pair: the mapped value of a number and its original index. This way, we can perform a
// stable sort, maintaining the original relative order of numbers with the same mapped value.
//Comparator: The custom comparator sorts the pairs first by their mapped values and then by their original indices
// if the mapped values are the same.
//Result Construction: Finally, the result array is built using the sorted order of the original indices.
//This approach ensures that the numbers are sorted based on their mapped values while maintaining stability.



//You are given a 0-indexed integer array mapping which represents the mapping rule of a shuffled decimal
// system. mapping[i] = j means digit i should be mapped to digit j in this system.
//The mapped value of an integer is the new integer obtained by replacing each occurrence of digit i in the
// integer with mapping[i] for all 0 <= i <= 9.
//You are also given another integer array nums. Return the array nums sorted in non-decreasing order based on
// the mapped values of its elements.
//Notes:
//Elements with the same mapped values should appear in the same relative order as in the input.
//The elements of nums should only be sorted based on their mapped values and not be replaced by them.
//
//Example 1:
//Input: mapping = [8,9,4,0,2,1,3,5,7,6], nums = [991,338,38]
//Output: [338,38,991]
//Explanation:
//Map the number 991 as follows:
//1. mapping[9] = 6, so all occurrences of the digit 9 will become 6.
//2. mapping[1] = 9, so all occurrences of the digit 1 will become 9.
//Therefore, the mapped value of 991 is 669.
//338 maps to 007, or 7 after removing the leading zeros.
//38 maps to 07, which is also 7 after removing leading zeros.
//Since 338 and 38 share the same mapped value, they should remain in the same relative order, so 338 comes before 38.
//Thus, the sorted array is [338,38,991].

//Example 2:
//Input: mapping = [0,1,2,3,4,5,6,7,8,9], nums = [789,456,123]
//Output: [123,456,789]
//Explanation: 789 maps to 789, 456 maps to 456, and 123 maps to 123. Thus, the sorted array is [123,456,789].
//
//Constraints:
//mapping.length == 10
//0 <= mapping[i] <= 9
//All the values of mapping[i] are unique.
//1 <= nums.length <= 3 * 104
//0 <= nums[i] < 109