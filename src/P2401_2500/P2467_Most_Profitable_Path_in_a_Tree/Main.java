package P2401_2500.P2467_Most_Profitable_Path_in_a_Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] edges1 = {{0,1},{1,2},{1,3},{3,4}};
        int bob1 = 3;
        int[] amount1 = {-2,4,2,-4,6};
        System.out.println(solution.mostProfitablePath(edges1, bob1, amount1)); // Output: 6

        int[][] edges2 = {{0,1}};
        int bob2 = 1;
        int[] amount2 = {-7280,2350};
        System.out.println(solution.mostProfitablePath(edges2, bob2, amount2)); // Output: -7280
    }

    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        int n = amount.length;
        List<List<Integer>> graph = new ArrayList<>();

        // Step 1: Build adjacency list
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        // Step 2: Find Bob's path to node 0
        int[] bobTime = new int[n];
        Arrays.fill(bobTime, Integer.MAX_VALUE);
        findBobPath(bob, -1, 0, graph, bobTime);

        // Step 3: Find Alice's max income using DFS
        return dfsAlice(0, -1, 0, 0, graph, bobTime, amount);
    }

    // Step 2: DFS to find Bob's time to each node
    private boolean findBobPath(int node, int parent, int time, List<List<Integer>> graph, int[] bobTime) {
        bobTime[node] = time;
        if (node == 0) return true;

        for (int neighbor : graph.get(node)) {
            if (neighbor != parent && findBobPath(neighbor, node, time + 1, graph, bobTime)) {
                return true;
            }
        }

        // Reset if no path found
        bobTime[node] = Integer.MAX_VALUE;
        return false;
    }

    // Step 3: DFS for Alice's path to find max income
    private int dfsAlice(
            int node,
            int parent,
            int aliceTime,
            int currentSum,
            List<List<Integer>> graph,
            int[] bobTime,
            int[] amount
    ) {
        // compute Alice's net gain at this node
        if (aliceTime < bobTime[node]) {
            currentSum += amount[node]; // Alice takes full amount
        } else if (aliceTime == bobTime[node]) {
            currentSum += amount[node] / 2; // Alice and Bob split
        } // else Bob already took everything, Alice gets nothing

        // explore paths for Alice
        int maxProfit = Integer.MIN_VALUE;
        boolean isLeaf = true;
        for (int neighbor : graph.get(node)) {
            if (neighbor != parent) {
                isLeaf = false;
                maxProfit = Math.max(
                        maxProfit,
                        dfsAlice(neighbor, node, aliceTime + 1, currentSum, graph, bobTime, amount)
                );
            }
        }

        // if it's a leaf, return current profit
        return isLeaf ? currentSum : maxProfit;
    }

}

//Complexity:
//Time Complexity: O(n)
//Space Complexity: O(n)


//There is an undirected tree with n nodes labeled from 0 to n - 1, rooted at node 0. You are given a 2D integer
// array edges of length n - 1 where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and
// bi in the tree.
//
//At every node i, there is a gate. You are also given an array of even integers amount, where amount[i] represents:
//the price needed to open the gate at node i, if amount[i] is negative, or,
//the cash reward obtained on opening the gate at node i, otherwise.
//The game goes on as follows:
//Initially, Alice is at node 0 and Bob is at node bob.
//At every second, Alice and Bob each move to an adjacent node. Alice moves towards some leaf node, while Bob
// moves towards node 0.
//For every node along their path, Alice and Bob either spend money to open the gate at that node, or accept the
// reward. Note that:
//If the gate is already open, no price will be required, nor will there be any cash reward.
//If Alice and Bob reach the node simultaneously, they share the price/reward for opening the gate there. In other
// words, if the price to open the gate is c, then both Alice and Bob pay c / 2 each. Similarly, if the reward at the
// gate is c, both of them receive c / 2 each.
//If Alice reaches a leaf node, she stops moving. Similarly, if Bob reaches node 0, he stops moving. Note that these
// events are independent of each other.
//Return the maximum net income Alice can have if she travels towards the optimal leaf node.
//
//Example 1:
//Input: edges = [[0,1],[1,2],[1,3],[3,4]], bob = 3, amount = [-2,4,2,-4,6]
//Output: 6
//Explanation:
//The above diagram represents the given tree. The game goes as follows:
//- Alice is initially on node 0, Bob on node 3. They open the gates of their respective nodes.
//  Alice's net income is now -2.
//- Both Alice and Bob move to node 1.
//  Since they reach here simultaneously, they open the gate together and share the reward.
//  Alice's net income becomes -2 + (4 / 2) = 0.
//- Alice moves on to node 3. Since Bob already opened its gate, Alice's income remains unchanged.
//  Bob moves on to node 0, and stops moving.
//- Alice moves on to node 4 and opens the gate there. Her net income becomes 0 + 6 = 6.
//Now, neither Alice nor Bob can make any further moves, and the game ends.
//It is not possible for Alice to get a higher net income.

//Example 2:
//Input: edges = [[0,1]], bob = 1, amount = [-7280,2350]
//Output: -7280
//Explanation:
//Alice follows the path 0->1 whereas Bob follows the path 1->0.
//Thus, Alice opens the gate at node 0 only. Hence, her net income is -7280.
//
//Constraints:
//2 <= n <= 105
//edges.length == n - 1
//edges[i].length == 2
//0 <= ai, bi < n
//ai != bi
//edges represents a valid tree.
//1 <= bob < n
//amount.length == n
//amount[i] is an even integer in the range [-104, 104].
