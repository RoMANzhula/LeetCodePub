package P1201_1300.P1292_Maximum_Side_Length_of_a_Square_with_Sum_Less_than_or_Equal_to_Threshold;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] mat1 = {
                {1, 1, 3, 2, 4, 3, 2},
                {1, 1, 3, 2, 4, 3, 2},
                {1, 1, 3, 2, 4, 3, 2}
        };
        int threshold1 = 4;
        System.out.println(solution.maxSideLength(mat1, threshold1)); // 2

        int[][] mat2 = {
                {2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2}
        };
        int threshold2 = 1;
        System.out.println(solution.maxSideLength(mat2, threshold2)); // 0
    }

    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;

        //prefix sum matrix (1-based indexing)
        int[][] prefix = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                prefix[i][j] = mat[i - 1][j - 1]
                        + prefix[i - 1][j]
                        + prefix[i][j - 1]
                        - prefix[i - 1][j - 1];
            }
        }

        int left = 0;
        int right = Math.min(m, n);
        int answer = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (existsSquare(prefix, mid, threshold)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    private static boolean existsSquare(int[][] prefix, int len, int threshold) {
        if (len == 0) return true;

        int m = prefix.length - 1;
        int n = prefix[0].length - 1;

        for (int i = len; i <= m; i++) {
            for (int j = len; j <= n; j++) {
                int sum = prefix[i][j]
                        - prefix[i - len][j]
                        - prefix[i][j - len]
                        + prefix[i - len][j - len]
                ;

                if (sum <= threshold) {
                    return true;
                }
            }
        }

        return false;
    }

}

//Complexity:
// time - O(m * n * log(min(m,n))
// space - O(m * n)


//Given a m x n matrix mat and an integer threshold, return the maximum side-length of a square with a sum less than
// or equal to threshold or return 0 if there is no such square.

//Example 1:
//Input: mat = [[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]], threshold = 4
//Output: 2
//Explanation: The maximum side length of square with sum less than 4 is 2 as shown.

//Example 2:
//Input: mat = [[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2]], threshold = 1
//Output: 0

//Constraints:
//m == mat.length
//n == mat[i].length
//1 <= m, n <= 300
//0 <= mat[i][j] <= 104
//0 <= threshold <= 105
