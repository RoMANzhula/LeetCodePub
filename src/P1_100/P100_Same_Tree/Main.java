package P1_100.P100_Same_Tree;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        // Test cases
        TreeNode p1 = new TreeNode(1);
        p1.left = new TreeNode(2);
        p1.right = new TreeNode(3);

        TreeNode q1 = new TreeNode(1);
        q1.left = new TreeNode(2);
        q1.right = new TreeNode(3);

        System.out.println(solution.isSameTree(p1, q1)); // Output: true

        TreeNode p2 = new TreeNode(1);
        p2.left = new TreeNode(2);

        TreeNode q2 = new TreeNode(1);
        q2.right = new TreeNode(2);

        System.out.println(solution.isSameTree(p2, q2)); // Output: false

        TreeNode p3 = new TreeNode(1);
        p3.left = new TreeNode(2);
        p3.right = new TreeNode(1);

        TreeNode q3 = new TreeNode(1);
        q3.left = new TreeNode(1);
        q3.right = new TreeNode(2);

        System.out.println(solution.isSameTree(p3, q3)); // Output: false
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        if (p.val != q.val)
            return false;

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
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

//Для розв'язання цієї задачі нам потрібно порівняти два бінарних дерева, щоб переконатися, чи вони однакові
// за структурою та значеннями вузлів.
//Щоб це зробити, нам потрібно пройти обидва дерева у рекурсивній манері, порівнюючи значення вузлів на
// кожному кроці. Якщо обидва дерева однакові за структурою та значеннями вузлів на кожному рівні, ми
// можемо стверджувати, що вони однакові.
//план рішення:
//Перевірка, чи обидва корені дерев (p та q) є null. Якщо так, повертаємо true, тому що два пусті
// дерева рахуються однаковими.
//Перевірка, чи один з коренів (p або q) є null, а інший - ні. Якщо так, повертаємо false, оскільки ці дерева
// не можуть бути однаковими.
//Порівнюємо значення коренів p та q. Якщо вони не однакові - false.
//Рекурсивно порівнюємо ліві піддерева p та q, а також їхні праві піддерева.
//Якщо всі вищезазначені перевірки пройдено успішно для кожного рівня дерев, то true; інакше - false.

//Given the roots of two binary trees p and q, write a function to check if they are the same or not.
//Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.

//Example 1:
//Input: p = [1,2,3], q = [1,2,3]
//Output: true

//Example 2:
//Input: p = [1,2], q = [1,null,2]
//Output: false

//Example 3:
//Input: p = [1,2,1], q = [1,1,2]
//Output: false
//
//Constraints:
//The number of nodes in both trees is in the range [0, 100].
//-104 <= Node.val <= 104
