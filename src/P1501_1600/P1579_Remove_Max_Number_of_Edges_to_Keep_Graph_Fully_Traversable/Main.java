package P1501_1600.P1579_Remove_Max_Number_of_Edges_to_Keep_Graph_Fully_Traversable;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        // Example 1
        int n1 = 4;
        int[][] edges1 = {
                {3,1,2},
                {3,2,3},
                {1,1,3},
                {1,2,4},
                {1,1,2},
                {2,3,4}
        };
        System.out.println("Output for Example 1: " + solution.maxNumEdgesToRemove(n1, edges1)); // Output: 2

        // Example 2
        int n2 = 4;
        int[][] edges2 = {
                {3,1,2},
                {3,2,3},
                {1,1,4},
                {2,1,4}
        };
        System.out.println("Output for Example 2: " + solution.maxNumEdgesToRemove(n2, edges2)); // Output: 0

        // Example 3
        int n3 = 4;
        int[][] edges3 = {
                {3,2,3},
                {1,1,2},
                {2,3,4}
        };
        System.out.println("Output for Example 3: " + solution.maxNumEdgesToRemove(n3, edges3)); // Output: -1
    }

    public int maxNumEdgesToRemove(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        UnionFind ufa = new UnionFind(n);
        UnionFind ufb = new UnionFind(n);

        int removeCount = 0;

        // Step 1: Add type 3 edges to all
        for (int[] edge : edges) {
            if (edge[0] == 3) {
                if (!uf.union(edge[1], edge[2])) {
                    removeCount++;
                } else {
                    ufa.union(edge[1], edge[2]);
                    ufb.union(edge[1], edge[2]);
                }
            }
        }

        // Step 2: Add type 1 edges to Alice
        for (int[] edge : edges) {
            if (edge[0] == 1) {
                if (!ufa.union(edge[1], edge[2])) {
                    removeCount++;
                }
            }
        }

        // Step 3: Add type 2 edges to Bob
        for (int[] edge : edges) {
            if (edge[0] == 2) {
                if (!ufb.union(edge[1], edge[2])) {
                    removeCount++;
                }
            }
        }

        // Check if Alice and Bob can traverse the whole graph
        if (ufa.getCount() > 1 || ufb.getCount() > 1) {
            return -1;
        }

        return removeCount;
    }

    // UnionFind Class
    class UnionFind {
        private int[] parent;
        private int[] rank;
        private int count;

        public UnionFind(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            count = n;
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
                count--;
                return true;
            }
            return false;
        }

        public int getCount() {
            return count;
        }
    }

}


//Explanation:
//Union-Find Data Structure: The UnionFind class manages the connected components.
//find method implements path compression to keep the tree flat.
//union method merges two sets using union by rank to keep the tree balanced.
//getCount method returns the number of connected components.
//Edge Processing:
//Type 3 edges are processed first because they can be used by both Alice and Bob, reducing redundancy.
//Type 1 and Type 2 edges are processed for Alice and Bob respectively.
//Validation: After processing all edges, we ensure that both Alice and Bob can traverse the entire graph by checking
// the number of connected components.
//Counting Removals: The edges that were not necessary for maintaining the connectivity of the graph for both Alice
// and Bob are counted as removable edges.
//This approach ensures the solution is efficient and meets the problem's constraints.


//Alice and Bob have an undirected graph of n nodes and three types of edges:
//Type 1: Can be traversed by Alice only.
//Type 2: Can be traversed by Bob only.
//Type 3: Can be traversed by both Alice and Bob.
//Given an array edges where edges[i] = [typei, ui, vi] represents a bidirectional edge of type typei between
// nodes ui and vi, find the maximum number of edges you can remove so that after removing the edges, the graph
// can still be fully traversed by both Alice and Bob. The graph is fully traversed by Alice and Bob if starting
// from any node, they can reach all other nodes.
//Return the maximum number of edges you can remove, or return -1 if Alice and Bob cannot fully traverse the graph.

//Example 1:
//Input: n = 4, edges = [[3,1,2],[3,2,3],[1,1,3],[1,2,4],[1,1,2],[2,3,4]]
//Output: 2
//Explanation: If we remove the 2 edges [1,1,2] and [1,1,3]. The graph will still be fully traversable by Alice
// and Bob. Removing any additional edge will not make it so. So the maximum number of edges we can remove is 2.

//Example 2:
//Input: n = 4, edges = [[3,1,2],[3,2,3],[1,1,4],[2,1,4]]
//Output: 0
//Explanation: Notice that removing any edge will not make the graph fully traversable by Alice and Bob.

//Example 3:
//Input: n = 4, edges = [[3,2,3],[1,1,2],[2,3,4]]
//Output: -1
//Explanation: In the current graph, Alice cannot reach node 4 from the other nodes. Likewise, Bob cannot
// reach 1. Therefore it's impossible to make the graph fully traversable.
//
//Constraints:
//1 <= n <= 105
//1 <= edges.length <= min(105, 3 * n * (n - 1) / 2)
//edges[i].length == 3
//1 <= typei <= 3
//1 <= ui < vi <= n
//All tuples (typei, ui, vi) are distinct.