package P1501_1600.P1519_Number_of_Nodes_in_the_SubTree_With_the_Same_Label;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int n1 = 7;
        int[][] edges1 = {{0,1},{0,2},{1,4},{1,5},{2,3},{2,6}};
        String labels1 = "abaedcd";
        System.out.println(Arrays.toString(solution.countSubTrees(n1, edges1, labels1))); // Output: [2,1,1,1,1,1,1]

        int n2 = 4;
        int[][] edges2 = {{0,1},{1,2},{0,3}};
        String labels2 = "bbbb";
        System.out.println(Arrays.toString(solution.countSubTrees(n2, edges2, labels2))); // Output: [4,2,1,1]

        int n3 = 5;
        int[][] edges3 = {{0,1},{0,2},{1,3},{0,4}};
        String labels3 = "aabab";
        System.out.println(Arrays.toString(solution.countSubTrees(n3, edges3, labels3))); // Output: [3,2,1,1,1]
    }

    public int[] countSubTrees(int n, int[][] edges, String labels) {
        // Build adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0; i<n; i++) {
            adj.add(new ArrayList<>());
        }
        for(int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        int[] ans = new int[n];
        dfs(0, -1, adj, labels, ans);
        return ans;
    }

    private int[] dfs(int node, int parent, List<List<Integer>> adj, String labels, int[] ans) {
        int[] count = new int[26];
        char currentLabel = labels.charAt(node);
        count[currentLabel - 'a'] = 1;

        for(int neighbor : adj.get(node)) {
            if(neighbor == parent) continue;
            int[] childCount = dfs(neighbor, node, adj, labels, ans);
            for(int i=0; i<26; i++) {
                count[i] += childCount[i];
            }
        }

        ans[node] = count[currentLabel - 'a'];
        return count;
    }
}

//Explanation of Code:
//Adjacency List Construction:
//We iterate through all edges and populate the adjacency list accordingly.
//DFS Function (dfs):
//Parameters:
//node: current node being visited.
//parent: parent of the current node to avoid revisiting.
//adj: adjacency list representing the tree.
//labels: string containing labels for each node.
//ans: array to store the result.
//Process:
//Initialize a count array for the current node.
//Set the count for the current node's label.
//Recursively visit all neighbors except the parent.
//After visiting a child, merge its count array with the current node's count array.
//Update the result array ans for the current node.
//Return the count array for merging with parent calls.
//Complexity Analysis:
//Time Complexity: O(n)
//Each node and each edge is visited exactly once during the traversal.
//Space Complexity: O(n)
//The adjacency list and recursion stack consume space proportional to the number of nodes.


//You are given a tree (i.e. a connected, undirected graph that has no cycles) consisting of n nodes numbered
// from 0 to n - 1 and exactly n - 1 edges. The root of the tree is the node 0, and each node of the
// tree has a label which is a lower-case character given in the string labels (i.e. The node with the
// number i has the label labels[i]).
//The edges array is given on the form edges[i] = [ai, bi], which means there is an edge between nodes ai and
// bi in the tree.
//Return an array of size n where ans[i] is the number of nodes in the subtree of the ith node which have
// the same label as node i.
//A subtree of a tree T is the tree consisting of a node in T and all of its descendant nodes.
//
//Example 1:
//Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], labels = "abaedcd"
//Output: [2,1,1,1,1,1,1]
//Explanation: Node 0 has label 'a' and its sub-tree has node 2 with label 'a' as well, thus the answer is 2. Notice
// that any node is part of its sub-tree.
//Node 1 has a label 'b'. The sub-tree of node 1 contains nodes 1,4 and 5, as nodes 4 and 5 have different labels
// than node 1, the answer is just 1 (the node itself).

//Example 2:
//Input: n = 4, edges = [[0,1],[1,2],[0,3]], labels = "bbbb"
//Output: [4,2,1,1]
//Explanation: The sub-tree of node 2 contains only node 2, so the answer is 1.
//The sub-tree of node 3 contains only node 3, so the answer is 1.
//The sub-tree of node 1 contains nodes 1 and 2, both have label 'b', thus the answer is 2.
//The sub-tree of node 0 contains nodes 0, 1, 2 and 3, all with label 'b', thus the answer is 4.

//Example 3:
//Input: n = 5, edges = [[0,1],[0,2],[1,3],[0,4]], labels = "aabab"
//Output: [3,2,1,1,1]
//
//Constraints:
//1 <= n <= 105
//edges.length == n - 1
//edges[i].length == 2
//0 <= ai, bi < n
//ai != bi
//labels.length == n
//labels is consisting of only of lowercase English letters.
