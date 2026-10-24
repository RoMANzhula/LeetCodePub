package P2401_2500.P2409_Count_Days_Spent_Together;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.countDaysTogether(
                "08-15", "08-18", "08-16", "08-19"
        )); // 3

        System.out.println(solution.countDaysTogether(
                "10-01", "10-31", "11-01", "12-31"
        )); // 0
    }

    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        int aliceStart = toDayOfYear(arriveAlice, daysInMonth);
        int aliceEnd = toDayOfYear(leaveAlice, daysInMonth);
        int bobStart = toDayOfYear(arriveBob, daysInMonth);
        int bobEnd = toDayOfYear(leaveBob, daysInMonth);

        int start = Math.max(aliceStart, bobStart);
        int end = Math.min(aliceEnd, bobEnd);

        return Math.max(0, end - start + 1);
    }

    private int toDayOfYear(String date, int[] daysInMonth) {
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));

        for (int i = 0; i < month - 1; i++) {
            day += daysInMonth[i];
        }

        return day;
    }

}

//Complexity:
// time and space - O(1)


//Alice and Bob are traveling to Rome for separate business meetings.
//You are given 4 strings arriveAlice, leaveAlice, arriveBob, and leaveBob. Alice will be in the city from the dates
// arriveAlice to leaveAlice (inclusive), while Bob will be in the city from the dates arriveBob to
// leaveBob (inclusive). Each will be a 5-character string in the format "MM-DD", corresponding to the month and
// day of the date.
//Return the total number of days that Alice and Bob are in Rome together.
//You can assume that all dates occur in the same calendar year, which is not a leap year. Note that the number of days
// per month can be represented as: [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31].

//Example 1:
//Input: arriveAlice = "08-15", leaveAlice = "08-18", arriveBob = "08-16", leaveBob = "08-19"
//Output: 3
//Explanation: Alice will be in Rome from August 15 to August 18. Bob will be in Rome from August 16 to August 19. They
// are both in Rome together on August 16th, 17th, and 18th, so the answer is 3.

//Example 2:
//Input: arriveAlice = "10-01", leaveAlice = "10-31", arriveBob = "11-01", leaveBob = "12-31"
//Output: 0
//Explanation: There is no day when Alice and Bob are in Rome together, so we return 0.

//Constraints:
//All dates are provided in the format "MM-DD".
//Alice and Bob's arrival dates are earlier than or equal to their leaving dates.
//The given dates are valid dates of a non-leap year.
