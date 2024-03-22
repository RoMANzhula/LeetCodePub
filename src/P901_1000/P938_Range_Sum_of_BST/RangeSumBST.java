package P901_1000.P938_Range_Sum_of_BST;


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


public class RangeSumBST {
      public int rangeSumBST(TreeNode root, int low, int high) {
          if (root == null) {
              return 0;
          }
          // If the current node's value is within the range [low, high], include it in the sum
          int sum = 0;
          if (root.val >= low && root.val <= high) {
              sum += root.val;
          }

          // Recursively traverse left and right subtrees
          sum += rangeSumBST(root.left, low, high);
          sum += rangeSumBST(root.right, low, high);

          return sum; //bingo
      }

      public static void main(String[] args) {
          // Example usage:
          // Construct the binary search tree from the given input
          TreeNode root1 = new TreeNode(10);
          root1.left = new TreeNode(5);
          root1.right = new TreeNode(15);
          root1.left.left = new TreeNode(3);
          root1.left.right = new TreeNode(7);
          root1.right.right = new TreeNode(18);

          RangeSumBST solution = new RangeSumBST();

          // Example 1
          int result1 = solution.rangeSumBST(root1, 7, 15);
          System.out.println("Output 1: " + result1); // Output: 32

          // Example 2
          TreeNode root2 = new TreeNode(10);
          root2.left = new TreeNode(5);
          root2.right = new TreeNode(15);
          root2.left.left = new TreeNode(3);
          root2.left.right = new TreeNode(7);
          root2.right.left = new TreeNode(13);
          root2.right.right = new TreeNode(18);
          root2.left.left.left = new TreeNode(1);
          root2.left.right.left = new TreeNode(6);

          int result2 = solution.rangeSumBST(root2, 6, 10);
          System.out.println("Output 2: " + result2); // Output: 23
      }
}

//Задача полягає в обчисленні суми значень вузлів бінарного дерева пошуку (BST), які знаходяться в заданому
// інтервалі [low, high].
//
//Основна ідея вирішення полягає в рекурсивному обходженні BST. Для кожного вузла перевіряється, чи його
// значення потрапляє в заданий інтервал [low, high]. Якщо так, то значення вузла додається до суми. Після
// цього рекурсивно викликаються функції для лівого та правого піддерева.
//
//Ми визначаємо клас TreeNode, який представляє вузли бінарного дерева.
//Клас RangeSumBST містить метод rangeSumBST, який обчислює суму в заданому інтервалі.
//У методі rangeSumBST перевіряється, чи значення поточного вузла знаходиться в межах [low, high]. Якщо так,
// то його значення додається до суми.
//Далі рекурсивно викликається rangeSumBST для лівого та правого піддерева.
//У головній функції main створюються два приклади бінарних дерев і викликається метод rangeSumBST для обчислення
// суми в заданих інтервалах.
//Надіюся, це пояснення вам допоможе зрозуміти розв'язок задачі.


//Given the root node of a binary search tree and two integers low and high, return the sum of values of all
// nodes with a value in the inclusive range [low, high].
//
//Example 1:
//Input: root = [10,5,15,3,7,null,18], low = 7, high = 15
//Output: 32
//Explanation: Nodes 7, 10, and 15 are in the range [7, 15]. 7 + 10 + 15 = 32.
//Example 2:
//Input: root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
//Output: 23
//Explanation: Nodes 6, 7, and 10 are in the range [6, 10]. 6 + 7 + 10 = 23.
//
//Constraints:
//The number of nodes in the tree is in the range [1, 2  104].
//1 <= Node.val <= 105
//1 <= low <= high <= 105
//All Node.val are unique.
