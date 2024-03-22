package P301_400.P349_Intersection_of_Two_Arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        // Example 1
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        int[] result1 = solution.intersection(nums1, nums2);
        System.out.println("Example 1 Output: " + Arrays.toString(result1)); // output [2]

        // Example 2
        int[] nums3 = {4, 9, 5};
        int[] nums4 = {9, 4, 9, 8, 4};
        int[] result2 = solution.intersection(nums3, nums4);
        System.out.println("Example 2 Output: " + Arrays.toString(result2)); // output [4,9] or [9,4]
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> setBefore = new HashSet<>();
        Set<Integer> setAfter = new HashSet<>();

        //add elements from nums1
        for (int num : nums1) {
            setBefore.add(num);
        }

        // check intersection with nums2
        for (int num : nums2) {
            if (setBefore.contains(num)) {
                setAfter.add(num);
            }
        }

        int[] result = new int[setAfter.size()];
        int index = 0;
        for (Integer num : setAfter) {
            result[index++] = num;
        }

        return result; //bingo

//        Set<Integer> result = new HashSet<>();
//
//        for (int n1 : nums1) {
//            for (int n2 : nums2) {
//                if (n1 == n2) result.add(n1);
//            }
//        }
//
//        int[] back = new int[result.size()];
//        int index = 0;
//        for (int num : result) {
//            back[index++] = num;
//        }
//
//        return back;
    }
}

//Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the
// result must be unique and you may return the result in any order.
//
//Example 1:
//Input: nums1 = [1,2,2,1], nums2 = [2,2]
//Output: [2]

//Example 2:
//Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//Output: [9,4]
//Explanation: [4,9] is also accepted.
//
//Constraints:
//1 <= nums1.length, nums2.length <= 1000
//0 <= nums1[i], nums2[i] <= 1000
