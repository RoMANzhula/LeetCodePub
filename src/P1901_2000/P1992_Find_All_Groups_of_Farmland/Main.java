package P1901_2000.P1992_Find_All_Groups_of_Farmland;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[][] land1 = {{1, 0, 0}, {0, 1, 1}, {0, 1, 1}};
        int[][] result1 = solution.findFarmland(land1);
        printResult(result1);

        int[][] land2 = {{1, 1}, {1, 1}};
        int[][] result2 = solution.findFarmland(land2);
        printResult(result2);

        int[][] land3 = {{0}};
        int[][] result3 = solution.findFarmland(land3);
        printResult(result3);
    }

    private static void printResult(int[][] result) {
        for (int[] row : result) {
            System.out.print("[");
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println("]");
        }
        System.out.println();
    }

    public int[][] findFarmland(int[][] land) {
        List<int[]> result = new ArrayList<>();

        int m = land.length;
        int n = land[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (land[i][j] == 1) {
                    int[] coordinates = findBottomRight(land, i, j);
                    result.add(new int[]{i, j, coordinates[0], coordinates[1]});
                    markVisited(land, i, j, coordinates[0], coordinates[1]);
                }
            }
        }

        return result.toArray(new int[0][]);
    }

    private int[] findBottomRight(int[][] land, int row, int col) {
        int m = land.length;
        int n = land[0].length;

        int bottom = row;
        int right = col;

        for (int i = row; i < m && land[i][col] == 1; i++) {
            for (int j = col; j < n && land[i][j] == 1; j++) {
                bottom = i;
                right = j;
            }
        }

        return new int[]{bottom, right};
    }

    private void markVisited(int[][] land, int rowStart, int colStart, int rowEnd, int colEnd) {
        for (int i = rowStart; i <= rowEnd; i++) {
            for (int j = colStart; j <= colEnd; j++) {
                land[i][j] = -1; // Mark visited cells
            }
        }
    }

}

//Ітерація по матриці: Першим кроком є ітерація по всіх елементах матриці land, щоб знайти початок кожної групи землі.
//Пошук кінця групи: Коли ми знаходимо початок групи (елемент зі значенням 1), нам потрібно знайти кінець цієї
// групи. Ми робимо це, шукаючи найнижчий рядок і найправіший стовпець, які також містять значення 1. Це буде
// нижній правий кут нашої групи.
//Позначення вже оброблених елементів: Після того, як ми знайшли групу, ми позначаємо всі її елементи як вже
// оброблені, встановлюючи їх значення на -1. Це потрібно, щоб уникнути повторного оброблення цієї ж групи у
// майбутньому під час ітерації.
//Збереження координат групи: Після знаходження початку і кінця групи ми зберігаємо їх координати в масиві,
// який потім додається до списку результатів.
//Повернення результату: На завершення ми повертаємо список результатів, що містить координати груп землі.
//Цей підхід дозволяє нам ефективно знаходити групи землі в матриці та повертати їх координати.

//You are given a 0-indexed m x n binary matrix land where a 0 represents a hectare of forested land and
// a 1 represents a hectare of farmland.
//To keep the land organized, there are designated rectangular areas of hectares that consist entirely of
// farmland. These rectangular areas are called groups. No two groups are adjacent, meaning farmland in
// one group is not four-directionally adjacent to another farmland in a different group.
//land can be represented by a coordinate system where the top left corner of land is (0, 0) and the bottom right
// corner of land is (m-1, n-1). Find the coordinates of the top left and bottom right corner of each group of
// farmland. A group of farmland with a top left corner at (r1, c1) and a bottom right corner at (r2, c2) is
// represented by the 4-length array [r1, c1, r2, c2].
//Return a 2D array containing the 4-length arrays described above for each group of farmland in land. If there
// are no groups of farmland, return an empty array. You may return the answer in any order.
//
//Example 1:
//Input: land = [[1,0,0],[0,1,1],[0,1,1]]
//Output: [[0,0,0,0],[1,1,2,2]]
//Explanation:
//The first group has a top left corner at land[0][0] and a bottom right corner at land[0][0].
//The second group has a top left corner at land[1][1] and a bottom right corner at land[2][2].

//Example 2:
//Input: land = [[1,1],[1,1]]
//Output: [[0,0,1,1]]
//Explanation:
//The first group has a top left corner at land[0][0] and a bottom right corner at land[1][1].

//Example 3:
//Input: land = [[0]]
//Output: []
//Explanation:
//There are no groups of farmland.
//
//Constraints:
//m == land.length
//n == land[i].length
//1 <= m, n <= 300
//land consists of only 0's and 1's.
//Groups of farmland are rectangular in shape.
