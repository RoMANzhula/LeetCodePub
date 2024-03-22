package P501_600.P513_Find_Bottom_Left_Tree_Value;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(3);
        Main solution1 = new Main();
        System.out.println("Example 1 Output: " + solution1.findBottomLeftValue(root1)); // output 1

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4);
        root2.right.left = new TreeNode(5);
        root2.right.right = new TreeNode(6);
        root2.right.left.left = new TreeNode(7);
        Main solution2 = new Main();
        System.out.println("Example 2 Output: " + solution2.findBottomLeftValue(root2)); // output 7
    }

    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return -1; // or throw an exception, depending on your requirement
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int leftmostValue = root.val;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            leftmostValue = queue.peek().val; // update leftmost value for the current level

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();

                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
        }

        return leftmostValue;
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

//аний алгоритм використовує метод обходу в ширину (BFS) для того, щоб пройтися по дереву рівень за рівнем. Він
// використовує чергу для зберігання вузлів, які потрібно обробити на поточному рівні. Починаючи з кореня дерева,
// ми додаємо його до черги. Потім ми починаємо обробку кожного рівня дерева.
//Поки черга не стане порожньою, ми визначаємо кількість вузлів на поточному рівні (це допоможе нам обробити
// всі вузли на цьому рівні перед переходом до наступного). Під час циклу ми витягуємо вузол з черги і перевіряємо,
// чи має він лівого та правого дітей. Якщо так, то ми додаємо їх у чергу для подальшої обробки.
//Ключовим моментом є те, що на кожному кроці ми оновлюємо значення leftmostValue на значення лівого вузла черги. Це
// означає, що кожний раз, коли ми переходимо на новий рівень, leftmostValue оновлюється на значення лівого вузла
// цього рівня. Коли ми завершимо обробку останнього рівня, leftmostValue буде містити значення лівого вузла
// останнього рівня, тобто найлівіший вузол в останньому рядку дерева.
//Нарешті, коли ми завершимо обробку всіх рівнів, повертаємо leftmostValue, яке містить значення лівого
// вузла останнього рівня.

//Given the root of a binary tree, return the leftmost value in the last row of the tree.

//Example 1:
//Input: root = [2,1,3]
//Output: 1

//Example 2:
//Input: root = [1,2,3,4,null,5,6,null,null,7]
//Output: 7
//
//Constraints:
//The number of nodes in the tree is in the range [1, 104].
//-231 <= Node.val <= 231 - 1
