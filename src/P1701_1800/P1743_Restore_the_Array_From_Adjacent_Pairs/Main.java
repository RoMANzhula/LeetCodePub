package P1701_1800.P1743_Restore_the_Array_From_Adjacent_Pairs;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        int[][] adjacentPairs1 = {{2, 1}, {3, 4}, {3, 2}};
        int[] result1 = restoreArray(adjacentPairs1);
        System.out.println(Arrays.toString(result1));

        int[][] adjacentPairs2 = {{4, -2}, {1, 4}, {-3, 1}};
        int[] result2 = restoreArray(adjacentPairs2);
        System.out.println(Arrays.toString(result2));

        int[][] adjacentPairs3 = {{100000, -100000}};
        int[] result3 = restoreArray(adjacentPairs3);
        System.out.println(Arrays.toString(result3));
    }


    // y даному випадку, де потрібно відновити масив nums з adjacentPairs, можна використовувати HashMap для швидкого доступу до сусідів кожного елемента
    public static int[] restoreArray(int[][] adjacentPairs) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        //створення графа
        for (int[] pair : adjacentPairs) {
            graph.computeIfAbsent(pair[0], k -> new ArrayList<>()).add(pair[1]);
            graph.computeIfAbsent(pair[1], k -> new ArrayList<>()).add(pair[0]);
        }

        int n = adjacentPairs.length + 1;
        int[] result = new int[n];

        //знаходження початкового вузла (вузла з одним сусідом)
        int startNode = 0;
        for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
            if (entry.getValue().size() == 1) {
                startNode = entry.getKey();
                break;
            }
        }

        //відновлення масиву за допомогою DFS
        Set<Integer> visited = new HashSet<>();
        restoreArrayDFS(graph, startNode, result, visited, 0);

        return result;
    }

    private static void restoreArrayDFS(Map<Integer, List<Integer>> graph, int node, int[] result, Set<Integer> visited, int index) {
        result[index] = node;
        visited.add(node);

        for (int neighbor : graph.get(node)) {
            if (!visited.contains(neighbor)) {
                restoreArrayDFS(graph, neighbor, result, visited, index + 1);
                break; //оскільки граф буде мати тільки два сусіди для кожного вузла
            }
        }
    }

}

//Вирішення цієї задачі можна здійснити за допомогою побудови графа, де кожен елемент ui та йому відповідний
// vi будуть з'єднані ребром. Потім ви можете знайти початковий вузол, який має лише одного сусіда, і відтак
// відновити весь масив, використовуючи глибинний пошук. У реалізації ви бачите, що кожен вузол з'єднаний
// зі своїми сусідами, і вибір початкового вузла допомагає правильно визначити порядок елементів у масиві.


//There is an integer array nums that consists of n unique elements, but you have forgotten it. However, you do
// remember every pair of adjacent elements in nums.
//You are given a 2D integer array adjacentPairs of size n - 1 where each adjacentPairs[i] = [ui, vi] indicates that
// the elements ui and vi are adjacent in nums.
//It is guaranteed that every adjacent pair of elements nums[i] and nums[i+1] will exist in adjacentPairs, either
// as [nums[i], nums[i+1]] or [nums[i+1], nums[i]]. The pairs can appear in any order.
//Return the original array nums. If there are multiple solutions, return any of them.

//Example 1:
//Input: adjacentPairs = [[2,1],[3,4],[3,2]]
//Output: [1,2,3,4]
//Explanation: This array has all its adjacent pairs in adjacentPairs.
//Notice that adjacentPairs[i] may not be in left-to-right order.

//Example 2:
//Input: adjacentPairs = [[4,-2],[1,4],[-3,1]]
//Output: [-2,4,1,-3]
//Explanation: There can be negative numbers.
//Another solution is [-3,1,4,-2], which would also be accepted.

//Example 3:
//Input: adjacentPairs = [[100000,-100000]]
//Output: [100000,-100000]

//Constraints:
//nums.length == n
//adjacentPairs.length == n - 1
//adjacentPairs[i].length == 2
//2 <= n <= 105
//-105 <= nums[i], ui, vi <= 105
//There exists some nums that has adjacentPairs as its pairs.