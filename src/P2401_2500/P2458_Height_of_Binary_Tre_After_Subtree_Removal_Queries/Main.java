package P2401_2500.P2458_Height_of_Binary_Tre_After_Subtree_Removal_Queries;

import java.util.*;

public class Main {

    private Map<Integer, Integer> nodeDepth = new HashMap<>();
    private Map<Integer, Integer> nodeHeight = new HashMap<>();
    private Map<Integer, List<Integer>> depthMaxHeights = new HashMap<>();

    public static void main(String[] args) {
        Main solution = new Main();

        // Tree: [1,3,4,2,null,6,5,null,null,null,null,null,7]
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(4);
        root1.left.left = new TreeNode(2);
        root1.right.left = new TreeNode(6);
        root1.right.right = new TreeNode(5);
        root1.right.right.right = new TreeNode(7);
        int[] queries1 = {4};
        int[] result1 = solution.treeQueries(root1, queries1);
        System.out.println("Example 1 Output: " + java.util.Arrays.toString(result1)); // Expected: [2]

        // Tree: [5,8,9,2,1,3,7,4,6]
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(8);
        root2.right = new TreeNode(9);
        root2.left.left = new TreeNode(2);
        root2.left.right = new TreeNode(1);
        root2.right.left = new TreeNode(3);
        root2.right.right = new TreeNode(7);
        root2.left.left.left = new TreeNode(4);
        root2.left.left.right = new TreeNode(6);
        int[] queries2 = {3, 2, 4, 8};
        int[] result2 = solution.treeQueries(root2, queries2);
        System.out.println("Example 2 Output: " + java.util.Arrays.toString(result2)); // Expected: [3, 3, 3, 3]
    }


//    public int[] treeQueries(TreeNode root, int[] queries) {
//        computeDepthAndHeight(root, 0);
//        calculateMaxHeightsAtEachDepth();
//
//        int initialHeight = getTreeHeight();
//        int[] results = new int[queries.length];
//
//        for (int i = 0; i < queries.length; i++) {
//            int queryNode = queries[i];
//            int depth = nodeDepth.get(queryNode);
//            int height = nodeHeight.get(queryNode);
//
//            List<Integer> maxHeights = depthMaxHeights.get(depth);
//            int newHeight = (maxHeights.get(0) == height) ? initialHeight - height + maxHeights.get(1) : initialHeight;
//
//            results[i] = newHeight;
//        }
//
//        return results;
//    }
//
//    private int computeDepthAndHeight(TreeNode node, int depth) {
//        if (node == null) return -1;
//
//        nodeDepth.put(node.val, depth);
//        int leftHeight = computeDepthAndHeight(node.left, depth + 1);
//        int rightHeight = computeDepthAndHeight(node.right, depth + 1);
//
//        int height = 1 + Math.max(leftHeight, rightHeight);
//        nodeHeight.put(node.val, height);
//
//        return height;
//    }
//
//    private void calculateMaxHeightsAtEachDepth() {
//        for (Map.Entry<Integer, Integer> entry : nodeDepth.entrySet()) {
//            int depth = entry.getValue();
//            int height = nodeHeight.get(entry.getKey());
//
//            depthMaxHeights.putIfAbsent(depth, new ArrayList<>(Arrays.asList(-1, -1)));
//            List<Integer> maxHeights = depthMaxHeights.get(depth);
//
//            if (height > maxHeights.get(0)) {
//                maxHeights.set(1, maxHeights.get(0));
//                maxHeights.set(0, height);
//            } else if (height > maxHeights.get(1)) {
//                maxHeights.set(1, height);
//            }
//        }
//    }
//
//    private int getTreeHeight() {
//        int maxHeight = 0;
//        for (Map.Entry<Integer, List<Integer>> entry : depthMaxHeights.entrySet()) {
//            int depth = entry.getKey();
//            int height = entry.getValue().get(0);
//            maxHeight = Math.max(maxHeight, depth + height);
//        }
//        return maxHeight;
//    }

    //little faster solution
    private Map<Integer, Integer> depthMap = new HashMap<>();
    private Map<Integer, Integer> heightMap = new HashMap<>();
    private Map<Integer, List<Integer>> depthHeights = new HashMap<>();

