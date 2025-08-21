package P1501_1600.P1504_Count_Submatrices_With_All_Ones;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] mat1 = {{1,0,1},{1,1,0},{1,1,0}};
        System.out.println(solution.numSubmat(mat1)); // 13

        int[][] mat2 = {{0,1,1,0},{0,1,1,1},{1,1,1,0}};
        System.out.println(solution.numSubmat(mat2)); // 24
    }

    public int numSubmat(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int[][] height = new int[m][n];

        // build histogram heights
        for (int j = 0; j < n; j++) {
            height[0][j] = mat[0][j];
            for (int i = 1; i < m; i++) {
                if (mat[i][j] == 1) {
                    height[i][j] = height[i - 1][j] + 1;
                } else {
                    height[i][j] = 0;
                }
            }
        }

        int result = 0;

        // process each row as histogram base
        for (int i = 0; i < m; i++) {
            result += countSubmatFromHistogram(height[i]);
        }

        return result;
    }

    // count rectangles from histogram row
    private int countSubmatFromHistogram(int[] h) {
        int n = h.length;
        int res = 0;
        int[] sum = new int[n];
        java.util.Stack<Integer> stack = new java.util.Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && h[stack.peek()] >= h[i]) {
                stack.pop();
            }

            if (!stack.isEmpty()) {
                int prev = stack.peek();
                sum[i] = sum[prev] + h[i] * (i - prev);
            } else {
                sum[i] = h[i] * (i + 1);
            }

            stack.push(i);
            res += sum[i];
        }

        return res;
    }

}

//Complexity:
// time - O(m * n)
// space - O(n)


//Given an m x n binary matrix mat, return the number of submatrices that have all ones.

//Example 1:
//Input: mat = [[1,0,1],[1,1,0],[1,1,0]]
//Output: 13
//Explanation:
//There are 6 rectangles of side 1x1.
//There are 2 rectangles of side 1x2.
//There are 3 rectangles of side 2x1.
//There is 1 rectangle of side 2x2.
//There is 1 rectangle of side 3x1.
//Total number of rectangles = 6 + 2 + 3 + 1 + 1 = 13.

//Example 2:
//Input: mat = [[0,1,1,0],[0,1,1,1],[1,1,1,0]]
//Output: 24
//Explanation:
//There are 8 rectangles of side 1x1.
//There are 5 rectangles of side 1x2.
//There are 2 rectangles of side 1x3.
//There are 4 rectangles of side 2x1.
//There are 2 rectangles of side 2x2.
//There are 2 rectangles of side 3x1.
//There is 1 rectangle of side 3x2.
//Total number of rectangles = 8 + 5 + 2 + 4 + 2 + 2 + 1 = 24.

//Constraints:
//1 <= m, n <= 150
//mat[i][j] is either 0 or 1.
