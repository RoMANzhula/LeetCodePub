package P501_600.P590_N_ary_Tree_Postorder_Traversal;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        // Example 1
        Node root1 = new Node(1, new ArrayList<>());
        Node node3 = new Node(3, new ArrayList<>());
        Node node2 = new Node(2, new ArrayList<>());
        Node node4 = new Node(4, new ArrayList<>());

        node3.children.add(new Node(5, new ArrayList<>()));
        node3.children.add(new Node(6, new ArrayList<>()));
        root1.children.add(node3);
        root1.children.add(node2);
        root1.children.add(node4);

        List<Integer> result1 = solution.postorder(root1);
        System.out.println(result1); // Output: [5, 6, 3, 2, 4, 1]
    }

    public List<Integer> postorder(Node root) {
        List<Integer> result = new ArrayList<>();
        traverse(root, result);
        return result;
    }

    private void traverse(Node node, List<Integer> result) {
        if (node == null) return;

        // Traverse each child first
//        if (node.children != null) {
            for (Node child : node.children) {
                traverse(child, result);
            }
//        }

        // Add the current node's value after visiting children
        result.add(node.val);
    }
}

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}


//Explanation:
//Node Class: The Node class represents each node of the n-ary tree, which contains a value (val) and a list of
// its children (children).
//Postorder Traversal: The postorder method starts the traversal from the root node. It uses a helper method
// traverse to recursively visit all children of each node before visiting the node itself.
//Helper Method traverse: This method is the heart of the postorder traversal:
//It first recursively traverses all the children of the current node.
//After visiting all the children, it adds the current node's value to the result list.


//Given the root of an n-ary tree, return the postorder traversal of its nodes' values.
//Nary-Tree input serialization is represented in their level order traversal. Each group of children is
// separated by the null value (See examples)
//
//Example 1:
//Input: root = [1,null,3,2,4,null,5,6]
//Output: [5,6,3,2,4,1]

//Example 2:
//Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
//Output: [2,6,14,11,7,3,12,8,4,13,9,10,5,1]
//
//Constraints:
//The number of nodes in the tree is in the range [0, 104].
//0 <= Node.val <= 104
//The height of the n-ary tree is less than or equal to 1000.
