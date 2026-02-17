package P401_500.P401_Binary_Watch;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int turnedOn = 1;
        List<String> times = solution.readBinaryWatch(turnedOn);

        System.out.println(times); // [0:01, 0:02, 0:04, 0:08, 0:16, 0:32, 1:00, 2:00, 4:00, 8:00]
    }

    public List<String> readBinaryWatch(int turnedOn) {
        List<String> result = new ArrayList<>();

        for (int hour = 0; hour < 12; hour++) {
            for (int minute = 0; minute < 60; minute++) {

                int ledsOn = Integer.bitCount(hour) + Integer.bitCount(minute);

                if (ledsOn == turnedOn) {
                    // format minute with leading zero
                    String time = hour + ":" + (minute < 10 ? "0" + minute : minute);
                    result.add(time);
                }
            }
        }

        return result;
    }

}

//Complexity:
// time - O(1)
// space - O(valid times complexity)


//A binary watch has 4 LEDs on the top to represent the hours (0-11), and 6 LEDs on the bottom to represent the
// minutes (0-59). Each LED represents a zero or one, with the least significant bit on the right.
//For example, the below binary watch reads "4:51".

//Given an integer turnedOn which represents the number of LEDs that are currently on (ignoring the PM), return all
// possible times the watch could represent. You may return the answer in any order.
//The hour must not contain a leading zero.
//For example, "01:00" is not valid. It should be "1:00".
//The minute must consist of two digits and may contain a leading zero.
//For example, "10:2" is not valid. It should be "10:02".

//Example 1:
//Input: turnedOn = 1
//Output: ["0:01","0:02","0:04","0:08","0:16","0:32","1:00","2:00","4:00","8:00"]

//Example 2:
//Input: turnedOn = 9
//Output: []

//Constraints:
//0 <= turnedOn <= 10
