package P3301_3400.P3318_Find_X_Sum_of_All_K_Long_Subarrays_I;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 1, 2, 2, 3, 4, 2, 3};
        int k1 = 6, x1 = 2;
        System.out.println(Arrays.toString(solution.findXSum(nums1, k1, x1))); // Output: [6, 10, 12]

        int[] nums2 = {3, 8, 7, 8, 7, 5};
        int k2 = 2, x2 = 2;
        System.out.println(Arrays.toString(solution.findXSum(nums2, k2, x2))); // Output: [11, 15, 15, 15, 12]
    }

    public int[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        int[] result = new int[n - k + 1];

        for (int i = 0; i <= n - k; i++) {
            // ccurrent subarray
            int[] sub = Arrays.copyOfRange(nums, i, i + k);

            // count frequency
            Map<Integer, Integer> freq = new HashMap<>();
            for (int num : sub) {
                freq.put(num, freq.getOrDefault(num, 0) + 1);
            }

            // sort elements by frequency desc, then value desc
            List<Integer> sorted = new ArrayList<>(freq.keySet());
            sorted.sort((a, b) -> {
                if (!freq.get(a).equals(freq.get(b))) {
                    return freq.get(b) - freq.get(a); // higher freq first
                }

                return b - a; // if same freq, bigger value first
            });

            // pick top x elements
            Set<Integer> topX = new HashSet<>();
            for (int j = 0; j < Math.min(x, sorted.size()); j++) {
                topX.add(sorted.get(j));
            }

            // calculate x-sum
            int sum = 0;
            for (int num : sub) {
                if (topX.contains(num)) {
                    sum += num;
                }
            }

            result[i] = sum;
        }

        return result;
    }

}

//Complexity:
// time - O((n - k + 1) * (k log k))
// space - O(k)


//You are given an array nums of n integers and two integers k and x.
//The x-sum of an array is calculated by the following procedure:
//Count the occurrences of all elements in the array.
//Keep only the occurrences of the top x most frequent elements. If two elements have the same number of occurrences,
// the element with the bigger value is considered more frequent.
//Calculate the sum of the resulting array.
//Note that if an array has less than x distinct elements, its x-sum is the sum of the array.
//Return an integer array answer of length n - k + 1 where answer[i] is the x-sum of the subarray nums[i..i + k - 1].

//Example 1:
//Input: nums = [1,1,2,2,3,4,2,3], k = 6, x = 2
//Output: [6,10,12]
//Explanation:
//For subarray [1, 1, 2, 2, 3, 4], only elements 1 and 2 will be kept in the resulting array. Hence,
// answer[0] = 1 + 1 + 2 + 2.
//For subarray [1, 2, 2, 3, 4, 2], only elements 2 and 4 will be kept in the resulting array. Hence,
// answer[1] = 2 + 2 + 2 + 4. Note that 4 is kept in the array since it is bigger than 3 and 1 which occur the same number of times.
//For subarray [2, 2, 3, 4, 2, 3], only elements 2 and 3 are kept in the resulting array. Hence,
// answer[2] = 2 + 2 + 2 + 3 + 3.

//Example 2:
//Input: nums = [3,8,7,8,7,5], k = 2, x = 2
//Output: [11,15,15,15,12]
//Explanation:
//Since k == x, answer[i] is equal to the sum of the subarray nums[i..i + k - 1].

//Constraints:
//1 <= n == nums.length <= 50
//1 <= nums[i] <= 50
//1 <= x <= k <= nums.length
