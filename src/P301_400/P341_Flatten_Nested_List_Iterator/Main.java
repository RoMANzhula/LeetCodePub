package P301_400.P341_Flatten_Nested_List_Iterator;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<NestedInteger> nestedList1 = Arrays.asList(
                new NestedIntegerImpl(Arrays.asList(
                        new NestedIntegerImpl(1),
                        new NestedIntegerImpl(1)
                )),
                new NestedIntegerImpl(2),
                new NestedIntegerImpl(Arrays.asList(
                        new NestedIntegerImpl(1),
                        new NestedIntegerImpl(1)
                ))
        );

        List<NestedInteger> nestedList2 = Arrays.asList(
                new NestedIntegerImpl(1),
                new NestedIntegerImpl(Arrays.asList(
                        new NestedIntegerImpl(4),
                        new NestedIntegerImpl(Arrays.asList(
                                new NestedIntegerImpl(6)
                        ))
                ))
        );

        System.out.println("Flattened list 1:");
        NestedIterator iterator1 = new NestedIterator(nestedList1);
        while (iterator1.hasNext()) {
            System.out.print(iterator1.next() + " ");
        }
        System.out.println();

        System.out.println("Flattened list 2:");
        NestedIterator iterator2 = new NestedIterator(nestedList2);
        while (iterator2.hasNext()) {
            System.out.print(iterator2.next() + " ");
        }
        System.out.println();
    }
}

class NestedIntegerImpl implements NestedInteger {
    private Integer value;
    private List<NestedInteger> list;

    public NestedIntegerImpl(int value) {
        this.value = value;
        this.list = null;
    }

    public NestedIntegerImpl(List<NestedInteger> list) {
        this.value = null;
        this.list = list;
    }

    @Override
    public boolean isInteger() {
        return value != null;
    }

    @Override
    public Integer getInteger() {
        return value;
    }

    @Override
    public List<NestedInteger> getList() {
        return list;
    }
}

class NestedIterator implements Iterator<Integer> {
    private Stack<NestedInteger> stack;

    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        flattenList(nestedList);
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            return null;
        }
        return stack.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty() && !stack.peek().isInteger()) {
            //unwrap the nested list and flatten it
            flattenList(stack.pop().getList());
        }
        return !stack.isEmpty();
    }

    private void flattenList(List<NestedInteger> nestedList) {
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            stack.push(nestedList.get(i));
        }
    }
}

interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}


//You are given a nested list of integers nestedList. Each element is either an integer or a list whose elements may
// also be integers or other lists. Implement an iterator to flatten it.
//Implement the NestedIterator class:
//NestedIterator(List<NestedInteger> nestedList) Initializes the iterator with the nested list nestedList.
//int next() Returns the next integer in the nested list.
//boolean hasNext() Returns true if there are still some integers in the nested list and false otherwise.
//Your code will be tested with the following pseudocode:
//initialize iterator with nestedList
//res = []
//while iterator.hasNext()
//    append iterator.next() to the end of res
//return res
//If res matches the expected flattened list, then your code will be judged as correct.

//Example 1:
//Input: nestedList = [[1,1],2,[1,1]]
//Output: [1,1,2,1,1]
//Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next
// should be: [1,1,2,1,1].

//Example 2:
//Input: nestedList = [1,[4,[6]]]
//Output: [1,4,6]
//Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next
// should be: [1,4,6].

//Constraints:
//1 <= nestedList.length <= 500
//The values of the integers in the nested list is in the range [-106, 106].
