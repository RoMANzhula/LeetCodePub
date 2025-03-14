package P2201_2300.P2226_Maximum_Candies_Allocated_to_K_Children;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] candies1 = {5, 8, 6};
        long k1 = 3;
        System.out.println("Output: " + solution.maximumCandies(candies1, k1)); // Expected Output: 5

        int[] candies2 = {2, 5};
        long k2 = 11;
        System.out.println("Output: " + solution.maximumCandies(candies2, k2)); // Expected Output: 0

        int[] candies3 = {10, 15, 20, 25};
        long k3 = 7;
        System.out.println("Output: " + solution.maximumCandies(candies3, k3)); // Expected Output: 8
    }

    public int maximumCandies(int[] candies, long k) {
        if (k > totalCandies(candies)) return 0;  // edge case: Not enough candies

        int left = 1, right = getMax(candies);
        int result = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (canDistribute(candies, k, mid)) {
                result = mid;  // possible answer, but let's check for a larger value
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    // function to check if we can distribute `mid` candies to `k` children
    private boolean canDistribute(int[] candies, long k, int mid) {
        long count = 0;  // Number of children that can be served
        for (int candy : candies) {
            count += candy / mid;
            if (count >= k) return true;  // no need to check further
        }
        return false;
    }

    // function to get the maximum element in the array
    private int getMax(int[] candies) {
        int max = 0;
        for (int candy : candies) {
            max = Math.max(max, candy);
        }
        return max;
    }

    // function to compute the total number of candies
    private long totalCandies(int[] candies) {
        long sum = 0;
        for (int candy : candies) {
            sum += candy;
        }
        return sum;
    }

}

//Complexity Analysis:
//Binary Search: O(log M), where M is the maximum candy count in a pile.
//Checking Feasibility (canDistribute method): O(N), where N is the number of elements in candies.
//Overall Complexity: O(N log M), which is efficient for large constraints.

//You are given a 0-indexed integer array candies. Each element in the array denotes a pile of candies of size
// candies[i]. You can divide each pile into any number of sub piles, but you cannot merge two piles together.
//You are also given an integer k. You should allocate piles of candies to k children such that each child gets
// the same number of candies. Each child can be allocated candies from only one pile of candies and some piles of
// candies may go unused.
//Return the maximum number of candies each child can get.

//Example 1:
//Input: candies = [5,8,6], k = 3
//Output: 5
//Explanation: We can divide candies[1] into 2 piles of size 5 and 3, and candies[2] into 2 piles of size 5 and 1. We
// now have five piles of candies of sizes 5, 5, 3, 5, and 1. We can allocate the 3 piles of size 5 to 3 children. It
// can be proven that each child cannot receive more than 5 candies.

//Example 2:
//Input: candies = [2,5], k = 11
//Output: 0
//Explanation: There are 11 children but only 7 candies in total, so it is impossible to ensure each child receives
// at least one candy. Thus, each child gets no candy and the answer is 0.

//Constraints:
//1 <= candies.length <= 105
//1 <= candies[i] <= 107
//1 <= k <= 1012
