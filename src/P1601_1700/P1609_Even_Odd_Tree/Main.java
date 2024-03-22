package P1601_1700.P1609_Even_Odd_Tree;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(10);
        root1.right = new TreeNode(4);
        root1.left.left = new TreeNode(3);
        root1.right.left = new TreeNode(7);
        root1.right.right = new TreeNode(9);
        root1.left.left.left = new TreeNode(12);
        root1.left.left.right = new TreeNode(8);
        root1.right.left.left = new TreeNode(6);
        root1.right.left.right = new TreeNode(2);

        Main solution = new Main();
        System.out.println(solution.isEvenOddTree(root1)); // Output: true

        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(4);
        root2.right = new TreeNode(2);
        root2.left.left = new TreeNode(3);
        root2.left.right = new TreeNode(3);
        root2.right.right = new TreeNode(7);

        System.out.println(solution.isEvenOddTree(root2)); // Output: false

        TreeNode root3 = new TreeNode(5);
        root3.left = new TreeNode(9);
        root3.right = new TreeNode(1);
        root3.left.left = new TreeNode(3);
        root3.left.right = new TreeNode(5);
        root3.right.right = new TreeNode(7);

        System.out.println(solution.isEvenOddTree(root3)); // Output: false
    }

    public boolean isEvenOddTree(TreeNode root) {
        if (root == null)
            return true;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            int prevVal = level % 2 == 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                // Check for invalid values
                if ((level % 2 == 0 && (node.val % 2 == 0 || node.val <= prevVal)) ||
                        (level % 2 != 0 && (node.val % 2 != 0 || node.val >= prevVal)))
                    return false;

                // Update prevVal for the next level
                prevVal = node.val;

                // Add children to the queue
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }

            // Increment level for the next iteration
            level++;
        }

        return true;
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

//Задача полягає у перевірці бінарного дерева на те, чи відповідає воно умовам Even-Odd (Парне-Непарне). Умови такі:
//Для рівнів з парними індексами всі вузли мають непарні значення, які збільшуються строго від лівого до правого вузла.
//Для рівнів з непарними індексами всі вузли мають парні значення, які зменшуються строго від лівого до правого вузла.
//Ми перевіряємо ці умови, проходячи по кожному рівню дерева у порядку згортання (BFS). Під час перевірки ми
// зберігаємо індекс поточного рівня і попереднє значення, щоб порівняти з наступним значенням. Якщо умови не
// виконуються на будь-якому рівні, ми повертаємо false. Якщо всі умови виконуються для всіх рівнів, ми
// повертаємо true, що вказує на те, що дерево є Even-Odd.

//A binary tree is named Even-Odd if it meets the following conditions:
//The root of the binary tree is at level index 0, its children are at level index 1, their children are at
// level index 2, etc.
//For every even-indexed level, all nodes at the level have odd integer values in strictly increasing
// order (from left to right).
//For every odd-indexed level, all nodes at the level have even integer values in strictly decreasing
// order (from left to right).
//Given the root of a binary tree, return true if the binary tree is Even-Odd, otherwise return false.

//Example 1:
//Input: root = [1,10,4,3,null,7,9,12,8,6,null,null,2]
//Output: true
//Explanation: The node values on each level are:
//Level 0: [1]
//Level 1: [10,4]
//Level 2: [3,7,9]
//Level 3: [12,8,6,2]
//Since levels 0 and 2 are all odd and increasing and levels 1 and 3 are all even and decreasing, the tree is Even-Odd.

//Example 2:
//Input: root = [5,4,2,3,3,7]
//Output: false
//Explanation: The node values on each level are:
//Level 0: [5]
//Level 1: [4,2]
//Level 2: [3,3,7]
//Node values in level 2 must be in strictly increasing order, so the tree is not Even-Odd.

//Example 3:
//Input: root = [5,9,1,3,5,7]
//Output: false
//Explanation: Node values in the level 1 should be even integers.
//
//Constraints:
//The number of nodes in the tree is in the range [1, 105].
//1 <= Node.val <= 106
