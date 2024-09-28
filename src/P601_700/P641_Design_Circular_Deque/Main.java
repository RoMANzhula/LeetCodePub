package P601_700.P641_Design_Circular_Deque;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main(3);

        System.out.println(solution.insertLast(1));  // returns true
        System.out.println(solution.insertLast(2));  // returns true
        System.out.println(solution.insertFront(3)); // returns true
        System.out.println(solution.insertFront(4)); // returns false (deque is full)

        System.out.println(solution.getRear());      // returns 2
        System.out.println(solution.isFull());       // returns true

        System.out.println(solution.deleteLast());   // returns true
        System.out.println(solution.insertFront(4)); // returns true

        System.out.println(solution.getFront());     // returns 4
    }

    private int[] deque;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public Main(int k) {
        // Initialize the deque with size k + 1 for the circular queue technique.
        deque = new int[k + 1];
        front = 0;
        rear = 0;
        capacity = k + 1;
        size = 0;
    }

    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        // Decrement front pointer circularly.
        front = (front - 1 + capacity) % capacity;
        deque[front] = value;
        size++;
        return true;
    }

    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        // Insert value at the rear and increment rear pointer circularly.
        deque[rear] = value;
        rear = (rear + 1) % capacity;
        size++;
        return true;
    }

    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        // Increment front pointer circularly.
        front = (front + 1) % capacity;
        size--;
        return true;
    }

    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        // Decrement rear pointer circularly.
        rear = (rear - 1 + capacity) % capacity;
        size--;
        return true;
    }

    public int getFront() {
        if (isEmpty()) {
            return -1;
        }
        return deque[front];
    }

    public int getRear() {
        if (isEmpty()) {
            return -1;
        }
        // The last element is at rear - 1 position.
        return deque[(rear - 1 + capacity) % capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        // The deque is full when size equals capacity - 1.
        return size == capacity - 1;
    }

}


//Design your implementation of the circular double-ended queue (deque).
//Implement the MyCircularDeque class:
//MyCircularDeque(int k) Initializes the deque with a maximum size of k.
//boolean insertFront() Adds an item at the front of Deque. Returns true if the operation is successful, or false otherwise.
//boolean insertLast() Adds an item at the rear of Deque. Returns true if the operation is successful, or false otherwise.
//boolean deleteFront() Deletes an item from the front of Deque. Returns true if the operation is successful, or false otherwise.
//boolean deleteLast() Deletes an item from the rear of Deque. Returns true if the operation is successful, or false otherwise.
//int getFront() Returns the front item from the Deque. Returns -1 if the deque is empty.
//int getRear() Returns the last item from Deque. Returns -1 if the deque is empty.
//boolean isEmpty() Returns true if the deque is empty, or false otherwise.
//boolean isFull() Returns true if the deque is full, or false otherwise.
//
//Example 1:
//Input
//["MyCircularDeque", "insertLast", "insertLast", "insertFront", "insertFront", "getRear", "isFull",
// "deleteLast", "insertFront", "getFront"]
//[[3], [1], [2], [3], [4], [], [], [], [4], []]
//Output
//[null, true, true, true, false, 2, true, true, true, 4]
//Explanation
//MyCircularDeque myCircularDeque = new MyCircularDeque(3);
//myCircularDeque.insertLast(1);  // return True
//myCircularDeque.insertLast(2);  // return True
//myCircularDeque.insertFront(3); // return True
//myCircularDeque.insertFront(4); // return False, the queue is full.
//myCircularDeque.getRear();      // return 2
//myCircularDeque.isFull();       // return True
//myCircularDeque.deleteLast();   // return True
//myCircularDeque.insertFront(4); // return True
//myCircularDeque.getFront();     // return 4
//
//Constraints:
//1 <= k <= 1000
//0 <= value <= 1000
//At most 2000 calls will be made to insertFront, insertLast, deleteFront,
// deleteLast, getFront, getRear, isEmpty, isFull.


//Explanation:
//Circular Queue Concept: The deque is represented as an array with an additional element (capacity = k + 1). The
// extra space is used to differentiate between a full and an empty deque. The indices for the front and rear are
// updated circularly using modulo arithmetic to wrap around the array.

//Key Operations:
//Insert Front/Last: These operations modify the front and rear pointers respectively, while ensuring
// the indices wrap around the array.
//Delete Front/Last: These operations adjust the front and rear pointers accordingly to "remove" elements from the deque.
//Get Front/Rear: These return the frontmost or rearmost element, handling edge cases when the deque is empty.
//isEmpty/isFull: These check if the deque is empty or full by comparing the size with capacity constraints.