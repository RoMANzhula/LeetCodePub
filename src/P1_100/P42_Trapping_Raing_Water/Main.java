package P1_100.P42_Trapping_Raing_Water;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] height1 = {0,1,0,2,1,0,1,3,2,1,2,1};
        int[] height2 = {4,2,0,3,2,5};

        System.out.println("Test Case 1: " + solution.trap(height1));
        System.out.println("Test Case 2: " + solution.trap(height2));
    }

    public int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int water = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    water += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    water += rightMax - height[right];
                }
                right--;
            }
        }

        return water;
    }

//    public int trap(int[] height) {
//        int n = height.length;
//
//        if (n <= 2) {
//            return 0;
//        }
//
//        int totalOCount = 0;
//        int[] leftMax = new int[n];
//        int[] rightMax = new int[n];
//
//        leftMax[0] = height[0];
//        for (int i = 1; i < n; i++) {
//            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
//        }
//
//        rightMax[n - 1] = height[n - 1];
//        for (int i = n - 2; i >= 0; i--) {
//            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
//        }
//
//        for (int i = 1; i < n - 1; i++) {
//            int minHeight = Math.min(leftMax[i - 1], rightMax[i + 1]);
//            if (minHeight > height[i]) {
//                totalOCount += minHeight - height[i];
//            }
//        }
//
//        return totalOCount;
//    }

//    public int trap(int[] height) {
//        int n = height.length;
//
//        int maxHeight = Arrays.stream(height).max().getAsInt();
//        char[][] waterArray = new char[maxHeight][n];
//
//        int totalEmptyCells = 0;
//        int countEmptyCells = 0;
//
//        for (int i = 0; i < n; i++) {
//            if (height[i] == 0) {
//                countEmptyCells++;
//            } else {
//                totalEmptyCells += countEmptyCells;
//                countEmptyCells = 0;
//            }
//        }
//
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < maxHeight; j++) {
//                if (j < height[i]) {
//                    waterArray[maxHeight - j - 1][i] = 'x';
//                } else {
//                    waterArray[maxHeight - j - 1][i] = 'o';
//                }
//            }
//        }
//
//        // Count the total 'o' count between 'x'
//        int totalOCount = 0;
//        for (int i = 0; i < maxHeight; i++) {
//            int startX = -1;
//            for (int j = 0; j < n; j++) {
//                if (waterArray[i][j] == 'x') {
//                    if (startX == -1) {
//                        startX = j;
//                    } else {
//                        totalOCount += j - startX - 1;
//                        startX = j;
//                    }
//                }
//            }
//        }
//        return totalOCount;
//    }
}

//Задача полягає в обчисленні кількості води, яка може затриматися на рельєфі, представленому масивом висот. Кожен
// елемент масиву висот відображає висоту стовпця води у відповідній позиції.
//Наш підхід базується на використанні двох вказівників, які рухаються зліва направо та зправа наліво. Під час
// кожної ітерації ми порівнюємо висоти стовпців, на які вказують ці вказівники. Якщо висота стовпця, на який
// вказує лівий вказівник, менше висоти стовпця, на який вказує правий вказівник, то ми обчислюємо кількість
// води, яку може затримати ця позиція, використовуючи висоту стовпця, на який вказує лівий вказівник, як
// рівень води. Потім переміщуємо лівий вказівник вправо або правий вказівник вліво, залежно від того, яка
// з висот більша. Повторюємо цей процес до тих пір, поки лівий вказівник не дорівняє правому.
//Коли ми завершили цикл, ми отримали кількість води, яка може бути затримана між стовпцями висот.

//Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much
// water it can trap after raining.
//
//Example 1:
//Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
//Output: 6
//Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case,
// 6 units of rain water (blue section) are being trapped.

//Example 2:
//Input: height = [4,2,0,3,2,5]
//Output: 9
//
//Constraints:
//n == height.length
//1 <= n <= 2 * 104
//0 <= height[i] <= 105
