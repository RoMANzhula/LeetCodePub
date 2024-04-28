package P801_900.P834_Sum_of_Distances_in_Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int n1 = 6;
        int[][] edges1 = {{0, 1}, {0, 2}, {2, 3}, {2, 4}, {2, 5}};
        int[] result1 = solution.sumOfDistancesInTree(n1, edges1);
        System.out.println("Output for Example 1: " + Arrays.toString(result1)); // Output: [8,12,6,10,10,10]

        int n2 = 1;
        int[][] edges2 = {};
        int[] result2 = solution.sumOfDistancesInTree(n2, edges2);
        System.out.println("Output for Example 2: " + Arrays.toString(result2)); // Output: [0]

        int n3 = 2;
        int[][] edges3 = {{1, 0}};
        int[] result3 = solution.sumOfDistancesInTree(n3, edges3);
        System.out.println("Output for Example 3: " + Arrays.toString(result3)); // Output: [1,1]
    }

    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        int[] subtreeCount = new int[n];
        int[] distanceSum = new int[n];

        dfs(0, -1, graph, subtreeCount, distanceSum);

        calculateDistances(0, -1, graph, n, subtreeCount, distanceSum);

        return distanceSum;
    }

    private void dfs(int node, int parent, List<List<Integer>> graph, int[] subtreeCount, int[] distanceSum) {
        subtreeCount[node] = 1;

        for (int neighbor : graph.get(node)) {
            if (neighbor != parent) {
                dfs(neighbor, node, graph, subtreeCount, distanceSum);
                subtreeCount[node] += subtreeCount[neighbor];
                distanceSum[node] += distanceSum[neighbor] + subtreeCount[neighbor];
            }
        }
    }

    private void calculateDistances(
            int node, int parent,
            List<List<Integer>> graph, int n,
            int[] subtreeCount, int[] distanceSum
    ) {
        for (int neighbor : graph.get(node)) {
            if (neighbor != parent) {
                distanceSum[neighbor] = distanceSum[node] - subtreeCount[neighbor] + n - subtreeCount[neighbor];
                calculateDistances(neighbor, node, graph, n, subtreeCount, distanceSum);
            }
        }
    }
}

//Задача вимагає ефективного обходу дерева. Ми використовуємо метод глибокого пошуку (DFS). Основна ідея
// полягає в тому, щоб пройтися по дереву двічі:
//Перший прохід DFS: Ми обчислюємо кількість вершин у піддереві кожної вершини (subtreeCount) та суму відстаней
// від кожної вершини до всіх інших вершин у її піддереві (distanceSum).
//Другий прохід DFS: Ми використовуємо обчислені значення з першого проходу, щоб обчислити суму відстаней від
// кожної вершини до всіх інших вершин у дереві.
//Отже, результатом є масив, де на кожній позиції ми маємо суму відстаней від цієї вершини до всіх інших
// вершин у дереві.

//There is an undirected connected tree with n nodes labeled from 0 to n - 1 and n - 1 edges.
//You are given the integer n and the array edges where edges[i] = [ai, bi] indicates that there is an edge between
// nodes ai and bi in the tree.
//Return an array answer of length n where answer[i] is the sum of the distances between the ith node in
// the tree and all other nodes.
//
//Example 1:
//Input: n = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
//Output: [8,12,6,10,10,10]
//Explanation: The tree is shown above.
//We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
//equals 1 + 1 + 2 + 2 + 2 = 8.
//Hence, answer[0] = 8, and so on.

//Example 2:
//Input: n = 1, edges = []
//Output: [0]

//Example 3:
//Input: n = 2, edges = [[1,0]]
//Output: [1,1]
//
//Constraints:
//1 <= n <= 3 * 104
//edges.length == n - 1
//edges[i].length == 2
//0 <= ai, bi < n
//ai != bi
//The given input represents a valid tree.
