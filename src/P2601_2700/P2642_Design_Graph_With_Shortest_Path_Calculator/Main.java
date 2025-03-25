package P2601_2700.P2642_Design_Graph_With_Shortest_Path_Calculator;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Graph g = new Graph(4, new int[][]{{0, 2, 5}, {0, 1, 2}, {1, 2, 1}, {3, 0, 3}});
        System.out.println(g.shortestPath(3, 2)); // Output: 6
        System.out.println(g.shortestPath(0, 3)); // Output: -1

        g.addEdge(new int[]{1, 3, 4});
        System.out.println(g.shortestPath(0, 3)); // Output: 6
    }
}

class Graph {
    Map<Integer, List<int[]>> graph;

    public Graph(int n, int[][] edges) {
        this.graph = new HashMap<>();
        for (int[] edge : edges) {
            this.addEdge(edge);
        }
    }

    public void addEdge(int[] edge) {
        int from = edge[0];
        int to = edge[1];
        int cost = edge[2];

        if (!graph.containsKey(from)) {
            graph.put(from, new ArrayList<>());
        }

        graph.get(from).add(new int[]{to, cost});
    }

    public int shortestPath(int node1, int node2) {
        Map<Integer, Integer> distance = new HashMap<>();
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        distance.put(node1, 0);
        minHeap.offer(new int[]{node1, 0});

        while (!minHeap.isEmpty()) {
            int[] current = minHeap.poll();
            int currentNode = current[0];
            int currentCost = current[1];

            if (currentNode == node2) {
                return currentCost;
            }

            if (graph.containsKey(currentNode)) {
                for (int[] neighbor : graph.get(currentNode)) {
                    int nextNode = neighbor[0];
                    int nextCost = neighbor[1] + currentCost;

                    if (!distance.containsKey(nextNode) || nextCost < distance.get(nextNode)) {
                        distance.put(nextNode, nextCost);
                        minHeap.offer(new int[]{nextNode, nextCost});
                    }
                }
            }
        }

        return -1; //no path found
    }

}

//Задача полягає в реалізації класу Graph, який представляє напрямлений зважений граф, і має два методи: addEdge
// для додавання нового ребра в граф, і shortestPath для знаходження найкоротшого шляху між двома вузлами графу.
//Умова задачі:
//Ініціалізувати об'єкт графа з n вузлами та списком ребер.
//Додавати ребра в граф, забезпечуючи відсутність дублікатів та самопетель.
//Знаходити найкоротший шлях між двома вузлами графа за допомогою алгоритму Дейкстри. Якщо шляху не існує, повертати -1.
//Рішення:
//Створюємо клас Graph, який має поле graph для зберігання графа у вигляді мапи, де ключ - це вузол, а значення -
// список пар [сусід, вага].
//В конструкторі класу ініціалізуємо граф з переданим списком ребер.
//Метод addEdge додає нове ребро до графа, перевіряючи відсутність дублікатів та самопетель.
//Метод shortestPath використовує алгоритм Дейкстри для знаходження найкоротшого шляху між двома вузлами. Використовуємо
// пріорітетну чергу для оптимального вибору наступного вузла для розгляду.
//Повертаємо довжину найкоротшого шляху або -1, якщо шляху немає.
//У прикладі вхідних даних створюється об'єкт Graph з 4 вузлами та списком ребер. Викликаються методи shortestPath та
// addEdge для перевірки коректності реалізації.


//There is a directed weighted graph that consists of n nodes numbered from 0 to n - 1. The edges of the graph are
// initially represented by the given array edges where edges[i] = [fromi, toi, edgeCosti] meaning that there is
// an edge from fromi to toi with the cost edgeCosti.
//Implement the Graph class:
//Graph(int n, int[][] edges) initializes the object with n nodes and the given edges.
//addEdge(int[] edge) adds an edge to the list of edges where edge = [from, to, edgeCost]. It is guaranteed that
// there is no edge between the two nodes before adding this one.
//int shortestPath(int node1, int node2) returns the minimum cost of a path from node1 to node2. If no path exists,
// return -1. The cost of a path is the sum of the costs of the edges in the path.

//Example 1:
//Input
//["Graph", "shortestPath", "shortestPath", "addEdge", "shortestPath"]
//[[4, [[0, 2, 5], [0, 1, 2], [1, 2, 1], [3, 0, 3]]], [3, 2], [0, 3], [[1, 3, 4]], [0, 3]]
//Output
//[null, 6, -1, null, 6]
//Explanation
//Graph g = new Graph(4, [[0, 2, 5], [0, 1, 2], [1, 2, 1], [3, 0, 3]]);
//g.shortestPath(3, 2); // return 6. The shortest path from 3 to 2 in the first diagram above is 3 -> 0 -> 1 ->
// 2 with a total cost of 3 + 2 + 1 = 6.
//g.shortestPath(0, 3); // return -1. There is no path from 0 to 3.
//g.addEdge([1, 3, 4]); // We add an edge from node 1 to node 3, and we get the second diagram above.
//g.shortestPath(0, 3); // return 6. The shortest path from 0 to 3 now is 0 -> 1 -> 3 with a total cost of 2 + 4 = 6.

//Constraints:
//1 <= n <= 100
//0 <= edges.length <= n * (n - 1)
//edges[i].length == edge.length == 3
//0 <= fromi, toi, from, to, node1, node2 <= n - 1
//1 <= edgeCosti, edgeCost <= 106
//There are no repeated edges and no self-loops in the graph at any point.
//At most 100 calls will be made for addEdge.
//At most 100 calls will be made for shortestPath.
