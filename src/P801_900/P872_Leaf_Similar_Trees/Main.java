package P801_900.P872_Leaf_Similar_Trees;

import java.util.ArrayList;
import java.util.List;

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
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(5);
        root1.right = new TreeNode(1);
        root1.left.left = new TreeNode(6);
        root1.left.right = new TreeNode(2);
        root1.right.left = new TreeNode(9);
        root1.right.right = new TreeNode(8);
        root1.left.right.left = new TreeNode(7);
        root1.left.right.right = new TreeNode(4);

        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(5);
        root2.right = new TreeNode(1);
        root2.left.left = new TreeNode(6);
        root2.left.right = new TreeNode(7);
        root2.right.left = new TreeNode(4);
        root2.right.right = new TreeNode(2);
        root2.right.right.left = new TreeNode(9);
        root2.right.right.right = new TreeNode(8);

        Main solution = new Main();
        System.out.println(solution.leafSimilar(root1, root2)); // Output: true

        // Example 2
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);
        root3.right = new TreeNode(3);

        TreeNode root4 = new TreeNode(1);
        root4.left = new TreeNode(3);
        root4.right = new TreeNode(2);

        System.out.println(solution.leafSimilar(root3, root4)); // Output: false
    }

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leafSequence1 = new ArrayList<>();
        List<Integer> leafSequence2 = new ArrayList<>();

        getLeafSequence(root1, leafSequence1);
        getLeafSequence(root2, leafSequence2);

        return leafSequence1.equals(leafSequence2);
    }

    private void getLeafSequence(TreeNode node, List<Integer> leafSequence) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            leafSequence.add(node.val);
        }

        getLeafSequence(node.left, leafSequence);
        getLeafSequence(node.right, leafSequence);
    }
}

//Задача полягає в перевірці того, чи є два задані бінарні дерева "leaf-similar" (тобто, чи мають вони однакову
// послідовність значень листків).
//
//У розв'язку використовується клас TreeNode, який представляє вузол дерева. Також визначений клас LeafSimilarTrees
// з методом leafSimilar, який приймає два корені дерев і повертає true, якщо вони є leaf-similar, та
// false в іншому випадку.
//
//Основна ідея розв'язку полягає в тому, щоб отримати послідовність значень листків для обох дерев і порівняти
// їх. Для цього використовується рекурсивна функція getLeafSequence, яка обходить дерево в глибину та додає
// значення листків до списку.
//
//В методі leafSimilar створюються два порожні списки leafSequence1 і leafSequence2, представляючи послідовності

//Порівнюється вміст двох списків. Якщо вони рівні, метод повертає true, інакше - false.
//Важливо зазначити, що послідовність отримується зліва направо, що відповідає умові задачі. Функція
// getLeafSequence додає значення лише для листків, і вони додаються в тому порядку, в якому вони зустрічаються
// під час обходу дерева.
//
//У прикладі використання методу дерева leafSimilar створюються два дерева, root1 і root2, і перевіряється,
// чи вони leaf-similar. У першому прикладі метод повертає true, оскільки послідовності значень листків
// однакові. У другому прикладі метод повертає false, оскільки послідовності відрізняються.


//Consider all the leaves of a binary tree, from left to right order, the values of those leaves form a leaf value sequence.

//For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).
//
//Two binary trees are considered leaf-similar if their leaf value sequence is the same.
//
//Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.

//Example 1:
//Input: root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
//Output: true

//Example 2:
//Input: root1 = [1,2,3], root2 = [1,3,2]
//Output: false

//Constraints:
//The number of nodes in each tree will be in the range [1, 200].
//Both of the given trees will have values in the range [0, 200].