package P1401_1500.P1441_Build_an_Array_With_Stack_Operations;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();
        int[] target1 = {1, 3};
        int n1 = 3;
        System.out.println(solution.buildArray(target1, n1)); // Output: ["Push","Push","Pop","Push"]

        int[] target2 = {1, 2, 3};
        int n2 = 3;
        System.out.println(solution.buildArray(target2, n2)); // Output: ["Push","Push","Push"]

        int[] target3 = {1, 2};
        int n3 = 4;
        System.out.println(solution.buildArray(target3, n3)); // Output: ["Push","Push"]

    }

    public List<String> buildArray(int[] target, int n) {
        List<String> operations = new ArrayList<>();
        int targetIndex = 0;

        for (int i = 1; i <= n; i++) {
            if (targetIndex >= target.length) {
                //we have already constructed the target array.
                break;
            }

            if (i == target[targetIndex]) {
                //if the current integer matches the target, push and increment.
                operations.add("Push");
                targetIndex++;
            } else {
                //if the current integer doesn't match, push and pop.
                operations.add("Push");
                operations.add("Pop");
            }
        }

        return operations;
    }

}

//You are given an integer array target and an integer n.
//You have an empty stack with the two following operations:
//"Push": pushes an integer to the top of the stack.
//"Pop": removes the integer on the top of the stack.
//You also have a stream of the integers in the range [1, n].
//Use the two stack operations to make the numbers in the stack (from the bottom to the top) equal to target. You
// should follow the following rules:
//If the stream of the integers is not empty, pick the next integer from the stream and push it to the top of the stack.
//If the stack is not empty, pop the integer at the top of the stack.
//If, at any moment, the elements in the stack (from the bottom to the top) are equal to target, do not read
// new integers from the stream and do not do more operations on the stack.
//Return the stack operations needed to build target following the mentioned rules. If there are multiple valid
// answers, return any of them.

//Example 1:
//Input: target = [1,3], n = 3
//Output: ["Push","Push","Pop","Push"]
//Explanation: Initially the stack s is empty. The last element is the top of the stack.
//Read 1 from the stream and push it to the stack. s = [1].
//Read 2 from the stream and push it to the stack. s = [1,2].
//Pop the integer on the top of the stack. s = [1].
//Read 3 from the stream and push it to the stack. s = [1,3].

//Example 2:
//Input: target = [1,2,3], n = 3
//Output: ["Push","Push","Push"]
//Explanation: Initially the stack s is empty. The last element is the top of the stack.
//Read 1 from the stream and push it to the stack. s = [1].
//Read 2 from the stream and push it to the stack. s = [1,2].
//Read 3 from the stream and push it to the stack. s = [1,2,3].

//Example 3:
//Input: target = [1,2], n = 4
//Output: ["Push","Push"]
//Explanation: Initially the stack s is empty. The last element is the top of the stack.
//Read 1 from the stream and push it to the stack. s = [1].
//Read 2 from the stream and push it to the stack. s = [1,2].
//Since the stack (from the bottom to the top) is equal to target, we stop the stack operations.
//The answers that read integer 3 from the stream are not accepted.

//Constraints:
//1 <= target.length <= 100
//1 <= n <= 100
//1 <= target[i] <= n
//target is strictly increasing.
