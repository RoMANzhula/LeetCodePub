package P3601_3700.P3629_Minimum_Jumps_to_Reach_End_via_Prime_Teleportation;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 2, 4, 6};
        System.out.println(solution.minJumps(nums1)); // 2

        int[] nums2 = {2, 3, 4, 7, 9};
        System.out.println(solution.minJumps(nums2)); // 2

        int[] nums3 = {4, 6, 5, 8};
        System.out.println(solution.minJumps(nums3)); // 3
    }

    public int minJumps(int[] nums) {
        int n = nums.length;

        if (n == 1) {
            return 0;
        }

        // all indices whose value is divisible by prime
        Map<Integer, List<Integer>> primeToIndices = new HashMap<>();

        // factor map
        for (int i = 0; i < n; i++) {
            List<Integer> primeFactors = getPrimeFactors(nums[i]);

            for (int p : primeFactors) {
                primeToIndices
                        .computeIfAbsent(p, k -> new ArrayList<>())
                        .add(i);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];

        // avoid processing same prime teleport many times
        Set<Integer> usedPrimeTeleport = new HashSet<>();

        queue.offer(0);
        visited[0] = true;

        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int s = 0; s < size; s++) {
                int current = queue.poll();

                if (current == n - 1) {
                    return steps;
                }

                // adjacent left
                if (current - 1 >= 0 && !visited[current - 1]) {
                    visited[current - 1] = true;
                    queue.offer(current - 1);
                }

                // adjacent right
                if (current + 1 < n && !visited[current + 1]) {
                    visited[current + 1] = true;
                    queue.offer(current + 1);
                }

                // pprime teleportation
                int value = nums[current];

                if (isPrime(value) && !usedPrimeTeleport.contains(value)) {

                    List<Integer> nextIndices =
                            primeToIndices.getOrDefault(value, Collections.emptyList());

                    for (int next : nextIndices) {
                        if (!visited[next]) {
                            visited[next] = true;
                            queue.offer(next);
                        }
                    }

                    usedPrimeTeleport.add(value);
                }
            }

            steps++;
        }

        return -1;
    }

    private List<Integer> getPrimeFactors(int num) {
        List<Integer> factors = new ArrayList<>();

        for (int p = 2; p * p <= num; p++) {
            if (num % p == 0) {
                factors.add(p);

                while (num % p == 0) {
                    num /= p;
                }
            }
        }

        if (num > 1) {
            factors.add(num);
        }

        return factors;
    }

    private boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }

        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

}

//Complexity:
// time - O(n * sqrt(max(nums)) + n)
// space - O(n)


//You are given an integer array nums of length n.
//You start at index 0, and your goal is to reach index n - 1.
//From any index i, you may perform one of the following operations:
//Adjacent Step: Jump to index i + 1 or i - 1, if the index is within bounds.
//Prime Teleportation: If nums[i] is a prime number p, you may instantly jump to any index j != i such that
// nums[j] % p == 0.
//Return the minimum number of jumps required to reach index n - 1.

//Example 1:
//Input: nums = [1,2,4,6]
//Output: 2
//Explanation:
//One optimal sequence of jumps is:
//Start at index i = 0. Take an adjacent step to index 1.
//At index i = 1, nums[1] = 2 is a prime number. Therefore, we teleport to index i = 3 as nums[3] = 6 is divisible by 2.
//Thus, the answer is 2.

//Example 2:
//Input: nums = [2,3,4,7,9]
//Output: 2
//Explanation:
//One optimal sequence of jumps is:
//Start at index i = 0. Take an adjacent step to index i = 1.
//At index i = 1, nums[1] = 3 is a prime number. Therefore, we teleport to index i = 4 since nums[4] = 9 is
// divisible by 3.
//Thus, the answer is 2.

//Example 3:
//Input: nums = [4,6,5,8]
//Output: 3
//Explanation:
//Since no teleportation is possible, we move through 0 → 1 → 2 → 3. Thus, the answer is 3.

//Constraints:
//1 <= n == nums.length <= 105
//1 <= nums[i] <= 106
