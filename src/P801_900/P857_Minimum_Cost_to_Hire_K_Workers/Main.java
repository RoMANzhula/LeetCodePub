package P801_900.P857_Minimum_Cost_to_Hire_K_Workers;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] quality1 = {10, 20, 5};
        int[] wage1 = {70, 50, 30};
        int k1 = 2;
        System.out.println("Example 1: " + solution.mincostToHireWorkers(quality1, wage1, k1)); // Example 1: 105.0

        int[] quality2 = {3, 1, 10, 10, 1};
        int[] wage2 = {4, 8, 2, 2, 7};
        int k2 = 3;
        System.out.println("Example 2: " + solution.mincostToHireWorkers(quality2, wage2, k2)); // Example 2: 30.666666666666664
    }

    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int n = quality.length;
        double[][] workers = new double[n][2];

        for (int i = 0; i < n; i++) {
            workers[i][0] = (double) wage[i] / quality[i];
            workers[i][1] = quality[i];
        }

        // Sort the workers by their wage per quality ratio
        Arrays.sort(workers, (a, b) -> Double.compare(a[0], b[0]));

        // Create a max heap to store the largest k qualities
        PriorityQueue<Double> maxHeap = new PriorityQueue<>(k, (a, b) -> Double.compare(b, a));

        int qualitySum = 0;
        double minCost = Double.MAX_VALUE;

        for (double[] worker : workers) {
            double wagePerQuality = worker[0];
            qualitySum += worker[1];
            maxHeap.offer(worker[1]);

            if (maxHeap.size() > k) {
                qualitySum -= maxHeap.poll();
            }

            if (maxHeap.size() == k) {
                minCost = Math.min(minCost, qualitySum * wagePerQuality);
            }
        }

        return minCost;
    }
}

//Підготовка даних:
//Ми спочатку обчислюємо відношення "зарплата до якості" для кожного робітника. Для цього ділимо зарплату на якість.
//Створюємо масив робітників, де кожен робітник має два значення: його відношення "зарплата до якості" та його якість.
//Сортування робітників:
//Сортуємо робітників за їхнім відношенням "зарплата до якості". Це допоможе нам вибрати найменшу можливу ціну при формуванні групи.
//Створення черги максимуму:
//Ми використовуємо пріоритетну чергу (по суті, вона є чергою максимуму), щоб відстежувати найбільші якості серед робітників, яких ми вибрали для групи. Це допомагає нам зберігати лише k найбільших якостей.
//Прохід по відсортованим робітникам:
//Ми проходимо по відсортованим робітникам.
//Додаємо якість кожного робітника до загальної суми якостей групи та додаємо його якість до черги максимуму.
//Якщо розмір черги перевищує k, видаляємо найнижчу якість з черги та віднімаємо її з загальної суми якостей.
//Якщо розмір черги дорівнює k, ми обчислюємо суму зарплат для цієї групи робітників, множимо її на відношення
// "зарплата до якості" останнього доданого робітника та оновлюємо мінімальну вартість, якщо вона менша за
// попередню мінімальну вартість.
//Повернення мінімальної вартості:
//Повертаємо обчислену мінімальну вартість, яка відповідає умовам задачі.
//Це дозволяє нам знайти мінімальну вартість для найбільш оптимального найму робітників, які відповідають вимогам задачі.

//There are n workers. You are given two integer arrays quality and wage where quality[i] is the quality of
// the ith worker and wage[i] is the minimum wage expectation for the ith worker.
//We want to hire exactly k workers to form a paid group. To hire a group of k workers, we must pay them according
// to the following rules:
//Every worker in the paid group must be paid at least their minimum wage expectation.
//In the group, each worker's pay must be directly proportional to their quality. This means if a worker’s
// quality is double that of another worker in the group, then they must be paid twice as much as the other worker.
//Given the integer k, return the least amount of money needed to form a paid group satisfying the above
// conditions. Answers within 10-5 of the actual answer will be accepted.
//
//Example 1:
//Input: quality = [10,20,5], wage = [70,50,30], k = 2
//Output: 105.00000
//Explanation: We pay 70 to 0th worker and 35 to 2nd worker.

//Example 2:
//Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], k = 3
//Output: 30.66667
//Explanation: We pay 4 to 0th worker, 13.33333 to 2nd and 3rd workers separately.
//
//Constraints:
//n == quality.length == wage.length
//1 <= k <= n <= 104
//1 <= quality[i], wage[i] <= 104