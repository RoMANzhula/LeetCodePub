package P601_700.P623_Add_One_Row_to_Tree;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        // Example 1
        TreeNode root1 = new TreeNode(4);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(6);
        root1.left.left = new TreeNode(3);
        root1.left.right = new TreeNode(1);
        root1.right.left = new TreeNode(5);
        int val1 = 1;
        int depth1 = 2;

        TreeNode result1 = solution.addOneRow(root1, val1, depth1);
        System.out.println("Example 1 Output:");
        printTree(result1);

        // Example 2
        TreeNode root2 = new TreeNode(4);
        root2.left = new TreeNode(2);
        root2.left.left = new TreeNode(3);
        root2.left.right = new TreeNode(1);
        int val2 = 1;
        int depth2 = 3;

        TreeNode result2 = solution.addOneRow(root2, val2, depth2);
        System.out.println("\nExample 2 Output:");
        printTree(result2);
    }

    //print the tree (inorder traversal)
    private static void printTree(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                System.out.print(current.val + " ");
                if (current.left != null) queue.offer(current.left);
                if (current.right != null) queue.offer(current.right);
            }
            System.out.println();
        }
    }

    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            TreeNode newRoot = new TreeNode(val);
            newRoot.left = root;
            return newRoot;
        }

        addRow(root, val, depth, 1);

        return root;
    }

    private void addRow(TreeNode node, int val, int depth, int currentDepth) {
        if (node == null) return;
        if (currentDepth == depth - 1) {
            TreeNode left = new TreeNode(val);
            left.left = node.left;
            node.left = left;

            TreeNode right = new TreeNode(val);
            right.right = node.right;
            node.right = right;
            return;
        }
        addRow(node.left, val, depth, currentDepth + 1);
        addRow(node.right, val, depth, currentDepth + 1);
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

//Задача полягає в додаванні ряду вузлів на певну глибину у бінарному дереві. У нас є дерево, корінь якого
// передається в функцію, а також два цілі числа: val та depth. Ми повинні додати ряд вузлів зі значенням val на
// глибині depth.
//Правила додавання такі:
//Якщо depth дорівнює 1, це означає, що на глибині 1 в дереві немає вузла. Тому ми створюємо новий корінь зі
// значенням val, а піддерево, що передали на вхід, стає лівим піддеревом нового кореня.
//Якщо depth більше 1, ми рекурсивно спускаємося по дереву на глибину depth - 1. Коли ми досягаємо глибини depth - 1,
// ми додаємо нові вузли зі значенням val як лівий та правий нащадки поточного вузла. При цьому, лівий нащадок отримує
// піддерево, яке раніше було лівим нащадком поточного вузла, а правий нащадок отримує піддерево, яке раніше було
// правим нащадком поточного вузла.
//Цей процес відбувається рекурсивно досягнення глибини depth - 1, після чого нові вузли з val додаються на
// відповідний рівень.

//Given the root of a binary tree and two integers val and depth, add a row of nodes with value val at the
// given depth depth.
//Note that the root node is at depth 1.
//The adding rule is:
//Given the integer depth, for each not null tree node cur at the depth depth - 1, create two tree nodes with
// value val as cur's left subtree root and right subtree root.
//cur's original left subtree should be the left subtree of the new left subtree root.
//cur's original right subtree should be the right subtree of the new right subtree root.
//If depth == 1 that means there is no depth depth - 1 at all, then create a tree node with value val as the new
// root of the whole original tree, and the original tree is the new root's left subtree.
//
//Example 1:
//Input: root = [4,2,6,3,1,5], val = 1, depth = 2
//Output: [4,1,1,2,null,null,6,3,1,5]

//Example 2:
//Input: root = [4,2,null,3,1], val = 1, depth = 3
//Output: [4,2,null,1,1,3,null,null,1]
//
//Constraints:
//The number of nodes in the tree is in the range [1, 104].
//The depth of the tree is in the range [1, 104].
//-100 <= Node.val <= 100
//-105 <= val <= 105
//1 <= depth <= the depth of tree + 1