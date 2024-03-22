package P201_300.P232_Implement_Queue_using_Stacks;

public class Main {
    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1);
        myQueue.push(2);
        System.out.println(myQueue.peek());  // Output: 1
        System.out.println(myQueue.pop());   // Output: 1
        System.out.println(myQueue.empty()); // Output: false
    }
}

//Мета задачі полягає в реалізації черги (queue) за допомогою двох стеків (stack) у мові програмування
// Java. Ви маєте реалізувати клас MyQueue, який підтримує операції push (додавання елементу),
// pop (видалення та повернення елемента з початку черги), peek (отримання елемента з початку черги без
// його видалення) та empty (перевірка, чи черга порожня).
//
//Основною ідеєю є використання двох стеків (stack1 та stack2). stack1 використовується для операції push,
// а stack2 — для операцій pop та peek. Коли потрібно викликати pop або peek, елементи переносяться з stack1 до stack2.

//Implement a first in first out (FIFO) queue using only two stacks. The implemented queue should support all the
// functions of a normal queue (push, peek, pop, and empty).
//Implement the MyQueue class:
//void push(int x) Pushes element x to the back of the queue.
//int pop() Removes the element from the front of the queue and returns it.
//int peek() Returns the element at the front of the queue.
//boolean empty() Returns true if the queue is empty, false otherwise.
//Notes:
//
//You must use only standard operations of a stack, which means only push to top, peek/pop from top, size, and is
// empty operations are valid.
//Depending on your language, the stack may not be supported natively. You may simulate a stack using a
// list or deque (double-ended queue) as long as you use only a stack's standard operations.

//Example 1:
//Input
//["MyQueue", "push", "push", "peek", "pop", "empty"]
//[[], [1], [2], [], [], []]
//Output
//[null, null, null, 1, 1, false]
//
//Explanation
//MyQueue myQueue = new MyQueue();
//myQueue.push(1); // queue is: [1]
//myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
//myQueue.peek(); // return 1
//myQueue.pop(); // return 1, queue is [2]
//myQueue.empty(); // return false

//Constraints:
//
//1 <= x <= 9
//At most 100 calls will be made to push, pop, peek, and empty.
//All the calls to pop and peek are valid.