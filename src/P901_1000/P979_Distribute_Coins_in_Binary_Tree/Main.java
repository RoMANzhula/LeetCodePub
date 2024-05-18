package P901_1000.P979_Distribute_Coins_in_Binary_Tree;

public class Main {
    public static void main(String[] args) {
        Main solution1 = new Main();
        Main solution2 = new Main();

        // Example 1
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(0);
        root1.right = new TreeNode(0);
        System.out.println("Example 1: " + solution1.distributeCoins(root1));  // Output: 2

        // Example 2
        TreeNode root2 = new TreeNode(0);
        root2.left = new TreeNode(3);
        root2.right = new TreeNode(0);
        System.out.println("Example 2: " + solution2.distributeCoins(root2));  // Output: 3
    }

    private int moves = 0;

    public int distributeCoins(TreeNode root) {
        dfs(root);

        return moves;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftBalance = dfs(node.left);
        int rightBalance = dfs(node.right);

        // current node's balance
        int nodeBalance = node.val + leftBalance + rightBalance - 1;

        // number of moves(steps) is the sum of the absolute values of the bakance
        moves += Math.abs(leftBalance) + Math.abs(rightBalance);

        return nodeBalance; // bingo
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

//Пояснення:
//leftBalance: це залишок монет у лівому піддереві.
//rightBalance: це залишок монет у правому піддереві.
//Math.abs(value): це метод, який повертає абсолютне значення (невід'ємне значення) аргументу value.
//Що відбувається в цьому рядку:
//Math.abs(leftBalance): обчислює абсолютне значення залишку монет у лівому піддереві.
//Math.abs(rightBalance): обчислює абсолютне значення залишку монет у правому піддереві.
//Math.abs(leftBalance) + Math.abs(rightBalance): додає ці два абсолютні значення.
//moves += ...: додає результат цього додавання до загальної кількості переміщень монет (moves).
//Навіщо це потрібно:
//Це робиться для того, щоб підрахувати загальну кількість переміщень монет, необхідних для того, щоб у всіх
// вузлах дерева залишилась рівно одна монета. Абсолютні значення використовуються, оскільки незалежно від того,
// чи є залишок позитивним чи негативним, нам потрібно стільки ж переміщень, щоб збалансувати монети.
//Приклад:
//Якщо leftBalance = 2 і rightBalance = -1, це означає, що в лівому піддереві є 2 зайві монети, а в правому
// піддереві не вистачає 1 монети. Тому:
//Math.abs(2) = 2
//Math.abs(-1) = 1
//Сума цих значень = 3
//Отже, потрібно 3 переміщення монет, щоб збалансувати ці піддерева, і це значення додається до moves.

//You are given the root of a binary tree with n nodes where each node in the tree has node.val coins. There are
// n coins in total throughout the whole tree.
//In one move, we may choose two adjacent nodes and move one coin from one node to another. A move may be from
// parent to child, or from child to parent.
//Return the minimum number of moves required to make every node have exactly one coin.
//
//Example 1:
//Input: root = [3,0,0]
//Output: 2
//Explanation: From the root of the tree, we move one coin to its left child, and one coin to its right child.

//Example 2:
//Input: root = [0,3,0]
//Output: 3
//Explanation: From the left child of the root, we move two coins to the root [taking two moves]. Then, we move
// one coin from the root of the tree to the right child.
//
//Constraints:
//The number of nodes in the tree is n.
//1 <= n <= 100
//0 <= Node.val <= n
//The sum of all Node.val is n.