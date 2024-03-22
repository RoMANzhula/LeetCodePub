package P601_700.P621_Task_Scheduler;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main scheduler = new Main();

        char[] tasks1 = {'A', 'A', 'A', 'B', 'B', 'B'};
        int n1 = 2;
        System.out.println("Example 1 Output: " + scheduler.leastInterval(tasks1, n1)); // Output: 8

        char[] tasks2 = {'A', 'C', 'A', 'B', 'D', 'B'};
        int n2 = 1;
        System.out.println("Example 2 Output: " + scheduler.leastInterval(tasks2, n2)); // Output: 6

        char[] tasks3 = {'A', 'A', 'A', 'B', 'B', 'B'};
        int n3 = 3;
        System.out.println("Example 3 Output: " + scheduler.leastInterval(tasks3, n3)); // Output: 10
    }

    public int leastInterval(char[] tasks, int n) {
        int[] frequencies = new int[26]; // frequencies of each task

        // count the frequencies of each task
        for (char task : tasks) {
            frequencies[task - 'A']++;
        }

        // sort the frequencies array to get the maximum frequency
        Arrays.sort(frequencies);

        // get the maximum frequency
        int maxFrequency = frequencies[25] - 1;

        // calculate the number of idle slots
        int idleSlots = maxFrequency * n;

        // fill in the idle slots with other tasks
        for (int i = 24; i >= 0 && frequencies[i] > 0; i--) {
            idleSlots -= Math.min(frequencies[i], maxFrequency);
        }

        // if there are any idle slots left, add them to the total intervals
        return idleSlots > 0 ? idleSlots + tasks.length : tasks.length;
    }
}

//Для вирішення цієї задачі ми використовуємо жадібний алгоритм. Ось як працює цей алгоритм:
//Спочатку ми обчислюємо частоту кожного типу задачі.
//Потім сортуємо ці частоти в порядку спадання, щоб мати можливість виконати задачі з найбільшою частотою в першу чергу.
//Ми обчислюємо максимальну частоту (кількість повторень найбільш часто зустрічаючоїся задачі) та кількість "порожніх"
// інтервалів між цими задачами.
//Далі ми заповнюємо ці порожні інтервали іншими задачами зі списку в порядку їх частоти. При цьому ми стараємося
// зменшити кількість порожніх інтервалів.
//Якщо після заповнення порожніх інтервалів залишаються ще деякі, ми просто додаємо їх до загальної кількості інтервалів.
//Це розв'язок, який дає нам мінімальну кількість інтервалів, необхідних для виконання всіх задач з врахуванням
// вимог щодо часу охолодження між повтореннями однакових задач.

//You are given an array of CPU tasks, each represented by letters A to Z, and a cooling time, n. Each cycle or
// interval allows the completion of one task. Tasks can be completed in any order, but there's a constraint:
// identical tasks must be separated by at least n intervals due to cooling time.
//​Return the minimum number of intervals required to complete all tasks.

//Example 1:
//Input: tasks = ["A","A","A","B","B","B"], n = 2
//Output: 8
//Explanation: A possible sequence is: A -> B -> idle -> A -> B -> idle -> A -> B.
//After completing task A, you must wait two cycles before doing A again. The same applies to task B. In the 3rd
// interval, neither A nor B can be done, so you idle. By the 4th cycle, you can do A again as 2 intervals have passed.
//
//Example 2:
//Input: tasks = ["A","C","A","B","D","B"], n = 1
//Output: 6
//Explanation: A possible sequence is: A -> B -> C -> D -> A -> B.
//With a cooling interval of 1, you can repeat a task after just one other task.
//
//Example 3:
//Input: tasks = ["A","A","A", "B","B","B"], n = 3
//Output: 10
//Explanation: A possible sequence is: A -> B -> idle -> idle -> A -> B -> idle -> idle -> A -> B.
//
//There are only two types of tasks, A and B, which need to be separated by 3 intervals. This leads to
// idling twice between repetitions of these tasks.
//
//Constraints:
//1 <= tasks.length <= 104
//tasks[i] is an uppercase English letter.
//0 <= n <= 100
