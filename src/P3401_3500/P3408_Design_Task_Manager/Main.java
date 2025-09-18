package P3401_3500.P3408_Design_Task_Manager;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<List<Integer>> init = Arrays.asList(
                Arrays.asList(1, 101, 10),
                Arrays.asList(2, 102, 20),
                Arrays.asList(3, 103, 15)
        );

        Main taskManager = new Main(init);
        taskManager.add(4, 104, 5);
        taskManager.edit(102, 8);
        System.out.println(taskManager.execTop()); // 3
        taskManager.rmv(101);
        taskManager.add(5, 105, 15);
        System.out.println(taskManager.execTop()); // 5
    }

    private static class Task {
        int userId;
        int taskId;
        int priority;
        Task(int userId, int taskId, int priority) {
            this.userId = userId;
            this.taskId = taskId;
            this.priority = priority;
        }
    }

    private Map<Integer, Task> taskMap; // taskId -> Task
    private PriorityQueue<Task> maxHeap;

    public Main(List<List<Integer>> tasks) {
        taskMap = new HashMap<>();
        maxHeap = new PriorityQueue<>((a, b) -> {
            int cmp = Integer.compare(b.priority, a.priority); // higher priority first
            if (cmp != 0) return cmp;

            return Integer.compare(b.taskId, a.taskId);        // tie-break by higher taskId
        });

        for (List<Integer> t : tasks) {
            int userId = t.get(0), taskId = t.get(1), priority = t.get(2);
            Task task = new Task(userId, taskId, priority);
            taskMap.put(taskId, task);
            maxHeap.offer(task);
        }
    }

    public void add(int userId, int taskId, int priority) {
        Task task = new Task(userId, taskId, priority);
        taskMap.put(taskId, task);
        maxHeap.offer(task);
    }

    public void edit(int taskId, int newPriority) {
        Task old = taskMap.get(taskId);

        if (old != null) {
            Task updated = new Task(old.userId, taskId, newPriority);
            taskMap.put(taskId, updated);
            maxHeap.offer(updated); // lazy update
        }
    }

    public void rmv(int taskId) {
        taskMap.remove(taskId); // lazy deletion: heap entries will be ignored later
    }

    public int execTop() {
        while (!maxHeap.isEmpty()) {
            Task top = maxHeap.poll();
            Task current = taskMap.get(top.taskId);
            // validate that this heap entry matches the current mapping exactly.
            // (check both priority and userId to avoid the "re-added with same priority" bug)
            if (current != null && current.priority == top.priority && current.userId == top.userId) {
                taskMap.remove(top.taskId);

                return top.userId;
            }
            // else stale entry -> skip
        }

        return -1;
    }

}

//Complexity:
// time - O(log n)
// space - O(n + m)


//There is a task management system that allows users to manage their tasks, each associated with a priority. The
// system should efficiently handle adding, modifying, executing, and removing tasks.
//Implement the TaskManager class:
//TaskManager(vector<vector<int>>& tasks) initializes the task manager with a list of user-task-priority triples.
// Each element in the input list is of the form [userId, taskId, priority], which adds a task to the
// specified user with the given priority.
//void add(int userId, int taskId, int priority) adds a task with the specified taskId and priority to the
// user with userId. It is guaranteed that taskId does not exist in the system.
//void edit(int taskId, int newPriority) updates the priority of the existing taskId to newPriority. It is
// guaranteed that taskId exists in the system.
//void rmv(int taskId) removes the task identified by taskId from the system. It is guaranteed that taskId exists
// in the system.
//int execTop() executes the task with the highest priority across all users. If there are multiple tasks with the
// same highest priority, execute the one with the highest taskId. After executing, the taskId is removed from
// the system. Return the userId associated with the executed task. If no tasks are available, return -1.
//Note that a user may be assigned multiple tasks.

//Example 1:
//Input:
//["TaskManager", "add", "edit", "execTop", "rmv", "add", "execTop"]
//[[[[1, 101, 10], [2, 102, 20], [3, 103, 15]]], [4, 104, 5], [102, 8], [], [101], [5, 105, 15], []]
//Output:
//[null, null, null, 3, null, null, 5]
//Explanation
//TaskManager taskManager = new TaskManager([[1, 101, 10], [2, 102, 20], [3, 103, 15]]); // Initializes with three
// tasks for Users 1, 2, and 3.
//taskManager.add(4, 104, 5); // Adds task 104 with priority 5 for User 4.
//taskManager.edit(102, 8); // Updates priority of task 102 to 8.
//taskManager.execTop(); // return 3. Executes task 103 for User 3.
//taskManager.rmv(101); // Removes task 101 from the system.
//taskManager.add(5, 105, 15); // Adds task 105 with priority 15 for User 5.
//taskManager.execTop(); // return 5. Executes task 105 for User 5.

//Constraints:
//1 <= tasks.length <= 105
//0 <= userId <= 105
//0 <= taskId <= 105
//0 <= priority <= 109
//0 <= newPriority <= 109
//At most 2 * 105 calls will be made in total to add, edit, rmv, and execTop methods.
//The input is generated such that taskId will be valid.
