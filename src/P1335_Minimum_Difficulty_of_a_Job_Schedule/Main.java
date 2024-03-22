package P1335_Minimum_Difficulty_of_a_Job_Schedule;

public class Main {
    public static void main(String[] args) {
        Main scheduler = new Main();

        // Example 1
        int[] jobDifficulty1 = {6, 5, 4, 3, 2, 1};
        int d1 = 2;
        System.out.println(scheduler.minDifficulty(jobDifficulty1, d1)); // Output: 7

        // Example 2
        int[] jobDifficulty2 = {9, 9, 9};
        int d2 = 4;
        System.out.println(scheduler.minDifficulty(jobDifficulty2, d2)); // Output: -1

        // Example 3
        int[] jobDifficulty3 = {1, 1, 1};
        int d3 = 3;
        System.out.println(scheduler.minDifficulty(jobDifficulty3, d3)); // Output: 3
    }

    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (n < d) return -1;

        int[][] dp = new int[d + 1][n + 1];
        for (int i = 0; i <= d; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = -1;
            }
        }

        return dfs(jobDifficulty, d, 0, dp);
    }

    private int dfs(int[] jobDifficulty, int d, int start, int[][] dp) {
        int n = jobDifficulty.length;
        if (d == 0 && start == n) return 0;
        if (d == 0 || start == n) return Integer.MAX_VALUE;
        if (dp[d][start] != -1) return dp[d][start];

        int maxDifficulty = 0;
        int minDifficulty = Integer.MAX_VALUE;

        for (int end = start; end < n; end++) {
            maxDifficulty = Math.max(maxDifficulty, jobDifficulty[end]);
            int remainingDifficulty = dfs(jobDifficulty, d - 1, end + 1, dp);

            if (remainingDifficulty != Integer.MAX_VALUE) {
                minDifficulty = Math.min(minDifficulty, maxDifficulty + remainingDifficulty);
            }
        }

        dp[d][start] = minDifficulty;
        return minDifficulty;
    }
}

//У цій задачі ми маємо список завдань, кожне з яких має свій рівень складності. Задача полягає в тому, щоб
// розподілити ці завдання на дні так, щоб сумарний рівень складності за кожен день був мінімальним. При цьому
// обов'язково потрібно виконати хоча б одне завдання щодня.
//
//Розв'язок використовує динамічне програмування. Основна ідея полягає в тому, щоб розглядати всі можливі способи
// розподілу завдань на дні і вибрати той, який дає мінімальний сумарний рівень складності.
//
//Ми використовуємо рекурсивну функцію dfs, яка розглядає всі можливі варіанти розподілу завдань. Щоб уникнути зайвих
// розрахунків, ми використовуємо мемоізацію за допомогою двовимірного масиву dp.
//
//Основний алгоритм полягає в ітерації через всі можливі дні та завдання, розглядаючи, яке завдання буде останнім
// виконаним у кожен конкретний день. Знаходження мінімального рівня складності ведеться за допомогою максимуму
// складності завдань, виконаних протягом даного дня, і мінімального рівня складності для решти завдань,
// які мають бути виконані.

//You want to schedule a list of jobs in d days. Jobs are dependent (i.e To work on the ith job, you have to
// finish all the jobs j where 0 <= j < i).
//
//You have to finish at least one task every day. The difficulty of a job schedule is the sum of difficulties of
// each day of the d days. The difficulty of a day is the maximum difficulty of a job done on that day.
//
//You are given an integer array jobDifficulty and an integer d. The difficulty of the ith job is jobDifficulty[i].
//
//Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1.
//
//
//
//Example 1:
//
//
//Input: jobDifficulty = [6,5,4,3,2,1], d = 2
//Output: 7
//Explanation: First day you can finish the first 5 jobs, total difficulty = 6.
//Second day you can finish the last job, total difficulty = 1.
//The difficulty of the schedule = 6 + 1 = 7
//Example 2:
//
//Input: jobDifficulty = [9,9,9], d = 4
//Output: -1
//Explanation: If you finish a job per day you will still have a free day. you cannot find a schedule for the given jobs.
//Example 3:
//
//Input: jobDifficulty = [1,1,1], d = 3
//Output: 3
//Explanation: The schedule is one job per day. total difficulty will be 3.
//
//
//Constraints:
//
//1 <= jobDifficulty.length <= 300
//0 <= jobDifficulty[i] <= 1000
//1 <= d <= 10
