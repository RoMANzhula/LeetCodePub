package P1301_1400.P1344_Angle_Between_Hands_of_a_Clock;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.angleClock(12, 30)); // 165.0
        System.out.println(solution.angleClock(3, 30));  // 75.0
        System.out.println(solution.angleClock(3, 15));  // 7.5
        System.out.println(solution.angleClock(6, 0));   // 180.0
        System.out.println(solution.angleClock(9, 45));  // 22.5
    }

    public double angleClock(int hour, int minutes) {
        // angle of the hour hand
        double hourAngle = (hour % 12) * 30.0 + minutes * 0.5;

        // angle of the minute hand
        double minuteAngle = minutes * 6.0;

        // absolute difference
        double diff = Math.abs(hourAngle - minuteAngle);

        // the smaller angle
        return Math.min(diff, 360.0 - diff);
    }

}

//Complexity:
// time and space - O(1)


//Given two numbers, hour and minutes, return the smaller angle (in degrees) formed between the hour and the
// minute hand.
//Answers within 10-5 of the actual value will be accepted as correct.

//Example 1:
//Input: hour = 12, minutes = 30
//Output: 165

//Example 2:
//Input: hour = 3, minutes = 30
//Output: 75

//Example 3:
//Input: hour = 3, minutes = 15
//Output: 7.5

//Constraints:
//1 <= hour <= 12
//0 <= minutes <= 59
