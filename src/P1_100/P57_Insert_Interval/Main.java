package P1_100.P57_Insert_Interval;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        // output: [1, 5] [6, 9]
        int[][] intervals1 = {{1, 3}, {6, 9}};
        int[] newInterval1 = {2, 5};
        int[][] result1 = solution.insert(intervals1, newInterval1);
        System.out.println("Example 1:");
        printIntervals(result1);

        // output: [1, 2] [3, 10] [12, 16]
        int[][] intervals2 = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval2 = {4, 8};
        int[][] result2 = solution.insert(intervals2, newInterval2);
        System.out.println("Example 2:");
        printIntervals(result2);
    }

    private static void printIntervals(int[][] intervals) {
        for (int[] interval : intervals) {
            System.out.print("[" + interval[0] + ", " + interval[1] + "] ");
        }
        System.out.println();
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;

        // add all intervals that end before newInterval starts
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // merge overlapping intervals
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);

        // add all remaining intervals
        while (i < intervals.length) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }

}

//Спочатку ми створюємо клас Solution, який містить метод insert, щоб вставити новий інтервал у список
// інтервалів і злити перекриваючіся інтервали за потреби. Метод insert приймає масив інтервалів та новий
// інтервал, який потрібно вставити.
//В методі insert ми створюємо список result, який буде містити відповідь.
//Ми проходимося по вихідному масиву інтервалів. Поки інтервал закінчується перед початком нового інтервалу, ми
// просто додаємо його до результату.
//Далі ми перевіряємо, чи перекриваються поточний інтервал і новий інтервал. Якщо так, ми зливаємо їх у новий інтервал.
//Потім ми додаємо об'єднаний інтервал до результату.
//Нарешті, ми додаємо всі решту інтервалів з вихідного масиву, які залишилися недоданими.
//Метод повертає результат у форматі масиву.

//You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent
// the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are
// also given an interval newInterval = [start, end] that represents the start and end of another interval.
//Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and
// intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
//Return intervals after the insertion.
//Note that you don't need to modify intervals in-place. You can make a new array and return it.
//
//Example 1:
//Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
//Output: [[1,5],[6,9]]

//Example 2:
//Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
//Output: [[1,2],[3,10],[12,16]]
//Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
//
//Constraints:
//0 <= intervals.length <= 104
//intervals[i].length == 2
//0 <= starti <= endi <= 105
//intervals is sorted by starti in ascending order.
//newInterval.length == 2
//0 <= start <= end <= 105

