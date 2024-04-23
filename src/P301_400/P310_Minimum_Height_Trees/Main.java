package P301_400.P310_Minimum_Height_Trees;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int n1 = 4;
        int[][] edges1 = {{1, 0}, {1, 2}, {1, 3}};
        List<Integer> result1 = solution.findMinHeightTrees(n1, edges1);
        System.out.println("Minimum Height Trees for n=4, edges=[[1,0],[1,2],[1,3]]: " + result1); // [1]

        int n2 = 6;
        int[][] edges2 = {{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}};
        List<Integer> result2 = solution.findMinHeightTrees(n2, edges2);
        System.out.println("Minimum Height Trees for n=6, edges=[[3,0],[3,1],[3,2],[3,4],[5,4]]: " + result2); // [3,4]

    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> result = new ArrayList<>();

        if (n == 1) {
            result.add(0);
            return result;
        }

        List<Set<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new HashSet<>());
        }

        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }

        Queue<Integer> leaves = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (adjList.get(i).size() == 1) {
                leaves.offer(i);
            }
        }

        while (n > 2) {
            int leavesSize = leaves.size();
            n -= leavesSize;

            for (int i = 0; i < leavesSize; i++) {
                int leaf = leaves.poll();
                int neighbor = adjList.get(leaf).iterator().next();
                adjList.get(neighbor).remove(leaf);

                if (adjList.get(neighbor).size() == 1) {
                    leaves.offer(neighbor);
                }
            }
        }

        result.addAll(leaves);
        return result;
    }
}

//Ми маємо дерево з n вузлів, позначених від 0 до n−1, та масив edges, що містить n−1 ребер. Кожне ребро
//edges[i]=[ai,bi] вказує на наявність неорієнтованого зв'язку між вузлами ai та bi у дереві.
//Щоб знайти мінімально високі дерева (MHTs), ми можемо використовувати ітеративний метод. Спочатку ми знаходимо
// усі листки (вузли з одним сусідом) і додаємо їх до черги. Потім ми починаємо видаляти листки та їх сусідів,
// оновлюючи чергу листків. Ми робимо це, доки залишається два або менше вузлів. Ці вузли і будуть коренями MHTs.
//алгоритм:
//Створити список сусідів для кожного вузла.
//Знайти всі листки (вузли з одним сусідом) та додати їх до черги.
//Почати ітеративно видаляти листки та їх сусідів, оновлюючи чергу листків, доки залишається два або менше вузлів.
//Повернути вузли, які залишилися у черзі, це будуть корені MHTs.
//Цей підхід дозволяє знаходити корені мінімально високих дерев за час O(n), де n - кількість вузлів у дереві.

//A tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any
// connected graph without simple cycles is a tree.
//Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates
// that there is an undirected edge between the two nodes ai and bi in the tree, you can choose any node of the
// tree as the root. When you select a node x as the root, the result tree has height h. Among all possible rooted
// trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).
//Return a list of all MHTs' root labels. You can return the answer in any order.
//The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
//
//Example 1:
//Input: n = 4, edges = [[1,0],[1,2],[1,3]]
//Output: [1]
//Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.

//Example 2:
//Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
//Output: [3,4]
//
//Constraints:
//1 <= n <= 2 * 104
//edges.length == n - 1
//0 <= ai, bi < n
//ai != bi
//All the pairs (ai, bi) are distinct.
//The given input is guaranteed to be a tree and there will be no repeated edges.
