package P701_800.P787_Cheapest_Flights_Within_K_Stops;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int n1 = 4;
        int[][] flights1 = {{0,1,100},{1,2,100},{2,0,100},{1,3,600},{2,3,200}};
        int src1 = 0;
        int dst1 = 3;
        int k1 = 1;
        System.out.println("Example 1: " + solution.findCheapestPrice(n1, flights1, src1, dst1, k1)); // Output: 700

        int n2 = 3;
        int[][] flights2 = {{0,1,100},{1,2,100},{0,2,500}};
        int src2 = 0;
        int dst2 = 2;
        int k2 = 1;
        System.out.println("Example 2: " + solution.findCheapestPrice(n2, flights2, src2, dst2, k2)); // Output: 200

        int n3 = 3;
        int[][] flights3 = {{0,1,100},{1,2,100},{0,2,500}};
        int src3 = 0;
        int dst3 = 2;
        int k3 = 0;
        System.out.println("Example 3: " + solution.findCheapestPrice(n3, flights3, src3, dst3, k3)); // Output: 500
    }

            // In this code we have the error - Time Limit Exceeded
//    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
//        Map<Integer, List<int[]>> graph = new HashMap<>();
//        for (int[] flight : flights) {
//            graph.putIfAbsent(flight[0], new ArrayList<>());
//            graph.get(flight[0]).add(new int[]{flight[1], flight[2]});
//        }
//
//        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
//        priorityQueue.offer(new int[]{0, src, k + 1});
//
//        while (!priorityQueue.isEmpty()) {
//            int[] info = priorityQueue.poll();
//
//            int price = info[0];
//            int city = info[1];
//            int stops = info[2];
//
//            if (city == dst) {
//                return price;
//            }
//
//            if (stops > 0) {
//                List<int[]> nexts = graph.getOrDefault(city, new ArrayList<>());
//                for (int[] next : nexts) {
//                    priorityQueue.offer(new int[]{price + next[1], next[0], stops - 1});
//                }
//            }
//        }
//
//        return -1;
//    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        final int INF = 10001 * 101;

        int[] dp = new int[n];
        Arrays.fill(dp, INF);
        dp[src] = 0;

        for (int i = 0; i <= k; i++) {
            int[] temp = Arrays.copyOf(dp, n);
            for (int[] flight : flights) {
                int from = flight[0];
                int to = flight[1];
                int price = flight[2];
                if (dp[from] != INF) {
                    temp[to] = Math.min(temp[to], dp[from] + price);
                }
            }
            dp = temp;
        }

        return dp[dst] == INF ? -1 : dp[dst];
    }
}

//Задача полягає у знаходженні найбільш вигідного маршруту з одного міста до іншого, при цьому можлива зупинка не
// більше k разів. Для розв'язання цієї задачі ми використовуємо алгоритм Беллмана-Форда.
//Алгоритм Беллмана-Форда розглядає кожен можливий шлях з вихідного міста до кінцевого міста через не
// більше k зупинок. Він використовує динамічне програмування, щоб ефективно знаходити найбільш вигідний шлях.
//Спочатку ми ініціалізуємо масив dp, де dp[i] відповідає вартості найбільш вигідного шляху з вихідного
// міста до міста i. Вартість усіх шляхів, крім вихідного, встановлюється на нескінченність, оскільки ще
// не відомо найкоротших маршрутів до них.
//Потім ми розглядаємо кожен можливий рейс із списку flights. Якщо вартість до міста виходу рейсу не є
// нескінченністю (тобто ми вже знаємо найкоротший шлях до цього міста), ми перевіряємо, чи можна досягти
// міста при використанні цього рейсу. Якщо так, ми оновлюємо вартість найбільш вигідного шляху до міста
// призначення через цей рейс.
//Ми повторюємо цей процес k разів, оскільки ми дозволяємо не більше k зупинок. Кожний раз ми оновлюємо
// масив dp з врахуванням нових можливих маршрутів.
//На завершення повертаємо вартість найбільш вигідного шляху до кінцевого міста. Якщо вартість є нескінченністю,
// це означає, що немає можливого маршруту, і повертаємо -1.
//Цей підхід дозволяє ефективно знаходити найбільш вигідний маршрут з урахуванням обмеження на кількість зупинок.

//There are n cities connected by some number of flights. You are given an array flights where
// flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.
//You are also given three integers src, dst, and k, return the cheapest price from src to dst with at
// most k stops. If there is no such route, return -1.

//Example 1:
//Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
//Output: 700
//Explanation:
//The graph is shown above.
//The optimal path with at most 1 stop from city 0 to 3 is marked in red and has cost 100 + 600 = 700.
//Note that the path through cities [0,1,2,3] is cheaper but is invalid because it uses 2 stops.

//Example 2:
//Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
//Output: 200
//Explanation:
//The graph is shown above.
//The optimal path with at most 1 stop from city 0 to 2 is marked in red and has cost 100 + 100 = 200.

//Example 3:
//Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
//Output: 500
//Explanation:
//The graph is shown above.
//The optimal path with no stops from city 0 to 2 is marked in red and has cost 500.
//
//Constraints:
//1 <= n <= 100
//0 <= flights.length <= (n * (n - 1) / 2)
//flights[i].length == 3
//0 <= fromi, toi < n
//fromi != toi
//1 <= pricei <= 104
//There will not be any multiple flights between two cities.
//0 <= src, dst, k < n
//src != dst
