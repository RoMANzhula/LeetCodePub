package P201_300.P225_Implement_Stack_using_Queues;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    private Queue<Integer> queue1; //основна черга для зберігання елементів стеку
    private Queue<Integer> queue2; //допоміжна черга для допоміжних операцій

    public Main() {
        queue1 = new LinkedList<>(); //ініціалізація основної черги
        queue2 = new LinkedList<>(); //ініціалізація допоміжної черги
    }

    public void push(int x) {
        queue1.offer(x); //додаємо елемент в кінець основної черги
    }

    public int pop() {
        while (queue1.size() > 1) {
            queue2.offer(queue1.poll()); //переміщуємо елементи, окрім останнього, у допоміжну чергу
        }
        int popped = queue1.poll(); //видаляємо та повертаємо останній елемент з основної черги
        swapQueues(); //міняємо імена черг для збереження порядку
        return popped;
    }

    public int top() {
        while (queue1.size() > 1) {
            queue2.offer(queue1.poll()); //переміщуємо елементи, окрім останнього, у допоміжну чергу
        }
        int top = queue1.peek(); //отримуємо значення верхнього елемента з основної черги
        queue2.offer(queue1.poll()); //переміщуємо останній елемент у допоміжну чергу
        swapQueues(); //міняємо імена черг для збереження порядку
        return top;
    }

    public boolean empty() {
        return queue1.isEmpty() && queue2.isEmpty(); //перевіряємо чи обидві черги пусті
    }

    private void swapQueues() {
        Queue<Integer> temp = queue1; //тимчасова змінна для обміну чергами
        queue1 = queue2; //переприсвоюємо чергу queue2 в queue1
        queue2 = temp; //переприсвоюємо чергу temp (що була queue1) в queue2
    }
}

//Implement a last-in-first-out (LIFO) stack using only two queues. The implemented stack should support all
// the functions of a normal stack (push, top, pop, and empty).
//Implement the MyStack class:
//void push(int x) Pushes element x to the top of the stack.
//int pop() Removes the element on the top of the stack and returns it.
//int top() Returns the element on the top of the stack.
//boolean empty() Returns true if the stack is empty, false otherwise.
//Notes:
//You must use only standard operations of a queue, which means that only push to back, peek/pop from front, size
// and is empty operations are valid.
//Depending on your language, the queue may not be supported natively. You may simulate a queue using a list or
// deque (double-ended queue) as long as you use only a queue's standard operations.

//Example 1:
//Input
//["MyStack", "push", "push", "top", "pop", "empty"]
//[[], [1], [2], [], [], []]
//Output
//[null, null, null, 2, 2, false]
//Explanation
//MyStack myStack = new MyStack();
//myStack.push(1);
//myStack.push(2);
//myStack.top(); // return 2
//myStack.pop(); // return 2
//myStack.empty(); // return False

//Constraints:
//1 <= x <= 9
//At most 100 calls will be made to push, pop, top, and empty.
//All the calls to pop and top are valid.
