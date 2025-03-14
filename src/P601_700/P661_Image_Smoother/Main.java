package P601_700.P661_Image_Smoother;

public class Main {

    public static void main(String[] args) {
        int[][] img1 = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        int[][] result1 = imageSmoother(img1);
        System.out.println("Input 1:");
        printMatrix(img1);
        System.out.println("Output 1:");
        printMatrix(result1);

        int[][] img2 = {{100, 200, 100}, {200, 50, 200}, {100, 200, 100}};
        int[][] result2 = imageSmoother(img2);
        System.out.println("Input 2:");
        printMatrix(img2);
        System.out.println("Output 2:");
        printMatrix(result2);
    }

    public static int[][] imageSmoother(int[][] img) {
        int m = img.length;
        int n = img[0].length;
        int[][] result = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = smoothCell(img, i, j);
            }
        }

        return result;
    }

    private static int smoothCell(int[][] img, int row, int col) {
        int m = img.length;
        int n = img[0].length;
        int sum = 0;
        int count = 0;

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (isValidCell(i, j, m, n)) {
                    sum += img[i][j];
                    count++;
                }
            }
        }

        return sum / count;
    }

    private static boolean isValidCell(int row, int col, int m, int n) {
        return row >= 0 && row < m && col >= 0 && col < n;
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

}


//An image smoother is a filter of the size 3 x 3 that can be applied to each cell of an image by rounding down
// the average of the cell and the eight surrounding cells (i.e., the average of the nine cells in the blue smoother).
// If one or more of the surrounding cells of a cell is not present, we do not consider it in the average (i.e., the
// average of the four cells in the red smoother).
//Given an m x n integer matrix img representing the grayscale of an image, return the image after applying the
// smoother on each cell of it.

//Example 1:
//Input: img = [[1,1,1],[1,0,1],[1,1,1]]
//Output: [[0,0,0],[0,0,0],[0,0,0]]
//Explanation:
//For the points (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0
//For the points (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0
//For the point (1,1): floor(8/9) = floor(0.88888889) = 0

//Example 2:
//Input: img = [[100,200,100],[200,50,200],[100,200,100]]
//Output: [[137,141,137],[141,138,141],[137,141,137]]
//Explanation:
//For the points (0,0), (0,2), (2,0), (2,2): floor((100+200+200+50)/4) = floor(137.5) = 137
//For the points (0,1), (1,0), (1,2), (2,1): floor((200+200+50+200+100+100)/6) = floor(141.666667) = 141
//For the point (1,1): floor((50+200+200+200+200+100+100+100+100)/9) = floor(138.888889) = 138

//Constraints:
//m == img.length
//n == img[i].length
//1 <= m, n <= 200
//0 <= img[i][j] <= 255