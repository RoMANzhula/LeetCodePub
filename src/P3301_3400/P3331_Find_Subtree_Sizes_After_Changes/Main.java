package P3301_3400.P3331_Find_Subtree_Sizes_After_Changes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] parent = {-1, 0, 0, 1, 1, 1};
        String s = "abaabc";

        int[] result = solution.findSubtreeSizes(parent, s);

        System.out.println(Arrays.toString(result));
    }

    private List<Integer>[] children;
    private int[] newParent;
    private int[] answer;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        children = new ArrayList[n];
        newParent = parent.clone();
        answer = new int[n];

        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            children[parent[i]].add(i);
        }

        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        dfs(0, s, lastSeen);

        List<Integer>[] newChildren = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            newChildren[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            newChildren[newParent[i]].add(i);
        }

        calculateSizes(0, newChildren);

        return answer;
    }

    private void dfs(int node, String s, int[] lastSeen) {
        int c = s.charAt(node) - 'a';

        int previous = lastSeen[c];

        if (previous != -1) {
            newParent[node] = previous;
        }

        lastSeen[c] = node;

        for (int child : children[node]) {
            dfs(child, s, lastSeen);
        }

        lastSeen[c] = previous;
    }

    private int calculateSizes(int node, List<Integer>[] newChildren) {
        int size = 1;

        for (int child : newChildren[node]) {
            size += calculateSizes(child, newChildren);
        }

        answer[node] = size;

        return size;
    }

}

//Complexity:
// time and space - O(n)


//You are given a tree rooted at node 0 that consists of n nodes numbered from 0 to n - 1. The tree is represented by
// an array parent of size n, where parent[i] is the parent of node i. Since node 0 is the root, parent[0] == -1.
//You are also given a string s of length n, where s[i] is the character assigned to node i.
//We make the following changes on the tree one time simultaneously for all nodes x from 1 to n - 1:
//Find the closest node y to node x such that y is an ancestor of x, and s[x] == s[y].
//If node y does not exist, do nothing.
//Otherwise, remove the edge between x and its current parent and make node y the new parent of x by adding an edge
// between them.
//Return an array answer of size n where answer[i] is the size of the subtree rooted at node i in the final tree.

//Example 1:
//Input: parent = [-1,0,0,1,1,1], s = "abaabc"
//Output: [6,3,1,1,1,1]
//Explanation:
//The parent of node 3 will change from node 1 to node 0.

//Example 2:
//Input: parent = [-1,0,4,0,1], s = "abbba"
//Output: [5,2,1,1,1]
//Explanation:
//The following changes will happen at the same time:
//The parent of node 4 will change from node 1 to node 0.
//The parent of node 2 will change from node 4 to node 1.

//Constraints:
//n == parent.length == s.length
//1 <= n <= 105
//0 <= parent[i] <= n - 1 for all i >= 1.
//parent[0] == -1
//parent represents a valid tree.
//s consists only of lowercase English letters.
