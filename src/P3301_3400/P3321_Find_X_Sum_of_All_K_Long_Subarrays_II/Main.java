package P3301_3400.P3321_Find_X_Sum_of_All_K_Long_Subarrays_II;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 1, 2, 2, 3, 4, 2, 3};
        int k1 = 6, x1 = 2;
        System.out.println(Arrays.toString(solution.findSum(nums1, k1, x1))); // [6, 10, 12]

        int[] nums2 = {3, 8, 7, 8, 7, 5};
        int k2 = 2, x2 = 2;
        System.out.println(Arrays.toString(solution.findSum(nums2, k2, x2))); // [11, 15, 15, 15, 12]
    }

    public long[] findSum(int[] nums, int k, int x) {
        class FreqValuePair implements Comparable<FreqValuePair> {
            int freq;
            int value;

            FreqValuePair(int freq, int value) {
                this.freq = freq;
                this.value = value;
            }

            @Override
            public int compareTo(FreqValuePair other) {
                if (this.freq != other.freq)
                    return this.freq - other.freq; // ascending by freq
                return this.value - other.value; // ascending by value
            }

            @Override
            public boolean equals(Object o) {
                if (!(o instanceof FreqValuePair)) return false;
                FreqValuePair p = (FreqValuePair) o;
                return freq == p.freq && value == p.value;
            }

            @Override
            public int hashCode() {
                return Objects.hash(freq, value);
            }
        }

        TreeSet<FreqValuePair> topX = new TreeSet<>();        // keeps best x
        TreeSet<FreqValuePair> remaining = new TreeSet<>();   // keeps the rest
        Map<Integer, Integer> frequency = new HashMap<>();

        final long[] currentSum = {0};
        int n = nums.length;
        long[] result = new long[n - k + 1];
        int resultIndex = 0;

        // helper lambdas
        Runnable dummy = () -> {};
        java.util.function.Consumer<Integer> addToSets = (value) -> {
            int freq = frequency.getOrDefault(value, 0);
            if (freq == 0) return;
            FreqValuePair element = new FreqValuePair(freq, value);

            if (!topX.isEmpty() && element.compareTo(topX.first()) > 0) {
                currentSum[0] += (long) element.freq * element.value;
                topX.add(element);
            } else {
                remaining.add(element);
            }
        };

        java.util.function.Consumer<Integer> removeFromSets = (value) -> {
            int freq = frequency.getOrDefault(value, 0);
            if (freq == 0) return;
            FreqValuePair element = new FreqValuePair(freq, value);
            if (topX.remove(element)) {
                currentSum[0] -= (long) element.freq * element.value;
            } else {
                remaining.remove(element);
            }
        };

        // sliding window
        for (int i = 0; i < n; i++) {
            // add new element
            removeFromSets.accept(nums[i]);
            frequency.put(nums[i], frequency.getOrDefault(nums[i], 0) + 1);
            addToSets.accept(nums[i]);

            int windowStart = i - k + 1;
            if (windowStart < 0) continue; // window not full yet

            // balance sets
            while (!remaining.isEmpty() && topX.size() < x) {
                FreqValuePair best = remaining.last();
                currentSum[0] += (long) best.freq * best.value;
                remaining.remove(best);
                topX.add(best);
            }

            while (topX.size() > x) {
                FreqValuePair worst = topX.first();
                currentSum[0] -= (long) worst.freq * worst.value;
                topX.remove(worst);
                remaining.add(worst);
            }

            // save current sum
            result[resultIndex++] = currentSum[0];

            // remove element leaving window
            removeFromSets.accept(nums[windowStart]);
            frequency.put(nums[windowStart], frequency.get(nums[windowStart]) - 1);
            if (frequency.get(nums[windowStart]) == 0)
                frequency.remove(nums[windowStart]);
            addToSets.accept(nums[windowStart]);
        }

        return result;
    }

}

//Complexity:
// time - O(n log k)
// space - O(n)


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
//nums.length == n
//1 <= n <= 105
//1 <= nums[i] <= 109
//1 <= x <= k <= nums.length
