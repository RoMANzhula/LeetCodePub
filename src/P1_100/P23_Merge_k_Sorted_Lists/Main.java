package P1_100.P23_Merge_k_Sorted_Lists;

import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> queueNodes = new PriorityQueue<>((a, b) -> a.val - b.val); //list with Nodes queue
        //in ascending order of their values

        for (ListNode list : lists) { //check all NodeList from input data
            if (list != null) {
                queueNodes.offer(list); //added to priority queue
            }
        }

        var resultListNode = new ListNode(); //we create a dummy Node for result
        var current = resultListNode; //pointer for loyal adding new Nodes

        while (!queueNodes.isEmpty()) { //if we have Nodes in our queue
            var minNode = queueNodes.poll(); // get minimal Node from priority queue
            current.next = minNode; //add Node to priority queue
            current = current.next; //move pointer to new added Node for next iterations

            if (minNode.next != null) { //while we have Nodes
                queueNodes.offer(minNode.next); //add next Node to priority queue
            }
        }

        return resultListNode.next; //give result
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}


//You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
//Merge all the linked-lists into one sorted linked-list and return it.

//Example 1:
//Input: lists = [[1,4,5],[1,3,4],[2,6]]
//Output: [1,1,2,3,4,4,5,6]
//Explanation: The linked-lists are:
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//merging them into one sorted list:
//1->1->2->3->4->4->5->6

//Example 2:
//Input: lists = []
//Output: []

//Example 3:
//Input: lists = [[]]
//Output: []

//Constraints:
//k == lists.length
//0 <= k <= 104
//0 <= lists[i].length <= 500
//-104 <= lists[i][j] <= 104
//lists[i] is sorted in ascending order.
//The sum of lists[i].length will not exceed 104.
