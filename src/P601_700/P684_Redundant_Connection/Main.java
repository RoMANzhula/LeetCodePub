package P601_700.P684_Redundant_Connection;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] edges1 = {{1,2},{1,3},{2,3}};
        int[] result1 = solution.findRedundantConnection(edges1);
        System.out.println("Output: [" + result1[0] + ", " + result1[1] + "]"); // Output: [2, 3]

        int[][] edges2 = {{1,2},{2,3},{3,4},{1,4},{1,5}};
        int[] result2 = solution.findRedundantConnection(edges2);
        System.out.println("Output: [" + result2[0] + ", " + result2[1] + "]"); // Output: [1, 4]
    }

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        UnionFind uf = new UnionFind(n);

        for (int[] edge : edges) {
            if (!uf.union(edge[0], edge[1])) {
                return edge; // an extra edge was found
            }
        }

        return new int[0]; // theoretically, this should not happen
    }
}

class UnionFind {
    private int[] parent;
    private int[] rank;

    public UnionFind(int n) {
        parent = new int[n + 1];
        rank = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // road compression
        }
        return parent[x];
    }

    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return false; // they are already joined, so this is an extra edge
        }

        // join components
        if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }

        return true;
    }
}

//Explanation:
//Union-Find (DSU)
//We use the parent array to store the parent nodes.
//We use the rank array to balance the union operation.
//The find(x) method performs path compression for quick access to the root of a set.
//The union(x, y) method merges sets. If x and y are already in the same set, it returns false (indicating this is
// the redundant edge).
//Edge Traversal
//If union(edge[0], edge[1]) returns false, it means the edge creates a cycle and is the answer.
//Complexity:
//find(x) ~ O(α(n)), union(x, y) ~ O(α(n)), where α(n) is the inverse Ackermann function (very small, practically O(1)).
//Overall complexity: O(n).
//This code runs efficiently even for n = 1000. 🚀


//In this problem, a tree is an undirected graph that is connected and has no cycles.
//You are given a graph that started as a tree with n nodes labeled from 1 to n, with one additional edge added. The
// added edge has two different vertices chosen from 1 to n, and was not an edge that already existed. The
// graph is represented as an array edges of length n where edges[i] = [ai, bi] indicates that there is an
// edge between nodes ai and bi in the graph.
//Return an edge that can be removed so that the resulting graph is a tree of n nodes. If there are multiple answers,
// return the answer that occurs last in the input.
//
//Example 1:
//Input: edges = [[1,2],[1,3],[2,3]]
//Output: [2,3]

//Example 2:
//Input: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]
//Output: [1,4]
//
//Constraints:
//n == edges.length
//3 <= n <= 1000
//edges[i].length == 2
//1 <= ai < bi <= edges.length
//ai != bi
//There are no repeated edges.
//The given graph is connected.