    public int[] treeQueries(TreeNode root, int[] queries) {
        // Step 1: Compute depth and height for each node
        computeDepthAndHeight(root, 0);

        // Step 2: Organize the heights per depth level
        for (int node : heightMap.keySet()) {
            int depth = depthMap.get(node);
            int height = heightMap.get(node);
            depthHeights.computeIfAbsent(depth, k -> new ArrayList<>()).add(height);
        }

        // Step 3: Sort heights within each depth level for easy access
        for (List<Integer> heights : depthHeights.values()) {
            Collections.sort(heights, Collections.reverseOrder());
        }

        // Step 4: Process each query
        int[] result = new int[queries.length];
        int originalHeight = depthHeights.keySet().stream().max(Integer::compare).orElse(0);

        for (int i = 0; i < queries.length; i++) {
            int queryNode = queries[i];
            int depth = depthMap.get(queryNode);
            int height = heightMap.get(queryNode);

            // Exclude this node's height from its depth level for current calculation
            List<Integer> heightsAtDepth = depthHeights.get(depth);
            int maxDepthHeight;
            if (heightsAtDepth.size() == 1) {
                maxDepthHeight = depth - 1; // No other node at this depth
            } else if (heightsAtDepth.get(0) == height) {
                maxDepthHeight = depth + heightsAtDepth.get(1); // Second highest at this depth
            } else {
                maxDepthHeight = originalHeight;
            }

            // Tree height with the query node's subtree removed
            result[i] = maxDepthHeight;
        }

        return result;
    }

    private int computeDepthAndHeight(TreeNode node, int depth) {
        if (node == null) return -1;

        depthMap.put(node.val, depth);

        int leftHeight = computeDepthAndHeight(node.left, depth + 1);
        int rightHeight = computeDepthAndHeight(node.right, depth + 1);

        int nodeHeight = 1 + Math.max(leftHeight, rightHeight);
        heightMap.put(node.val, nodeHeight);

        return nodeHeight;
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

//Explanation
//computeDepthAndHeight: Calculates the depth and height of each node in the tree.
//calculateMaxHeightsAtEachDepth: Tracks the maximum and second-highest heights at each depth.
//getTreeHeight: Computes the initial height of the tree.
//Query Processing:
//For each query, we check if the queried node has the maximum height at its depth level.
//If it does, we replace it with the second-highest height.
//Otherwise, the tree height remains the initial height.

//Explanation of Key Steps in faster solution
//Compute Depth and Height:
//The computeDepthAndHeight method populates depthMap and heightMap.
//Depth-Heights Map:
//For each depth, heights of nodes are sorted in descending order to allow quick access to the maximum or the
// second-highest height at that depth.
//Query Processing:
//For each query, the subtree is virtually removed by ignoring the node's height in the depthHeights map and
// recalculating the tree height.


//2458. Height of Binary Tree After Subtree Removal Queries
//Hard
//Topics
//Companies
//Hint
//You are given the root of a binary tree with n nodes. Each node is assigned a unique value from 1 to n. You are
// also given an array queries of size m.
//You have to perform m independent queries on the tree where in the ith query you do the following
//Remove the subtree rooted at the node with the value queries[i] from the tree. It is guaranteed that queries[i] will
// not be equal to the value of the root.
//Return an array answer of size m where answer[i] is the height of the tree after performing the ith query.
//Note:
//The queries are independent, so the tree returns to its initial state after each query.
//The height of a tree is the number of edges in the longest simple path from the root to some node in the tree.
//
//Example 1:
//Input: root = [1,3,4,2,null,6,5,null,null,null,null,null,7], queries = [4]
//Output: [2]
//Explanation: The diagram above shows the tree after removing the subtree rooted at node with value 4.
//The height of the tree is 2 (The path 1 -> 3 -> 2).

//Example 2:
//Input: root = [5,8,9,2,1,3,7,4,6], queries = [3,2,4,8]
//Output: [3,2,3,2]
//Explanation: We have the following queries:
//- Removing the subtree rooted at node with value 3. The height of the tree becomes 3 (The path 5 -> 8 -> 2 -> 4).
//- Removing the subtree rooted at node with value 2. The height of the tree becomes 2 (The path 5 -> 8 -> 1).
//- Removing the subtree rooted at node with value 4. The height of the tree becomes 3 (The path 5 -> 8 -> 2 -> 6).
//- Removing the subtree rooted at node with value 8. The height of the tree becomes 2 (The path 5 -> 9 -> 3).
//
//
//Constraints:
//The number of nodes in the tree is n.
//2 <= n <= 105
//1 <= Node.val <= n
//All the values in the tree are unique.
//m == queries.length
//1 <= m <= min(n, 104)
//1 <= queries[i] <= n
//queries[i] != root.val
