package P2801_2900.P2818_Apply_Operations_to_Maximize_Score;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        List<Integer> nums1 = Arrays.asList(8, 3, 9, 3, 8);
        int k1 = 2;
        System.out.println(solution.maximumScore(nums1, k1)); // Output: 81

        List<Integer> nums2 = Arrays.asList(19, 12, 14, 6, 10, 18);
        int k2 = 3;
        System.out.println(solution.maximumScore(nums2, k2)); // Output: 4788
    }

    private static final int MOD = 1_000_000_007;

    public int maximumScore(List<Integer> nums, int k) {
        int n = nums.size();
        int maxNum = Collections.max(nums);
        int[] minPrimeFactors = sieveEratosthenes(maxNum + 1);
        int[] primeScores = getPrimeScores(nums, minPrimeFactors);
        int ans = 1;

        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, n);
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; --i) {
            while (!stack.isEmpty() && primeScores[stack.peek()] <= primeScores[i]) {
                left[stack.pop()] = i;
            }
            stack.push(i);
        }

        stack.clear();

        for (int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && primeScores[stack.peek()] < primeScores[i]) {
                right[stack.pop()] = i;
            }
            stack.push(i);
        }

        List<int[]> numAndIndexes = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            numAndIndexes.add(new int[]{nums.get(i), i});
        }
        numAndIndexes.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);

        for (int[] pair : numAndIndexes) {
            int num = pair[0], i = pair[1];
            long rangeCount = (long) (i - left[i]) * (right[i] - i);
            long actualCount = Math.min(rangeCount, (long) k);
            k -= actualCount;
            ans = (int) ((long) ans * modPow(num, actualCount) % MOD);
            if (k == 0) break;
        }

        return ans;
    }

    private long modPow(long x, long n) {
        if (n == 0) return 1;
        if (n % 2 == 1) return x * modPow(x % MOD, n - 1) % MOD;
        return modPow(x * x % MOD, n / 2) % MOD;
    }

    private int[] sieveEratosthenes(int n) {
        int[] minPrimeFactors = new int[n + 1];
        for (int i = 2; i <= n; i++) minPrimeFactors[i] = i;
        for (int i = 2; i * i <= n; i++) {
            if (minPrimeFactors[i] == i) {
                for (int j = i * i; j <= n; j += i) {
                    if (minPrimeFactors[j] == j) minPrimeFactors[j] = i;
                }
            }
        }
        return minPrimeFactors;
    }

    private int[] getPrimeScores(List<Integer> nums, int[] minPrimeFactors) {
        int[] primeScores = new int[nums.size()];
        for (int i = 0; i < nums.size(); i++) {
            primeScores[i] = getPrimeScore(nums.get(i), minPrimeFactors);
        }
        return primeScores;
    }

    private int getPrimeScore(int num, int[] minPrimeFactors) {
        Set<Integer> primeFactors = new HashSet<>();
        while (num > 1) {
            int divisor = minPrimeFactors[num];
            primeFactors.add(divisor);
            while (num % divisor == 0) num /= divisor;
        }
        return primeFactors.size();
    }

}

//Explanation:
//Precompute Prime Scores:
//-Compute the smallest prime factor for numbers up to the maximum value in nums using Sieve of Eratosthenes.
//-Use these factors to determine the prime score of each number, which is the count of distinct prime factors.
//Determine Next Greater Elements:
//-Calculate left[i]: the nearest index on the left where the prime score is greater or equal.
//-Calculate right[i]: the nearest index on the right where the prime score is greater.
//-This is done using monotonic stacks, ensuring efficient O(n) time complexity.
//Sort Numbers in Descending Order:
//-Each number is stored as {value, index} pairs.
//-Sorting ensures that we start picking numbers from the largest to the smallest.
//Calculate Contribution of Each Number:
//-A number at index i can be the maximum in (i - left[i]) * (right[i] - i) different subarrays.
//-Select as many subarrays as possible while k > 0.
//-Multiply contributions to ans using modular exponentiation (modPow) for efficiency.
// Time and Space complexity: O(n)


//You are given an array nums of n positive integers and an integer k.
//Initially, you start with a score of 1. You have to maximize your score by applying the following operation
// at most k times:
//Choose any non-empty subarray nums[l, ..., r] that you haven't chosen previously.
//Choose an element x of nums[l, ..., r] with the highest prime score. If multiple such elements exist, choose the
// one with the smallest index.
//Multiply your score by x.
//Here, nums[l, ..., r] denotes the subarray of nums starting at index l and ending at the index r, both ends
// being inclusive.
//The prime score of an integer x is equal to the number of distinct prime factors of x. For example, the prime
// score of 300 is 3 since 300 = 2 * 2 * 3 * 5 * 5.
//Return the maximum possible score after applying at most k operations.
//Since the answer may be large, return it modulo 109 + 7.

//Example 1:
//Input: nums = [8,3,9,3,8], k = 2
//Output: 81
//Explanation: To get a score of 81, we can apply the following operations:
//- Choose subarray nums[2, ..., 2]. nums[2] is the only element in this subarray. Hence, we multiply the score
// by nums[2]. The score becomes 1 * 9 = 9.
//- Choose subarray nums[2, ..., 3]. Both nums[2] and nums[3] have a prime score of 1, but nums[2] has the
// smaller index. Hence, we multiply the score by nums[2]. The score becomes 9 * 9 = 81.
//It can be proven that 81 is the highest score one can obtain.

//Example 2:
//Input: nums = [19,12,14,6,10,18], k = 3
//Output: 4788
//Explanation: To get a score of 4788, we can apply the following operations:
//- Choose subarray nums[0, ..., 0]. nums[0] is the only element in this subarray. Hence, we multiply the score
// by nums[0]. The score becomes 1 * 19 = 19.
//- Choose subarray nums[5, ..., 5]. nums[5] is the only element in this subarray. Hence, we multiply the score
// by nums[5]. The score becomes 19 * 18 = 342.
//- Choose subarray nums[2, ..., 3]. Both nums[2] and nums[3] have a prime score of 2, but nums[2] has the smaller
// index. Hence, we multipy the score by nums[2]. The score becomes 342 * 14 = 4788.
//It can be proven that 4788 is the highest score one can obtain.

//Constraints:
//1 <= nums.length == n <= 105
//1 <= nums[i] <= 105
//1 <= k <= min(n * (n + 1) / 2, 109)
