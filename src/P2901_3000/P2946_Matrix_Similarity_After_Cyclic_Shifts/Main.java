package P2901_3000.P2946_Matrix_Similarity_After_Cyclic_Shifts;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] mat1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        System.out.println(solution.areSimilar(mat1, 4)); // false

        int[][] mat2 = {
                {1, 2, 1, 2},
                {5, 5, 5, 5},
                {6, 3, 6, 3}
        };
        System.out.println(solution.areSimilar(mat2, 2)); // true

        int[][] mat3 = {
                {2, 2},
                {2, 2}
        };
        System.out.println(solution.areSimilar(mat3, 3)); // true
    }

    public boolean areSimilar(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;

        int shift = k % n; // effective shift

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                int newIndex;

                if (i % 2 == 0) {
                    // even row -> left shift
                    newIndex = (j + shift) % n;
                } else {
                    // odd row -> right shift
                    newIndex = (j - shift + n) % n;
                }

                if (mat[i][j] != mat[i][newIndex]) {
                    return false;
                }
            }
        }

        return true;
    }

}

//Complexity:
// time - O(m * n)
// space - O(1)


//You are given an m x n integer matrix mat and an integer k. The matrix rows are 0-indexed.
//The following proccess happens k times:
//Even-indexed rows (0, 2, 4, ...) are cyclically shifted to the left. here image
//Odd-indexed rows (1, 3, 5, ...) are cyclically shifted to the right. here image
//Return true if the final modified matrix after k steps is identical to the original matrix, and false otherwise.

//Example 1:
//Input: mat = [[1,2,3],[4,5,6],[7,8,9]], k = 4
//Output: false
//Explanation:
//In each step left shift is applied to rows 0 and 2 (even indices), and right shift to row 1 (odd index).
// here image

//Example 2:
//Input: mat = [[1,2,1,2],[5,5,5,5],[6,3,6,3]], k = 2
//Output: true
//Explanation: here image

//Example 3:
//Input: mat = [[2,2],[2,2]], k = 3
//Output: true
//Explanation:
//As all the values are equal in the matrix, even after performing cyclic shifts the matrix will remain the same.

//Constraints:
//1 <= mat.length <= 25
//1 <= mat[i].length <= 25
//1 <= mat[i][j] <= 25
//1 <= k <= 50
