package P901_1000.P947_Most_Stones_Removed_with_Same_Row_or_Column;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[][] stones1 = {{0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}};
        System.out.println(solution.removeStones(stones1)); // Output: 5

        int[][] stones2 = {{0, 0}, {0, 2}, {1, 1}, {2, 0}, {2, 2}};
        System.out.println(solution.removeStones(stones2)); // Output: 3

        int[][] stones3 = {{0, 0}};
        System.out.println(solution.removeStones(stones3)); // Output: 0
    }

    public int removeStones(int[][] stones) {
        int n = stones.length;
        UnionFind uf = new UnionFind(20000);

        for (int[] stone : stones) {
            uf.union(stone[0], stone[1] + 10001); // Offset the y-coordinate by 10001 to avoid collision
        }

        Set<Integer> uniqueRoots = new HashSet<>();
        for (int[] stone : stones) {
            uniqueRoots.add(uf.find(stone[0]));
        }

        return n - uniqueRoots.size();
    }

    class UnionFind {
        int[] parent;

        UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                parent[rootX] = rootY;
            }
        }
    }
}

//Explanation:
//Union-Find Structure: The UnionFind class is used to keep track of which stones are in the same connected
// component. The find method helps find the root of a given node, while the union method merges two components.
//Union Operation: Each stone is represented by its x and y coordinates. Since we can have a large range of
// values, we offset the y coordinates by 10000 to differentiate them from x coordinates when storing them in the
// Union-Find structure.
//Counting Components: After processing all stones, the number of unique components is found by iterating through all
// stones and collecting their roots. The maximum number of stones that can be removed is the total number of
// stones minus the number of unique components.
//Complexity:
//Time Complexity: The time complexity is approximately O(n * α(n)), where α(n) is the inverse Ackermann
// function (which grows very slowly).
//Space Complexity: The space complexity is O(n) for storing the parent pointers.


//On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one stone.
//A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.
//Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone, return
// the largest possible number of stones that can be removed.
//
//Example 1:
//Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
//Output: 5
//Explanation: One way to remove 5 stones is as follows:
//1. Remove stone [2,2] because it shares the same row as [2,1].
//2. Remove stone [2,1] because it shares the same column as [0,1].
//3. Remove stone [1,2] because it shares the same row as [1,0].
//4. Remove stone [1,0] because it shares the same column as [0,0].
//5. Remove stone [0,1] because it shares the same row as [0,0].
//Stone [0,0] cannot be removed since it does not share a row/column with another stone still on the plane.

//Example 2:
//Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
//Output: 3
//Explanation: One way to make 3 moves is as follows:
//1. Remove stone [2,2] because it shares the same row as [2,0].
//2. Remove stone [2,0] because it shares the same column as [0,0].
//3. Remove stone [0,2] because it shares the same row as [0,0].
//Stones [0,0] and [1,1] cannot be removed since they do not share a row/column with another stone still on the plane.

//Example 3:
//Input: stones = [[0,0]]
//Output: 0
//Explanation: [0,0] is the only stone on the plane, so you cannot remove it.
//
//Constraints:
//1 <= stones.length <= 1000
//0 <= xi, yi <= 104
//No two stones are at the same coordinate point.