package P901_1000.P988_Smallest_String_Starting_From_Leaf;

public class Main {
    String result = "~"; // initialize result as maximum lexicographically string

    public static void main(String[] args) {
        Main solution = new Main();

        // Example 1
        TreeNode root1 = new TreeNode(0);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(2);
        root1.left.left = new TreeNode(3);
        root1.left.right = new TreeNode(4);
        root1.right.left = new TreeNode(3);
        root1.right.right = new TreeNode(4);

        System.out.println(solution.smallestFromLeaf(root1)); // Output: "dba"

        // Example 2
        TreeNode root2 = new TreeNode(25);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(1);
        root2.left.right = new TreeNode(3);
        root2.right.left = new TreeNode(0);
        root2.right.right = new TreeNode(2);

        System.out.println(solution.smallestFromLeaf(root2)); // Output: "adz"

        // Example 3
        TreeNode root3 = new TreeNode(2);
        root3.left = new TreeNode(2);
        root3.left.left = new TreeNode(1);
        root3.left.left.left = new TreeNode(1);
        root3.left.left.left.left = new TreeNode(0);
        root3.left.left.left.left.left = new TreeNode(0);

        System.out.println(solution.smallestFromLeaf(root3)); // Output: "aabbcc"
    }

    public String smallestFromLeaf(TreeNode root) {
        dfs(root, new StringBuilder());
        return result;
    }

    private void dfs(TreeNode node, StringBuilder sb) {
        sb.append((char)('a' + node.val)); // append current node's character to the string

        if (node.left == null && node.right == null) { // if it's a leaf node
            sb.reverse(); // reverse the string to get the path from leaf to root
            String str = sb.toString();
            if (str.compareTo(result) < 0) { // compare with current result
                result = str;
            }
            sb.reverse(); // revert the string back to original
        } else {
            if (node.left != null) {
                dfs(node.left, sb);
                sb.deleteCharAt(sb.length() - 1); // backtrack
            }
            if (node.right != null) {
                dfs(node.right, sb);
                sb.deleteCharAt(sb.length() - 1); // backtrack
            }
        }
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

//Ця задача вирішується за допомогою пошуку в глибину (DFS), що дозволяє нам пройтися по всіх шляхах від листя до
// кореня бінарного дерева. На кожному листковому вузлі будується рядок, який складається з символів, які ми
// зустріли вздовж шляху до цього листа. Потім порівнюємо ці рядки, щоб знайти лексикографічно найменший серед них.
// розв'язок:
//Ми розпочинаємо з кореня дерева і рекурсивно обходимо його вузли.
//На кожному вузлі ми додаємо символ, що відповідає значенню цього вузла, до рядка.
//Якщо ми досягли листка, ми перевертаємо рядок, щоб отримати шлях від листка до кореня, і порівнюємо його з
// поточним найменшим результатом. Якщо цей шлях менший, ніж попередній найменший результат, ми зберігаємо його
// як новий найменший результат.
//У випадку, якщо вузол не є листком, ми рекурсивно викликаємо функцію для його дочірніх вузлів.
//Після того, як ми завершимо обробку поточного вузла, ми видаляємо останній символ з рядка, щоб підготувати його
// для наступного шляху.
//Цей процес продовжується до тих пір, поки всі можливі шляхи не будуть оброблені. На завершення, повертається
// знайдений найменший лексикографічний шлях.

//You are given the root of a binary tree where each node has a value in the range [0, 25] representing the
// letters 'a' to 'z'.
//Return the lexicographically smallest string that starts at a leaf of this tree and ends at the root.
//As a reminder, any shorter prefix of a string is lexicographically smaller.
//For example, "ab" is lexicographically smaller than "aba".
//A leaf of a node is a node that has no children.
//
//Example 1:
//Input: root = [0,1,2,3,4,3,4]
//Output: "dba"

//Example 2:
//Input: root = [25,1,3,1,3,0,2]
//Output: "adz"

//Example 3:
//Input: root = [2,2,1,null,1,0,null,0]
//Output: "abc"
//
//Constraints:
//The number of nodes in the tree is in the range [1, 8500].
//0 <= Node.val <= 25