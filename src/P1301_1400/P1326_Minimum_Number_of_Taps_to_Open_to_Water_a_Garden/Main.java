package P1301_1400.P1326_Minimum_Number_of_Taps_to_Open_to_Water_a_Garden;

public class Main {

    public static void main(String[] args) {
        Main result = new Main();
        int n1 = 5;
        int[] ranges1 = {3,4,1,1,0,0};
        System.out.println(result.minTaps(n1, ranges1));

        int n2 = 3;
        int[] ranges2 = {0,0,0,0};
        System.out.println(result.minTaps(n2, ranges2));
    }

    public int minTaps(int n, int[] ranges) {
        int[] maxCoverage = new int[n + 1]; //creat a new array for max coverage
        for (int i = 0; i <= n; i++) { //loop for all taps
            int left = Math.max(0, i - ranges[i]); //left max coverage
            int right = Math.min(n, i + ranges[i]); //right max coverage
            maxCoverage[left] = Math.max(maxCoverage[left], right); //reload new max coverage for each tap
        }

        //variables for calculate max coverage
        int currentMaxCoverage = 0;
        int nextMaxCoverage = 0;
        int count = 0; //nums for opened taps
        int i = 0; //for current position

        while (currentMaxCoverage < n) { //if length Garden is not coverage
            while (i <= currentMaxCoverage) { //all position to max current coverage
                nextMaxCoverage = Math.max(nextMaxCoverage, maxCoverage[i]); //between two values from
                i++;
            }

            if (currentMaxCoverage == nextMaxCoverage) { //if taps for coverage not found
                return -1;
            }

            currentMaxCoverage = nextMaxCoverage; //reload
            count++;//update
        }

        return count; //bingo
    }

}

//There is a one-dimensional garden on the x-axis. The garden starts at the point 0 and ends at the
// point n. (i.e The length of the garden is n).
//There are n + 1 taps located at points [0, 1, ..., n] in the garden.
//Given an integer n and an integer array ranges of length n + 1 where ranges[i] (0-indexed) means the i-th tap
// can water the area [i - ranges[i], i + ranges[i]] if it was open.
//Return the minimum number of taps that should be open to water the whole garden, If the garden cannot be
// watered return -1.

//Example 1:
//Input: n = 5, ranges = [3,4,1,1,0,0]
//Output: 1
//Explanation: The tap at point 0 can cover the interval [-3,3]
//The tap at point 1 can cover the interval [-3,5]
//The tap at point 2 can cover the interval [1,3]
//The tap at point 3 can cover the interval [2,4]
//The tap at point 4 can cover the interval [4,4]
//The tap at point 5 can cover the interval [5,5]
//Opening Only the second tap will water the whole garden [0,5]

//Example 2:
//Input: n = 3, ranges = [0,0,0,0]
//Output: -1
//Explanation: Even if you activate all the four taps you cannot water the whole garden.

//Constraints:
//1 <= n <= 104
//ranges.length == n + 1
//0 <= ranges[i] <= 100
