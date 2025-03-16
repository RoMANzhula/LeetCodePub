package P1301_1400.P1361_Validate_Binary_Tree_Nodes;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();
        int n1 = 4;
        int[] leftChild1 = {1, -1, 3, -1};
        int[] rightChild1 = {2, -1, -1, -1};
        System.out.println(solution.validateBinaryTreeNodes(n1, leftChild1, rightChild1)); // Output: true

        int n2 = 4;
        int[] leftChild2 = {1, -1, 3, -1};
        int[] rightChild2 = {2, 3, -1, -1};
        System.out.println(solution.validateBinaryTreeNodes(n2, leftChild2, rightChild2)); // Output: false

        int n3 = 2;
        int[] leftChild3 = {1, 0};
        int[] rightChild3 = {-1, -1};
        System.out.println(solution.validateBinaryTreeNodes(n3, leftChild3, rightChild3)); // Output: false

        int n4 = 4;
        int[] leftChild4 = {1, 0, 3, -1};
        int[] rightChild4 = {-1, -1, -1, -1};
        System.out.println(solution.validateBinaryTreeNodes(n4, leftChild4, rightChild4)); // Output: false
    }


    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        int[] inDegree = new int[n];
        int root = -1;

        for (int i = 0; i < n; i++) {
            int left = leftChild[i];
            int right = rightChild[i];

            if (left != -1) {
                inDegree[left]++;
                if (inDegree[left] == 2) {
                    return false;
                }
            }

            if (right != -1) {
                inDegree[right]++;
                if (inDegree[right] == 2) {
                    return false;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                if (root == -1) {
                    root = i;
                } else {
                    return false; //if many roots
                }
            }
        }

        if (root == -1) {
            return false; //the root didn't find
        }

        return countNodes(root, leftChild, rightChild, n) == n;
    }

    private int countNodes(int root, int[] leftChild, int[] rightChild, int n) {
        if (root == -1) {
            return 0;
        }

        return 1 + countNodes(leftChild[root], leftChild, rightChild, n) +
                countNodes(rightChild[root], leftChild, rightChild, n);
    }

}

//You have n binary tree nodes numbered from 0 to n - 1 where node i has two children leftChild[i] and rightChild[i],
// return true if and only if all the given nodes form exactly one valid binary tree.
//If node i has no left child then leftChild[i] will equal -1, similarly for the right child.
//Note that the nodes have no values and that we only use the node numbers in this problem.

//Example 1:
//Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,-1,-1,-1]
//Output: true

//Example 2:
//Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,3,-1,-1]
//Output: false

//Example 3:
//Input: n = 2, leftChild = [1,0], rightChild = [-1,-1]
//Output: false

//Constraints:
//n == leftChild.length == rightChild.length
//1 <= n <= 104
//-1 <= leftChild[i], rightChild[i] <= n - 1
