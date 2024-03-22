package P701_800.P739_Daily_Temperatures;

import java.util.Arrays;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        int[] temperatures1 = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] result1 = dailyTemperatures(temperatures1);
        System.out.println("Output for Example 1: " + Arrays.toString(result1)); // output [1, 1, 4, 2, 1, 1, 0, 0]

        int[] temperatures2 = {30, 40, 50, 60};
        int[] result2 = dailyTemperatures(temperatures2);
        System.out.println("Output for Example 2: " + Arrays.toString(result2)); // output [1, 1, 1, 0]

        int[] temperatures3 = {30, 60, 90};
        int[] result3 = dailyTemperatures(temperatures3);
        System.out.println("Output for Example 3: " + Arrays.toString(result3)); // output [1, 1, 0]
    }

    public static int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int idx = stack.pop();
                result[idx] = i - idx;
            }
            stack.push(i);
        }

        return result;
    }
}

//Задача передбачає знаходження кількості днів, які потрібно зачекати після кожного дня, щоб отримати температуру
// теплішу, ніж у поточний день. Якщо немає майбутніх днів із теплішою температурою, то відповідний елемент
// в результативному масиві буде рівний 0.
//
//Алгоритм використовує стек для відстеження індексів днів, для яких ми ще не знайшли тепліший день. Ми проходимо
// по масиву температур. Якщо температура поточного дня більша, ніж температура дня, що знаходиться на вершині
// стеку, то це означає, що поточний день став теплішим. Тому ми видаляємо індекс з вершини стеку і
// встановлюємо відповідний елемент в результативному масиві.


//Given an array of integers temperatures represents the daily temperatures, return an array answer
// such that answer[i] is the number of days you have to wait after the ith day to get a warmer
// temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.

//Example 1:
//Input: temperatures = [73,74,75,71,69,72,76,73]
//Output: [1,1,4,2,1,1,0,0]

//Example 2:
//Input: temperatures = [30,40,50,60]
//Output: [1,1,1,0]

//Example 3:
//Input: temperatures = [30,60,90]
//Output: [1,1,0]

//Constraints:
//1 <= temperatures.length <= 105
//30 <= temperatures[i] <= 100
