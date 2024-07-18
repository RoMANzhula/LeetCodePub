package P1501_1600.P1530_Number_of_Good_Leaf_Nodes_Pairs;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.right = new TreeNode(4);
        System.out.println(solution.countPairs(root1, 3)); // Output: 1

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4);
        root2.left.right = new TreeNode(5);
        root2.right.left = new TreeNode(6);
        root2.right.right = new TreeNode(7);
        System.out.println(solution.countPairs(root2, 3)); // Output: 2

        TreeNode root3 = new TreeNode(7);
        root3.left = new TreeNode(1);
        root3.right = new TreeNode(4);
        root3.left.left = new TreeNode(6);
        root3.right.left = new TreeNode(5);
        root3.right.right = new TreeNode(3);
        root3.right.right.right = new TreeNode(2);
        System.out.println(solution.countPairs(root3, 3)); // Output: 1
    }

    public int countPairs(TreeNode root, int distance) {
        if (root == null) return 0;
        int[] result = new int[1];
        dfs(root, distance, result);
        return result[0];
    }

    private List<Integer> dfs(TreeNode node, int distance, int[] result) {
        List<Integer> distances = new ArrayList<>();
        if (node == null) return distances;
        if (node.left == null && node.right == null) {
            distances.add(1); // leaf node has a distance of 1 from itself
            return distances;
        }

        List<Integer> leftDistances = dfs(node.left, distance, result);
        List<Integer> rightDistances = dfs(node.right, distance, result);

        // Count good pairs between left and right subtrees
        for (int l : leftDistances) {
            for (int r : rightDistances) {
                if (l + r <= distance) {
                    result[0]++;
                }
            }
        }

        // Collect distances for the current node
        for (int l : leftDistances) {
            if (l + 1 < distance) {
                distances.add(l + 1);
            }
        }
        for (int r : rightDistances) {
            if (r + 1 < distance) {
                distances.add(r + 1);
            }
        }

        return distances;
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
//TreeNode class: A simple definition of the tree node.
//countPairs method: This method initializes the result array and calls the dfs method.
//dfs method: This method performs a depth-first search. It returns a list of distances of leaf nodes from the
// current node. It also updates the result array with the count of good leaf node pairs.
//Combination of results: For each leaf distance from the left subtree, it is combined with each leaf distance
// from the right subtree to check if they form a good pair.
//Distance collection: Distances are incremented by 1 as we move up the tree and only those within the specified
// distance are considered.
//This solution effectively counts the number of good leaf node pairs while traversing the tree in a DFS manner.


//You are given the root of a binary tree and an integer distance. A pair of two different leaf nodes of a binary
// tree is said to be good if the length of the shortest path between them is less than or equal to distance.
//Return the number of good leaf node pairs in the tree.
//
//Example 1:
//Input: root = [1,2,3,null,4], distance = 3
//Output: 1
//Explanation: The leaf nodes of the tree are 3 and 4 and the length of the shortest path between
// them is 3. This is the only good pair.

//Example 2:
//Input: root = [1,2,3,4,5,6,7], distance = 3
//Output: 2
//Explanation: The good pairs are [4,5] and [6,7] with shortest path = 2. The pair [4,6] is not good because
// the length of ther shortest path between them is 4.

//Example 3:
//Input: root = [7,1,4,6,null,5,3,null,null,null,null,null,2], distance = 3
//Output: 1
//Explanation: The only good pair is [2,5].
//
//Constraints:
//The number of nodes in the tree is in the range [1, 210].
//1 <= Node.val <= 100
//1 <= distance <= 10
