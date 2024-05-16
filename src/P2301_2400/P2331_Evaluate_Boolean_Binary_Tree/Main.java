package P2301_2400.P2331_Evaluate_Boolean_Binary_Tree;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        // Example 1:
        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(3);
        root1.right.left = new TreeNode(0);
        root1.right.right = new TreeNode(1);
        System.out.println(solution.evaluateTree(root1));  // Output: true

        // Example 2:
        TreeNode root2 = new TreeNode(0);
        System.out.println(solution.evaluateTree(root2));  // Output: false
    }

    public boolean evaluateTree(TreeNode root) {
        // Base case: if the node is a leaf node, return its boolean value.
        if (root.left == null && root.right == null) {
            return root.val == 1;
        }

        // Recursively evaluate the left and right subtrees.
        boolean leftValue = evaluateTree(root.left);
        boolean rightValue = evaluateTree(root.right);

        // Apply the boolean operation based on the value of the non-leaf node.
        if (root.val == 2) {
            return leftValue || rightValue;  // OR operation
        } else if (root.val == 3) {
            return leftValue && rightValue;  // AND operation
        }

        // This line should not be reached because of the constraints.
        return false;
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

//Клас TreeNode: Цей клас визначає вузол бінарного дерева зі значенням (val), лівою дитиною (left) і
// правою дитиною (right).
//Метод evaluateTree:
//Базовий випадок: Якщо вузол є листком (обидві дитини null), повертаємо true, якщо значення вузла 1, і
// false, якщо значення 0.
//Рекурсивний випадок: Для нелисткових вузлів:
//Рекурсивно оцінюємо лівих та правих дітей.
//Якщо значення вузла 2, виконуємо операцію АБО (OR) над результатами лівого та правого піддерева.
//Якщо значення вузла 3, виконуємо операцію І (AND) над результатами лівого та правого піддерева.
//Ураховуючи обмеження, ми припускаємо, що структура дерева та значення вузлів є правильними, тому return
// false в кінці є запасним варіантом.
//Метод main: Цей метод демонструє, як створити дерево та оцінити його за допомогою методу evaluateTree на прикладах.
//Цей підхід гарантує, що дерево оцінюється правильно відповідно до заданих правил, використовуючи рекурсію
// для обробки кожного піддерева.

//You are given the root of a full binary tree with the following properties:
//Leaf nodes have either the value 0 or 1, where 0 represents False and 1 represents True.
//Non-leaf nodes have either the value 2 or 3, where 2 represents the boolean OR and 3 represents the boolean AND.
//The evaluation of a node is as follows:
//If the node is a leaf node, the evaluation is the value of the node, i.e. True or False.
//Otherwise, evaluate the node's two children and apply the boolean operation of its value with the
// children's evaluations.
//Return the boolean result of evaluating the root node.
//A full binary tree is a binary tree where each node has either 0 or 2 children.
//A leaf node is a node that has zero children.
//
//Example 1:
//Input: root = [2,1,3,null,null,0,1]
//Output: true
//Explanation: The above diagram illustrates the evaluation process.
//The AND node evaluates to False AND True = False.
//The OR node evaluates to True OR False = True.
//The root node evaluates to True, so we return true.

//Example 2:
//Input: root = [0]
//Output: false
//Explanation: The root node is a leaf node and it evaluates to false, so we return false.
//
//Constraints:
//The number of nodes in the tree is in the range [1, 1000].
//0 <= Node.val <= 3
//Every node has either 0 or 2 children.
//Leaf nodes have a value of 0 or 1.
//Non-leaf nodes have a value of 2 or 3.
