package P501_600.P543_Diameter_of_Binary_Tree;

public class Main {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);

        Main solution = new Main();
        System.out.println(solution.diameterOfBinaryTree(root1)); // Output: 3

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);

        System.out.println(solution.diameterOfBinaryTree(root2)); // Output: 1
    }

//    public int diameterOfBinaryTree(TreeNode root) {
//        if (root == null) return 0;
//
//        int maxDiameter = 0;
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.offer(root);
//
//        while (!queue.isEmpty()) {
//            TreeNode node = queue.poll();
//            int diameterThroughNode = depth(node.left) + depth(node.right);
//            maxDiameter = Math.max(maxDiameter, diameterThroughNode);
//
//            if (node.left != null) queue.offer(node.left);
//            if (node.right != null) queue.offer(node.right);
//        }
//
//        return maxDiameter;
//    }
//
//    private int depth(TreeNode node) {
//        if (node == null) return 0;
//        return Math.max(depth(node.left), depth(node.right)) + 1;
//    }

    // faster solve
    int maxDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        computeDiameter(root);
        return maxDiameter;
    }

    private int computeDiameter(TreeNode node) {
        if (node == null) return 0;

        int leftDepth = computeDiameter(node.left);
        int rightDepth = computeDiameter(node.right);

        // Update the maximum diameter encountered so far
        maxDiameter = Math.max(maxDiameter, leftDepth + rightDepth);

        // Return the depth of the current subtree rooted at 'node'
        return Math.max(leftDepth, rightDepth) + 1;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
}

//Ми можемо використовувати обхід у глибину (DFS), але одночасно обчислювати і зберігати максимальний діаметр,
// проходячи крізь кожен вузол.
//Основна ідея полягає в тому, що для кожного вузла ми можемо обчислити суму максимальних глибин лівого і
// правого піддерева. Під час обходу вузла ми також можемо оновлювати максимальний діаметр, якщо він
// перевищує попередній максимум.
//Цей підхід використовує обхід у глибину для обчислення максимального діаметру дерева та вимагає меншого
// обсягу ресурсів, оскільки ми обчислюємо лише максимальну глибину для кожного вузла разом із максимальним діаметром.

//Given the root of a binary tree, return the length of the diameter of the tree.
//The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This
// path may or may not pass through the root.
//The length of a path between two nodes is represented by the number of edges between them.
//Example 1:
//Input: root = [1,2,3,4,5]
//Output: 3
//Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].

//Example 2:
//Input: root = [1,2]
//Output: 1
//
//
//Constraints:
//The number of nodes in the tree is in the range [1, 104].
//-100 <= Node.val <= 100
