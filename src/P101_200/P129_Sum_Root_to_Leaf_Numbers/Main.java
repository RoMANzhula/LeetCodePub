package P101_200.P129_Sum_Root_to_Leaf_Numbers;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        // Example 1: root = [1,2,3]
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        System.out.println("Example 1 Output: " + solution.sumNumbers(root1)); // Output: 25

        // Example 2: root = [4,9,0,5,1]
        TreeNode root2 = new TreeNode(4);
        root2.left = new TreeNode(9);
        root2.right = new TreeNode(0);
        root2.left.left = new TreeNode(5);
        root2.left.right = new TreeNode(1);
        System.out.println("Example 2 Output: " + solution.sumNumbers(root2)); // Output: 1026
    }

    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode node, int currentSum) {
        if (node == null) return 0;
        currentSum = currentSum * 10 + node.val;
        if (node.left == null && node.right == null) {
            return currentSum;
        }
        int leftSum = dfs(node.left, currentSum);
        int rightSum = dfs(node.right, currentSum);
        return leftSum + rightSum;
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

//Це рішення використовує пошук у глибину (DFS) для обходу дерева від кореня до листків. На кожному вузлі воно
// обчислює суму чисел у шляху від кореня до цього вузла, множачи поточну суму на 10 і додаючи значення
// вузла. Якщо поточний вузол є листком, воно повертає обчислену суму. В іншому випадку воно продовжує
// обхід DFS до досягнення листків. На завершення, воно повертає суму всіх чисел від кореня до листя.

//You are given the root of a binary tree containing digits from 0 to 9 only.
//Each root-to-leaf path in the tree represents a number.
//For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
//Return the total sum of all root-to-leaf numbers. Test cases are generated so that the answer will fit in
// a 32-bit integer.
//A leaf node is a node with no children.
//
//Example 1:
//Input: root = [1,2,3]
//Output: 25
//Explanation:
//The root-to-leaf path 1->2 represents the number 12.
//The root-to-leaf path 1->3 represents the number 13.
//Therefore, sum = 12 + 13 = 25.

//Example 2:
//Input: root = [4,9,0,5,1]
//Output: 1026
//Explanation:
//The root-to-leaf path 4->9->5 represents the number 495.
//The root-to-leaf path 4->9->1 represents the number 491.
//The root-to-leaf path 4->0 represents the number 40.
//Therefore, sum = 495 + 491 + 40 = 1026.
//
//Constraints:
//The number of nodes in the tree is in the range [1, 1000].
//0 <= Node.val <= 9
//The depth of the tree will not exceed 10.
