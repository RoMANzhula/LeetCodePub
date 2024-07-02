package P301_400.P350_Intersection_of_Two_Arrays_II;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        System.out.println(Arrays.toString(solution.intersect(nums1, nums2))); // Output: [2, 2]

        int[] nums1_2 = {4, 9, 5};
        int[] nums2_2 = {9, 4, 9, 8, 4};
        System.out.println(Arrays.toString(solution.intersect(nums1_2, nums2_2))); // Output: [4, 9]
    }

//    public int[] intersect(int[] nums1, int[] nums2) {
//        // Step 1: Create a HashMap to store counts of each element in nums1
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int num : nums1) {
//            map.put(num, map.getOrDefault(num, 0) + 1);
//        }
//
//        // Step 2: Find the intersection
//        List<Integer> resultList = new ArrayList<>();
//        for (int num : nums2) {
//            if (map.getOrDefault(num, 0) > 0) {
//                resultList.add(num);
//                map.put(num, map.get(num) - 1);
//            }
//        }
//
//        // Step 3: Convert the result list to an array
//        int[] result = new int[resultList.size()];
//        for (int i = 0; i < result.length; i++) {
//            result[i] = resultList.get(i);
//        }
//        return result;
//    }

    //faster solution
    public int[] intersect(int[] nums1, int[] nums2) {
        // Since the value range is between 0 and 1000, use arrays to count occurrences
        int[] count1 = new int[1001];
        int[] count2 = new int[1001];

        // Count the occurrences of each number in nums1
        for (int num : nums1) {
            count1[num]++;
        }

        // Count the occurrences of each number in nums2
        for (int num : nums2) {
            count2[num]++;
        }

        // Find the intersection by taking the minimum count for each number
        List<Integer> resultList = new ArrayList<>();
        for (int i = 0; i < count1.length; i++) {
            int minCount = Math.min(count1[i], count2[i]);
            for (int j = 0; j < minCount; j++) {
                resultList.add(i);
            }
        }

        // Convert the result list to an array
        int[] result = new int[resultList.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }

}

//Explanation:
//Count Arrays: We use two arrays count1 and count2 of size 1001 (since the values are between 0 and 1000) to count
// the occurrences of each number in nums1 and nums2.
//Intersection Calculation: For each index i, we find the minimum count between count1[i] and count2[i]. This
// represents the number of times i should appear in the intersection.
//Result Construction: We add the number i to the result list the calculated number of times.
//Conversion to Array: Finally, we convert the list of results to an array.
//This approach ensures that we only perform linear operations with respect to the size of the input arrays and
// the value range, making it very efficient.


//Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result
// must appear as many times as it shows in both arrays and you may return the result in any order.
//
//Example 1:
//Input: nums1 = [1,2,2,1], nums2 = [2,2]
//Output: [2,2]

//Example 2:
//Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//Output: [4,9]
//Explanation: [9,4] is also accepted.
//
//Constraints:
//1 <= nums1.length, nums2.length <= 1000
//0 <= nums1[i], nums2[i] <= 1000
//
//Follow up:
//What if the given array is already sorted? How would you optimize your algorithm?
//What if nums1's size is small compared to nums2's size? Which algorithm is better?
//What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all
// elements into the memory at once?