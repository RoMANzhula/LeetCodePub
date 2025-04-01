package P2101_2200.P2140_Solving_Questions_With_Brainpower;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] questions1 = {{3,2}, {4,3}, {4,4}, {2,5}};
        System.out.println("Output: " + solution.mostPoints(questions1)); // Expected: 5

        int[][] questions2 = {{1,1}, {2,2}, {3,3}, {4,4}, {5,5}};
        System.out.println("Output: " + solution.mostPoints(questions2)); // Expected: 7

        int[][] questions3 = {{10,1}, {2,2}, {3,3}, {4,1}, {5,2}, {6,0}, {7,1}};
        System.out.println("Output: " + solution.mostPoints(questions3)); // Expected: 27
    }

    public long mostPoints(int[][] questions) {
        int n = questions.length;
        long[] dp = new long[n + 1]; // using long to avoid integer overflow

        for (int i = n - 1; i >= 0; i--) {
            int points = questions[i][0];
            int skip = questions[i][1];
            int nextIndex = i + skip + 1;

            // step 1: solve this question
            long solve = points + (nextIndex < n ? dp[nextIndex] : 0);

            // stepp 2: skip this question
            long skipQuestion = dp[i + 1];

            // store the max value
            dp[i] = Math.max(solve, skipQuestion);
        }

        return dp[0]; // max points starting from question 0
    }

}

//Complexity Analysis
//Time Complexity: O(n)
//We iterate once from the end to the start, updating dp[i] in constant time.
//Space Complexity: O(n)
//We use a dp array of size n + 1.


//You are given a 0-indexed 2D integer array questions where questions[i] = [pointsi, brainpoweri].
//The array describes the questions of an exam, where you have to process the questions in order (i.e., starting
// from question 0) and make a decision whether to solve or skip each question. Solving question i will earn you
// pointsi points but you will be unable to solve each of the next brainpoweri questions. If you skip question i,
// you get to make the decision on the next question.
//For example, given questions = [[3, 2], [4, 3], [4, 4], [2, 5]]:
//If question 0 is solved, you will earn 3 points but you will be unable to solve questions 1 and 2.
//If instead, question 0 is skipped and question 1 is solved, you will earn 4 points but you will be unable to
// solve questions 2 and 3.
//Return the maximum points you can earn for the exam.

//Example 1:
//Input: questions = [[3,2],[4,3],[4,4],[2,5]]
//Output: 5
//Explanation: The maximum points can be earned by solving questions 0 and 3.
//- Solve question 0: Earn 3 points, will be unable to solve the next 2 questions
//- Unable to solve questions 1 and 2
//- Solve question 3: Earn 2 points
//Total points earned: 3 + 2 = 5. There is no other way to earn 5 or more points.

//Example 2:
//Input: questions = [[1,1],[2,2],[3,3],[4,4],[5,5]]
//Output: 7
//Explanation: The maximum points can be earned by solving questions 1 and 4.
//- Skip question 0
//- Solve question 1: Earn 2 points, will be unable to solve the next 2 questions
//- Unable to solve questions 2 and 3
//- Solve question 4: Earn 5 points
//Total points earned: 2 + 5 = 7. There is no other way to earn 7 or more points.

//Constraints:
//1 <= questions.length <= 105
//questions[i].length == 2
//1 <= pointsi, brainpoweri <= 105
