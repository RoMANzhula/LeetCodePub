package P2301_2400.P2392_Build_a_Matrix_With_Conditions;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int k = 3;
        int[][] rowConditions = {{1, 2}, {3, 2}};
        int[][] colConditions = {{2, 1}, {3, 2}};

        int[][] result = solution.buildMatrix(k, rowConditions, colConditions);
        for (int[] row : result) {
            System.out.println(Arrays.toString(row));
        }
    }

//    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
//        List<int[]> rowCondList = Arrays.asList(rowConditions);
//        List<int[]> colCondList = Arrays.asList(colConditions);
//
//        List<Integer> rowOrder = topologicalSort(k, rowCondList);
//        List<Integer> colOrder = topologicalSort(k, colCondList);
//
//        if (rowOrder.isEmpty() || colOrder.isEmpty()) {
//            return new int[0][0];
//        }
//
//        Map<Integer, Integer> rowIndexMap = new HashMap<>();
//        Map<Integer, Integer> colIndexMap = new HashMap<>();
//
//        for (int i = 0; i < k; i++) {
//            rowIndexMap.put(rowOrder.get(i), i);
//            colIndexMap.put(colOrder.get(i), i);
//        }
//
//        int[][] matrix = new int[k][k];
//        for (int i = 0; i < k; i++) {
//            Arrays.fill(matrix[i], 0);
//        }
//
//        for (int num = 1; num <= k; num++) {
//            int row = rowIndexMap.get(num);
//            int col = colIndexMap.get(num);
//            matrix[row][col] = num;
//        }
//
//        return matrix;
//    }
//
//    public List<Integer> topologicalSort(int k, List<int[]> conditions) {
//        int[] indegree = new int[k + 1];
//        Map<Integer, List<Integer>> graph = new HashMap<>();
//        for (int i = 1; i <= k; i++) {
//            graph.put(i, new ArrayList<>());
//        }
//        for (int[] condition : conditions) {
//            int u = condition[0];
//            int v = condition[1];
//            graph.get(u).add(v);
//            indegree[v]++;
//        }
//
//        Queue<Integer> queue = new LinkedList<>();
//        for (int i = 1; i <= k; i++) {
//            if (indegree[i] == 0) {
//                queue.add(i);
//            }
//        }
//
//        List<Integer> order = new ArrayList<>();
//        while (!queue.isEmpty()) {
//            int current = queue.poll();
//            order.add(current);
//            for (int neighbor : graph.get(current)) {
//                indegree[neighbor]--;
//                if (indegree[neighbor] == 0) {
//                    queue.add(neighbor);
//                }
//            }
//        }
//
//        if (order.size() != k) {
//            return new ArrayList<>();  // Return empty list if there is a cycle
//        }
//        return order;
//    }


    //faster solution
    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        List<int[]> rowCondList = Arrays.asList(rowConditions);
        List<int[]> colCondList = Arrays.asList(colConditions);

        List<Integer> rowOrder = topologicalSort(k, rowCondList);
        List<Integer> colOrder = topologicalSort(k, colCondList);

        if (rowOrder.isEmpty() || colOrder.isEmpty()) {
            return new int[0][0];
        }

        int[] rowIndexMap = new int[k + 1];
        int[] colIndexMap = new int[k + 1];

        for (int i = 0; i < k; i++) {
            rowIndexMap[rowOrder.get(i)] = i;
            colIndexMap[colOrder.get(i)] = i;
        }

        int[][] matrix = new int[k][k];
        for (int i = 0; i < k; i++) {
            Arrays.fill(matrix[i], 0);
        }

        for (int num = 1; num <= k; num++) {
            int row = rowIndexMap[num];
            int col = colIndexMap[num];
            matrix[row][col] = num;
        }

        return matrix;
    }

    public List<Integer> topologicalSort(int k, List<int[]> conditions) {
        int[] inDegree = new int[k + 1];
        List<Integer>[] graph = new ArrayList[k + 1];
        for (int i = 1; i <= k; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] condition : conditions) {
            int u = condition[0];
            int v = condition[1];
            graph[u].add(v);
            inDegree[v]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= k; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        List<Integer> order = new ArrayList<>();
        while (!queue.isEmpty()) {
            int current = queue.poll();
            order.add(current);
            for (int neighbor : graph[current]) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        if (order.size() != k) {
            return new ArrayList<>();  // Return empty list if there is a cycle
        }
        return order;
    }

}

//Explanation
//Topological Sort: The topological sort function is the same as before, but we use an array of lists
// instead of a Map to represent the graph.
//Matrix Construction: We use arrays (rowIndexMap and colIndexMap) to store the indices of each number in
// the row and column orders.
//Main Method: We test the solution with provided input.
//This approach maintains the same logic and functionality but replaces the Map with arrays for simplicit
// y and efficiency.


//You are given a positive integer k. You are also given:
//a 2D integer array rowConditions of size n where rowConditions[i] = [abovei, belowi], and
//a 2D integer array colConditions of size m where colConditions[i] = [lefti, righti].
//The two arrays contain integers from 1 to k.
//You have to build a k x k matrix that contains each of the numbers from 1 to k exactly once. The remaining
// cells should have the value 0.
//The matrix should also satisfy the following conditions:
//The number abovei should appear in a row that is strictly above the row at which the number belowi appears
// for all i from 0 to n - 1.
//The number lefti should appear in a column that is strictly left of the column at which the
// number righti appears for all i from 0 to m - 1.
//Return any matrix that satisfies the conditions. If no answer exists, return an empty matrix.
//
//Example 1:
//Input: k = 3, rowConditions = [[1,2],[3,2]], colConditions = [[2,1],[3,2]]
//Output: [[3,0,0],[0,0,1],[0,2,0]]
//Explanation: The diagram above shows a valid example of a matrix that satisfies all the conditions.
//The row conditions are the following:
//- Number 1 is in row 1, and number 2 is in row 2, so 1 is above 2 in the matrix.
//- Number 3 is in row 0, and number 2 is in row 2, so 3 is above 2 in the matrix.
//The column conditions are the following:
//- Number 2 is in column 1, and number 1 is in column 2, so 2 is left of 1 in the matrix.
//- Number 3 is in column 0, and number 2 is in column 1, so 3 is left of 2 in the matrix.
//Note that there may be multiple correct answers.

//Example 2:
//Input: k = 3, rowConditions = [[1,2],[2,3],[3,1],[2,3]], colConditions = [[2,1]]
//Output: []
//Explanation: From the first two conditions, 3 has to be below 1 but the third conditions needs 3 to be
// above 1 to be satisfied.
//No matrix can satisfy all the conditions, so we return the empty matrix.
//
//Constraints:
//2 <= k <= 400
//1 <= rowConditions.length, colConditions.length <= 104
//rowConditions[i].length == colConditions[i].length == 2
//1 <= abovei, belowi, lefti, righti <= k
//abovei != belowi
//lefti != righti
