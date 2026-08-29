package P101_200.P164_Maximum_Gap;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.maxGap(new int[]{3, 6, 9, 1})); // 3
        System.out.println(solution.maxGap(new int[]{10})); // 0
    }

    public int maxGap(int[] nums) {
        int n = nums.length;

        if (n < 2) {
            return 0;
        }

        int min = nums[0];
        int max = nums[0];

        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        // if all numbers are equal
        if (min == max) {
            return 0;
        }

        // min possible maximum gap
        long bucketSize = Math.max(1L, (long) (max - min) / (n - 1));

        int bucketCount = (int) ((max - min) / bucketSize) + 1;

        int[] bucketMin = new int[bucketCount];
        int[] bucketMax = new int[bucketCount];
        boolean[] used = new boolean[bucketCount];

        Arrays.fill(bucketMin, Integer.MAX_VALUE);
        Arrays.fill(bucketMax, Integer.MIN_VALUE);

        // put every number into a bucket
        for (int num : nums) {
            int index = (int) ((num - (long) min) / bucketSize);

            bucketMin[index] = Math.min(bucketMin[index], num);
            bucketMax[index] = Math.max(bucketMax[index], num);
            used[index] = true;
        }

        // find maximum gap between consecutive non-empty buckets
        int answer = 0;
        int previousMax = min;

        for (int i = 0; i < bucketCount; i++) {
            if (!used[i]) {
                continue;
            }

            answer = Math.max(answer, bucketMin[i] - previousMax);
            previousMax = bucketMax[i];
        }

        return answer;
    }

}

//Complexity:
// time and space - O(n)


//Given an integer array nums, return the maximum difference between two successive elements in its sorted form. If
// the array contains less than two elements, return 0.
//You must write an algorithm that runs in linear time and uses linear extra space.

//Example 1:
//Input: nums = [3,6,9,1]
//Output: 3
//Explanation: The sorted form of the array is [1,3,6,9], either (3,6) or (6,9) has the maximum difference 3.

//Example 2:
//Input: nums = [10]
//Output: 0
//Explanation: The array contains less than 2 elements, therefore return 0.

//Constraints:
//1 <= nums.length <= 105
//0 <= nums[i] <= 109
