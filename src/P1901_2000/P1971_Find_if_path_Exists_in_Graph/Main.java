package P1901_2000.P1971_Find_if_path_Exists_in_Graph;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        // Example 1
        int n1 = 3;
        int[][] edges1 = {{0, 1}, {1, 2}, {2, 0}};
        int source1 = 0;
        int destination1 = 2;
        System.out.println("Example 1 Output: " + solution.validPath(n1, edges1, source1, destination1)); //output true

        // Example 2
        int n2 = 6;
        int[][] edges2 = {{0, 1}, {0, 2}, {3, 5}, {5, 4}, {4, 3}};
        int source2 = 0;
        int destination2 = 5;
        System.out.println("Example 2 Output: " + solution.validPath(n2, edges2, source2, destination2)); //output false

    }

//    public boolean validPath(int n, int[][] edges, int source, int destination) {
//        // Create adjacency list representation of the graph
//        List<List<Integer>> adjList = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            adjList.add(new ArrayList<>());
//        }
//        for (int[] edge : edges) {
//            adjList.get(edge[0]).add(edge[1]);
//            adjList.get(edge[1]).add(edge[0]); // Since it's a bidirectional graph
//        }
//
//        // Initialize visited array to keep track of visited vertices
//        boolean[] visited = new boolean[n];
//
//        // Perform DFS from source vertex
//        return dfs(adjList, visited, source, destination);
//    }
//
//    private boolean dfs(List<List<Integer>> adjList, boolean[] visited, int current, int destination) {
//        if (current == destination) {
//            return true; // Found a path
//        }
//
//        visited[current] = true;
//
//        // Explore neighbors
//        for (int neighbor : adjList.get(current)) {
//            if (!visited[neighbor]) {
//                if (dfs(adjList, visited, neighbor, destination)) {
//                    return true; // Found a path
//                }
//            }
//        }
//
//        return false; // No path found
//    }

    // faster solve
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        // Create adjacency list representation of the graph
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int i = 0; i < n; i++) {
            adjList.put(i, new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]); // Since it's a bidirectional graph
        }

        // Perform BFS from source vertex
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        queue.offer(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (current == destination) {
                return true; // Found a path
            }
            for (int neighbor : adjList.get(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }

        return false; // No path found
    }

}

//Це рішення використовує алгоритм пошуку в ширину (BFS), щоб знайти шлях у графі від вершини-джерела до
// вершини-призначення. Давайте розглянемо кожен крок:
//Спочатку ми створюємо представлення графа у вигляді списку суміжності. Для цього ми використовуємо мапу, де
// ключами є вершини, а значеннями - списки суміжних вершин.
//Потім ми розпочинаємо BFS з вершини-джерела. Для цього ми використовуємо чергу, де ми додаємо вершини, які ми
// ще не відвідали. Також ми позначаємо відвідані вершини, щоб уникнути повторного відвідування їх.
//На кожному кроці ми вибираємо вершину з початку черги і додаємо всі суміжні з нею вершини до черги. Ми
// продовжуємо цей процес, доки черга не стане порожньою або доки не досягнемо вершини-призначення.
//Якщо вершина-призначення знаходиться серед відвіданих вершин, ми повертаємо true, що означає, що існує шлях
// від вершини-джерела до вершини-призначення. Якщо вершина-призначення не була відвідана, то шляха не існує, і
// ми повертаємо false.
//БФС є ефективним алгоритмом для пошуку шляху в графі, оскільки він досліджує вершини у порядку за їх відстанню
// від вершини-джерела. Це дає йому швидкість у багатьох випадках, що дозволяє швидко визначити наявність шляху у графі.

//There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive). The
// edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a
// bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and
// no vertex has an edge to itself.
//You want to determine if there is a valid path that exists from vertex source to vertex destination.
//Given edges and the integers n, source, and destination, return true if there is a valid path from source to
// destination, or false otherwise.
//
//Example 1:
//Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
//Output: true
//Explanation: There are two paths from vertex 0 to vertex 2:
//- 0 → 1 → 2
//- 0 → 2

//Example 2:
//Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
//Output: false
//Explanation: There is no path from vertex 0 to vertex 5.
//
//Constraints:
//1 <= n <= 2 * 105
//0 <= edges.length <= 2 * 105
//edges[i].length == 2
//0 <= ui, vi <= n - 1
//ui != vi
//0 <= source, destination <= n - 1
//There are no duplicate edges.
//There are no self edges.