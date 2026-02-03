package P3601_3700.P3637_Trionic_Array_I;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.isTrionic(new int[]{1, 3, 5, 4, 2, 6}));    // true
        System.out.println(solution.isTrionic(new int[]{2, 1, 3}));             // false
        System.out.println(solution.isTrionic(new int[]{1, 2, 3}));             // false
        System.out.println(solution.isTrionic(new int[]{1, 3, 2, 1}));          // false
    }

    public boolean isTrionic(int[] nums) {
        int n = nums.length;
        if (n < 3) return false;

        int i = 0;

        // first strictly increasing part
        while (i + 1 < n && nums[i] < nums[i + 1]) {
            i++;
        }
        if (i == 0) return false; // no increasing part

        // strictly decreasing part
        int p = i;
        while (i + 1 < n && nums[i] > nums[i + 1]) {
            i++;
        }
        if (i == p) return false; // no decreasing part

        // final strictly increasing part
        int q = i;
        while (i + 1 < n && nums[i] < nums[i + 1]) {
            i++;
        }
        if (i == q) return false; // no final increasing part

        // must reach the end
        return i == n - 1;
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//You are given an integer array nums of length n.
//An array is trionic if there exist indices 0 < p < q < n − 1 such that:
//nums[0...p] is strictly increasing,
//nums[p...q] is strictly decreasing,
//nums[q...n − 1] is strictly increasing.
//Return true if nums is trionic, otherwise return false.

//Example 1:
//Input: nums = [1,3,5,4,2,6]
//Output: true
//Explanation:
//Pick p = 2, q = 4:
//nums[0...2] = [1, 3, 5] is strictly increasing (1 < 3 < 5).
//nums[2...4] = [5, 4, 2] is strictly decreasing (5 > 4 > 2).
//nums[4...5] = [2, 6] is strictly increasing (2 < 6).

//Example 2:
//Input: nums = [2,1,3]
//Output: false
//Explanation:
//There is no way to pick p and q to form the required three segments.

//Constraints:
//3 <= n <= 100
//-1000 <= nums[i] <= 1000
