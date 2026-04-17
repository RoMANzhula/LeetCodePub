package P3701_3800.P3761_Minimum_Absolute_Distance_Between_Mirror_Pairs;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {12, 21, 45, 33, 54};
        System.out.println(solution.minMirrorPairDistance(nums1)); // 1

        int[] nums2 = {120, 21};
        System.out.println(solution.minMirrorPairDistance(nums2)); // 1

        int[] nums3 = {21, 120};
        System.out.println(solution.minMirrorPairDistance(nums3)); // -1
    }

    public int minMirrorPairDistance(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int len = nums.length;
        int minDistance = len + 1;

        for (int i = 0; i < len; ++i) {
            int reversed = reverse(nums[i]);

            if (map.containsKey(nums[i])) {
                minDistance = Math.min(minDistance, i - map.get(nums[i]));
            }

            // store AFTER checking
            map.put(reversed, i);
        }

        return minDistance > len ? -1 : minDistance;
    }

    private int reverse(int x) {
        int result = 0;

        while (x > 0) {
            result = result * 10 + x % 10;
            x /= 10;
        }

        return result;
    }

}

//Complexity:
// time and space - O(n)


//You are given an integer array nums.
//A mirror pair is a pair of indices (i, j) such that:
//0 <= i < j < nums.length, and
//reverse(nums[i]) == nums[j], where reverse(x) denotes the integer formed by reversing the digits of x. Leading
// zeros are omitted after reversing, for example reverse(120) = 21.
//Return the minimum absolute distance between the indices of any mirror pair. The absolute distance between indices
// i and j is abs(i - j).
//If no mirror pair exists, return -1.

//Example 1:
//Input: nums = [12,21,45,33,54]
//Output: 1
//Explanation:
//The mirror pairs are:
//(0, 1) since reverse(nums[0]) = reverse(12) = 21 = nums[1], giving an absolute distance abs(0 - 1) = 1.
//(2, 4) since reverse(nums[2]) = reverse(45) = 54 = nums[4], giving an absolute distance abs(2 - 4) = 2.
//The minimum absolute distance among all pairs is 1.

//Example 2:
//Input: nums = [120,21]
//Output: 1
//Explanation:
//There is only one mirror pair (0, 1) since reverse(nums[0]) = reverse(120) = 21 = nums[1].
//The minimum absolute distance is 1.

//Example 3:
//Input: nums = [21,120]
//Output: -1
//Explanation:
//There are no mirror pairs in the array.

//Constraints:
//1 <= nums.length <= 105
//1 <= nums[i] <= 109
