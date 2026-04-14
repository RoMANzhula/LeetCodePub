package P101_200.P133_Clone_Graph;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        node1.neighbors.add(node2);
        node1.neighbors.add(node4);

        node2.neighbors.add(node1);
        node2.neighbors.add(node3);

        node3.neighbors.add(node2);
        node3.neighbors.add(node4);

        node4.neighbors.add(node1);
        node4.neighbors.add(node3);

        Node cloned = solution.cloneGraph(node1);

        printGraph(cloned, new HashSet<>());
    }

    private static void printGraph(Node node, Set<Node> visited) {
        if (node == null || visited.contains(node)) return;

        visited.add(node);

        System.out.print("Node " + node.val + " neighbors: ");
        for (Node n : node.neighbors) {
            System.out.print(n.val + " ");
        }
        System.out.println();

        for (Node n : node.neighbors) {
            printGraph(n, visited);
        }
    }


    private Map<Node, Node> map = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) return null;

        //if already cloned → return it
        if (map.containsKey(node)) {
            return map.get(node);
        }

        // clone current node
        Node clone = new Node(node.val);
        map.put(node, clone);

        // clone neighbors recursively
        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(cloneGraph(neighbor));
        }

        return clone;
    }

}

class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

//Complexity:
// time - O(N * E)
// space - O(N)


//Given a reference of a node in a connected undirected graph.
//Return a deep copy (clone) of the graph.
//Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
//class Node {
//    public int val;
//    public List<Node> neighbors;
//}

//Test case format:
//For simplicity, each node's value is the same as the node's index (1-indexed). For example, the first node with
// val == 1, the second node with val == 2, and so on. The graph is represented in the test case using an
// adjacency list.
//An adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the
// set of neighbors of a node in the graph.
//The given node will always be the first node with val = 1. You must return the copy of the given node as a
// reference to the cloned graph.

//Example 1:
//Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
//Output: [[2,4],[1,3],[2,4],[1,3]]
//Explanation: There are 4 nodes in the graph.
//1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
//2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
//3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
//4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).

//Example 2:
//Input: adjList = [[]]
//Output: [[]]
//Explanation: Note that the input contains one empty list. The graph consists of only one node with val = 1 and it
// does not have any neighbors.

//Example 3:
//Input: adjList = []
//Output: []
//Explanation: This an empty graph, it does not have any nodes.

//Constraints:
//The number of nodes in the graph is in the range [0, 100].
//1 <= Node.val <= 100
//Node.val is unique for each node.
//There are no repeated edges and no self-loops in the graph.
//The Graph is connected and all nodes can be visited starting from the given node.
