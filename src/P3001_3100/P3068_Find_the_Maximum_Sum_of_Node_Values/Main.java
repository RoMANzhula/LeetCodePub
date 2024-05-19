package P3001_3100.P3068_Find_the_Maximum_Sum_of_Node_Values;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 2, 1};
        int k1 = 3;
        int[][] edges1 = {{0, 1}, {0, 2}};
        System.out.println(solution.maximumValuesSum(nums1, k1, edges1)); // Output: 6

        int[] nums2 = {2, 3};
        int k2 = 7;
        int[][] edges2 = {{0, 1}};
        System.out.println(solution.maximumValuesSum(nums2, k2, edges2)); // Output: 9

        int[] nums3 = {7, 7, 7, 7, 7, 7};
        int k3 = 3;
        int[][] edges3 = {{0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}};
        System.out.println(solution.maximumValuesSum(nums3, k3, edges3)); // Output: 42
    }

    public long maximumValuesSum(int[] nums, int k, int[][] edges) {
        long maxSum = 0;
        int changedCount = 0;
        int minChangeDiff = Integer.MAX_VALUE;

        for (int num : nums) {
            int original = num;
            int xorValue = num ^ k;

            maxSum += Math.max(original, xorValue);

            if (xorValue > original) {
                changedCount++;
            }

            int changeDiff = Math.abs(original - xorValue);
            minChangeDiff = Math.min(minChangeDiff, changeDiff);
        }

        if (changedCount % 2 == 0) {
            return maxSum;
        } else {
            return maxSum - minChangeDiff;
        }
    }
}

//Пояснення:
//Обчислення maxSum: Для кожного елемента в nums обчислюємо максимальне значення між оригінальним значенням
// та значенням після XOR з k.
//Обчислення changedCount: Підраховуємо кількість вузлів, де значення після XOR більше за оригінальне.
//Перевірка парності changedCount: Якщо кількість змінених вузлів парна, повертаємо maxSum. Інакше, віднімаємо
// мінімальну різницю між оригінальним та XOR'ованим значенням від maxSum.

//There exists an undirected tree with n nodes numbered 0 to n - 1. You are given a 0-indexed 2D integer array
// edges of length n - 1, where edges[i] = [ui, vi] indicates that there is an edge between nodes ui and vi in
// the tree. You are also given a positive integer k, and a 0-indexed array of non-negative integers nums of
// length n, where nums[i] represents the value of the node numbered i.
//Alice wants the sum of values of tree nodes to be maximum, for which Alice can perform the following operation
// any number of times (including zero) on the tree:
//Choose any edge [u, v] connecting the nodes u and v, and update their values as follows:
//nums[u] = nums[u] XOR k
//nums[v] = nums[v] XOR k
//Return the maximum possible sum of the values Alice can achieve by performing the operation any number of times.
//
//Example 1:
//Input: nums = [1,2,1], k = 3, edges = [[0,1],[0,2]]
//Output: 6
//Explanation: Alice can achieve the maximum sum of 6 using a single operation:
//- Choose the edge [0,2]. nums[0] and nums[2] become: 1 XOR 3 = 2, and the array nums becomes: [1,2,1] -> [2,2,2].
//The total sum of values is 2 + 2 + 2 = 6.
//It can be shown that 6 is the maximum achievable sum of values.

//Example 2:
//Input: nums = [2,3], k = 7, edges = [[0,1]]
//Output: 9
//Explanation: Alice can achieve the maximum sum of 9 using a single operation:
//- Choose the edge [0,1]. nums[0] becomes: 2 XOR 7 = 5 and nums[1] become: 3 XOR 7 = 4, and the array
// nums becomes: [2,3] -> [5,4].
//The total sum of values is 5 + 4 = 9.
//It can be shown that 9 is the maximum achievable sum of values.

//Example 3:
//Input: nums = [7,7,7,7,7,7], k = 3, edges = [[0,1],[0,2],[0,3],[0,4],[0,5]]
//Output: 42
//Explanation: The maximum achievable sum is 42 which can be achieved by Alice performing no operations.
//
//Constraints:
//2 <= n == nums.length <= 2 * 104
//1 <= k <= 109
//0 <= nums[i] <= 109
//edges.length == n - 1
//edges[i].length == 2
//0 <= edges[i][0], edges[i][1] <= n - 1
//The input is generated such that edges represent a valid tree.