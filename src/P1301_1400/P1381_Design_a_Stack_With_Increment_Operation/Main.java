package P1301_1400.P1381_Design_a_Stack_With_Increment_Operation;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main(3);

        solution.push(1);                          // stack becomes [1]
        solution.push(2);                          // stack becomes [1, 2]
        solution.pop();                            // return 2 --> Return top of the stack 2, stack becomes [1]
        solution.push(2);                          // stack becomes [1, 2]
        solution.push(3);                          // stack becomes [1, 2, 3]
        solution.push(4);                          // stack still [1, 2, 3], because maxSize is 3
        solution.increment(5, 100);                // stack becomes [101, 102, 103]
        solution.increment(2, 100);                // stack becomes [201, 202, 103]
        solution.pop();                            // return 103 --> Return top of the stack 103, stack becomes [201, 202]
        solution.pop();                            // return 202 --> Return top of the stack 202, stack becomes [201]
        solution.pop();                            // return 201 --> Return top of the stack 201, stack becomes []
        solution.pop();                            // return -1 --> Stack is empty, return -1.
    }

    private int maxSize;
    private Stack<Integer> stack;
    private int[] increment;  // Array to store increment values for each level of the stack

    public Main(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new Stack<>();
        this.increment = new int[maxSize];  // To track increments at each index
    }

    public void push(int x) {
        if (stack.size() < maxSize) {
            stack.push(x);
        }
    }

    public int pop() {
        int index = stack.size() - 1;
        if (index == -1) return -1;  // Stack is empty

        // Add the increment value to the element at the top of the stack
        if (index > 0) {
            increment[index - 1] += increment[index];
        }
        int result = stack.pop() + increment[index];
        increment[index] = 0;  // Reset the increment at this index after using it
        return result;
    }

    public void increment(int k, int val) {
        int limit = Math.min(k, stack.size()) - 1;  // Apply increment only to the first k elements
        if (limit >= 0) {
            increment[limit] += val;
        }
    }

}

//Explanation:
//push(int x): Adds an element to the stack if the current size of the stack is less than the maximum size.
//pop(): Removes the top element of the stack and adds any pending increment that applies to this element. The
// increment array is used to store the increment value for each position. When popping an element, we apply the
// increment and propagate the value down the stack.
//increment(int k, int val): Increments the bottom k elements of the stack by val. Instead of directly updating the
// stack, we update the increment array at the correct position. This defers the actual increment until
// the element is popped.
//Time Complexity:
//push(): O(1)
//pop(): O(1)
//increment(): O(1) (increment is deferred and applied during pop)


//Design a stack that supports increment operations on its elements.
//Implement the CustomStack class:
//CustomStack(int maxSize) Initializes the object with maxSize which is the maximum number of elements in the stack.
//void push(int x) Adds x to the top of the stack if the stack has not reached the maxSize.
//int pop() Pops and returns the top of the stack or -1 if the stack is empty.
//void inc(int k, int val) Increments the bottom k elements of the stack by val. If there are less than k elements
// in the stack, increment all the elements in the stack.
//
//Example 1:
//Input
//["CustomStack","push","push","pop","push","push","push","increment","increment","pop","pop","pop","pop"]
//[[3],[1],[2],[],[2],[3],[4],[5,100],[2,100],[],[],[],[]]
//Output
//[null,null,null,2,null,null,null,null,null,103,202,201,-1]
//Explanation
//CustomStack stk = new CustomStack(3); // Stack is Empty []
//stk.push(1);                          // stack becomes [1]
//stk.push(2);                          // stack becomes [1, 2]
//stk.pop();                            // return 2 --> Return top of the stack 2, stack becomes [1]
//stk.push(2);                          // stack becomes [1, 2]
//stk.push(3);                          // stack becomes [1, 2, 3]
//stk.push(4);                          // stack still [1, 2, 3], Do not add another elements as size is 4
//stk.increment(5, 100);                // stack becomes [101, 102, 103]
//stk.increment(2, 100);                // stack becomes [201, 202, 103]
//stk.pop();                            // return 103 --> Return top of the stack 103, stack becomes [201, 202]
//stk.pop();                            // return 202 --> Return top of the stack 202, stack becomes [201]
//stk.pop();                            // return 201 --> Return top of the stack 201, stack becomes []
//stk.pop();                            // return -1 --> Stack is empty return -1.
//
//Constraints:
//1 <= maxSize, x, k <= 1000
//0 <= val <= 100
//At most 1000 calls will be made to each method of increment, push and pop each separately.
