package P1001_1100.P1026_Maximum_Difference_Between_Node_and_Ancestor;

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
public class Main {
    public static void main(String[] args) {
        // Example 1
        TreeNode root1 = new TreeNode(8,
                new TreeNode(3,
                        new TreeNode(1),
                        new TreeNode(6,
                                new TreeNode(4),
                                new TreeNode(7))),
                new TreeNode(10,
                        null,
                        new TreeNode(14,
                                new TreeNode(13),
                                null)));
        Main solution = new Main();
        System.out.println(solution.maxAncestorDiff(root1)); // Output: 7

        // Example 2
        TreeNode root2 = new TreeNode(1,
                null,
                new TreeNode(2,
                        null,
                        new TreeNode(0,
                                null,
                                new TreeNode(3))));
        System.out.println(solution.maxAncestorDiff(root2)); // Output: 3
    }

    public int maxAncestorDiff(TreeNode root) {
        return dfs(root, root.val, root.val);
    }


    private int dfs(TreeNode node, int min, int max) {
        if (node == null) {
            return max - min;
        }

        // Update min and max values for the current path
        min = Math.min(min, node.val);
        max = Math.max(max, node.val);

        // Calculate the maximum ancestor difference for the left and right subtrees
        int leftDiff = dfs(node.left, min, max);
        int rightDiff = dfs(node.right, min, max);

        // Return the maximum difference among the current node and its descendants
        return Math.max(leftDiff, rightDiff);
    }
}

//Ця задача вирішується за допомогою обходу дерева в глибину (DFS). Основна ідея полягає в тому, щоб для
// кожного вузла визначити максимальну різницю між його значенням і найменшим/найбільшим значенням, яке
// було знайдено на шляху від кореня до цього вузла.
//
//У методі maxAncestorDiff спочатку викликається рекурсивна функція dfs для кореневого вузла з початковими
// значеннями мінімуму та максимуму, які встановлюються на значення кореня. Функція dfs виконує обхід
// дерева в глибину, оновлюючи мінімальне та максимальне значення на шляху до кожного вузла.
//
//Для кожного вузла визначається різниця між його значенням та мінімальним/максимальним значенням на
// поточному шляху. Після завершення обходу лівого та правого піддерев функція повертає максимальну
// різницю серед поточного вузла та його нащадків.
//
//Цей підхід дозволяє знаходити максимальну різницю між значеннями вузлів, де один вузол є предком
// іншого. При використанні цього методу з відповідними тестовими входами, ви отримаєте очікувані
// значення для максимальної різниці між предком і нащадком у бінарному дереві.


//Given the root of a binary tree, find the maximum value v for which there exist different nodes a and
// b where v = |a.val - b.val| and a is an ancestor of b.
//A node a is an ancestor of b if either: any child of a is equal to b or any child of a is an ancestor of b.

//Example 1:
//Input: root = [8,3,10,1,6,null,14,null,null,4,7,13]
//Output: 7
//Explanation: We have various ancestor-node differences, some of which are given below :
//|8 - 3| = 5
//|3 - 7| = 4
//|8 - 1| = 7
//|10 - 13| = 3
//Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.

//Example 2:
//Input: root = [1,null,2,null,0,3]
//Output: 3

//Constraints:
//The number of nodes in the tree is in the range [2, 5000].
//0 <= Node.val <= 105
