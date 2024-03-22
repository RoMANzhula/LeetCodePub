package P2385_Amount_of_Time_for_Binary_Tree_to_Be_Infected;

import java.util.*;

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
        // Example usage:
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(5);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(6);
        root.left.right = new TreeNode(4);
        root.left.right.right = new TreeNode(2);
        root.left.right.left = new TreeNode(9);

        int start = 3;

        Main treeInfection = new Main();
        int result = treeInfection.amountOfTime(root, start);

        System.out.println("Output: " + result);
    }

    public int amountOfTime(TreeNode root, int start) {
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        populateParentMap(root, null, parentMap);

        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> infected = new HashSet<>();

        queue.offer(getNodeWithValue(root, start));
        infected.add(getNodeWithValue(root, start));

        int minutes = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();

                if (current.left != null && infected.add(current.left)) {
                    queue.offer(current.left);
                }

                if (current.right != null && infected.add(current.right)) {
                    queue.offer(current.right);
                }

                TreeNode parent = parentMap.get(current);
                if (parent != null && infected.add(parent)) {
                    queue.offer(parent);
                }
            }

            minutes++;
        }

        return minutes - 1;
    }

    private void populateParentMap(TreeNode node, TreeNode parent, Map<TreeNode, TreeNode> parentMap) {
        if (node != null) {
            parentMap.put(node, parent);
            populateParentMap(node.left, node, parentMap);
            populateParentMap(node.right, node, parentMap);
        }
    }

    private TreeNode getNodeWithValue(TreeNode root, int value) {
        if (root == null) {
            return null;
        }

        if (root.val == value) {
            return root;
        }

        TreeNode leftResult = getNodeWithValue(root.left, value);
        if (leftResult != null) {
            return leftResult;
        }

        return getNodeWithValue(root.right, value);
    }
}

//Задача полягає у визначенні часу, необхідного для того, щоб інфекція почала поширюватися по бінарному
// дереву від заданого вузла.
//
//Розв'язок використовує пошук в ширину (BFS) та обхід у глибину (DFS) для визначення батьківських вузлів. Основні етапи:
//
//Заповнення словника parentMap, який зберігає інформацію про батьківські вузли кожного вузла у дереві.
//Використання черги та множини для відстеження інфікованих вузлів.
//Додавання стартового вузла до черги та множини інфікованих.
//Проходження по черзі та додавання сусідніх вузлів до черги та множини, поки всі вузли не стануть інфікованими.
//Зазначте, що результат виводиться в хвилинах, але в останньому кроці вираховується час на 1 менше (minutes - 1),
// оскільки останній крок не є фактичною інфекцією.
//
//Цей код має на меті вирішити задачу, що полягає в поширенні інфекції у бінарному дереві, визначаючи кількість
// хвилин, необхідних для того, щоб інфекція охопила всі вузли дерева.


//You are given the root of a binary tree with unique values, and an integer start. At minute 0, an
// infection starts from the node with value start.
//
//Each minute, a node becomes infected if:
//
//The node is currently uninfected.
//The node is adjacent to an infected node.
//Return the number of minutes needed for the entire tree to be infected.

//Example 1:
//Input: root = [1,5,3,null,4,10,6,9,2], start = 3
//Output: 4
//Explanation: The following nodes are infected during:
//- Minute 0: Node 3
//- Minute 1: Nodes 1, 10 and 6
//- Minute 2: Node 5
//- Minute 3: Node 4
//- Minute 4: Nodes 9 and 2
//It takes 4 minutes for the whole tree to be infected so we return 4.

//Example 2:
//Input: root = [1], start = 1
//Output: 0
//Explanation: At minute 0, the only node in the tree is infected so we return 0.

//Constraints:
//The number of nodes in the tree is in the range [1, 105].
//1 <= Node.val <= 105
//Each node has a unique value.
//A node with a value of start exists in the tree.

