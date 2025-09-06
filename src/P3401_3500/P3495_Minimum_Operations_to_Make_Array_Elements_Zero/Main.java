package P3401_3500.P3495_Minimum_Operations_to_Make_Array_Elements_Zero;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] queries1 = {{1, 2}, {2, 4}};
        System.out.println(solution.minOperations(queries1)); // Output 3

        int[][] queries2 = {{2, 6}};
        System.out.println(solution.minOperations(queries2)); // Output 4
    }

    // count steps for one number
    private long steps(long x) {
        long cnt = 0;
        while (x > 0) {
            x /= 4;
            cnt++;
        }
        return cnt;
    }

    // prefix sum of steps(1..n)
    private long prefixSteps(long n) {
        if (n <= 0) return 0;
        long total = 0;
        long pow = 1;  // 4^0
        long level = 1;

        while (pow <= n) {
            long nextPow = pow * 4;
            long count = Math.min(n, nextPow - 1) - pow + 1;
            total += count * level;
            pow = nextPow;
            level++;
        }
        return total;
    }

    public long minOperations(int[][] queries) {
        long result = 0;

        for (int[] q : queries) {
            int l = q[0], r = q[1];
            long sumSteps = prefixSteps(r) - prefixSteps(l - 1);
            long ops = (sumSteps + 1) / 2; // ceil(sumSteps/2)
            result += ops;
        }

        return result;
    }

}

//Complexity:
// time - O(log n)
// space - O(1)


//You are given a 2D array queries, where queries[i] is of the form [l, r]. Each queries[i] defines an array of
// integers nums consisting of elements ranging from l to r, both inclusive.
//In one operation, you can:
//Select two integers a and b from the array.
//Replace them with floor(a / 4) and floor(b / 4).
//Your task is to determine the minimum number of operations required to reduce all elements of the array to
// zero for each query. Return the sum of the results for all queries.

//Example 1:
//Input: queries = [[1,2],[2,4]]
//Output: 3
//Explanation:
//For queries[0]:
//The initial array is nums = [1, 2].
//In the first operation, select nums[0] and nums[1]. The array becomes [0, 0].
//The minimum number of operations required is 1.
//For queries[1]:
//The initial array is nums = [2, 3, 4].
//In the first operation, select nums[0] and nums[2]. The array becomes [0, 3, 1].
//In the second operation, select nums[1] and nums[2]. The array becomes [0, 0, 0].
//The minimum number of operations required is 2.
//The output is 1 + 2 = 3.

//Example 2:
//Input: queries = [[2,6]]

//Output: 4
//Explanation:
//For queries[0]:
//The initial array is nums = [2, 3, 4, 5, 6].
//In the first operation, select nums[0] and nums[3]. The array becomes [0, 3, 4, 1, 6].
//In the second operation, select nums[2] and nums[4]. The array becomes [0, 3, 1, 1, 1].
//In the third operation, select nums[1] and nums[2]. The array becomes [0, 0, 0, 1, 1].
//In the fourth operation, select nums[3] and nums[4]. The array becomes [0, 0, 0, 0, 0].
//The minimum number of operations required is 4.
//The output is 4.

//Constraints:
//1 <= queries.length <= 105
//queries[i].length == 2
//queries[i] == [l, r]
//1 <= l < r <= 109
