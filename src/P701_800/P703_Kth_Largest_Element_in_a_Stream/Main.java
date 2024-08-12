package P701_800.P703_Kth_Largest_Element_in_a_Stream;

import java.util.PriorityQueue;

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */

public class Main {
    public static void main(String[] args) {
        // Example case
        Main kthLargest = new Main(3, new int[]{4, 5, 8, 2});

        // Test the add method and print the results
        System.out.println(kthLargest.add(3));   // Output: 4
        System.out.println(kthLargest.add(5));   // Output: 5
        System.out.println(kthLargest.add(10));  // Output: 5
        System.out.println(kthLargest.add(9));   // Output: 8
        System.out.println(kthLargest.add(4));   // Output: 8
    }

    public int add(int val) {
        if (minHeap.size() < k) {
            minHeap.offer(val);
        } else if (val > minHeap.peek()) {
            minHeap.poll();
            minHeap.offer(val);
        }
        return minHeap.peek();
    }

    private PriorityQueue<Integer> minHeap;
    private int k;

    public Main(int k, int[] nums) {
        this.k = k;
        minHeap = new PriorityQueue<>(k);
        for (int num : nums) {
            add(num);
        }
    }
}

//Explanation:
//main Method:
//The main method is used to run the program.
//Inside the main method, an instance of KthLargest is created with k = 3 and an initial array of [4, 5, 8, 2].
//The add method is called multiple times, and the results are printed to the console.


//Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the
// sorted order, not the kth distinct element.
//Implement KthLargest class:
//KthLargest(int k, int[] nums) Initializes the object with the integer k and the stream of integers nums.
//int add(int val) Appends the integer val to the stream and returns the element representing the kth largest
// element in the stream.
//
//Example 1:
//Input
//["KthLargest", "add", "add", "add", "add", "add"]
//[[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
//Output
//[null, 4, 5, 5, 8, 8]
//
//Explanation
//KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
//kthLargest.add(3);   // return 4
//kthLargest.add(5);   // return 5
//kthLargest.add(10);  // return 5
//kthLargest.add(9);   // return 8
//kthLargest.add(4);   // return 8
//
//Constraints:
//1 <= k <= 104
//0 <= nums.length <= 104
//-104 <= nums[i] <= 104
//-104 <= val <= 104
//At most 104 calls will be made to add.
//It is guaranteed that there will be at least k elements in the array when you search for the kth element.