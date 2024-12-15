package P1701_1800.P1792_Maximum_Average_Pass_Ratio;

import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[][] classes1 = {{1, 2}, {3, 5}, {2, 2}};
        int extraStudents1 = 2;
        System.out.println(solution.maxAverageRatio(classes1, extraStudents1)); // Output: 0.78333

        int[][] classes2 = {{2, 4}, {3, 9}, {4, 5}, {2, 10}};
        int extraStudents2 = 4;
        System.out.println(solution.maxAverageRatio(classes2, extraStudents2)); // Output: 0.53485
    }

    public double maxAverageRatio(int[][] classes, int extraStudents) {
        // Max-heap to store classes based on the potential gain in pass ratio
        PriorityQueue<double[]> maxHeap = new PriorityQueue<>(
                (a, b) -> Double.compare(b[0], a[0]) // Sort by potential gain in descending order
        );

        // Calculate initial potential gain for each class and add to heap
        for (int[] cls : classes) {
            int pass = cls[0];
            int total = cls[1];
            double gain = potentialGain(pass, total);
            maxHeap.offer(new double[]{gain, pass, total});
        }

        // Assign extra students to maximize average pass ratio
        while (extraStudents > 0) {
            double[] top = maxHeap.poll(); // Get the class with the highest gain
            int pass = (int) top[1];
            int total = (int) top[2];
            pass++;
            total++;
            extraStudents--;
            double gain = potentialGain(pass, total);
            maxHeap.offer(new double[]{gain, pass, total}); // Update the heap
        }

        // Calculate the final average pass ratio
        double totalRatio = 0.0;
        while (!maxHeap.isEmpty()) {
            double[] top = maxHeap.poll();
            int pass = (int) top[1];
            int total = (int) top[2];
            totalRatio += (double) pass / total;
        }

        return totalRatio / classes.length;
    }

    private double potentialGain(int pass, int total) {
        return (double) (pass + 1) / (total + 1) - (double) pass / total;
    }

}

//Explanation of the Code:
//Heap Initialization:
//-Each class is pushed into a max-heap with its potential gain as the key.
//Assign Extra Students:
//-In each iteration, we poll the class with the highest potential gain, update its pass and total values, and
// recalculate its potential gain.
//-The updated class is added back to the heap.
//Final Average Calculation:
//-Sum the pass ratios of all classes and divide by the total number of classes to compute the final average pass ratio.
//Complexity:
//Time Complexity: O((N+extraStudents)⋅log(N)), where N is the number of classes.
//Space Complexity: O(N) for the heap.


//There is a school that has classes of students and each class will be having a final exam. You are given a 2D
// integer array classes, where classes[i] = [passi, totali]. You know beforehand that in the ith class,
// there are totali total students, but only passi number of students will pass the exam.
//You are also given an integer extraStudents. There are another extraStudents brilliant students that are
// guaranteed to pass the exam of any class they are assigned to. You want to assign each of the extraStudents
// students to a class in a way that maximizes the average pass ratio across all the classes.
//The pass ratio of a class is equal to the number of students of the class that will pass the exam divided by
// the total number of students of the class. The average pass ratio is the sum of pass ratios of all the
// classes divided by the number of the classes.
//Return the maximum possible average pass ratio after assigning the extraStudents students. Answers within
// 10-5 of the actual answer will be accepted.
//
//Example 1:
//Input: classes = [[1,2],[3,5],[2,2]], extraStudents = 2
//Output: 0.78333
//Explanation: You can assign the two extra students to the first class. The average pass ratio will be
// equal to (3/4 + 3/5 + 2/2) / 3 = 0.78333.

//Example 2:
//Input: classes = [[2,4],[3,9],[4,5],[2,10]], extraStudents = 4
//Output: 0.53485
//
//Constraints:
//1 <= classes.length <= 105
//classes[i].length == 2
//1 <= passi <= totali <= 105
//1 <= extraStudents <= 105
