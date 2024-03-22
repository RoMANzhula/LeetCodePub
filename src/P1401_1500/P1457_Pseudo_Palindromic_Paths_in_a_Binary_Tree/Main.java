package P1401_1500.P1457_Pseudo_Palindromic_Paths_in_a_Binary_Tree;

public class Main {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(1);
        root1.left.left = new TreeNode(3);
        root1.left.right = new TreeNode(1);
        root1.right.right = new TreeNode(1);

        Main solution = new Main();
        int result1 = solution.pseudoPalindromicPaths(root1);
        System.out.println("Output for Example 1: " + result1); //output 2

        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(1);
        root2.left.left = new TreeNode(1);
        root2.left.right = new TreeNode(3);
        root2.right.right = new TreeNode(1);

        int result2 = solution.pseudoPalindromicPaths(root2);
        System.out.println("Output for Example 2: " + result2); //output 2

        TreeNode root3 = new TreeNode(9);
        int result3 = solution.pseudoPalindromicPaths(root3);
        System.out.println("Output for Example 3: " + result3); //output 1
    }

    public int pseudoPalindromicPaths(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode node, int count) {
        if (node == null)
            return 0;

        // Update count based on the current node value
        count ^= 1 << (node.val - 1);

        // If it's a leaf node, check if the path is pseudo-palindromic
        if (node.left == null && node.right == null) {
            // If there is at most one set bit in count, it is pseudo-palindromic
            return (count & (count - 1)) == 0 ? 1 : 0;
        }

        // Continue DFS for left and right children
        int leftCount = dfs(node.left, count);
        int rightCount = dfs(node.right, count);

        return leftCount + rightCount;
    }
}


//  Definition for a binary tree node.
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

//адача передбачає обчислення кількості псевдопаліндромних шляхів у бінарному дереві. Щоб розв'язати цю задачу,
// можна використовувати метод обходу в глибину (DFS) для проходження через дерево. Під час обходу на кожному
// вузлі потрібно оновлювати лічильник кількості псевдопаліндромних шляхів на основі поточного шляху і
// продовжувати обхід для його лівого та правого дочірніх елементів.
//
//Основна ідея полягає в тому, що для кожного вузла ми використовуємо бітову маску для відстеження того, які
// цифри ми зустрічаємо на шляху до даного вузла. Якщо для шляху до листя з цього вузла є можливість створити
// паліндром (тобто маска має не більше одного встановленого біта), то збільшуємо лічильник.
//
//кроки у реалізації:
//
//Створюємо клас TreeNode для представлення вузла дерева.
//Створюємо клас PseudoPalindromicPaths, який містить метод pseudoPalindromicPaths для ініціалізації DFS та
// метод dfs для рекурсивного обходу дерева.
//У методі dfs оновлюємо бітову маску та перевіряємо, чи можливо створити паліндром для поточного шляху до листя.
//У головному методі демонструємо використання цього рішення для заданих прикладів.
 

//Given a binary tree where node values are digits from 1 to 9. A path in the binary tree is said to be
// pseudo-palindromic if at least one permutation of the node values in the path is a palindrome.
//Return the number of pseudo-palindromic paths going from the root node to leaf nodes.

//Example 1:
//Input: root = [2,3,1,3,1,null,1]
//Output: 2
//Explanation: The figure above represents the given binary tree. There are three paths going from the root
// node to leaf nodes: the red path [2,3,3], the green path [2,1,1], and the path [2,3,1]. Among these paths
// only red path and green path are pseudo-palindromic paths since the red path [2,3,3] can be rearranged
// in [3,2,3] (palindrome) and the green path [2,1,1] can be rearranged in [1,2,1] (palindrome).

//Example 2:
//Input: root = [2,1,1,1,3,null,null,null,null,null,1]
//Output: 1
//Explanation: The figure above represents the given binary tree. There are three paths going from the root
// node to leaf nodes: the green path [2,1,1], the path [2,1,3,1], and the path [2,1]. Among these paths only
// the green path is pseudo-palindromic since [2,1,1] can be rearranged in [1,2,1] (palindrome).

//Example 3:
//Input: root = [9]
//Output: 1

//Constraints:
//The number of nodes in the tree is in the range [1, 105].
//1 <= Node.val <= 9