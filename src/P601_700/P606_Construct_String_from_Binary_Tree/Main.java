package P601_700.P606_Construct_String_from_Binary_Tree;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        // Example 1
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.left.left = new TreeNode(4);
        root1.right = new TreeNode(3);
        System.out.println(solution.tree2str(root1)); // Output: "1(2(4))(3)"

        // Example 2
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.right = new TreeNode(4);
        System.out.println(solution.tree2str(root2)); // Output: "1(2()(4))(3)"
    }

    public static String tree2str(TreeNode root) {
        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(root.val);

        if (root.left != null || root.right != null) {
            sb.append("(");
            sb.append(tree2str(root.left));
            sb.append(")");

            if (root.right != null) {
                sb.append("(");
                sb.append(tree2str(root.right));
                sb.append(")");
            }
        }

        return sb.toString();
    }
}

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


//Основна ідея рішення полягає в рекурсивному обходженні бінарного дерева у порядку попереднього обходу та
// конструюванні рядка у відповідному форматі. Ми використовуємо StringBuilder для уникнення зайвих
// операцій конкатенації рядків, що може бути неефективним.
//
//Якщо поточний вузол не існує (є null), ми повертаємо порожній рядок. Інакше ми додаємо значення поточного
// вузла до рядка. Якщо є лівий або правий дочірній вузол, ми включаємо його у рядок разом з обрамленням у
// дужки. Якщо є тільки правий дочірній вузол, то включаємо тільки його.

//Given the root of a binary tree, construct a string consisting of parenthesis and integers from a binary
// tree with the preorder traversal way, and return it.
//Omit all the empty parenthesis pairs that do not affect the one-to-one mapping relationship between the
// string and the original binary tree.

//Example 1:
//Input: root = [1,2,3,4]
//Output: "1(2(4))(3)"
//Explanation: Originally, it needs to be "1(2(4)())(3()())", but you need to omit all the unnecessary empty
// parenthesis pairs. And it will be "1(2(4))(3)"

//Example 2:
//Input: root = [1,2,3,null,4]
//Output: "1(2()(4))(3)"
//Explanation: Almost the same as the first example, except we cannot omit the first parenthesis pair to break
// the one-to-one mapping relationship between the input and the output.

//Constraints:
//The number of nodes in the tree is in the range [1, 104].
//-1000 <= Node.val <= 1000
