package P101_200.P138_Copy_List_with_Random_Pointer;

import java.util.HashMap;
import java.util.Map;

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

class Main {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        //create a mapping from original nodes to their copies
        Map<Node, Node> nodeMap = new HashMap<>();

        //first pass: create a copy of each node and map the original node to its copy
        Node current = head;
        while (current != null) {
            nodeMap.put(current, new Node(current.val));
            current = current.next;
        }

        //second pass: assign next and random pointers for each copy
        current = head;
        while (current != null) {
            Node copyNode = nodeMap.get(current);
            copyNode.next = nodeMap.get(current.next);
            copyNode.random = nodeMap.get(current.random);
            current = current.next;
        }

        //return the head of the copied linked list
        return nodeMap.get(head);
    }
}

//We have a Node class that represents a node in a doubly linked list. Each node contains a val (value), a
// next pointer to the next node, and a random pointer, which can point to any node in the list or be null.
//In the DeepCopyLinkedList class, we have a method copyRandomList, which takes the head node of the list and
// returns a deep copy of the list, including the random pointers.
//First, we check if head (the starting node) is null. If so, the list is empty, and we simply return null.
//We create a Map named nodeMap, which will be used to map original nodes to their copies. This helps us track
// already created copies of nodes and prevents cycles in the resulting list.
//First pass (while (current != null)) is used to create copies of all nodes and add them to nodeMap. We create a
// new node with the same val as each original node and add it to nodeMap, using the original node as the key.
//Second pass (while (current != null)) is used to set the next and random pointers for each copied node. We retrieve
// the copy of the current node (Node copyNode = nodeMap.get(current);) and set copyNode.next and copyNode.random to
// the corresponding copied nodes.
// Finally, we return the deep copy of the head node, which is stored in nodeMap.get(head).
// As a result, we obtain a new list where each node is a deep copy of the corresponding node in the original list,
// and the next and random pointers correctly reference the new copied nodes instead of pointing to nodes in
// the original list.


//A linked list of length n is given such that each node contains an additional random pointer, which could point
// to any node in the list, or null.
//Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node
// has its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes
// should point to new nodes in the copied list such that the pointers in the original list and copied list represent
// the same list state. None of the pointers in the new list should point to nodes in the original list.
//For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding
// two nodes x and y in the copied list, x.random --> y.
//Return the head of the copied linked list.
//The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair
// of [val, random_index] where:
//val: an integer representing Node.val
//random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does
// not point to any node.
//Your code will only be given the head of the original linked list.

//Example 1:
//Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
//Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]

//Example 2:
//Input: head = [[1,1],[2,1]]
//Output: [[1,1],[2,1]]

//Example 3:
//Input: head = [[3,null],[3,0],[3,null]]
//Output: [[3,null],[3,0],[3,null]]

//Constraints:
//0 <= n <= 1000
//-104 <= Node.val <= 104
//Node.random is null or is pointing to some node in the linked list.


