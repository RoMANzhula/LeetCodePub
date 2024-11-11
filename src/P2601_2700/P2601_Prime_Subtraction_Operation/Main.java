package P2601_2700.P2601_Prime_Subtraction_Operation;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {4, 9, 6, 10};
        System.out.println("Example 1: " + solution.primeSubOperation(nums1)); // Expected output: true

        // Example 2
        int[] nums2 = {6, 8, 11, 12};
        System.out.println("Example 2: " + solution.primeSubOperation(nums2)); // Expected output: true

        // Example 3
        int[] nums3 = {5, 8, 3};
        System.out.println("Example 3: " + solution.primeSubOperation(nums3)); // Expected output: false
    }

    public boolean primeSubOperation(int[] nums) {
        List<Integer> primes = new ArrayList<>();

        // Generate all prime numbers up to 1000
        for (int i = 2; i <= 1000; i++) {
            boolean isPrime = true;
            for (int prime : primes) {
                if (i % prime == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primes.add(i);
            }
        }

        int size = nums.length;

        // Process each number in the array starting from the second-to-last
        for (int i = size - 2; i >= 0; i--) {
            // If the current element is smaller than the next one, move on
            if (nums[i] < nums[i + 1]) {
                continue;
            }

            // Find the smallest prime such that nums[i] - prime is larger than nums[i + 1]
            int diff = nums[i] - nums[i + 1];
            int idx = upperBound(primes, diff);

            // If no such prime exists or the prime is greater than or equal to nums[i], return false
            if (idx == primes.size() || primes.get(idx) >= nums[i]) {
                return false;
            }

            // Reduce nums[i] by the found prime number to satisfy the condition
            nums[i] -= primes.get(idx);
        }

        return true;
    }

    // Helper method for upper bound (similar to C++ std::upper_bound)
    private int upperBound(List<Integer> list, int value) {
        int left = 0, right = list.size();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) <= value) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

}


//You are given a 0-indexed integer array nums of length n.
//You can perform the following operation as many times as you want:
//Pick an index i that you haven’t picked before, and pick a prime p strictly less than nums[i], then
// subtract p from nums[i].
//Return true if you can make nums a strictly increasing array using the above operation and false otherwise.
//A strictly increasing array is an array whose each element is strictly greater than its preceding element.
//
//Example 1:
//Input: nums = [4,9,6,10]
//Output: true
//Explanation: In the first operation: Pick i = 0 and p = 3, and then subtract 3 from nums[0], so
// that nums becomes [1,9,6,10].
//In the second operation: i = 1, p = 7, subtract 7 from nums[1], so nums becomes equal to [1,2,6,10].
//After the second operation, nums is sorted in strictly increasing order, so the answer is true.

//Example 2:
//Input: nums = [6,8,11,12]
//Output: true
//Explanation: Initially nums is sorted in strictly increasing order, so we don't need to make any operations.

//Example 3:
//Input: nums = [5,8,3]
//Output: false
//Explanation: It can be proven that there is no way to perform operations to make nums sorted in
// strictly increasing order, so the answer is false.
//
//Constraints:
//1 <= nums.length <= 1000
//1 <= nums[i] <= 1000
//nums.length == n